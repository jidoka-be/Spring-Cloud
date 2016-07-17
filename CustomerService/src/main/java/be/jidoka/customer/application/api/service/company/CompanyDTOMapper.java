package be.jidoka.customer.application.api.service.company;

import be.jidoka.customer.architecture.Mapper;
import be.jidoka.customer.domain.company.Company;
import org.springframework.stereotype.Component;

import static be.jidoka.customer.application.api.service.company.CompanyDTO.CompanyDTOBuilder.companyDTOWith;

@Component
public class CompanyDTOMapper implements Mapper<Company, CompanyDTO> {

    @Override
    public CompanyDTO map(Company company) {
        return companyDTOWith()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .website(company.getWebsite())
                .employeeCount(company.getNumberOfEmployees().getDescription())
                .sector(company.getSector().getName())
                .build();
    }

}
