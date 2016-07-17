package be.jidoka.employee.application.api.service.mbti;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "mbti-service", fallback = MBTIService.class)
public interface MBTIClient  {

    @RequestMapping(value = "/mbti/{type}", method = GET)
    MbtiDTO getMBTI(@PathVariable("type") String type);

}
