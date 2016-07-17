package be.jidoka.mbti.controller;

import be.jidoka.mbti.application.api.service.MBTI;
import be.jidoka.mbti.application.api.service.MBTIService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("mbti")
public class MBTIController {

    private final MBTIService mbtiService;

    public MBTIController(MBTIService mbtiService) {
        this.mbtiService = mbtiService;
    }

    @RequestMapping(method = GET)
    public List<String> availableTypes() {
        return mbtiService.findAll().stream()
                .map(MBTI::getType)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{type}", method = GET)
    public MBTI findOne(@PathVariable("type") String type) {
        return mbtiService.findOne(type);
    }

    @RequestMapping(value = "/search/{searchTerm}", method = GET)
    public List<MBTI> searchInRawText(@PathVariable("searchTerm") String searchTerm) {
        return mbtiService.searchInRawText(searchTerm);
    }

}
