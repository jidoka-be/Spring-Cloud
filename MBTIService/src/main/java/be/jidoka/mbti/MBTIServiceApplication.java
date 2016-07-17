package be.jidoka.mbti;

import be.jidoka.mbti.domain.MBTIRepository;
import be.jidoka.mbti.infrastructure.DocumentIndexer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class MBTIServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MBTIServiceApplication.class, args);
	}

    @Bean
    AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }

    @Bean
    CommandLineRunner runner(DocumentIndexer documentIndexer, MBTIRepository mbtiRepository) {
        return args -> mbtiRepository.save(documentIndexer.index());
    }

}
