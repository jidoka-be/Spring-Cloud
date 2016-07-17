package be.jidoka.employee.architecture.ddd;

import java.io.Serializable;
import java.util.UUID;

public final class AggregateID implements Serializable {

    private final String id;

    private AggregateID(String id) {
        this.id = id;
    }

    public static AggregateID generate() {
        return new AggregateID(UUID.randomUUID().toString());
    }

    public static AggregateID from(String id) {
        return new AggregateID(id);
    }

    public String getID() {
        return id;
    }
}
