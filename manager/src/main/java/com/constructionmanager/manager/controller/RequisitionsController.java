package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.RequisitionsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/requisitions")
public class RequisitionsController {

    private RequisitionsRepository requisitionsRepository;

    @GetMapping
    public List<Requisitions> getRequisitions() {return requisitionsRepository.findAll();}

    @GetMapping("/{id}")
    public Requisitions requisitions(@PathVariable Integer id) {
        return requisitionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requisition does not exist"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Requisitions createRequisition(@RequestBody Requisitions requisitions) {
        return requisitionsRepository.save(requisitions);
    }

    @PutMapping("/{id}")
    public Requisitions updateRequisition(@PathVariable Integer id, @RequestBody Requisitions requisitionsDetail) {
        Requisitions requisitions = requisitionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requisition does not exist"));

        requisitions.setContractPrice(requisitionsDetail.getContractPrice());
        requisitions.setTotalChangeOrderAmount(requisitionsDetail.getTotalChangeOrderAmount());
        requisitions.setTotalContractAndCOAmount(requisitionsDetail.getTotalContractAndCOAmount());
        requisitions.setTotalMoneyReceived(requisitions.getTotalMoneyReceived());
        requisitions.setRetainage(requisitionsDetail.getRetainage());
        requisitions.setCompanyName(requisitionsDetail.getCompanyName());
        requisitions.setOwnerOrRepresentativeFullName(requisitionsDetail.getOwnerOrRepresentativeFullName());

        return requisitionsRepository.save(requisitions);

    }

    @DeleteMapping("/{id}")
    public void deleteRequisition(@PathVariable Integer id) {
        requisitionsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requisition does not exist"));
        requisitionsRepository.deleteById(id);
    }

}
