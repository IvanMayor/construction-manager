package com.constructionmanager.manager.repository;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Integer> {

//    @Query("SELECT o FROM projects")
    Optional<Projects> findProjectsById(@Param("id") Integer id);

    Optional<Projects> findByAddressContaining(String address);
}
