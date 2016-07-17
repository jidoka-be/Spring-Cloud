package be.jidoka.employee.domain.employee;

public class FreeLancer extends Employee {

    private String companyName;
    private String vatNumber;

    private FreeLancer() { }

    private FreeLancer(FreeLancerBuilder builder) {
        super(builder);

        this.companyName = builder.companyName;
        this.vatNumber = builder.vatNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public static class FreeLancerBuilder extends EmployeeBuilder<FreeLancer, FreeLancerBuilder> {
        public String companyName;
        public String vatNumber;

        private FreeLancerBuilder() {
            super(EmployeeType.FREE_LANCER);
        }

        public static FreeLancerBuilder freeLancerWith() {
            return new FreeLancerBuilder();
        }

        public FreeLancerBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public FreeLancerBuilder vatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
            return this;
        }

        @Override
        public FreeLancer build() {
            return new FreeLancer(this);
        }
    }

}
