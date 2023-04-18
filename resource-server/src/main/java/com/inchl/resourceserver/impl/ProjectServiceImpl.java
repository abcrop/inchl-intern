package com.inchl.resourceserver.impl;

import com.inchl.resourceserver.entity.ProjectModelEntity;
import com.inchl.resourceserver.exception.BadRequestException;
import com.inchl.resourceserver.exception.NotFoundExceptions;
import com.inchl.resourceserver.model.ProjectModel;
import com.inchl.resourceserver.repository.ProjectRepository;
import com.inchl.resourceserver.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public ProjectModel createProject(ProjectModel projectModel) {
        projectRepository.save(projectModel.toEntity());
        return projectModel;
    }

    @Override
    public List<ProjectModel> getAllProjects() {
        return projectRepository.findAll().stream().map(
                ProjectModelEntity::toProjectModelResponse
        ).collect(Collectors.toList());
    }

    @Override
    public ProjectModel getProject(Long id) {
        ProjectModelEntity projectModelEntity = projectRepository.findById(id).orElseThrow(()->{
            throw new NotFoundExceptions.IdNotFoundException(id);
        });
        return projectModelEntity.toProjectModelResponse();
    }

    @Override
    public ProjectModel updateProject(Long id, ProjectModel projectModel) {
        ProjectModelEntity updatedProjectEntity = projectRepository.findById(id).map(project->{
            try{
                project.setName(projectModel.getName());
                project.setDescription(projectModel.getDescription());
                project.setImage(projectModel.getImage());
            }catch (Exception e){
                throw new BadRequestException(e.getMessage());
            }
            return projectRepository.save(project);
        }).orElseThrow(()-> new NotFoundExceptions.IdNotFoundException(id));

        return updatedProjectEntity.toProjectModelResponse();
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.findById(id).orElseThrow(()->{
            throw  new NotFoundExceptions.IdNotFoundException(id);
        });
        projectRepository.deleteById(id);
    }
}
