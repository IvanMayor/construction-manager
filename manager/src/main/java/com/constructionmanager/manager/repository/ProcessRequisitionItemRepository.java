package com.constructionmanager.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.constructionmanager.manager.model.ProcessRequisitionItem;

public interface ProcessRequisitionItemRepository extends JpaRepository<ProcessRequisitionItem, Integer> {
};
