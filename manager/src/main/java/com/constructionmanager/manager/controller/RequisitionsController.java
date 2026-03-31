package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import com.constructionmanager.manager.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/requisitions")
public class RequisitionsController {
    @Autowired
    private RequisitionService requisitionService;
    @Autowired
    private RequisitionContractItemService requisitionContractItemService;

    @GetMapping
    public List<Requisitions> getRequisitions() {
        return requisitionService.getAllRequisitions();
    }

    @GetMapping("/{id}")
    public Requisitions requisitions(@PathVariable Integer id) {
        return requisitionService.getRequisition(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Requisitions createRequisition(@PathVariable Integer id, @RequestBody Requisitions requisitions) {
        return requisitionService.createRequisition(id, requisitions);
    }

    @PostMapping("/{requisitionId}/requisition-contract-items")
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitionContractItems createRequisitionContractItem(@PathVariable Integer requisitionId,
            @RequestBody RequisitionContractItems requisitionContractItem) {
        return requisitionContractItemService.createRequisitionContractItem(requisitionId, requisitionContractItem);
    }

    @GetMapping("/{requisitionId}/requisition-contract-items")
    public List<RequisitionContractItems> getRequisitionContractItems(@PathVariable Integer requisitionId) {
        return requisitionContractItemService.getRequisitionContractItems(requisitionId);
    }

    @PutMapping("/{requisitionId}/requisition-contract-items/{contractItemId}")
    public RequisitionContractItems updateRequisitionContractItem(
            @PathVariable Integer requisitionId,
            @PathVariable Integer contractItemId,
            @RequestBody RequisitionContractItems requisitionContractItemDetails) {

        return requisitionContractItemService.updateRequisitionContractItem(requisitionId, contractItemId,
                requisitionContractItemDetails);

    }

    @PutMapping("/{id}")
    public Requisitions updateRequisition(@PathVariable Integer id, @RequestBody Requisitions requisitionsDetail) {
        return requisitionService.updateRequisition(id, requisitionsDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteRequisition(@PathVariable Integer id) {
        requisitionService.deleteRequisition(id);
    }

}
