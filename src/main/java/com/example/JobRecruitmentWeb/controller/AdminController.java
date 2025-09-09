package com.example.JobRecruitmentWeb.controller;

import com.example.JobRecruitmentWeb.model.Applicant;
import com.example.JobRecruitmentWeb.model.Job;
import com.example.JobRecruitmentWeb.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    JobService service;
    @GetMapping("home")
    public String home(){
        return "at home page" ;
    }
    @PostMapping("create")
    public String createJob(@RequestBody Job job){
        return service.postJob(job) ;
    }
    @GetMapping("view")
    public List<Job> viewAllJobs(){
        return service.viewAllJobs() ;
    }
    @PutMapping("update")
        public String  updateJobById(@RequestParam int id , @RequestBody Job updatedJob){
            return service.updateJob(id , updatedJob) ;
        }
    @DeleteMapping("delete")
    public String deleteJobById(@RequestParam int id){
        return service.deleteJob(id) ;
    }
    @GetMapping("showApplicants")
    public List<Applicant>viewAllApplicants(@RequestParam int jobId){
        return service.viewAllApplicants(jobId) ;
    }
    @GetMapping("filterByATS/{jobId}")
    public List<Applicant>shortListedCandidates(@PathVariable int jobId , @RequestParam int num){
        return service.showShortListedCandidates(jobId , num) ;
    }
}
