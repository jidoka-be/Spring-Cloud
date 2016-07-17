package be.jidoka.customer.application.api.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(Class clazz) {
        super(String.format("%s already exists!", clazz.getSimpleName()));
    }

}
