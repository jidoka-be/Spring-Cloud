package be.jidoka.mbti.architecture;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@FunctionalInterface
public interface Mapper<SOURCE, TARGET> {

    TARGET map(SOURCE source);

    default List<TARGET> map(List<SOURCE> sources) {
        return sources.stream().map(this::map).collect(Collectors.toList());
    }

    default List<TARGET> map(Iterable<SOURCE> sources) {
        return StreamSupport.stream(sources.spliterator(), false)
                .map(this::map)
                .collect(Collectors.toList());
    }

}

