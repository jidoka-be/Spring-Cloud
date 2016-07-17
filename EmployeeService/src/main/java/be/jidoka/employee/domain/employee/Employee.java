package be.jidoka.employee.domain.employee;

import be.jidoka.employee.architecture.ddd.AggregateID;
import be.jidoka.employee.domain.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
public abstract class Employee {

    @Id
    private AggregateID aggregate;
    private String firstName;
    private String lastName;
    private ContactInformation contactInformation;
    private LocalDate dateOfBirth;
    private Address address;
    private EmployeeType type;
    private String mbti;
    private List<ConsultancyAssignment> assignments;
    @DBRef
    private Role role;
    private boolean active;

    protected Employee() { }

    protected Employee(EmployeeBuilder builder) {
        this.aggregate = AggregateID.generate();
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

    public String getID() {
        return aggregate.getID();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public EmployeeType getType() {
        return type;
    }

    public String getMbti() {
        return mbti;
    }

    public List<ConsultancyAssignment> getAssignments() {
        return assignments;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public void addProject(ConsultancyAssignment consultancyAssignment) {
        this.assignments.add(consultancyAssignment);
    }

    protected static abstract class EmployeeBuilder<EMPLOYEE extends Employee, BUILDER extends EmployeeBuilder> {
        private String firstName;
        private String lastName;
        private ContactInformation contactInformation;
        private LocalDate dateOfBirth;
        private Address address;
        private EmployeeType type;
        private String mbti;
        private List<ConsultancyAssignment> assignments = new ArrayList<>();
        private Role role;
        private boolean active = true;

        protected EmployeeBuilder(EmployeeType type) {
            this.type = type;
        }

        public BUILDER firstName(String firstName) {
            this.firstName = firstName;
            return (BUILDER) this;
        }

        public BUILDER lastName(String lastName) {
            this.lastName = lastName;
            return (BUILDER) this;
        }

        public BUILDER contactInformation(ContactInformation contactInformation) {
            this.contactInformation = contactInformation;
            return (BUILDER) this;
        }

        public BUILDER dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return (BUILDER) this;
        }

        public BUILDER address(Address address) {
            this.address = address;
            return (BUILDER) this;
        }

        public BUILDER mbti(String mbti) {
            this.mbti = mbti;
            return (BUILDER) this;
        }

        public BUILDER assignments(List<ConsultancyAssignment> assignments) {
            this.assignments.addAll(assignments);
            return (BUILDER) this;
        }

        public BUILDER role(Role role) {
            this.role = role;
            return (BUILDER) this;
        }

        public BUILDER inActive() {
            this.active = false;
            return (BUILDER) this;
        }

        public abstract EMPLOYEE build();
    }

}
