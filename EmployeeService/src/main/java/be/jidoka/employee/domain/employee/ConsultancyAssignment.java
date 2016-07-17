package be.jidoka.employee.domain.employee;

import java.time.LocalDate;

public class ConsultancyAssignment {

    private Long project;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private ConsultancyAssignment(Long project, String description, LocalDate startDate, LocalDate endDate) {
        this.project = project;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ConsultancyAssignment of(Long project, String description, LocalDate startDate, LocalDate endDate) {
        return new ConsultancyAssignment(project, description, startDate, endDate);
    }

    public static ConsultancyAssignment of(Long project, String description, LocalDate startDate) {
        return new ConsultancyAssignment(project, description, startDate, null);
    }

    public Long getProject() {
        return project;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
