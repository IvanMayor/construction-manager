package com.constructionmanager.manager.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.ProcessRequisitionRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;

import jakarta.transaction.Transactional;

@Service
public class ProcessRequisitionService {

	@Autowired
	private ProcessRequisitionRepository processRequisitionRepository;

	@Autowired
	private RequisitionsRepository requisitionsRepository;

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
		return processRequisitionRepository.save(processRequisition);
	}

	@Transactional
	public ProcessRequisition updateProcessRequisition(Integer requisitionId,
			Integer processRequisitionId, ProcessRequisition processRequisitionDetail) {

		Requisitions requisition = requisitionsRepository.findById(requisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This requisition does not exist!!!"));

		ProcessRequisition processRequisition = processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requisition wasnt processed, its not exist."));

		if (processRequisitionDetail.getTotalChangeOrdersToDate() != null) {
			processRequisition.setTotalChangeOrdersToDate(
					processRequisitionDetail.getTotalChangeOrdersToDate());
		}
		if (processRequisitionDetail.getTotalCompletedWork() != null) {
			processRequisition.setTotalCompletedWork(processRequisitionDetail.getTotalCompletedWork());
		}
		if (processRequisitionDetail.getTotalCompletedRetainage() != null) {
			processRequisition.setTotalCompletedRetainage(
					processRequisitionDetail.getTotalCompletedRetainage());
		}
		if (processRequisitionDetail.getTotalCompletedWorkNoRetainage() != null) {
			processRequisition.setTotalCompletedWorkNoRetainage(
					processRequisitionDetail.getTotalCompletedWorkNoRetainage());
		}
		if (processRequisitionDetail.getCurrentlyPaymentDue() != null) {
			processRequisition.setCurrentlyPaymentDue(processRequisitionDetail.getCurrentlyPaymentDue());
		}
		if (processRequisitionDetail.getBalanceToFinishIncludingRetainage() != null) {
			processRequisition.setBalanceToFinishIncludingRetainage(
					processRequisitionDetail.getBalanceToFinishIncludingRetainage());
		}
		if (processRequisitionDetail.getTotalApprovedChangeOrdersThisMonth() != null) {
			processRequisition.setTotalApprovedChangeOrdersThisMonth(
					processRequisitionDetail.getTotalApprovedChangeOrdersThisMonth());
		}
		return processRequisitionRepository.save(processRequisition);
	}

}
