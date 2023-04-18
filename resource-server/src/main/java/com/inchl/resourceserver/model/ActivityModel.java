package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.ActivityModelEntity;
import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.entity.ProjectModelEntity;
import com.inchl.resourceserver.entity.UserModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityModel {
    Long id;
    UserModel user;
    ProjectModel project;
    BugModel bug;
    String activityType;
    Long dateCreated;

    public ActivityModelEntity toEntity() {
        return new ActivityModelEntity(
                id,
                user.mapModelToEntity(),
                project.toEntity(),
                bug.toEntity(),
                activityType,
                dateCreated
        );
    }
}
