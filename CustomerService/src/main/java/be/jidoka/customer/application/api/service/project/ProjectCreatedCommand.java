package be.jidoka.customer.application.api.service.project;

public class ProjectCreatedCommand {

    private String customer;
    private Long project;

    private ProjectCreatedCommand(String customer, Long project) {
        this.customer = customer;
        this.project = project;
    }

    public static ProjectCreatedCommand of(String customer, Long project) {
        return new ProjectCreatedCommand(customer, project);
    }

    public String getCustomer() {
        return customer;
    }

    public Long getProject() {
        return project;
    }

}
