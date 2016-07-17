package be.jidoka.customer.domain.project;

import be.jidoka.customer.domain.company.Company;
import org.springframework.stereotype.Component;

@Component
public final class ProjectFactory {

    public Project create(String name, String description, Company company) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setCompany(company);
        return project;
    }

}
