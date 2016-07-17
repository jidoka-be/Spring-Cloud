package be.jidoka.employee.configuration;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "project")
public class ProjectConfiguration {

    @NotBlank
    private String description;
    private CompanyConfiguration company;

    public ProjectConfiguration(CompanyConfiguration company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return company.getName();
    }

    public String getCompanyDescription() {
        return company.getDescription();
    }

    public String getCompanyEmployeeCount() {
        return company.getEmployeeCount();
    }

    public String getCompanyWebsite() {
        return company.getWebsite();
    }

    public String getCompanySector() {
        return company.getSector();
    }

}
