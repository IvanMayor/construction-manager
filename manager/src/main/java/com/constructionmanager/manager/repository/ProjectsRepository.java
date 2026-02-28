package com.constructionmanager.manager.repository;

import com.constructionmanager.manager.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Integer> {

}
