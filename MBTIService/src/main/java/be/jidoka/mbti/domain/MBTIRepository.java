package be.jidoka.mbti.domain;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MBTIRepository extends ElasticsearchRepository<MBTIFile, String> {

    List<MBTIFile> findByRawTextContainingIgnoreCase(String rawText);

}
