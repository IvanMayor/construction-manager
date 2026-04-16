package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.repository.ProjectsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectsRepository projectsRepository;

    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    public Projects createProject(
            String name,
            String address,
            Projects.JobType jobType,
            LocalDate dateStarted,
            LocalDate dateFinished,
            List<ChangeOrders> changeOrders) {

        Projects projects = new Projects();

        projects.setName(name != null ? name : null);
        projects.setAddress(address != null ? address : null);
        projects.setJobType(jobType != null ? jobType : null);
        projects.setDateStarted(dateStarted != null ? dateStarted : null);
        projects.setDateFinished(dateFinished != null ? dateFinished : null);
        projects.setChangeOrders(changeOrders != null ? changeOrders : null);

        return projectsRepository.save(projects);
    }

    @Transactional
    public Projects updateProject(Integer id, Projects projectsDetail) {
        Projects projects = projectsRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with this id does not exist"));

        if (projectsDetail.getName() != null) {
            projects.setName(projectsDetail.getName());
        }
        if (projectsDetail.getAddress() != null) {
            projects.setAddress(projectsDetail.getAddress());
        }
        if (projectsDetail.getJobType() != null) {
            projects.setJobType(projectsDetail.getJobType());
        }
        if (projectsDetail.getTotalContract() != null) {
            projects.setTotalContract(projectsDetail.getTotalContract());
        }
        if (projectsDetail.getDateStarted() != null) {
            projects.setDateStarted(projectsDetail.getDateStarted());
        }
        if (projectsDetail.getDateFinished() != null) {
            projects.setDateFinished(projectsDetail.getDateFinished());
        }

        return projectsRepository.save(projects);
    }

    public void deleteProject(Integer id) {
        if (!projectsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        projectsRepository.deleteById(id);
    }

    public Optional<Projects> findByAddressContaining(String address) {
        return projectsRepository.findByAddressContaining(address);
    }

    public Projects getProjectById(Integer id) {
        return projectsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "This Project does not exist"));
    }

    public List<ChangeOrders> getProjectChangeOrders(Integer projectId) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "This Project does not exist"));

        return project.getChangeOrders();
    }

    public BigDecimal getTotalChangeOrderAmount(Integer projectId) {
        BigDecimal totalPrice = new BigDecimal(0);
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Project does not exist!!!"));
        List<ChangeOrders> changeOrders = project.getChangeOrders();
        for (ChangeOrders changeOrder : changeOrders) {
            totalPrice = totalPrice.add(changeOrder.getPrice());
        }
        return totalPrice;
    }

    public BigDecimal getTotalChangeOrderAndOriginalContract(Integer projectId) {
        Projects project = projectsRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This project does not exist"));
        BigDecimal totalChangeOrderAmount = getTotalChangeOrderAmount(projectId);
        return project.getTotalContract().add(totalChangeOrderAmount);
    }
}
