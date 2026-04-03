package com.constructionmanager.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructionmanager.manager.model.ProcessRequisition;

public interface ProcessRequisitionRepository extends JpaRepository<ProcessRequisition, Integer> {
}
