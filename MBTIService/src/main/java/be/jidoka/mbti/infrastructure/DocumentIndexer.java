package be.jidoka.mbti.infrastructure;

import be.jidoka.mbti.domain.MBTIFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class DocumentIndexer {

    private static final String FILES_DIRECTORY = "documents";
    private static final String READ_ACCESS_MODE = "r";

    private static List<String> HEADERS = Arrays.asList("Characteristics of %ss", "%ss with Others", "%ss at Work", "Potential Blind Spots for %ss");

    public List<MBTIFile> index() throws IOException, URISyntaxException {
        final List<MBTIFile> mbtiFiles = new ArrayList<>();
        Files.list(Paths.get(getClass().getClassLoader().getResource(FILES_DIRECTORY).toURI())).forEach(file -> {
            try {
                final PDFParser parser = new PDFParser(new RandomAccessFile(file.toFile(), READ_ACCESS_MODE));
                parser.parse();
                final COSDocument document = parser.getDocument();
                final PDDocument pdf = new PDDocument(document);
                final PDFTextStripper textStripper = new PDFTextStripper();

                final MBTIFile mbtiFile = new MBTIFile();
                mbtiFile.setType(FilenameUtils.removeExtension(file.toFile().getName()));
                mbtiFile.setRawText(textStripper.getText(pdf));
                IntStream.rangeClosed(1, pdf.getNumberOfPages()).forEach(pageNumber -> {
                    textStripper.setStartPage(pageNumber);
                    textStripper.setEndPage(pageNumber);

                    try {
                        final String[] lines = textStripper.getText(pdf).split(textStripper.getLineSeparator());
                        parseTypeDescription(mbtiFile, lines);
                        parseCharacteristics(mbtiFile, lines);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                mbtiFiles.add(mbtiFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return mbtiFiles;
    }

    private void parseTypeDescription(MBTIFile file, String[] lines) {
        final OptionalInt mainCharacteristicsIndex = mainCharacteristicsIndex(lines);

        if (mainCharacteristicsIndex.isPresent()) {
            file.setMainCharacteristics(mainCharacteristics(lines[mainCharacteristicsIndex.getAsInt()]));

            final int minorCharacteristicsIndex = minorCharacteristicsIndex(lines, mainCharacteristicsIndex.getAsInt());
            file.setMinorCharacteristics(minorCharacteristics(lines, minorCharacteristicsIndex));
            file.setDescription(description(lines, mainCharacteristicsIndex.getAsInt() + 1, minorCharacteristicsIndex));
        }
    }

    private OptionalInt mainCharacteristicsIndex(String lines[]) {
        return IntStream.range(0, lines.length)
                .filter(index -> Stream.of(lines[index].split("\\|")).filter(StringUtils::isNotBlank).collect(Collectors.toList()).size() == 4)
                .findFirst();
    }

    private List<String> mainCharacteristics(String line) {
        return Stream.of(line.split("\\|")).map(String::trim).collect(Collectors.toList());
    }

    private int minorCharacteristicsIndex(String[] lines, int startIndex) {
        return IntStream.range(startIndex, lines.length)
                .filter(index -> lines[index].split(" ").length == 1)
                .filter(index -> lines[index].equals(lines[index].toUpperCase()))
                .findFirst()
                .getAsInt();
    }

    private List<String> minorCharacteristics(String[] lines, int startIndex) {
        return IntStream.range(startIndex, lines.length)
                .mapToObj(index -> lines[index])
                .filter(line -> line.equals(line.toUpperCase()))
                .map(String::toLowerCase)
                .map(WordUtils::capitalize)
                .collect(Collectors.toList());
    }

    private String description(String[] lines, int startIndex, int endIndex) {
        return IntStream.range(startIndex, endIndex)
                .mapToObj(index -> lines[index])
                .filter(line -> line.split(" ").length > 1)
                .map(String::trim)
                .collect(Collectors.joining(" "));
    }

    private void parseCharacteristics(MBTIFile file, String[] lines) {
        final String type = file.getType();
        final Map<String, List<String>> points = new HashMap<>();
        HEADERS.stream().forEach(header -> {
            final String mbtiHeader = String.format(header, type);

            points.put(mbtiHeader, new ArrayList<>());

            final List<String> headerPoints = new ArrayList<>();
            boolean headerFound = false;
            boolean summation = false;
            for (String line : lines) {
                if (line.equals(mbtiHeader)) {
                    headerFound = true;
                } else if (headerFound && (line.startsWith("•") || (!headerPoints.isEmpty() && !headerPoints.get(headerPoints.size() - 1).endsWith(".")))) {
                    summation = true;
                } else {
                    headerFound = false;
                    summation = false;
                }

                if (headerFound && summation) {
                    if (line.startsWith("•")) {
                        headerPoints.add(line.replace("•\t ", ""));
                    } else {
                        String unCompleteSummation = headerPoints.get(headerPoints.size() - 1);
                        unCompleteSummation = unCompleteSummation.concat(line);
                        headerPoints.set(headerPoints.size() - 1, unCompleteSummation);
                    }
                }
            }

            points.get(mbtiHeader).addAll(headerPoints);
        });

        for (String header : points.keySet()) {
            final List<String> summation = points.get(header);
            if (!summation.isEmpty()) {
                if (header.equals(String.format(HEADERS.get(0), type))) {
                    file.setCharacteristics(points.get(header));
                } else if (header.equals(String.format(HEADERS.get(1), type))) {
                    file.setWithOthers(points.get(header));
                } else if (header.equals(String.format(HEADERS.get(2), type))) {
                    file.setAtWork(points.get(header));
                } else {
                    file.setPotentialBlindSpots(points.get(header));
                }
            }
        }
    }

}
