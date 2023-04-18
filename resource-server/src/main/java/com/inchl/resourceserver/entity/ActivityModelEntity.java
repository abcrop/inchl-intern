package com.inchl.resourceserver.entity;

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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinColumn(name = "user_id")
    UserModelEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "project_id")
    ProjectModelEntity project;

    @ManyToOne(cascade = CascadeType.ALL)
            @JoinColumn(name="bug_id")
    BugModelEntity bug;

    Long dateCreated;

    public ActivityModelEntity(Long id, UserModelEntity user, ProjectModelEntity project, BugModelEntity bug, Long dateCreated) {
        this.id = id;
        this.user = user;
        this.project = project;
        this.bug = bug;
        this.dateCreated = dateCreated;
    }

}
