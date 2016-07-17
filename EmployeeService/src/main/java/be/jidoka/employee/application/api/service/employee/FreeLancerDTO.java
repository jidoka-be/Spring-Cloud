package be.jidoka.employee.application.api.service.employee;

public class FreeLancerDTO extends EmployeeDTO {

    private String companyName;
    private String vatNumber;

    private FreeLancerDTO() { }

    public FreeLancerDTO(FreeLancerDTOBuilder builder) {
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

    public static class FreeLancerDTOBuilder extends EmployeeDTOBuilder<FreeLancerDTO, FreeLancerDTOBuilder> {
        private String companyName;
        private String vatNumber;

        private FreeLancerDTOBuilder() { }

        public static FreeLancerDTOBuilder freeLancerDTOWith() {
            return new FreeLancerDTOBuilder();
        }

        public FreeLancerDTOBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public FreeLancerDTOBuilder vatNumber(String vatNumber) {
            this.vatNumber = vatNumber;
            return this;
        }

        @Override
        public FreeLancerDTO build() {
            return new FreeLancerDTO(this);
        }
    }

}
