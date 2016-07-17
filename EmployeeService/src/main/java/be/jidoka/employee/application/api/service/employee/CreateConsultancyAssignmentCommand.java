package be.jidoka.employee.application.api.service.employee;

import java.time.LocalDate;

public class CreateConsultancyAssignmentCommand {

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private CreateProjectCommand project;

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CreateProjectCommand getProject() {
        return project;
    }

}
