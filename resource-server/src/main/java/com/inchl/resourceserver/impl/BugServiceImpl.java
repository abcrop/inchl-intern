package com.inchl.resourceserver.impl;

import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.exception.BadRequestException;
import com.inchl.resourceserver.exception.NotFoundExceptions;
import com.inchl.resourceserver.model.BugModel;
import com.inchl.resourceserver.repository.BugRepository;
import com.inchl.resourceserver.service.BugService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BugServiceImpl implements BugService {

    @Autowired
    private BugRepository bugRepository;


    @Override
    public BugModel createBug(BugModel bugModel) {
        BugModelEntity bugModelEntity = bugRepository.save(bugModel.toEntity());
        return bugModelEntity.toBugModelWithAllData();
    }

    @Override
    public List<BugModel> getAllBugs() {
        return bugRepository.findAll().stream().map(
                BugModelEntity::toBugModelWithAllData
        ).collect(Collectors.toList());
    }

    @Override
    public BugModel getBug(Long id) {
        BugModelEntity bugModelEntity = bugRepository.findById(id).orElseThrow(()->{
            throw new NotFoundExceptions.IdNotFoundException(id);
        });
        return bugModelEntity.toBugModelWithAllData();
    }

    @Override
    public BugModel updateBug(Long id, BugModel bugModel) {
        BugModelEntity updatedBugEntity = bugRepository.findById(id).map(bugModelEntity -> {
           try{
               if(bugModel.getTitle() != null)
                bugModelEntity.setTitle(bugModel.getTitle());

               if(bugModel.getDescription() != null)
                   bugModelEntity.setDescription(bugModel.getDescription());

               if(bugModel.getLogcat() != null)
                   bugModelEntity.setLogcat(bugModel.getLogcat());

               if(bugModel.getBugStatus() != null)
                   bugModelEntity.setBugStatus(bugModel.getBugStatus());

               if(bugModel.getBugFlag() != null)
                   bugModelEntity.setBugFlag(bugModel.getBugFlag());

               if(bugModel.getReporter() != null)
                   bugModelEntity.setReporter(bugModel.getReporter().mapModelToEntity());

               if(bugModel.getProject() != null)
                   bugModelEntity.setProject(bugModel.getProject().toEntityWithoutAllFields());

               if(bugModel.getAssignedTo() != null)
                   bugModelEntity.setAssignedTo(bugModel.getAssignedTo().mapModelToEntity());

               if(bugModel.getAppVersion() != null)
                   bugModelEntity.setAppVersion(bugModel.getAppVersion());

               if(bugModel.getScreenshot() != null)
                   bugModelEntity.setScreenshot(bugModel.getScreenshot());
           }catch (Exception e) {
               throw  new BadRequestException(e.getMessage());
           }
           return bugRepository.save(bugModelEntity);
        }).orElseThrow(()->{
            throw new NotFoundExceptions.IdNotFoundException(id);
        });
        return updatedBugEntity.toBugModelWithAllData();
    }

    @Override
    public void deleteBug(Long id) {
        bugRepository.findById(id).orElseThrow(()-> {
            throw new NotFoundExceptions.IdNotFoundException(id);
        });
        bugRepository.deleteById(id);
    }

}
