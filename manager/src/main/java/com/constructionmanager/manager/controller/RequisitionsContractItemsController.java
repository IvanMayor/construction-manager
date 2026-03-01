package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/requisitions/items")
public class RequisitionsContractItemsController {

    @Autowired
    private RequisitionContractItemsRepository requisitionContractItemsRepository;

    @GetMapping
    public List<RequisitionContractItems> getRequisitionContractItems() {return requisitionContractItemsRepository.findAll();}

    @GetMapping("/{id}")
    public RequisitionContractItems requisitionContractItems(@PathVariable Integer id) {
        return requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition item does not exist"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitionContractItems postRequisitionContractItem(@RequestBody RequisitionContractItems requisitionContractItems) {
        return requisitionContractItemsRepository.save(requisitionContractItems);
    }

    @PutMapping("/{id}")
    public RequisitionContractItems updateRequisitionContractItem(@PathVariable Integer id, @RequestBody RequisitionContractItems requisitionContractItemsDetail) {
        RequisitionContractItems requisitionContractItems = requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition item does not exist"));

        requisitionContractItems.setName(requisitionContractItemsDetail.getName());
        requisitionContractItems.setTotalCost(requisitionContractItemsDetail.getTotalCost());
        requisitionContractItems.setRetainage(requisitionContractItemsDetail.getRetainage());
        requisitionContractItems.setPreviousReq(requisitionContractItemsDetail.getPreviousReq());
        requisitionContractItems.setThisReq(requisitionContractItemsDetail.getThisReq());
        requisitionContractItems.setPresentlyStoredMaterial(requisitionContractItemsDetail.getPresentlyStoredMaterial());
        requisitionContractItems.setTotalCompleted(requisitionContractItemsDetail.getTotalCompleted());
        requisitionContractItems.setPercentCompleted(requisitionContractItemsDetail.getPercentCompleted());
        requisitionContractItems.setTotalToFinish(requisitionContractItemsDetail.getTotalToFinish());

        return requisitionContractItemsRepository.save(requisitionContractItems);

    }

    @DeleteMapping("/{id}")
    public void deleteRequisitionContractItem(@PathVariable Integer id) {
        requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition item does not exist"));

        requisitionContractItemsRepository.deleteById(id);
    }
}
