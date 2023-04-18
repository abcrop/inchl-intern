package com.inchl.resourceserver.service;


import com.inchl.resourceserver.model.ActivityModel;

import java.util.List;

public interface ActivityService {
    ActivityModel createActivity(ActivityModel activityModel);
    ActivityModel updateActivity(Long id, ActivityModel activityModel);
    void deleteActivity(Long id);
    ActivityModel getActivity(Long id);
    List<ActivityModel> getAllActivities();
}
