package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequisitionService {

    @Autowired
    private RequisitionsRepository requisitionsRepository;

    @Autowired
    private RequisitionContractItemsRepository requisitionContractItemsRepository;

    public List<Requisitions> getAllRequisitions() {return requisitionsRepository.findAll();}

    public Requisitions getRequisition(Integer id) {
        return requisitionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition does not exist"));
    }

    public Requisitions createRequisition(Requisitions requisitions) {
        return requisitionsRepository.save(requisitions);
    }

    public Requisitions updateRequisition(Integer id, Requisitions requisitionDetail) {
        Requisitions requisitions = requisitionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition does not exist"));

        //TODO: BeanUtils.copyProperties... implement getNullPropertyNames for ignore argument in copy property.
        if (requisitionDetail.getContractPrice() != null) {
            requisitions.setContractPrice(requisitionDetail.getContractPrice());
        }
        if (requisitionDetail.getTotalChangeOrderAmount() != null) {
            requisitions.setTotalChangeOrderAmount(requisitionDetail.getTotalChangeOrderAmount());
        }

        if (requisitionDetail.getTotalContractAndCOAmount() != null) {
            requisitions.setTotalContractAndCOAmount(requisitionDetail.getTotalContractAndCOAmount());
        }

        if (requisitionDetail.getTotalMoneyReceived() != null) {
            requisitions.setTotalMoneyReceived(requisitionDetail.getTotalMoneyReceived());
        }

        if (requisitionDetail.getRetainage() != null) {
            requisitions.setRetainage(requisitionDetail.getRetainage());
        }

        if (requisitionDetail.getCompanyName() != null) {
            requisitions.setCompanyName(requisitionDetail.getCompanyName());
        }

        if (requisitionDetail.getOwnerOrRepresentativeFullName() != null) {
            requisitions.setOwnerOrRepresentativeFullName(requisitionDetail.getOwnerOrRepresentativeFullName());
        }

        if (requisitionDetail.getRequisitionContractItems() != null) {
            requisitions.setRequisitionContractItems(requisitionDetail.getRequisitionContractItems());
        }

        return requisitionsRepository.save(requisitions);
    }

    public void deleteRequisition(Integer id) {
        if (requisitionsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition does not exist");
        }
        requisitionsRepository.deleteById(id);
    }

}
