package be.jidoka.employee.application.api.service.mbti;

import java.util.List;

public class MbtiDTO {

    private String type;
    private List<String> mainCharacteristics;
    private List<String> minorCharacteristics;
    private String description;
    private List<String> characteristics;
    private List<String> withOthers;
    private List<String> atWork;
    private List<String> potentialBlindSpots;

    private MbtiDTO() { }

    private MbtiDTO(String type) {
        this.type = type;
    }

    public static MbtiDTO of(String type) {
        return new MbtiDTO(type);
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

}
