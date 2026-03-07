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


        //TODO: BeanUtils.copyProperties... implement getNullPropertyNames for ignore argument in copy property.
        if (requisitionContractItemDetail.getName() != null) {
            requisitionContractItem.setName(requisitionContractItemDetail.getName());
        }

        if (requisitionContractItemDetail.getTotalCompleted() != null) {
            requisitionContractItem.setTotalCompleted(requisitionContractItemDetail.getTotalCompleted());
        }

        if (requisitionContractItemDetail.getTotalCost() != null) {
            requisitionContractItem.setTotalCost(requisitionContractItemDetail.getTotalCost());
        }

        if (requisitionContractItemDetail.getRetainage() != null) {
            requisitionContractItem.setRetainage(requisitionContractItemDetail.getRetainage());
        }

        if (requisitionContractItemDetail.getPreviousReq() != null) {
            requisitionContractItem.setPreviousReq(requisitionContractItemDetail.getPreviousReq());
        }

        if (requisitionContractItemDetail.getThisReq() != null) {
            requisitionContractItem.setThisReq(requisitionContractItemDetail.getThisReq());
        }

        if (requisitionContractItemDetail.getPresentlyStoredMaterial() != null) {
            requisitionContractItem.setPresentlyStoredMaterial(requisitionContractItemDetail.getPresentlyStoredMaterial());
        }

        if (requisitionContractItemDetail.getPercentCompleted() != null) {
            requisitionContractItem.setPercentCompleted(requisitionContractItemDetail.getPercentCompleted());
        }

        if (requisitionContractItemDetail.getTotalToFinish() != null) {
            requisitionContractItem.setTotalToFinish(requisitionContractItemDetail.getTotalToFinish());
        }

        return requisitionContractItemsRepository.save(requisitionContractItem);
    }

    public void deleteRequisitionContractItem(Integer id) {
        if(!requisitionContractItemsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition contract item does not exist.");
        }

        requisitionContractItemsRepository.deleteById(id);
    }

}
