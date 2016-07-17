package be.jidoka.employee.application.api.service.employee;

public class AddressDTO {

    private String street;
    private String houseNumber;
    private String boxNumber;
    private String zipCode;
    private String cityName;

    private AddressDTO() { }

    private AddressDTO(AddressDTOBuilder builder) {
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.boxNumber = builder.boxNumber;
        this.zipCode = builder.zipCode;
        this.cityName = builder.cityName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public static class AddressDTOBuilder {
        private String street;
        private String houseNumber;
        private String boxNumber;
        private String zipCode;
        private String cityName;

        private AddressDTOBuilder() { }

        public static AddressDTOBuilder addressDTOWith() {
            return new AddressDTOBuilder();
        }

        public AddressDTOBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressDTOBuilder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressDTOBuilder boxNumber(String boxNumber) {
            this.boxNumber = boxNumber;
            return this;
        }

        public AddressDTOBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressDTOBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public AddressDTO build() {
            return new AddressDTO(this);
        }
    }

}
