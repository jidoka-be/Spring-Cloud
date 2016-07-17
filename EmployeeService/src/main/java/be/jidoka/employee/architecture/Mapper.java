package be.jidoka.employee.architecture;

import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Mapper<SOURCE, TARGET> {

    TARGET map(SOURCE source);

    default List<TARGET> map(List<SOURCE> sources) {
        return sources.stream().map(this::map).collect(Collectors.toList());
    }

}

