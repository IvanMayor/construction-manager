package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisitions/items")
public class RequisitionsContractItemsController {

    @Autowired
    private RequisitionContractItemService requisitionContractItemService;

    @GetMapping
    public List<RequisitionContractItems> getRequisitionContractItems() {
        return requisitionContractItemService.getAllRequisitionContractItems();
    }

    @GetMapping("/{id}")
    public RequisitionContractItems requisitionContractItems(@PathVariable Integer id) {
        return requisitionContractItemService.getRequisitionContractItem(id);

    }

    @DeleteMapping("/{id}")
    public void deleteRequisitionContractItem(@PathVariable Integer id) {
        requisitionContractItemService.deleteRequisitionContractItem(id);
    }
}
