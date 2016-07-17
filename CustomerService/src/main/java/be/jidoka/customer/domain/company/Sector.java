package be.jidoka.customer.domain.company;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sector {

    @Id
    private String name;

    private Sector() { }

    public String getName() {
        return name;
    }

}
