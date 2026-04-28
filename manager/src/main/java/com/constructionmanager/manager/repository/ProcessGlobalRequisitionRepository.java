package com.constructionmanager.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.constructionmanager.manager.model.ProcessGlobalRequisition;

@Repository
public interface ProcessGlobalRequisitionRepository extends JpaRepository<ProcessGlobalRequisition, Integer> {
}
