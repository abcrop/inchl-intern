package com.inchl.resourceserver.impl;

import com.inchl.resourceserver.entity.ActivityModelEntity;
import com.inchl.resourceserver.entity.UserModelEntity;
import com.inchl.resourceserver.exception.UserNotFoundException;
import com.inchl.resourceserver.model.ActivityModel;
import com.inchl.resourceserver.model.HomeModel;
import com.inchl.resourceserver.model.UserModel;
import com.inchl.resourceserver.repository.ActivityRepository;
import com.inchl.resourceserver.repository.BugRepository;
import com.inchl.resourceserver.repository.ProjectRepository;
import com.inchl.resourceserver.repository.UserRepository;
import com.inchl.resourceserver.service.HomeService;
import com.inchl.resourceserver.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.inchl.resourceserver.util.Constants.*;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Autowired
    BugRepository bugRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public HomeModel getHomeData() {
        HomeModel homeModel = new HomeModel();

        homeModel.setTotalApps(projectRepository.findAll().toArray().length);

        userRepository.findAll().stream().forEach(userModelEntity -> {
            if(userModelEntity.getUserType().equalsIgnoreCase(DEVELOPER)) {
                LOGGER.info("print developers " + homeModel.getTotalDevelopers());
                homeModel.setTotalDevelopers(homeModel.getTotalDevelopers() + 1);
            } else if(userModelEntity.getUserType().equalsIgnoreCase(TESTER)) {
                homeModel.setTotalTesters(homeModel.getTotalTesters() + 1);
            }
            LOGGER.info("print get total develoopers: " + homeModel.getTotalDevelopers());
            LOGGER.info("print user matches dev: " + userModelEntity.getUserType().equalsIgnoreCase(DEVELOPER));
        });

        bugRepository.findAll().stream().forEach(bugModelEntity -> {
            homeModel.setTotalBugs(homeModel.getTotalBugs() + 1);


            if(bugModelEntity.getBugStatus().equalsIgnoreCase(ONGOING)) {
                homeModel.setOpenBugs(homeModel.getOpenBugs() + 1);
            } else if(bugModelEntity.getBugStatus().equalsIgnoreCase(DONE) || bugModelEntity.getBugStatus().equalsIgnoreCase(CANCEL)) {
                homeModel.setClosedBugs(homeModel.getClosedBugs() + 1);
            }

            if(bugModelEntity.getBugFlag().equalsIgnoreCase(CRITICAL)) {
                homeModel.setCriticalBugs(homeModel.getCriticalBugs() + 1);
            } else if(bugModelEntity.getBugFlag().equalsIgnoreCase(MAJOR)) {
                homeModel.setMajorBugs(homeModel.getMajorBugs() + 1);
            }
        });

        homeModel.setActivityList(activityRepository.findAll().stream().map(ActivityModelEntity::toActivityModelWithAllData).collect(Collectors.toList()));

        return homeModel;
    }
}
