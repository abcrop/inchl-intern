package com.inchl.resourceserver.entity;

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

    @ManyToOne(cascade = CascadeType.ALL)
    UserModelEntity reporter;

    @ManyToOne(cascade = CascadeType.ALL)
    UserModelEntity assignedTo;

    @OneToMany(mappedBy = "bug")
    List<ActivityModelEntity> activitiesCreated;

    String appVersion;
    String screenshot;

    Long dateCreated;

    public BugModelEntity(Long id, String title, String description, String logcat, String bugStatus, String bugFlag, UserModelEntity reporter, UserModelEntity assignedTo, List<ActivityModelEntity> activitiesCreated, String appVersion, String screenshot, Long dateCreated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logcat = logcat;
        this.bugStatus = bugStatus;
        this.bugFlag = bugFlag;
        this.reporter = reporter;
        this.assignedTo = assignedTo;
        this.activitiesCreated = activitiesCreated;
        this.appVersion = appVersion;
        this.screenshot = screenshot;
        this.dateCreated = dateCreated;
    }

    public BugModelEntity(Long id, String title, String description, String logcat, String bugStatus, String bugFlag, UserModelEntity reporter, UserModelEntity assignedTo, String appVersion, String screenshot, Long dateCreated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.logcat = logcat;
        this.bugStatus = bugStatus;
        this.bugFlag = bugFlag;
        this.reporter = reporter;
        this.assignedTo = assignedTo;
        this.appVersion = appVersion;
        this.screenshot = screenshot;
        this.dateCreated = dateCreated;
    }
}

