package com.inchl.resourceserver.controller;

import com.inchl.resourceserver.model.ProjectModel;
import com.inchl.resourceserver.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/createProject")
    public ProjectModel createProject(@RequestBody ProjectModel project) {
        return projectService.createProject(project);
    }

    @GetMapping("/getAllProjects")
    public List<ProjectModel> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PutMapping("/updateProject/{id}")
    public ProjectModel updateProject(@PathVariable Long id, @RequestBody ProjectModel projectModel){
        return projectService.updateProject(id, projectModel);
    }

    @GetMapping("/getProject/{id}")
    public ProjectModel getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @DeleteMapping("/deleteProject/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
