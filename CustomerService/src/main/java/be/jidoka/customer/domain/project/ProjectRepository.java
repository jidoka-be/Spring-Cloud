package be.jidoka.customer.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByNameAndCompanyId(String name, Long companyId);

    List<Project> findByNameContainingIgnoreCase(String name);

}
