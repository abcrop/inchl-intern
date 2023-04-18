package com.inchl.resourceserver.entity;

import com.inchl.resourceserver.model.ProjectModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    List<ActivityModelEntity> activitiesCreated;

    public ProjectModelEntity(Long id, String name, String description, String image, Long dateCreated, List<ActivityModelEntity> activitiesCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateCreated = dateCreated;
        this.activitiesCreated = activitiesCreated;
    }

    public ProjectModelEntity(Long id, String name, String description, String image, Long dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.dateCreated = dateCreated;
    }

    public ProjectModel toProjectModelResponse() {
        return new ProjectModel(
                id,
                name,
                description,
                image,
                dateCreated
        );
    }
}
