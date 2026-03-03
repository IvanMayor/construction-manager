package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Requisitions;
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
        requisitions.setContractPrice(requisitionDetail.getContractPrice());
        requisitions.setTotalChangeOrderAmount(requisitionDetail.getTotalChangeOrderAmount());
        requisitions.setTotalContractAndCOAmount(requisitionDetail.getTotalContractAndCOAmount());
        requisitions.setTotalMoneyReceived(requisitionDetail.getTotalMoneyReceived());
        requisitions.setRetainage(requisitionDetail.getRetainage());
        requisitions.setCompanyName(requisitionDetail.getCompanyName());
        requisitions.setOwnerOrRepresentativeFullName(requisitionDetail.getOwnerOrRepresentativeFullName());
        requisitions.setRequisitionContractItems(requisitionDetail.getRequisitionContractItems());

        return requisitionsRepository.save(requisitions);
    }

    public void deleteRequisition(Integer id) {
        if (requisitionsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition does not exist");
        }
        requisitionsRepository.deleteById(id);
    }

}
