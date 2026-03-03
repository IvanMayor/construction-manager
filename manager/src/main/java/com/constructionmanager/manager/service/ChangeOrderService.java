package com.constructionmanager.manager.service;

import com.constructionmanager.manager.repository.ChangeOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.constructionmanager.manager.model.ChangeOrders;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.lang.Integer;

@Service
public class ChangeOrderService {

    @Autowired
    private ChangeOrdersRepository changeOrdersRepository;

    public List<ChangeOrders> getAllChangeOrders() {return changeOrdersRepository.findAll();}

    public ChangeOrders getChangeOrder(Integer id) {
        return changeOrdersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This change order does not exist."));
    }

    public ChangeOrders createChangeOrder(ChangeOrders changeOrders) {return changeOrdersRepository.save(changeOrders);}

    public ChangeOrders updateChangeOrder(Integer id, ChangeOrders changeOrderDetails) {
        ChangeOrders changeOrder = changeOrdersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This change order does not exist."));

        changeOrder.setNumber(changeOrderDetails.getNumber());
        changeOrder.setTitle(changeOrderDetails.getTitle());
        changeOrder.setDescription(changeOrderDetails.getDescription());
        changeOrder.setBreakdown(changeOrderDetails.getBreakdown());
        changeOrder.setPrice(changeOrderDetails.getPrice());
        changeOrder.setProjects(changeOrderDetails.getProjects());

        return changeOrdersRepository.save(changeOrder);
    }

    public void deleteChangeOrder(Integer id) {
        if (!changeOrdersRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This change order does not exist.");
        }

        changeOrdersRepository.deleteById(id);
    }



}
