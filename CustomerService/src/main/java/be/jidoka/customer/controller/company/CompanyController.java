package be.jidoka.customer.controller.company;

import be.jidoka.customer.application.api.service.company.CompanyDTO;
import be.jidoka.customer.application.api.service.company.CompanyService;
import be.jidoka.customer.architecture.Mapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(method = GET)
    public List<BusinessCardDTO> findAll() {
        return businessCardMapper().map(companyService.findAll());
    }

    @RequestMapping(value = "/{id}", method = GET)
    public CompanyDTO findOne(@PathVariable("id") Long id) {
        return companyService.findOne(id);
    }

    private Mapper<CompanyDTO, BusinessCardDTO> businessCardMapper() {
        return (company) -> BusinessCardDTO.of(company.getId(), company.getName(), company.getSector());
    }

    private static class BusinessCardDTO {
        private final Long id;
        private final String name;
        private final String sector;

        private BusinessCardDTO(Long id, String name, String sector) {
            this.id = id;
            this.name = name;
            this.sector = sector;
        }

        public static BusinessCardDTO of(Long id, String name, String sector) {
            return new BusinessCardDTO(id, name, sector);
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSector() {
            return sector;
        }
    }

}
