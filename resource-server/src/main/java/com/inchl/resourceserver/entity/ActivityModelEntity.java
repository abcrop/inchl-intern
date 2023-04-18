package com.inchl.resourceserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.inchl.resourceserver.model.ActivityModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
public class ActivityModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="activity_id")
    Long id;

    //Fetch Type: Is by default Lazy(means when we call activity.User only user is fetched)
    //Cascade: It means when changes in user occurs same change will reflect on activity too.
    //JoinColumn: Helps to remain joined_column_name, and point to the foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_ref")
    UserModelEntity user;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    @JsonBackReference(value = "project_ref")
    ProjectModelEntity project;

    @ManyToOne()
    @JoinColumn(name="bug_id")
    @JsonBackReference(value = "bug_ref")
    BugModelEntity bug;

    String activityType;

    Long dateCreated;

    public ActivityModelEntity(Long id, UserModelEntity user, ProjectModelEntity project, BugModelEntity bug, String activityType,  Long dateCreated) {
        this.id = id;
        this.user = user;
        this.project = project;
        this.bug = bug;
        this.activityType = activityType;
        this.dateCreated = dateCreated;
    }

    public ActivityModel toActivityModelWithAllData(){
        return new ActivityModel(
                id,
                user.toUserModelWithoutAllData(),
                project.toProjectModelResponse(),
                bug.toBugModelWithAllData(),
                activityType,
                dateCreated
        );
    }

}
