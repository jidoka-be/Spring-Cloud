package be.jidoka.employee.application.api.service.employee;

import be.jidoka.employee.application.api.service.employee.CoWorkerDTO.CoWorkerDTOBuilder;
import be.jidoka.employee.application.api.service.employee.EmployeeDTO.EmployeeDTOBuilder;
import be.jidoka.employee.application.api.service.mbti.MBTIClient;
import be.jidoka.employee.application.api.service.project.ProjectClient;
import be.jidoka.employee.architecture.Mapper;
import be.jidoka.employee.domain.employee.Address;
import be.jidoka.employee.domain.employee.CoWorker;
import be.jidoka.employee.domain.employee.ConsultancyAssignment;
import be.jidoka.employee.domain.employee.ContactInformation;
import be.jidoka.employee.domain.employee.Employee;
import be.jidoka.employee.domain.employee.EmployeeType;
import be.jidoka.employee.domain.employee.FreeLancer;
import be.jidoka.employee.domain.employee.SocialMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static be.jidoka.employee.application.api.service.employee.AddressDTO.AddressDTOBuilder.addressDTOWith;
import static be.jidoka.employee.application.api.service.employee.CoWorkerDTO.CoWorkerDTOBuilder.coWorkerWith;
import static be.jidoka.employee.application.api.service.employee.ConsultancyAssignmentDTO.ConsultancyAssignmentDTOBuilder.consultancyAssignmentDTOWith;
import static be.jidoka.employee.application.api.service.employee.ContactInformationDTO.ContactInformationDTOBuilder.contactInformationDTOWith;

@Component
public class EmployeeDTOMapper implements Mapper<Employee, EmployeeDTO> {

    private final MBTIClient mbtiClient;

    private final Mapper<ContactInformation, ContactInformationDTO> contactInformationDTOMapper;
    private final Mapper<Address, AddressDTO> addressDTOMapper;
    private final Mapper<ConsultancyAssignment, ConsultancyAssignmentDTO> consultancyAssignmentDTOMapper;

    @Autowired
    public EmployeeDTOMapper(ProjectClient projectClient, MBTIClient mbtiClient) {
        this.mbtiClient = mbtiClient;

        this.contactInformationDTOMapper = (contactInformation) -> contactInformationDTOWith()
                .email(contactInformation.getEmail())
                .mobile(contactInformation.getMobile())
                .linkedIn(contactInformation.getSocialMediaAccount(SocialMediaType.LINKEDIN))
                .skype(contactInformation.getSocialMediaAccount(SocialMediaType.SKYPE))
                .twitter(contactInformation.getSocialMediaAccount(SocialMediaType.TWITTER))
                .build();
        this.addressDTOMapper = (address) -> addressDTOWith()
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .boxNumber(address.getBoxNumber())
                .zipCode(address.getZipCode())
                .cityName(address.getCityName())
                .build();
        this.consultancyAssignmentDTOMapper = (consultancyAssignment) -> consultancyAssignmentDTOWith()
                .project(projectClient.get(consultancyAssignment.getProject()))
                .description(consultancyAssignment.getDescription())
                .startDate(consultancyAssignment.getStartDate())
                .endDate(consultancyAssignment.getEndDate())
                .build();
    }

    @Override
    public EmployeeDTO map(Employee employee) {
        return builder(employee)
                .id(employee.getID())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .contactInformation(contactInformationDTOMapper.map(employee.getContactInformation()))
                .dateOfBirth(employee.getDateOfBirth())
                .address(addressDTOMapper.map(employee.getAddress()))
                .type(employee.getType().name())
                .mbti(mbtiClient.getMBTI(employee.getMbti()))
                .assignments(consultancyAssignmentDTOMapper.map(employee.getAssignments()))
                .role(employee.getRole().getName())
                .active(employee.isActive())
                .build();
    }

    private EmployeeDTOBuilder builder(Employee employee) {
        if (employee.getType() == EmployeeType.CO_WORKER) {
            return coWorker(((CoWorker) employee));
        }
        return freeLancer((FreeLancer) employee);
    }

    private CoWorkerDTOBuilder coWorker(CoWorker employee) {
        return coWorkerWith()
                .maritalStatus(employee.getMaritalStatus().name())
                .numberOfChildren(employee.getNumberOfChildren());
    }

    private FreeLancerDTO.FreeLancerDTOBuilder freeLancer(FreeLancer employee) {
        return FreeLancerDTO.FreeLancerDTOBuilder.freeLancerDTOWith()
                .companyName(employee.getCompanyName())
                .vatNumber(employee.getVatNumber());
    }

}
