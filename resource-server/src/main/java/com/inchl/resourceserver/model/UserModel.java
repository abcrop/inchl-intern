package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.ActivityModelEntity;
import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.entity.UserModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Each model is what we send and see as a data in the frontend
 */
@Data
@NoArgsConstructor
public class UserModel {
    Long id;
    String fullName;
    String userName;
    String password;
    String email;
    String role;
    String image;
    String userType;
    Long dateCreated;
    Boolean enabled;
    List<BugModelEntity> bugsReported;
    List<BugModelEntity> bugsAssigned;
    List<ActivityModelEntity> activitiesCreated;

    /**
     * All Params
     * @param id
     * @param fullName
     * @param userName
     * @param password
     * @param email
     * @param role
     * @param image
     * @param userType
     * @param dateCreated
     * @param enabled
     * @param bugsReported
     * @param bugsAssigned
     * @param activitiesCreated
     */
    public UserModel(Long id, String fullName, String userName, String password, String email, String role, String image, String userType, Long dateCreated, Boolean enabled, List<BugModelEntity> bugsReported, List<BugModelEntity> bugsAssigned, List<ActivityModelEntity> activitiesCreated) {
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


    /**
     * All Params except @param activitiesCreated
     * @param id
     * @param fullName
     * @param userName
     * @param password
     * @param email
     * @param role
     * @param image
     * @param userType
     * @param dateCreated
     * @param enabled
     * @param bugsReported
     * @param bugsAssigned
     */
    public UserModel(Long id, String fullName, String userName, String password, String email, String role, String image, String userType, Long dateCreated, Boolean enabled, List<BugModelEntity> bugsReported, List<BugModelEntity> bugsAssigned) {
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
    }

    /**
     * Except @bugsReported, @bugsAssignedTo, @activitiesCreated
     * @param id
     * @param fullName
     * @param userName
     * @param password
     * @param email
     * @param role
     * @param image
     * @param userType
     * @param dateCreated
     * @param enabled
     */
    public UserModel(Long id, String fullName, String userName, String password, String email, String role, String image, String userType, Long dateCreated, Boolean enabled) {
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

    public UserModelEntity mapModelToEntity() {
        UserModelEntity en = new UserModelEntity();
        en.setId(id);
        en.setEmail(email);
        en.setFullName(fullName);
        en.setImage(image);
        en.setUserType(userType);
        en.setRole(role);
        en.setEnabled(true);
        en.setDateCreated(dateCreated);
        return en;
    }
}
