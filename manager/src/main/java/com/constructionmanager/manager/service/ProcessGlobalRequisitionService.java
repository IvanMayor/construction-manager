package com.constructionmanager.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.constructionmanager.manager.model.ProcessGlobalRequisition;
import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.repository.ProcessGlobalRequisitionRepository;
import com.constructionmanager.manager.repository.ProcessRequisitionItemRepository;
import com.constructionmanager.manager.repository.ProcessRequisitionRepository;

@Service
public class ProcessGlobalRequisitionService {

	@Autowired
	private ProcessRequisitionRepository processRequisitionRepository;
	@Autowired
	private ProcessRequisitionItemRepository processRequisitionItemRepository;
	@Autowired
	private ProcessGlobalRequisitionRepository processGlobalRequisitionRepository;

	public List<ProcessGlobalRequisition> getAllProcessGlobalRequisitions() {
		return processGlobalRequisitionRepository.findAll();
	}

	public ProcessGlobalRequisition getProcessGlobalRequisition(Integer pGRId) {
		return processGlobalRequisitionRepository.findById(pGRId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Thsi Process Global Requisition does not exist!!!"));
	}

	public ProcessGlobalRequisition createProcessGlobalRequisition(Integer processRequisitionId,
			List<ProcessRequisitionItem> processRequisitionItems) {

		ProcessRequisition processRequisition = processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This ProcessRequisitiondows not exist!!!"));

		ProcessGlobalRequisition processGlobalRequisition = new ProcessGlobalRequisition();
		processGlobalRequisition.setProcessReuqisition(processRequisition);
		processGlobalRequisition.setProcessRequisitionItems(processRequisitionItems);

		return processGlobalRequisitionRepository.save(processGlobalRequisition);
	}

	public ProcessGlobalRequisition updateProcessGlobalRequisition(Integer processRequisitionId,
			List<ProcessRequisitionItem> processRequisitionItems, Integer processGlobalRequisitionId) {
		ProcessGlobalRequisition processGlobalRequisition = processGlobalRequisitionRepository
				.findById(processGlobalRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Process Global Req with this id does not exist"));

		ProcessRequisition processRequisition = processRequisitionRepository.findById(processRequisitionId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"This Process Requisition does not exist!!!!"));

		processGlobalRequisition.setProcessReuqisition(processRequisition);
		processGlobalRequisition.setProcessRequisitionItems(processRequisitionItems);

		return processGlobalRequisitionRepository.save(processGlobalRequisition);

	}

}
