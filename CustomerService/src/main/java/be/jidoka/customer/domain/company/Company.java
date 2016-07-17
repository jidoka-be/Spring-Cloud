package be.jidoka.customer.domain.company;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private NumberOfEmployees numberOfEmployees;
    private String website;
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "SECTOR_ID", updatable = false)
    private Sector sector;

    private Company() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NumberOfEmployees getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getWebsite() {
        return website;
    }

    public String getDescription() {
        return description;
    }

    public Sector getSector() {
        return sector;
    }

}
