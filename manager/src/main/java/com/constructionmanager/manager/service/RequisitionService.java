package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.ProjectsRepository;
import com.constructionmanager.manager.repository.RequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequisitionService {

    @Autowired
    private RequisitionsRepository requisitionsRepository;

    @Autowired
    private ProjectsRepository projectsRepository;

    public List<Requisitions> getAllRequisitions() {
        return requisitionsRepository.findAll();
    }

    public Requisitions getRequisition(Integer id) {
        return requisitionsRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition does not exist"));
    }

    public Requisitions createRequisition(Integer projectId, Requisitions requisitions) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project with this ID does not exist"));
        requisitions.setProject(project);
        return requisitionsRepository.save(requisitions);
    }

    public Requisitions updateRequisition(Integer projectId, Integer requisitionId, Requisitions requisitionDetail) {

        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This project does not exist."));

        Requisitions requisitions = requisitionsRepository.findById(requisitionId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Requisition does not exist"));

        // TODO: BeanUtils.copyProperties... implement getNullPropertyNames for ignore
        // argument in copy property.
        if (requisitionDetail.getContractPrice() != null) {
            requisitions.setContractPrice(requisitionDetail.getContractPrice());
        }

        if (requisitionDetail.getCompanyName() != null) {
            requisitions.setCompanyName(requisitionDetail.getCompanyName());
        }

        if (requisitionDetail.getOwnerOrRepresentativeFullName() != null) {
            requisitions.setOwnerOrRepresentativeFullName(requisitionDetail.getOwnerOrRepresentativeFullName());
        }

        requisitions.setProject(project);

        return requisitionsRepository.save(requisitions);
    }

    public void deleteRequisition(Integer id) {
        if (requisitionsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This requisition does not exist");
        }
        requisitionsRepository.deleteById(id);
    }

}
