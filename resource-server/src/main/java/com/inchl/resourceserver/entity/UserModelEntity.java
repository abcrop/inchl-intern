package com.inchl.resourceserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.inchl.resourceserver.util.Constants.USER_EMAIL_UNIQUE_CONSTRAINT;
import static com.inchl.resourceserver.util.Constants.USER_USERNAME_UNIQUE_CONSTRAINT;

@Entity(name = "users")
@Data
@Table(name = "users", uniqueConstraints = {
        //White adding @UniqueConstraint manually remove unique=true from the @Column
        @UniqueConstraint(name = USER_EMAIL_UNIQUE_CONSTRAINT, columnNames = {"email"}),
        @UniqueConstraint(name = USER_USERNAME_UNIQUE_CONSTRAINT, columnNames = { "user_name" } )
})
@NoArgsConstructor
public class UserModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    Long id;
    String fullName;
    @Column(name="user_name", nullable = false)
    String userName;
    @Column(name="password", nullable = false, length = 60)
    String password;
    @Column(name="email", nullable = false)
    String email;
    String role;
    String image;
    @Column(nullable = false)
    String userType;
    Long dateCreated;
    Boolean enabled = true;

    //MappedBy: It is used for @oneToMany only, that determines foreign key for child table
    //i.e "reporter" is used as a field name for the Bug Table
    @OneToMany(mappedBy = "reporter")
    List<BugModelEntity> bugsReported;

    @OneToMany(mappedBy = "assignedTo")
    List<BugModelEntity> bugsAssigned;

    @OneToMany(mappedBy = "user")
    List<ActivityModelEntity> activitiesCreated;

    public UserModelEntity(Long id, String fullName, String userName, String password, String email, String role, String image, String userType, Long dateCreated, Boolean enabled, List<BugModelEntity> bugsReported, List<BugModelEntity> bugsAssigned, List<ActivityModelEntity> activitiesCreated) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.image = image;
        this.userType = userType;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.bugsReported = bugsReported;
        this.bugsAssigned = bugsAssigned;
        this.activitiesCreated = activitiesCreated;
    }

    public UserModelEntity(Long id, String fullName, String userName, String password, String email, String role, String image, String userType, Long dateCreated, Boolean enabled) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.image = image;
        this.userType = userType;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
    }
}
