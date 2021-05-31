package com.iordache.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class DemoController {

    @GetMapping("/xxx")
    @PreAuthorize("hasRole('ADMIN')")
    public String demo(){
        return "merge";
    }
}
