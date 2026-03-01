package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.repository.ChangeOrdersRepository;
import com.constructionmanager.manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/changeOrders")
public class ChangeOrdersController {

    @Autowired
    private ChangeOrdersRepository changeOrdersRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<ChangeOrders> getChangeOrders() {return changeOrdersRepository.findAll();}

    @GetMapping("/{id}")
    public ChangeOrders changeOrders(@PathVariable Integer id) {
        return changeOrdersRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Change order does not exist"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChangeOrders postChangeOrders(@RequestBody ChangeOrders changeOrders) {
        return changeOrdersRepository.save(changeOrders);
    }

    @PutMapping("/{id}")
    public ChangeOrders updateChangeOrder(@PathVariable Integer id, @RequestBody ChangeOrders changeOrdersDetails) {
        ChangeOrders changeOrder = changeOrdersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Change order does not exits!"));

        changeOrder.setNumber(changeOrdersDetails.getNumber());
        changeOrder.setTitle(changeOrdersDetails.getTitle());
        changeOrder.setDescription(changeOrdersDetails.getDescription());
        changeOrder.setBreakdown(changeOrdersDetails.getBreakdown());
        changeOrder.setPrice(changeOrdersDetails.getPrice());
        changeOrder.setProjects(changeOrdersDetails.getProjects());

        return changeOrdersRepository.save(changeOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        ChangeOrders changeOrders = changeOrdersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Change order does not exist"));
        changeOrdersRepository.deleteById(id);
    }

}
