package be.jidoka.mbti.application.impl.service;

import be.jidoka.mbti.application.api.service.MBTI;
import be.jidoka.mbti.application.api.service.MBTIService;
import be.jidoka.mbti.architecture.Mapper;
import be.jidoka.mbti.domain.MBTIFile;
import be.jidoka.mbti.domain.MBTIRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static be.jidoka.mbti.application.api.service.MBTI.MBTIBuilder.mbtiWith;

@Service
class MBTIServiceImpl implements MBTIService {

    private final MBTIRepository mbtiRepository;
    private final Mapper<MBTIFile, MBTI> mbtiMapper;

    public MBTIServiceImpl(MBTIRepository mbtiRepository) {
        this.mbtiRepository = mbtiRepository;
        this.mbtiMapper = (mbti) -> mbtiWith()
                .type(mbti.getType())
                .mainCharacteristics(mbti.getMainCharacteristics())
                .minorCharacteristics(mbti.getMinorCharacteristics())
                .description(mbti.getDescription())
                .characteristics(mbti.getCharacteristics())
                .withOthers(mbti.getWithOthers())
                .atWork(mbti.getAtWork())
                .potentialBlindSpots(mbti.getPotentialBlindSpots())
                .build();
    }

    @Override
    public MBTI findOne(String type) {
        return mbtiMapper.map(mbtiRepository.findOne(type));
    }

    @Override
    public List<MBTI> findAll() {
        return mbtiMapper.map(mbtiRepository.findAll());
    }

    @Override
    public List<MBTI> searchInRawText(String searchTerm) {
        return mbtiMapper.map(mbtiRepository.findByRawTextContainingIgnoreCase(searchTerm));
    }

}
