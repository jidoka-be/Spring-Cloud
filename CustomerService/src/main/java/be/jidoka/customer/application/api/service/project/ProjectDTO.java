package be.jidoka.customer.application.api.service.project;

import be.jidoka.customer.application.api.service.company.CompanyDTO;

public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private CompanyDTO company;

    private ProjectDTO() { }

    private ProjectDTO(ProjectDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.company = builder.company;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public static class ProjectDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private CompanyDTO company;

        private ProjectDTOBuilder() {
        }

        public static ProjectDTOBuilder projectDTOWith() {
            return new ProjectDTOBuilder();
        }

        public ProjectDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProjectDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProjectDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProjectDTOBuilder company(CompanyDTO company) {
            this.company = company;
            return this;
        }

        public ProjectDTO build() {
            return new ProjectDTO(this);
        }
    }

}
