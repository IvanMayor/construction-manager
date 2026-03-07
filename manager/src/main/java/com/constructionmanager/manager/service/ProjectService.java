package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectsRepository projectsRepository;

    public List<Projects> getAllProjects() {return projectsRepository.findAll();}

    public Projects createProject(
            String name,
            String address,
            Projects.JobType jobType,
            LocalDate dateStarted,
            LocalDate dateFinished,
            List<ChangeOrders> changeOrders) {
        Projects projects = new Projects();
        projects.setName(name);
        projects.setAddress(address);
        projects.setJobType(jobType);
        projects.setDateStarted(dateStarted);
        projects.setDateFinished(dateFinished);
        projects.setChangeOrders(changeOrders);

        return projectsRepository.save(projects);
    }

    public Projects updateProject(Integer id, Projects projectsDetail) {
        Projects projects = projectsRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with this id does not exist"));

        //TODO: BeanUtils.copyProperties... implement getNullPropertyNames for ignore argument in copy property.
        if (projectsDetail.getName() != null) {
            projects.setName(projectsDetail.getName());
        }
        if (projectsDetail.getAddress() != null) {
            projects.setAddress(projectsDetail.getAddress());
        }
        if (projectsDetail.getJobType() != null) {
            projects.setJobType(projectsDetail.getJobType());
        }
        if (projectsDetail.getDateStarted() != null) {
            projects.setDateStarted(projectsDetail.getDateStarted());
        }
        if (projectsDetail.getDateFinished() != null) {
            projects.setDateFinished(projectsDetail.getDateFinished());
        }
        if (projectsDetail.getChangeOrders() != null) {
            for (ChangeOrders changeOrder : projectsDetail.getChangeOrders()) {
                projects.addChangeOrder(changeOrder);
            }
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This Project does not exist"));
    }



}
