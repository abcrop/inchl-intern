package com.inchl.resourceserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.inchl.resourceserver.model.ProjectModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class ProjectModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="project_id")
    Long id;
    String name;
    String description;
    String image;
    Long dateCreated;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value="pBugRef")
    List<BugModelEntity> bugs;

    @OneToMany(mappedBy = "project",  fetch = FetchType.LAZY)
    @JsonManagedReference(value = "project_ref")
    List<ActivityModelEntity> activities;

    public ProjectModelEntity(Long id, String name, String description, String image, Long dateCreated, List<BugModelEntity> bugs, List<ActivityModelEntity> activities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateCreated = dateCreated;
        this.bugs = bugs;
        this.activities = activities;
    }

    public ProjectModelEntity(Long id, String name, String description, String image, Long dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateCreated = dateCreated;
    }

    public ProjectModel toProjectModelWithoutAllFields() {
        return new ProjectModel(
                id,
                name,
                description,
                image,
                dateCreated
        );
    }

    public ProjectModel toProjectModelWithAllFields() {
        return new ProjectModel(
                id,
                name,
                description,
                image,
                dateCreated,
                bugs.stream().map(BugModelEntity::toBugModelWithAllData).collect(Collectors.toList()),
                activities.stream().map(ActivityModelEntity::toActivityModelWithAllData).collect(Collectors.toList())
        );
    }
}
