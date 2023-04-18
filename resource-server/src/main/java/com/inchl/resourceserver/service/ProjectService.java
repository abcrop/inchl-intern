package com.inchl.resourceserver.service;

import com.inchl.resourceserver.model.ProjectModel;

import java.util.List;

public interface ProjectService {
    ProjectModel createProject(ProjectModel projectModel);
    List<ProjectModel> getAllProjects();
    ProjectModel getProject(Long id);
    ProjectModel updateProject(Long id, ProjectModel projectModel);
    void deleteProject(Long id);
}
