package be.jidoka.customer.application.impl.service.company;

import be.jidoka.customer.application.api.service.company.CompanyDTO;
import be.jidoka.customer.application.api.service.company.CompanyService;
import be.jidoka.customer.architecture.Mapper;
import be.jidoka.customer.domain.company.Company;
import be.jidoka.customer.domain.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final Mapper<Company, CompanyDTO> companyDTOMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, Mapper<Company, CompanyDTO> companyDTOMapper) {
        this.companyRepository = companyRepository;
        this.companyDTOMapper = companyDTOMapper;
    }

    @Override
    public CompanyDTO findOne(Long id) {
        return companyDTOMapper.map(companyRepository.findOne(id));
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyDTOMapper.map(companyRepository.findAll());
    }

}
