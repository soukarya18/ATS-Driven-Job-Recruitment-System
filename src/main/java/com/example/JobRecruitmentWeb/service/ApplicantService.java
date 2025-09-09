package com.example.JobRecruitmentWeb.service;

import com.example.JobRecruitmentWeb.model.Applicant;
import com.example.JobRecruitmentWeb.model.Job;
import com.example.JobRecruitmentWeb.repo.ApplicantRepo;
import com.example.JobRecruitmentWeb.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {
    @Autowired
    ApplicantRepo applicantRepo ;
    @Autowired
    JobRepo jobRepo ;


    public String registerApplicant(Applicant applicant, int jobId) {
        Job job =  jobRepo.findById(jobId).get() ;
        List<Applicant>applicants = job.getApplicants() ;
        applicants.add(applicant) ;
        job.setApplicants(applicants);
        applicantRepo.save(applicant) ;
        return "applicant id "+applicant.getApplicantID()+" "+applicant.getApplicantName()+" has been registered to "+job ;
    }

    public List<Job> viewAllJobs() {
        List<Job>jobs = jobRepo.findAll() ;
        return jobs ;
    }

    public String removeApplicant(int applicantId) {
        Applicant applicant = applicantRepo.findById(applicantId).get() ;
        List<Job>allJobs = jobRepo.findAll() ;
        for (int i = 0; i < allJobs.size(); i++) {
            List<Applicant>applicants = allJobs.get(i).getApplicants() ;
            for(Applicant ap : applicants){
                if(ap.getApplicantID()==applicantId){
                    applicants.remove(ap) ;
                    break ;
                }
            }
        }
        applicantRepo.deleteById(applicantId);
        return applicant+" registration is called off" ;
    }
}
