package com.inchl.resourceserver.controller;

import com.inchl.resourceserver.model.ActivityModel;
import com.inchl.resourceserver.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping("/createActivity")
    public ActivityModel createActivity(@RequestBody ActivityModel activity) {
        return activityService.createActivity(activity);
    }

    @GetMapping("/getAllActivities")
    public List<ActivityModel> getAllActivities(){
        return activityService.getAllActivities();
    }

    @PutMapping("/updateActivity/{id}")
    public ActivityModel updateActivity(@PathVariable Long id, @RequestBody ActivityModel activityModel){
        return activityService.updateActivity(id, activityModel);
    }

    @GetMapping("/getActivity/{id}")
    public ActivityModel getActivity(@PathVariable Long id) {
        return activityService.getActivity(id);
    }

    @DeleteMapping("/deleteActivity/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
