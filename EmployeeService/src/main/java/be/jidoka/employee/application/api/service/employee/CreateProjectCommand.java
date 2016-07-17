package be.jidoka.employee.application.api.service.employee;

public class CreateProjectCommand {

    private String name;
    private String description;
    private Long company;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCompany() {
        return company;
    }

}
