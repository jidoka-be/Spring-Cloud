package be.jidoka.mbti.application.api.service;

import java.util.List;

public interface MBTIService {

    MBTI findOne(String type);

    List<MBTI> findAll();

    List<MBTI> searchInRawText(String searchTerm);

}
