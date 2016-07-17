package be.jidoka.employee.domain.employee;

public class CoWorker extends Employee {

    private MaritalStatus maritalStatus;
    private int numberOfChildren;

    private CoWorker() { }

    private CoWorker(CoWorkerBuilder builder) {
        super(builder);

        this.maritalStatus = builder.maritalStatus;
        this.numberOfChildren = builder.numberOfChildren;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public static class CoWorkerBuilder extends EmployeeBuilder<CoWorker, CoWorkerBuilder> {
        private MaritalStatus maritalStatus;
        private int numberOfChildren;

        private CoWorkerBuilder() {
            super(EmployeeType.CO_WORKER);
        }

        public static CoWorkerBuilder coWorkerWith() {
            return new CoWorkerBuilder();
        }

        public CoWorkerBuilder maritalStatus(MaritalStatus maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public CoWorkerBuilder numberOfChildren(int numberOfChildren) {
            this.numberOfChildren = numberOfChildren;
            return this;
        }

        @Override
        public CoWorker build() {
            return new CoWorker(this);
        }
    }

}
