package com.inchl.resourceserver.service;

import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.model.BugModel;

import java.util.List;

public interface BugService {
    BugModel createBug(BugModel bugModel);
    List<BugModel> getAllBugs();
    BugModel getBug(Long id);
    BugModel updateBug(Long id, BugModel bugModel);
    void deleteBug(Long id);
}
