package com.inchl.resourceserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.inchl.resourceserver.model.BugModel;
import com.inchl.resourceserver.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "bugs")
@Data
@NoArgsConstructor
public class BugModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bug_id")
    Long id;
    String title;
    String description;
    String logcat;
    String bugStatus;
    String bugFlag;

    /**
     * In child entity / Where foreign_Key is stored There we put @JsonBackReference
     * What is does is that, it tells the JPA to stop retrieving data more than one time, to avoid infinite loop.
     */

    /**
     * Without add JsonBackReference, When fetching Users, it has to fetch AllBugs Reported by that user,
     * While retrieving reporter, it will again fetch for 'bug' field and then 'reporter'...it goes on infinitely.
     * Backward Reference, this force to omit the serialization (thus reporter, assignedTo won't be seen in the List<Bug> in the UserEntity)</Bug>
     */
    @ManyToOne()
    @JsonBackReference(value = "reporterRef")
    UserModelEntity reporter;

    @ManyToOne()
    @JsonBackReference(value = "assignedToRef")
    UserModelEntity assignedTo;

    @ManyToOne()
    @JsonBackReference(value = "pBugRef")
    ProjectModelEntity project;

//    @OneToMany(mappedBy = "bug",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "bug",  fetch = FetchType.LAZY)
    @JsonManagedReference(value = "bug_ref")
    List<ActivityModelEntity> activities;

    String appVersion;
    String screenshot;

    Long dateCreated;

    public BugModelEntity(Long id, String title, String description, String logcat, String bugStatus, String bugFlag, ProjectModelEntity project, UserModelEntity reporter, UserModelEntity assignedTo, List<ActivityModelEntity> activities, String appVersion, String screenshot, Long dateCreated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logcat = logcat;
        this.project = project;
        this.bugStatus = bugStatus;
        this.bugFlag = bugFlag;
        this.reporter = reporter;
        this.assignedTo = assignedTo;
        this.appVersion = appVersion;
        this.screenshot = screenshot;
        this.activities = activities;
        this.dateCreated = dateCreated;
    }

    public BugModelEntity(Long id, String title, String description, String logcat,  ProjectModelEntity project, String bugStatus, String bugFlag, UserModelEntity reporter, UserModelEntity assignedTo, String appVersion, String screenshot, Long dateCreated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logcat = logcat;
        this.project = project;
        this.bugStatus = bugStatus;
        this.bugFlag = bugFlag;
        this.reporter = reporter;
        this.assignedTo = assignedTo;
        this.appVersion = appVersion;
        this.screenshot = screenshot;
        this.dateCreated = dateCreated;
    }


    public BugModel toBugModelWithAllData() {
        return new BugModel(
                id,
                title,
                description,
                logcat,
                bugStatus,
                bugFlag,
                project.toProjectModelWithoutAllFields(),
                reporter.toUserModelWithoutAllData(),
                assignedTo.toUserModelWithoutAllData(),
                appVersion,
                screenshot,
                dateCreated
        );
    }
}

