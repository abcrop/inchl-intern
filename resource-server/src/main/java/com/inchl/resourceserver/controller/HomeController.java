package com.inchl.resourceserver.controller;

import com.inchl.resourceserver.impl.HomeServiceImpl;
import com.inchl.resourceserver.model.HomeModel;
import com.inchl.resourceserver.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/getHomeData")
    public HomeModel getHomeData(){
        return homeService.getHomeData();
    }
}
