package com.inchl.resourceserver.impl;

import com.inchl.resourceserver.entity.ProjectModelEntity;
import com.inchl.resourceserver.exception.BadRequestException;
import com.inchl.resourceserver.exception.NotFoundExceptions;
import com.inchl.resourceserver.model.ProjectModel;
import com.inchl.resourceserver.model.ProjectResponseModel;
import com.inchl.resourceserver.repository.BugRepository;
import com.inchl.resourceserver.repository.ProjectRepository;
import com.inchl.resourceserver.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.inchl.resourceserver.util.Constants.*;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectModel createProject(ProjectModel projectModel) {
        ProjectModelEntity projectModelEntity = projectRepository.save(projectModel.toEntityWithoutAllFields());
        return projectModelEntity.toProjectModelWithoutAllFields();
    }

    @Override
    public List<ProjectModel> getAllProjects() {
        return projectRepository.findAll().stream().map(
                ProjectModelEntity::toProjectModelWithAllFields
        ).collect(Collectors.toList());
    }

    @Override
    public ProjectModel getProject(Long id) {
        ProjectModelEntity projectModelEntity = projectRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundExceptions.IdNotFoundException(id);
        });
        return projectModelEntity.toProjectModelWithAllFields();
    }

    @Override
    public ProjectModel updateProject(Long id, ProjectModel projectModel) {
        ProjectModelEntity updatedProjectEntity = projectRepository.findById(id).map(project -> {
            try {
                if (projectModel.getName() != null)
                    project.setName(projectModel.getName());

                if (projectModel.getDescription() != null)
                    project.setDescription(projectModel.getDescription());

                if (projectModel.getImage() != null)
                    project.setImage(projectModel.getImage());
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
            return projectRepository.save(project);
        }).orElseThrow(() -> new NotFoundExceptions.IdNotFoundException(id));

        return updatedProjectEntity.toProjectModelWithoutAllFields();
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundExceptions.IdNotFoundException(id);
        });
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectResponseModel> getAllProjectDataResponse() {
        List<ProjectResponseModel> projectResponseModelList = new ArrayList<ProjectResponseModel>();
        List<ProjectModel> projectList = getAllProjects();

        projectList.forEach(projectModel -> {
            ProjectResponseModel projectResponseModel = new ProjectResponseModel();
            projectResponseModel.setProject(projectModel);
            projectModel.getBugList().forEach(bugModel -> {
                if (bugModel.getBugStatus().equalsIgnoreCase(ONGOING)) {
                    projectResponseModel.setOpenBugs(projectResponseModel.getOpenBugs() + 1);
                } else if (bugModel.getBugStatus().equalsIgnoreCase(DONE) || bugModel.getBugStatus().equalsIgnoreCase(CANCEL)) {
                    projectResponseModel.setClosedBugs(projectResponseModel.getClosedBugs() + 1);
                }


                if(bugModel.getBugFlag().equalsIgnoreCase(CRITICAL)) {
                    projectResponseModel.setCriticalBugs(projectResponseModel.getCriticalBugs() + 1);
                } else if(bugModel.getBugFlag().equalsIgnoreCase(MAJOR)) {
                    projectResponseModel.setMajorBugs(projectResponseModel.getMajorBugs() + 1);
                }  else if(bugModel.getBugFlag().equalsIgnoreCase(MEDIUM)) {
                    projectResponseModel.setMediumBugs(projectResponseModel.getMediumBugs() + 1);
                }  else if(bugModel.getBugFlag().equalsIgnoreCase(MINOR)) {
                    projectResponseModel.setMinorBugs(projectResponseModel.getMinorBugs() + 1);
                } else if(bugModel.getBugFlag().equalsIgnoreCase(TRIVIAL)) {
                    projectResponseModel.setTrivialBugs(projectResponseModel.getTrivialBugs() + 1);
                }

            });
            projectResponseModelList.add(projectResponseModel);
        });

        return projectResponseModelList;
    }
}
