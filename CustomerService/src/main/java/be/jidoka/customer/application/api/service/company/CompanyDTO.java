package be.jidoka.customer.application.api.service.company;

public class CompanyDTO {

    private Long id;
    private String name;
    private String description;
    private String employeeCount;
    private String website;
    private String sector;

    private CompanyDTO() { }

    private CompanyDTO(CompanyDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.employeeCount = builder.employeeCount;
        this.website = builder.website;
        this.sector = builder.sector;
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

    public String getEmployeeCount() {
        return employeeCount;
    }

    public String getWebsite() {
        return website;
    }

    public String getSector() {
        return sector;
    }

    public static class CompanyDTOBuilder {
        private Long id;
        private String name;
        private String description;
        private String employeeCount;
        private String website;
        private String sector;

        private CompanyDTOBuilder() {
        }

        public static CompanyDTOBuilder companyDTOWith() {
            return new CompanyDTOBuilder();
        }

        public CompanyDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CompanyDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CompanyDTOBuilder employeeCount(String employeeCount) {
            this.employeeCount = employeeCount;
            return this;
        }

        public CompanyDTOBuilder website(String website) {
            this.website = website;
            return this;
        }

        public CompanyDTOBuilder sector(String sector) {
            this.sector = sector;
            return this;
        }

        public CompanyDTO build() {
            return new CompanyDTO(this);
        }
    }

}
