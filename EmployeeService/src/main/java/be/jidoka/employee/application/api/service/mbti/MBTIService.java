package be.jidoka.employee.application.api.service.mbti;

import org.springframework.stereotype.Service;

@Service
class MBTIService implements MBTIClient {

    @Override
    public MbtiDTO getMBTI(String type) {
        return MbtiDTO.of(type);
    }

}
