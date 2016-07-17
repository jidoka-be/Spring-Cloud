package be.jidoka.employee.application.api.service.employee;

import be.jidoka.employee.application.api.service.project.ProjectDTO;

import java.time.LocalDate;

public class ConsultancyAssignmentDTO {

    private ProjectDTO project;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private ConsultancyAssignmentDTO() { }

    private ConsultancyAssignmentDTO(ConsultancyAssignmentDTOBuilder builder) {
        this.project = builder.project;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public ProjectDTO getProject() {
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

    public static class ConsultancyAssignmentDTOBuilder {
        private ProjectDTO project;
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;

        private ConsultancyAssignmentDTOBuilder() { }

        public static ConsultancyAssignmentDTOBuilder consultancyAssignmentDTOWith() {
            return new ConsultancyAssignmentDTOBuilder();
        }

        public ConsultancyAssignmentDTOBuilder project(ProjectDTO project) {
            this.project = project;
            return this;
        }

        public ConsultancyAssignmentDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ConsultancyAssignmentDTOBuilder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public ConsultancyAssignmentDTOBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public ConsultancyAssignmentDTO build() {
            return new ConsultancyAssignmentDTO(this);
        }
    }

}
