package be.jidoka.mbti.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "mbti", type = "mbti", shards = 1, replicas = 0, refreshInterval = "-1")
public class MBTIFile {

    @Id
    private String type;
    private List<String> mainCharacteristics = new ArrayList<>();
    private List<String> minorCharacteristics = new ArrayList<>();
    private String description;
    private List<String> characteristics = new ArrayList<>();
    private List<String> withOthers = new ArrayList<>();
    private List<String> atWork = new ArrayList<>();
    private List<String> potentialBlindSpots = new ArrayList<>();
    private String rawText;

    public MBTIFile() { }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMainCharacteristics() {
        return mainCharacteristics;
    }

    public void setMainCharacteristics(List<String> mainCharacteristics) {
        this.mainCharacteristics = mainCharacteristics;
    }

    public List<String> getMinorCharacteristics() {
        return minorCharacteristics;
    }

    public void setMinorCharacteristics(List<String> minorCharacteristics) {
        this.minorCharacteristics = minorCharacteristics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }

    public List<String> getWithOthers() {
        return withOthers;
    }

    public void setWithOthers(List<String> withOthers) {
        this.withOthers = withOthers;
    }

    public List<String> getAtWork() {
        return atWork;
    }

    public void setAtWork(List<String> atWork) {
        this.atWork = atWork;
    }

    public List<String> getPotentialBlindSpots() {
        return potentialBlindSpots;
    }

    public void setPotentialBlindSpots(List<String> potentialBlindSpots) {
        this.potentialBlindSpots = potentialBlindSpots;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

}
