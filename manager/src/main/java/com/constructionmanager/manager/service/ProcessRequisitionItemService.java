package com.constructionmanager.manager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	public ProcessRequisitionItem createProcessRequisitionItem(Integer requisitionCIId,
			ProcessRequisitionItem processRequisitionItem) {
		RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository
				.findById(requisitionCIId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Thie requisition contract item does not exist!!!"));
		processRequisitionItem.setRequisitionContractItem(requisitionContractItem);

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

	public BigDecimal getRetainageItemToDate(Integer requisitionContractItemId, BigDecimal thisRequisitionBilling) {
		RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository
				.findById(requisitionContractItemId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Req Contract Item does not exist!!!"));
		BigDecimal retainage = requisitionContractItem.getRetainage();
		return getTotalCompletedItemToDate(requisitionContractItemId, thisRequisitionBilling)
				.divide(new BigDecimal(100))
				.multiply(retainage);
	}

	public BigDecimal getPercentItemCompleted(Integer requisitionContractItemId, BigDecimal totalCompleted) {
		RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository
				.findById(requisitionContractItemId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Requisition Contract Item does not exist!!!"));
		return totalCompleted.divide(requisitionContractItem.getTotalCost());
	}

	public ProcessRequisitionItem setAllFieldsByPercentCompleted(RequisitionContractItems requisitionContractItem,
			ProcessRequisitionItem processRequisitionItem) {
		System.out.println("----------------------------here------------------------");
		Integer percentCompleted = processRequisitionItem.getPercentItemCompleted();
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal totalMoneyCompleted = requisitionContractItem.getTotalCost().divide(hundred)
				.multiply(new BigDecimal(percentCompleted));
		List<ProcessRequisitionItem> processRequisitionItems = requisitionContractItem
				.getProcessRequisitionContractItems();
		if (!processRequisitionItems.isEmpty()) {
			Collections.sort(processRequisitionItems);
			processRequisitionItem.setPreviousRequisitionItemBilled(
					processRequisitionItems.getLast().getThisRequisitionItemBilled());
		} else {
			processRequisitionItem.setPreviousRequisitionItemBilled(new BigDecimal(0));
		}
		BigDecimal thisRequisitionBilling = totalMoneyCompleted
				.subtract(processRequisitionItem.getPreviousRequisitionItemBilled());
		processRequisitionItem.setThisRequisitionItemBilled(thisRequisitionBilling);
		processRequisitionItem.setTotalCompletedItemToDate(
				requisitionContractItem.getTotalCost().divide(hundred)
						.multiply(new BigDecimal(percentCompleted)));
		BigDecimal retainageToDate = (totalMoneyCompleted.divide(hundred))
				.multiply(requisitionContractItem.getRetainage());
		processRequisitionItem.setRetainageItemToDate(retainageToDate);
		processRequisitionItem.setDateCreated(LocalDate.now());

		testForSetFildsByPercent(processRequisitionItem);

		return processRequisitionItem;
	}

	public void testForSetFildsByPercent(ProcessRequisitionItem processRequisitionItem) {
		String message = "Total cost of Item is: "
				+ String.valueOf(processRequisitionItem.getRequisitionContractItem().getTotalCost())
				+ ". \nPrev Req: "
				+ String.valueOf(processRequisitionItem.getPreviousRequisitionItemBilled())
				+ "\nThis Req Bill: "
				+ String.valueOf(processRequisitionItem.getThisRequisitionItemBilled())
				+ "\nTotal Complete to date: "
				+ String.valueOf(processRequisitionItem.getTotalCompletedItemToDate())
				+ "\nTotal To Finish: " +
				String.valueOf(processRequisitionItem.getTotalToFinishItem()) + "\nRetainage Item: "
				+ String.valueOf(processRequisitionItem.getRetainageItemToDate())
				+ "\nPercent item Complete: "
				+ String.valueOf(processRequisitionItem.getPercentItemCompleted()) +
				"\nDate Created: " + String.valueOf(processRequisitionItem.getDateCreated());

		System.out.println(message);
	}

}
