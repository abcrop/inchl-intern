package com.inchl.resourceserver.model;

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
}
