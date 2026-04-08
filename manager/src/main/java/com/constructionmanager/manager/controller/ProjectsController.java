package com.constructionmanager.manager.controller;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ChangeOrderService;
import com.constructionmanager.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ChangeOrderService changeOrderService;

    // GET ALL PROJECTS
    @GetMapping
    public List<Projects> getAllProjects() {
        return projectService.getAllProjects();
    }

    // GET PROJECT BY ID
    @GetMapping("/{id}")
    public Projects getProjectById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Projects> createProject(@RequestBody Projects projects) {

        Projects savedProject = projectService.createProject(
                projects.getName(),
                projects.getAddress(),
                projects.getJobType(),
                projects.getDateStarted(),
                projects.getDateFinished(),
                projects.getChangeOrders());
        return ResponseEntity.ok(savedProject);

    }

    @PostMapping("/{projectId}/change-orders")
    @ResponseStatus(HttpStatus.CREATED)
    public ChangeOrders postChangeOrders(@PathVariable Integer projectId, @RequestBody ChangeOrders changeOrders) {
        return changeOrderService.createChangeOrder(projectId, changeOrders);
    }

    @GetMapping("/{projectId}/change-orders")
    public List<ChangeOrders> getAllChangeOrdersAtProject(@PathVariable Integer projectId) {
        return changeOrderService.getChangeOrdersWithId(projectId);
    }

    @PutMapping("/{projectId}/change-orders/{changeOrderId}")
    public ChangeOrders updateChangeOrder(@PathVariable Integer projectId, @PathVariable Integer changeOrderId,
            @RequestBody ChangeOrders changeOrderDetails) {
        return changeOrderService.updateChangeOrder(projectId, changeOrderId, changeOrderDetails);
    }

    // Update a Company
    @PutMapping("/{id}")
    public Projects updateProject(@PathVariable Integer id, @RequestBody Projects projectsDetails) {
        return projectService.updateProject(id, projectsDetails);
    }

    // DELETE PROJECT
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
