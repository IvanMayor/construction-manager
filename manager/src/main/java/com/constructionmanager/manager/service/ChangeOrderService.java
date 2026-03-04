package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.repository.ChangeOrdersRepository;
import com.constructionmanager.manager.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.constructionmanager.manager.model.ChangeOrders;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.lang.Integer;

@RequiredArgsConstructor
@Service
public class ChangeOrderService {

    @Autowired
    private ChangeOrdersRepository changeOrdersRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    public List<ChangeOrders> getAllChangeOrders() {return changeOrdersRepository.findAll();}

    public ChangeOrders getChangeOrder(Integer id) {
        return changeOrdersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This change order does not exist."));
    }

    public ChangeOrders createChangeOrder(Integer projectId, ChangeOrders changeOrders) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        changeOrders.setProjects(project);

        return changeOrdersRepository.save(changeOrders);
    }

    public ChangeOrders updateChangeOrder(Integer id, ChangeOrders changeOrderDetails) {
        ChangeOrders changeOrder = changeOrdersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This change order does not exist."));

        if (changeOrderDetails.getNumber() != null) {
            changeOrder.setNumber(changeOrderDetails.getNumber());
        }

        if (changeOrderDetails.getTitle() != null){
            changeOrder.setTitle(changeOrderDetails.getTitle());
        }

        if (changeOrderDetails.getDescription() != null) {
            changeOrder.setDescription(changeOrderDetails.getDescription());
        }

        if (changeOrderDetails.getBreakdown() != null) {
            changeOrder.setBreakdown(changeOrderDetails.getBreakdown());
        }

        if (changeOrderDetails.getPrice() != null) {
            changeOrder.setPrice(changeOrderDetails.getPrice());
        }
        System.out.println(changeOrderDetails.getProjects()+"----------------------");
        if (changeOrderDetails.getProjects() != null) {
            changeOrder.setProjects(changeOrderDetails.getProjects());
        }

        return changeOrdersRepository.save(changeOrder);
    }

    public void deleteChangeOrder(Integer id) {
        if (!changeOrdersRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This change order does not exist.");
        }

        changeOrdersRepository.deleteById(id);
    }



}
