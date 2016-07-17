package be.jidoka.employee.application.api.service.employee;

public class CoWorkerDTO extends EmployeeDTO {

    private String maritalStatus;
    private int numberOfChildren;

    private CoWorkerDTO() { }

    private CoWorkerDTO(CoWorkerDTOBuilder builder) {
        super(builder);

        this.maritalStatus = builder.maritalStatus;
        this.numberOfChildren = builder.numberOfChildren;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public static class CoWorkerDTOBuilder extends EmployeeDTOBuilder<CoWorkerDTO, CoWorkerDTOBuilder> {
        private String maritalStatus;
        private int numberOfChildren;

        private CoWorkerDTOBuilder() { }

        public static CoWorkerDTOBuilder coWorkerWith() {
            return new CoWorkerDTOBuilder();
        }

        public CoWorkerDTOBuilder maritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public CoWorkerDTOBuilder numberOfChildren(int numberOfChildren) {
            this.numberOfChildren = numberOfChildren;
            return this;
        }

        @Override
        public CoWorkerDTO build() {
            return new CoWorkerDTO(this);
        }
    }

}
