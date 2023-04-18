package com.inchl.resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeModel {

    int totalApps;
    int totalDevelopers;
    int totalTesters;

    int totalBugs;
    int openBugs;
    int closedBugs;
    int criticalBugs;
    int majorBugs;

    List<ActivityModel> activityList;
}
