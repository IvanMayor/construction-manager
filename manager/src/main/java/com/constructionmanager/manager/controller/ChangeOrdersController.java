package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.repository.ChangeOrdersRepository;
import com.constructionmanager.manager.repository.EmployeeRepository;
import com.constructionmanager.manager.service.ChangeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/changeOrders")
public class ChangeOrdersController {

    @Autowired
    private ChangeOrderService changeOrderService;

    @GetMapping
    public List<ChangeOrders> getChangeOrders() {return changeOrderService.getAllChangeOrders();}

    @GetMapping("/{id}")
    public ChangeOrders changeOrders(@PathVariable Integer id) {
        return changeOrderService.getChangeOrder(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChangeOrders postChangeOrders(@RequestBody ChangeOrders changeOrders) {
        return changeOrderService.createChangeOrder(changeOrders);
    }

    @PutMapping("/{id}")
    public ChangeOrders updateChangeOrder(@PathVariable Integer id, @RequestBody ChangeOrders changeOrdersDetails) {
        return changeOrderService.updateChangeOrder(id, changeOrdersDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {changeOrderService.deleteChangeOrder(id);}

}
