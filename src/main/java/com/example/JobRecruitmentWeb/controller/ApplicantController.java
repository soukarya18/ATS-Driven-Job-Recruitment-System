package com.example.JobRecruitmentWeb.controller;

import com.example.JobRecruitmentWeb.model.Applicant;
import com.example.JobRecruitmentWeb.model.Job;
import com.example.JobRecruitmentWeb.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("applicant")
public class ApplicantController {
    @Autowired
    ApplicantService service ;
    @PostMapping("register/{jobId}")
    public String register(@RequestBody Applicant applicant , @PathVariable int jobId){
        return service.registerApplicant(applicant , jobId) ;
    }
    @GetMapping("view")
    public List<Job> viewAllJobs(){
        return service.viewAllJobs() ;
    }
    @DeleteMapping("withdraw")
    public String withdrawApplication(@RequestParam int applicantId){
        return service.removeApplicant(applicantId) ;
    }
}
