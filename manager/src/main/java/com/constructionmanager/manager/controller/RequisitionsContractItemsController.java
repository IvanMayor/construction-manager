package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/requisitions/items")
public class RequisitionsContractItemsController {

    @Autowired
    private RequisitionContractItemService requisitionContractItemService;

    @GetMapping
    public List<RequisitionContractItems> getRequisitionContractItems() {return requisitionContractItemService.getAllRequisitionContractItems();}

    @GetMapping("/{id}")
    public RequisitionContractItems requisitionContractItems(@PathVariable Integer id) {
        return requisitionContractItemService.getRequisitionContractItem(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequisitionContractItems postRequisitionContractItem(@RequestBody RequisitionContractItems requisitionContractItems) {
        return requisitionContractItemService.createRequisitionContractItem(requisitionContractItems);
    }

    @PutMapping("/{id}")
    public RequisitionContractItems updateRequisitionContractItem(@PathVariable Integer id, @RequestBody RequisitionContractItems requisitionContractItemsDetail) {
        return requisitionContractItemService.updateRequisitionContractItem(id, requisitionContractItemsDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteRequisitionContractItem(@PathVariable Integer id) {
        requisitionContractItemService.deleteRequisitionContractItem(id);
    }
}
