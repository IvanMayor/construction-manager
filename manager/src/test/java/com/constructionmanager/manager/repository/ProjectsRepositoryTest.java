package com.constructionmanager.manager.repository;

import com.constructionmanager.manager.model.Projects;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProjectsRepositoryTest {

    @Autowired
    ProjectsRepository projectsRepository;

    @Autowired
    TestEntityManager em;

    @Test
    public void saveProject() {
        Projects projects = new Projects();
        projects.setName("The Surrey");
        em.persist(projects);
        em.flush();

        List<Projects> projectsList = projectsRepository.findAll();

        assertThat(projectsList).hasSize(1);
        assertThat(projectsList.get(0).getName()).isEqualTo(projects.getName());
    }
}
