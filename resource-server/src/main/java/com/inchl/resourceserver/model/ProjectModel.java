package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.entity.ProjectModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProjectModel {
    Long id;
    String name;
    String description;
    String image;
    Long dateCreated;
    List<BugModel> bugList;
    List<ActivityModel> activityList;

    public ProjectModel(Long id, String name, String description, String image, Long dateCreated, List<BugModel> bugList, List<ActivityModel> activityList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateCreated = dateCreated;
        this.bugList = bugList;
        this.activityList = activityList;
    }

    public ProjectModel(Long id, String name, String description, String image, Long dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateCreated = dateCreated;
    }

    public ProjectModelEntity toEntityWithoutAllFields() {
        return new ProjectModelEntity(
          id,
          name,
          description,
          image,
          dateCreated
        );
    }

//    public ProjectModel toModelFromEntity() {
//        return new ProjectModel(
//                id,
//                name,
//                description,
//                image,
//                dateCreated
//        );
//    }
}
