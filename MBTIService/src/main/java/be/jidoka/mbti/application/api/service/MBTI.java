package be.jidoka.mbti.application.api.service;

import java.util.ArrayList;
import java.util.List;

public class MBTI {

    private final String type;
    private final List<String> mainCharacteristics;
    private final List<String> minorCharacteristics;
    private final String description;
    private final List<String> characteristics;
    private final List<String> withOthers;
    private final List<String> atWork;
    private final List<String> potentialBlindSpots;

    private MBTI(MBTIBuilder builder) {
        this.type = builder.type;
        this.mainCharacteristics = builder.mainCharacteristics;
        this.minorCharacteristics = builder.minorCharacteristics;
        this.description = builder.description;
        this.characteristics = builder.characteristics;
        this.withOthers = builder.withOthers;
        this.atWork = builder.atWork;
        this.potentialBlindSpots = builder.potentialBlindSpots;
    }

    public String getType() {
        return type;
    }

    public List<String> getMainCharacteristics() {
        return mainCharacteristics;
    }

    public List<String> getMinorCharacteristics() {
        return minorCharacteristics;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public List<String> getWithOthers() {
        return withOthers;
    }

    public List<String> getAtWork() {
        return atWork;
    }

    public List<String> getPotentialBlindSpots() {
        return potentialBlindSpots;
    }

    public static class MBTIBuilder {
        private String type;
        private List<String> mainCharacteristics = new ArrayList<>();
        private List<String> minorCharacteristics = new ArrayList<>();
        private String description;
        private List<String> characteristics = new ArrayList<>();
        private List<String> withOthers = new ArrayList<>();
        private List<String> atWork = new ArrayList<>();
        private List<String> potentialBlindSpots = new ArrayList<>();

        private MBTIBuilder() { }

        public static MBTIBuilder mbtiWith() {
            return new MBTIBuilder();
        }

        public MBTIBuilder type(String type) {
            this.type = type;
            return this;
        }

        public MBTIBuilder mainCharacteristics(List<String> mainCharacteristics) {
            this.mainCharacteristics.addAll(mainCharacteristics);
            return this;
        }

        public MBTIBuilder minorCharacteristics(List<String> minorCharacteristics) {
            this.minorCharacteristics.addAll(minorCharacteristics);
            return this;
        }

        public MBTIBuilder description(String description) {
            this.description = description;
            return this;
        }

        public MBTIBuilder characteristics(List<String> characteristics) {
            this.characteristics.addAll(characteristics);
            return this;
        }

        public MBTIBuilder withOthers(List<String> withOthers) {
            this.withOthers.addAll(withOthers);
            return this;
        }

        public MBTIBuilder atWork(List<String> atWork) {
            this.atWork.addAll(atWork);
            return this;
        }

        public MBTIBuilder potentialBlindSpots(List<String> potentialBlindSpots) {
            this.potentialBlindSpots.addAll(potentialBlindSpots);
            return this;
        }

        public MBTI build() {
            return new MBTI(this);
        }
    }

}
