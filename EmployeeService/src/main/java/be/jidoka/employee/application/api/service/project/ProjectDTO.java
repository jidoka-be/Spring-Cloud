package be.jidoka.employee.application.api.service.project;

public class ProjectDTO {

    public static final ProjectDTO EMPTY = new ProjectDTO();

    private Long id;
    private String name;
    private String description;
    private CompanyDTO company;

    private ProjectDTO() { }

    private ProjectDTO(String description, CompanyDTO company) {
        this.description = description;
        this.company = company;
    }

    public static ProjectDTO of(String description, CompanyDTO company) {
        return new ProjectDTO(description, company);
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

}
