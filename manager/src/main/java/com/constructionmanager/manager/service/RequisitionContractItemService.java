package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequisitionContractItemService {

    @Autowired
    private RequisitionContractItemsRepository requisitionContractItemsRepository;

    public List<RequisitionContractItems> getAllRequisitionContractItems() {return requisitionContractItemsRepository.findAll();}

    public RequisitionContractItems getRequisitionContractItem(Integer id) {
        return requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition Item does not exist"));
    }

    public RequisitionContractItems createRequisitionContractItem(RequisitionContractItems requisitionContractItems) {
        return requisitionContractItemsRepository.save(requisitionContractItems);
    }

    public RequisitionContractItems updateRequisitionContractItem(Integer id, RequisitionContractItems requisitionContractItemDetail) {
        RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition contract item does not exist"));

        requisitionContractItem.setName(requisitionContractItemDetail.getName());
        requisitionContractItem.setTotalCompleted(requisitionContractItemDetail.getTotalCompleted());
        requisitionContractItem.setTotalCost(requisitionContractItemDetail.getTotalCost());
        requisitionContractItem.setRetainage(requisitionContractItemDetail.getRetainage());
        requisitionContractItem.setPreviousReq(requisitionContractItemDetail.getPreviousReq());
        requisitionContractItem.setThisReq(requisitionContractItemDetail.getThisReq());
        requisitionContractItem.setPresentlyStoredMaterial(requisitionContractItemDetail.getPresentlyStoredMaterial());
        requisitionContractItem.setPercentCompleted(requisitionContractItemDetail.getPercentCompleted());
        requisitionContractItem.setTotalToFinish(requisitionContractItemDetail.getTotalToFinish());

        return requisitionContractItemsRepository.save(requisitionContractItem);
    }

    public void deleteRequisitionContractItem(Integer id) {
        if(!requisitionContractItemsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition contract item does not exist.");
        }

        requisitionContractItemsRepository.deleteById(id);
    }

}
