package be.jidoka.employee.infrastructure;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomerServiceChannels {

    @Output
    MessageChannel output();

}
