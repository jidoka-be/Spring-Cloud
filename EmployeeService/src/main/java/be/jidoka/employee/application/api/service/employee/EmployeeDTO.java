package be.jidoka.employee.application.api.service.employee;

import be.jidoka.employee.application.api.service.mbti.MbtiDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class EmployeeDTO {

    private String id;
    private String firstName;
    private String lastName;
    private ContactInformationDTO contactInformation;
    private LocalDate dateOfBirth;
    private AddressDTO address;
    private String type;
    private MbtiDTO mbti;
    private List<ConsultancyAssignmentDTO> assignments;
    private String role;
    private boolean active;

    protected EmployeeDTO() { }

    protected EmployeeDTO(EmployeeDTOBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contactInformation = builder.contactInformation;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;
        this.type = builder.type;
        this.mbti = builder.mbti;
        this.assignments = builder.assignments;
        this.role = builder.role;
        this.active = builder.active;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactInformationDTO getContactInformation() {
        return contactInformation;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public MbtiDTO getMbti() {
        return mbti;
    }

    public List<ConsultancyAssignmentDTO> getAssignments() {
        return assignments;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public static abstract class EmployeeDTOBuilder<DTO extends EmployeeDTO, BUILDER extends EmployeeDTOBuilder> {
        private String id;
        private String firstName;
        private String lastName;
        private ContactInformationDTO contactInformation;
        private LocalDate dateOfBirth;
        private AddressDTO address;
        private String type;
        private MbtiDTO mbti;
        private List<ConsultancyAssignmentDTO> assignments = new ArrayList<>();
        private String role;
        private boolean active;

        public BUILDER id(String id) {
            this.id = id;
            return (BUILDER) this;
        }

        public BUILDER firstName(String firstName) {
            this.firstName = firstName;
            return (BUILDER) this;
        }

        public BUILDER lastName(String lastName) {
            this.lastName = lastName;
            return (BUILDER) this;
        }

        public BUILDER contactInformation(ContactInformationDTO contactInformation) {
            this.contactInformation = contactInformation;
            return (BUILDER) this;
        }

        public BUILDER dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return (BUILDER) this;
        }

        public BUILDER address(AddressDTO address) {
            this.address = address;
            return (BUILDER) this;
        }

        public BUILDER type(String type) {
            this.type = type;
            return (BUILDER) this;
        }

        public BUILDER mbti(MbtiDTO mbti) {
            this.mbti = mbti;
            return (BUILDER) this;
        }

        public BUILDER assignments(List<ConsultancyAssignmentDTO> assignments) {
            this.assignments.addAll(assignments);
            return (BUILDER) this;
        }

        public BUILDER role(String role) {
            this.role = role;
            return (BUILDER) this;
        }

        public BUILDER active(boolean active) {
            this.active = active;
            return (BUILDER) this;
        }

        public abstract DTO build();
    }

}
