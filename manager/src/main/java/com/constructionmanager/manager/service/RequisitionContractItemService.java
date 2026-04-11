package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.repository.ProjectsRepository;
import com.constructionmanager.manager.repository.RequisitionContractItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequisitionContractItemService {

    @Autowired
    private RequisitionContractItemsRepository requisitionContractItemsRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    public List<RequisitionContractItems> getAllRequisitionContractItems() {
        return requisitionContractItemsRepository.findAll();
    }

    public RequisitionContractItems getRequisitionContractItem(Integer id) {
        return requisitionContractItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This Requisition Contract Item does not exist!!!"));
    }

    public List<RequisitionContractItems> getRequisitionContractItemById(Integer projectId) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This Requisition Item does not exist"));
        return project.getRequisitionContractItems();
    }

    public RequisitionContractItems createRequisitionContractItem(Integer projectId,
            RequisitionContractItems requisitionContractItem) {

        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This project does not exist!!!!"));

        requisitionContractItem.setProject(project);

        return requisitionContractItemsRepository.save(requisitionContractItem);
    }

    public RequisitionContractItems updateRequisitionContractItem(Integer projectId, Integer requisitionContractItemId,
            RequisitionContractItems requisitionContractItemDetail) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This project does not exist!!!"));
        RequisitionContractItems requisitionContractItem = requisitionContractItemsRepository
                .findById(requisitionContractItemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "This requisition contract item does not exist!!!"));
        if (requisitionContractItemDetail.getName() != null) {
            requisitionContractItem.setName(requisitionContractItemDetail.getName());
        }
        if (requisitionContractItemDetail.getTotalCost() != null) {
            requisitionContractItem.setTotalCost(requisitionContractItemDetail.getTotalCost());
        }
        if (requisitionContractItemDetail.getRetainage() != null) {
            requisitionContractItem.setRetainage(requisitionContractItemDetail.getRetainage());
        }
        requisitionContractItem.setProject(project);
        return requisitionContractItemsRepository.save(requisitionContractItem);
    }

    public void deleteRequisitionContractItem(Integer id) {
        if (!requisitionContractItemsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition contract item does not exist.");
        }

        requisitionContractItemsRepository.deleteById(id);
    }

}
