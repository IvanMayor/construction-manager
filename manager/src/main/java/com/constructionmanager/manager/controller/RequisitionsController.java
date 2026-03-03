package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.Requisitions;
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

    @GetMapping
    public List<Requisitions> getRequisitions() {return requisitionService.getAllRequisitions();}

    @GetMapping("/{id}")
    public Requisitions requisitions(@PathVariable Integer id) {
        return requisitionService.getRequisition(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Requisitions createRequisition(@RequestBody Requisitions requisitions) {
        return requisitionService.createRequisition(requisitions);
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
