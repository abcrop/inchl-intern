package com.inchl.resourceserver.model;

import com.inchl.resourceserver.entity.ProjectModelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseModel {
    ProjectModel project;
    int openBugs;
    int closedBugs;
    int criticalBugs;
    int majorBugs;
    int mediumBugs;
    int minorBugs;
    int trivialBugs;
}
