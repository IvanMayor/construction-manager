package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.ProcessRequisitionRepository;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class RequisitionContractItemService {

    @Autowired
    private ProcessRequisitionRepository processRequisitionRepository;

    @Autowired
    private RequisitionContractItemsRepository requisitionContractItemsRepository;

    @Autowired
    private RequisitionsRepository requisitionsRepository;

    public List<RequisitionContractItems> getAllRequisitionContractItems() {
        return requisitionContractItemsRepository.findAll();
    }

    public RequisitionContractItems getRequisitionContractItem(Integer id) {
        return requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This Requisition Item does not exist"));
    }

    public RequisitionContractItems createRequisitionContractItem(Integer requisitionId,
            RequisitionContractItems requisitionContractItem) {
        Requisitions requisition = requisitionsRepository.findById(requisitionId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition does not exist."));

        return requisitionContractItemsRepository.save(requisitionContractItem);
    }

    public Set<RequisitionContractItems> getRequisitionContractItems(Integer processRequisitionId) {
        ProcessRequisition processRequisition = processRequisitionRepository.findById(processRequisitionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Requisition with provided ID does not exist"));

        return processRequisition.getRequisitionContractItems();
    }

    public RequisitionContractItems updateRequisitionContractItem(Integer requisitionId, Integer contractItemId,
            RequisitionContractItems requisitionContractItemDetail) {
        Requisitions requisition = requisitionsRepository.findById(requisitionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Requisition with this id does not exist."));

        RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository.findById(contractItemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This requisition contract item does not exist"));

        if (requisitionContractItemDetail.getName() != null) {
            requisitionContractItem.setName(requisitionContractItemDetail.getName());
        }

        if (requisitionContractItemDetail.getTotalCost() != null) {
            requisitionContractItem.setTotalCost(requisitionContractItemDetail.getTotalCost());
        }

        return requisitionContractItemsRepository.save(requisitionContractItem);
    }

    public void deleteRequisitionContractItem(Integer id) {
        if (!requisitionContractItemsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition contract item does not exist.");
        }

        requisitionContractItemsRepository.deleteById(id);
    }

}
