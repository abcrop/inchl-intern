package com.inchl.resourceserver.controller;

import com.inchl.resourceserver.entity.BugModelEntity;
import com.inchl.resourceserver.model.BugModel;
import com.inchl.resourceserver.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BugController {
    @Autowired
    private BugService bugService;

    @PostMapping(value="/createBug", consumes = {"application/json", "application/json;charset=UTF-8"})
    public BugModel createBug(@RequestBody BugModel bugModel) {
        return bugService.createBug(bugModel);
    }

    @GetMapping("/getAllBugs")
    public List<BugModel> getAllBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/getBug/{id}")
    public BugModel getUser(@PathVariable Long id ) {
        return bugService.getBug(id);
    }

    @PutMapping("/updateBug/{id}")
    public BugModel updateBug(@PathVariable Long id, @RequestBody BugModel bugModel) {
        return bugService.updateBug(id, bugModel);
    }

    @DeleteMapping("/deleteBug/{id}")
    public void DeleteBug(@PathVariable Long id){
        bugService.deleteBug(id);
    }
}
