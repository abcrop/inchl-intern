package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.ActivityModelEntity;
import com.inchl.resourceserver.entity.ProjectModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModel {
    Long id;
    String name;
    String description;
    String image;
    Long dateCreated;

    public ProjectModelEntity toEntity() {
        return new ProjectModelEntity(
          id,
          name,
          description,
          image,
          dateCreated
        );
    }
}
