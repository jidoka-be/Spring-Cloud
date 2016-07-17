package be.jidoka.customer.application.api.service.company;

import java.util.List;

public interface CompanyService {

    CompanyDTO findOne(Long id);

    List<CompanyDTO> findAll();

}
