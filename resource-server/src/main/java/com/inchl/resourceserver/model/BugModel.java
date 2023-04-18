package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.entity.UserModelEntity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BugModel {
    Long id;
    String title;
    String description;
    String logcat;
    String bugStatus;
    String bugFlag;
    UserModel reporter;
    UserModel assignedTo;
    String appVersion;
    String screenshot;
    Long dateCreated;

    public BugModelEntity toEntity(){
        return new BugModelEntity(
                id,
                title,
                description,
                logcat,
                bugStatus,
                bugFlag,
                reporter.mapModelToEntity(),
                assignedTo.mapModelToEntity(),
                appVersion,
                screenshot,
                dateCreated
        );
    }


}
