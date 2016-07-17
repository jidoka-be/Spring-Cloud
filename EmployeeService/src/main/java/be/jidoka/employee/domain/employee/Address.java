package be.jidoka.employee.domain.employee;

public class Address {

    private final String street;
    private final String houseNumber;
    private final String boxNumber;
    private final String zipCode;
    private final String cityName;

    private Address(String street, String houseNumber, String boxNumber, String zipCode, String cityName) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.boxNumber = boxNumber;
        this.zipCode = zipCode;
        this.cityName = cityName;
    }

    public static Address of(String street, String houserNumber, String zipCode, String cityName) {
        return new Address(street, houserNumber, null, zipCode, cityName);
    }

    public static Address of(String street, String houserNumber, String boxNumber, String zipCode, String cityName) {
        return new Address(street, houserNumber, boxNumber, zipCode, cityName);
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

}
