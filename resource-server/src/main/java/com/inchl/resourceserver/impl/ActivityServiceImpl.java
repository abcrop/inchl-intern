package com.inchl.resourceserver.impl;

import com.inchl.resourceserver.entity.ActivityModelEntity;
import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.exception.BadRequestException;
import com.inchl.resourceserver.exception.NotFoundExceptions;
import com.inchl.resourceserver.model.ActivityModel;
import com.inchl.resourceserver.model.BugModel;
import com.inchl.resourceserver.repository.ActivityRepository;
import com.inchl.resourceserver.service.ActivityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;


    @Override
    public ActivityModel createActivity(ActivityModel activityModel) {
        activityRepository.save(activityModel.toEntity());
        return activityModel;
    }

    @Override
    public ActivityModel updateActivity(Long id, ActivityModel activityModel) {
        ActivityModelEntity updatedActivityEntity = activityRepository.findById(id).map(activityEntity -> {
            try{
                if(activityModel.getUser() != null)
                    activityEntity.setUser(activityModel.getUser().mapModelToEntity());

                if(activityModel.getProject() != null)
                    activityEntity.setProject(activityModel.getProject().toEntity());

                if(activityModel.getBug() != null)
                    activityEntity.setBug(activityModel.getBug().toEntity());

                if(activityModel.getActivityType() != null)
                    activityEntity.setActivityType(activityModel.getActivityType());

            }catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
            return activityEntity;
        }).orElseThrow(()-> new NotFoundExceptions.IdNotFoundException(id));

        return updatedActivityEntity.toActivityModelWithAllData();
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.findById(id).orElseThrow(()->new NotFoundExceptions.IdNotFoundException(id));
        activityRepository.deleteById(id);
    }

    @Override
    public ActivityModel getActivity(Long id) {
        ActivityModelEntity activityModelEntity = activityRepository.findById(id).orElseThrow(()->
                 new NotFoundExceptions.IdNotFoundException(id)
        );
        return activityModelEntity.toActivityModelWithAllData();
    }

    @Override
    public List<ActivityModel> getAllActivities() {
        return activityRepository.findAll().stream().map(
                ActivityModelEntity::toActivityModelWithAllData
        ).collect(Collectors.toList());
    }
}
