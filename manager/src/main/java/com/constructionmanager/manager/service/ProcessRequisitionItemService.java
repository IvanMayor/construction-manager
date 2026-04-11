package com.constructionmanager.manager.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.repository.ProcessRequisitionItemRepository;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;

@Service
public class ProcessRequisitionItemService {
	@Autowired
	private ProcessRequisitionItemRepository processRequisitionItemRepository;
	@Autowired
	private RequisitionContractItemsRepository requisitionContractItemsRepository;

	public List<ProcessRequisitionItem> getAllProcessRequisitionItems() {
		return processRequisitionItemRepository.findAll();
	}

	public ProcessRequisitionItem getProcessRequisitionItem(Integer id) {
		return processRequisitionItemRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Not Found: Process Requisition Item with ID: " + id));
	}

	public ProcessRequisitionItem createProcessRequisitionItem(ProcessRequisitionItem processRequisitionItem) {
		return processRequisitionItemRepository.save(processRequisitionItem);
	}

	public ProcessRequisitionItem updateProcessRequisitionItem(Integer id,
			ProcessRequisitionItem processRequisitionItemDetail) {
		ProcessRequisitionItem processRequisitionItem = processRequisitionItemRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Process Requisition Item with this ID does not exist!!!"));
		if (processRequisitionItemDetail.getPreviousRequisitionItemBilled() != null) {
			processRequisitionItem.setPreviousRequisitionItemBilled(
					processRequisitionItemDetail.getPreviousRequisitionItemBilled());
		}
		if (processRequisitionItemDetail.getThisRequisitionItemBilled() != null) {
			processRequisitionItem.setThisRequisitionItemBilled(
					processRequisitionItemDetail.getThisRequisitionItemBilled());
		}
		if (processRequisitionItemDetail.getTotalCompletedItemToDate() != null) {
			processRequisitionItem.setTotalCompletedItemToDate(
					processRequisitionItemDetail.getTotalCompletedItemToDate());
		}
		if (processRequisitionItemDetail.getTotalToFinishItem() != null) {
			processRequisitionItem
					.setTotalToFinishItem(processRequisitionItemDetail.getTotalToFinishItem());
		}
		if (processRequisitionItemDetail.getRetainageItemToDate() != null) {
			processRequisitionItem
					.setRetainageItemToDate(processRequisitionItemDetail.getRetainageItemToDate());
		}
		if (processRequisitionItemDetail.getPercentItemCompleted() != null) {
			processRequisitionItem.setPercentItemCompleted(
					processRequisitionItemDetail.getPercentItemCompleted());
		}

		return processRequisitionItemRepository.save(processRequisitionItem);
	}

	public void deleteProcessRequisitionItem(Integer id) {
		if (!processRequisitionItemRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"This Process Requisition Item does not exist");
		}
		processRequisitionItemRepository.deleteById(id);
	}

	public BigDecimal getPreviousRequisitionItem(Integer requisitionContractItemId) {
		RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository
				.findById(requisitionContractItemId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requisition Contract Item doesnt exist!!!!!!!"));

		List<ProcessRequisitionItem> processRequisitionItems = requisitionContractItem
				.getProcessRequisitionContractItems();
		if (processRequisitionItems.isEmpty()) {
			return null;
		}
		Collections.sort(processRequisitionItems);
		return processRequisitionItems.getLast().getThisRequisitionItemBilled();
	}

	public BigDecimal getTotalCompletedItemToDate(Integer requisitionContractItemId,
			BigDecimal thisRequisitionBilling) {

		BigDecimal prevReq = getPreviousRequisitionItem(requisitionContractItemId);
		if (prevReq != null) {
			return prevReq.add(thisRequisitionBilling);
		} else {
			return thisRequisitionBilling;
		}
	}

	public BigDecimal getTotalItemToFinish(Integer requisitionContractItemId, BigDecimal thisRequisitionBilling) {
		RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository
				.findById(requisitionContractItemId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requisition Contract Item does not exist!!!"));
		return requisitionContractItem.getTotalCost().subtract(
				getTotalCompletedItemToDate(requisitionContractItemId, thisRequisitionBilling));
	}
}
