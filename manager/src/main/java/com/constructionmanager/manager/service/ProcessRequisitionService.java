package com.constructionmanager.manager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.ProcessRequisitionRepository;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;

public class ProcessRequisitionService {

	@Autowired
	private ProcessRequisitionRepository processRequisitionRepository;

	@Autowired
	private RequisitionsRepository requisitionsRepository;

	@Autowired
	private RequisitionContractItemsRepository requisitionContractItemsRepository;

	public List<ProcessRequisition> getAllProcessRequisitions() {
		return processRequisitionRepository.findAll();
	}

	public ProcessRequisition getProcessRequisition(Integer processRequisitionId) {
		return processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requistion was not generated"));
	}

	public ProcessRequisition createProcessRequisition(Integer requisitionId,
			Set<RequisitionContractItems> requisitionContractItems, ProcessRequisition processRequisition) {
		Requisitions requisition = requisitionsRepository.findById(requisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requisition does not exist"));
		processRequisition.setRequisitionContractItems(requisitionContractItems);
		processRequisition.setRequisition(requisition);
		return processRequisitionRepository.save(processRequisition);
	}

	public ProcessRequisition updateProcessRequisition(Integer requisitionId,
			Set<RequisitionContractItems> requisitionContractItems,
			Integer processRequisitionId, ProcessRequisition processRequisitionDetail) {

		Requisitions requisition = requisitionsRepository.findById(requisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This requisition does not exist!!!"));

		ProcessRequisition processRequisition = processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requisition wasnt processed, its not exist."));

		processRequisitionDetail.setRequisition(requisition);
		processRequisition.setRequisitionContractItems(requisitionContractItems);
		processRequisition.setRequisition(requisition);

		return null;
	}

}
