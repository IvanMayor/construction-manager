package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectsRepository projectsRepository;

    //Home Page
    @GetMapping("/")
    public String welcome() {
        return "<html><body><h1>WELCOME</h1></body></html>";
    }

    //GET ALL PROJECTS
    @GetMapping
    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    //GET PROJECT BY ID
    @GetMapping("/{id}")
    public Projects getProjectById(@PathVariable Integer id) {
        return projectsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Projects createProject(@RequestBody Projects projects) {
        return projectsRepository.save(projects);
    }

    //Update a Company
    @PutMapping("/{id}")
    public Projects updateProject(@PathVariable Integer id, @RequestBody Projects projectsDetails) {
        Projects project = projectsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));

        project.setName(projectsDetails.getName());
        project.setAddress(projectsDetails.getAddress());
        project.setJobType(projectsDetails.getJobType());
        project.setDateStarted(projectsDetails.getDateStarted());
        project.setDateFinished(projectsDetails.getDateFinished());

        return projectsRepository.save(project);
    }

    //DELETE PROJECT
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Integer id) {
        if (!projectsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found");
        }
        projectsRepository.deleteById(id);
    }
}
