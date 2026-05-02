package com.constructionmanager.manager.service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.ProcessRequisitionRepository;
import com.constructionmanager.manager.repository.ProjectsRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;

import jakarta.transaction.Transactional;

@Service
public class ProcessRequisitionService {

	@Autowired
	private ProcessRequisitionRepository processRequisitionRepository;

	@Autowired
	private RequisitionsRepository requisitionsRepository;

	@Autowired
	private ProjectsRepository projectsRepository;

	public List<ProcessRequisition> getAllProcessRequisitions() {
		return processRequisitionRepository.findAll();
	}

	public ProcessRequisition getProcessRequisition(Integer processRequisitionId) {
		return processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requistion was not generated"));
	}

	public ProcessRequisition createProcessRequisition(Integer requisitionId,
			ProcessRequisition processRequisition) {
		Requisitions requisition = requisitionsRepository.findById(requisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Project does not exist!!!"));

		processRequisition.setRequisition(requisition);

		return processRequisitionRepository.save(processRequisition);
	}

	@Transactional
	public ProcessRequisition updateProcessRequisition(Integer processRequisitionId,
			ProcessRequisition processRequisitionDetail) {
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
		if (processRequisitionDetail.getThisRequisitionBilling() != null) {
			processRequisition.setThisRequisitionBilling(
					processRequisitionDetail.getThisRequisitionBilling());
		}
		if (processRequisitionDetail.getPreviousRequisitionBilled() != null) {
			processRequisition.setPreviousRequisitionBilled(
					processRequisitionDetail.getPreviousRequisitionBilled());
		}
		if (processRequisitionDetail.getTotalChangeOrdersAndOriginalContract() != null) {
			processRequisition.setTotalChangeOrdersAndOriginalContract(
					processRequisitionDetail.getTotalChangeOrdersAndOriginalContract());
		}
		if (processRequisitionDetail.getRequisitionDate() != null) {
			processRequisition.setRequisitionDate(processRequisitionDetail.getRequisitionDate());
		}
		return processRequisitionRepository.save(processRequisition);
	}

	public void deleteProcessRequisition(Integer processRequisitionId) {
		ProcessRequisition processRequisition = processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This ProcessRequisition does not exist!!!"));
		Requisitions requisition = processRequisition.getRequisition();
		requisition.getProcessRequisitions().remove(processRequisition);
		requisitionsRepository.save(requisition);
	}

	public ProcessRequisition generatePGRFromProcessRequisitionItems(
			List<ProcessRequisitionItem> processRequisitionItems) {

		ProcessRequisition processRequisition = new ProcessRequisition();
		Projects project = processRequisitionItems.getFirst().getRequisitionContractItem().getRequisition()
				.getProject();
		List<ProcessRequisition> processRequisitions = processRequisitionItems.getFirst()
				.getRequisitionContractItem().getRequisition().getProcessRequisitions();

		BigDecimal prevReqBill = new BigDecimal(0);
		BigDecimal thisReqBill = new BigDecimal(0);
		BigDecimal totalChangeOrders = new BigDecimal(0);
		BigDecimal totalChangeOrderAndOriginalContract = project.getTotalContract().add(totalChangeOrders);
		BigDecimal completedWork = new BigDecimal(0);
		BigDecimal totalCompletedRetainage = new BigDecimal(0);
		BigDecimal totalCompletedWorkNoRetainage = completedWork.subtract(totalCompletedRetainage);
		BigDecimal currentlyPaymentDue = new BigDecimal(0);
		BigDecimal balanceToFinishIncludingRetainage = project.getTotalContract().subtract(completedWork);
		BigDecimal totalApprovedChangeOrdersThisMonth = new BigDecimal(0);

		for (ProcessRequisitionItem processRequisitionItem : processRequisitionItems) {
			prevReqBill.add(processRequisitionItem.getPreviousRequisitionItemBilled());
			thisReqBill.add(processRequisitionItem.getThisRequisitionItemBilled());
			completedWork.add(processRequisitionItem.getTotalCompletedItemToDate());
			totalCompletedRetainage.add(processRequisitionItem.getRetainageItemToDate());
		}

		for (ChangeOrders changeOrder : project.getChangeOrders()) {
			if (changeOrder.getApproved()) {
				totalChangeOrders.add(changeOrder.getPrice());
			}

			boolean isThisMonth = changeOrder.getDateCreated().getYear() == LocalDate.now().getYear()
					&& changeOrder.getDateCreated().getMonth() == LocalDate.now().getMonth();
			if (isThisMonth && changeOrder.getApproved()) {
				totalApprovedChangeOrdersThisMonth.add(changeOrder.getPrice());
			}
		}

		if (!processRequisitions.isEmpty()) {
			for (ProcessRequisition oneProcessRequisition : processRequisitions) {
				currentlyPaymentDue.add(oneProcessRequisition.getThisRequisitionBilling());
			}
			currentlyPaymentDue.add(totalChangeOrders);
		}

		processRequisition.setPreviousRequisitionBilled(prevReqBill);
		processRequisition.setThisRequisitionBilling(thisReqBill);
		processRequisition.setTotalChangeOrdersToDate(totalChangeOrders);
		processRequisition.setTotalChangeOrdersAndOriginalContract(totalChangeOrderAndOriginalContract);
		processRequisition.setTotalCompletedWork(completedWork);
		processRequisition.setTotalCompletedRetainage(totalCompletedRetainage);
		processRequisition.setTotalCompletedWorkNoRetainage(totalCompletedWorkNoRetainage);
		processRequisition.setCurrentlyPaymentDue(currentlyPaymentDue);
		processRequisition.setBalanceToFinishIncludingRetainage(balanceToFinishIncludingRetainage);
		processRequisition.setTotalApprovedChangeOrdersThisMonth(totalApprovedChangeOrdersThisMonth);
		processRequisition.setRequisitionDate(LocalDate.now());

		testGenerateProcessRequisition(processRequisition);

		return processRequisition;
	}

	private void testGenerateProcessRequisition(ProcessRequisition processRequisition) {
		System.out.println(processRequisition.getId());
		System.out.println(processRequisition.getPreviousRequisitionBilled());
		System.out.println(processRequisition.getThisRequisitionBilling());
		System.out.println(processRequisition.getTotalChangeOrdersToDate());
		System.out.println(processRequisition.getTotalChangeOrdersAndOriginalContract());
		System.out.println(processRequisition.getTotalCompletedWork());
		System.out.println(processRequisition.getTotalCompletedRetainage());
		System.out.println(processRequisition.getTotalCompletedWorkNoRetainage());
		System.out.println(processRequisition.getCurrentlyPaymentDue());
		System.out.println(processRequisition.getBalanceToFinishIncludingRetainage());
		System.out.println(processRequisition.getTotalApprovedChangeOrdersThisMonth());
		System.out.println(processRequisition.getRequisitionDate());
	}

}
