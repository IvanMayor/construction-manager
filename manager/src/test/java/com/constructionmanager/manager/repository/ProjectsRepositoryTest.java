package com.constructionmanager.manager.repository;

import com.constructionmanager.manager.model.Projects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class ProjectsRepositoryTest {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Test
    public void testProjects() {
        Projects projects = new Projects();

        projects.setName("The Surrey");
        projects.setAddress("20 e 76");
        projects.setJobType(Projects.JobType.HOSPITALITY);
        projects.setDateStarted(LocalDate.now());
        projects.setDateFinished(LocalDate.now());


        projectsRepository.save(projects);

        Optional<Projects> foundProject = projectsRepository.findByAddressContaining("20");

        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getAddress()).isEqualTo("20 e 76");

    }
}
