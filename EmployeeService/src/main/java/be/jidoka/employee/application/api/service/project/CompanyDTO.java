package be.jidoka.employee.application.api.service.project;

public class CompanyDTO {

    private String name;
    private String description;
    private String employeeCount;
    private String website;
    private String sector;

    private CompanyDTO() { }

    private CompanyDTO(String name, String description, String employeeCount, String website, String sector) {
        this.name = name;
        this.description = description;
        this.employeeCount = employeeCount;
        this.website = website;
        this.sector = sector;
    }

    public static CompanyDTO of(String name, String description, String employeeCount, String website, String sector) {
        return new CompanyDTO(name, description, employeeCount, website, sector);
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

}
