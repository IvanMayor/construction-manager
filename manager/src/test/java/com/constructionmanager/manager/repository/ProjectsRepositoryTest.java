package com.constructionmanager.manager.repository;

import com.constructionmanager.manager.model.Projects;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ProjectsRepositoryTest {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Test
    public void testProjects() {
        Projects projects = new Projects();

        projects.setName("The Surrey");
        projects.setAddress("20 e 76");

        Projects savedProject = projectsRepository.save(projects);

        Optional<Projects> foundProject = projectsRepository.findById(savedProject.getId());
        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getAddress()).isEqualTo("20 e 76");

    }
}
