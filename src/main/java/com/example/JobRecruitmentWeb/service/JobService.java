package com.example.JobRecruitmentWeb.service;

import com.example.JobRecruitmentWeb.model.Applicant;
import com.example.JobRecruitmentWeb.model.ApplicantATS;
import com.example.JobRecruitmentWeb.model.Job;
import com.example.JobRecruitmentWeb.repo.ApplicantRepo;
import com.example.JobRecruitmentWeb.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService {
    @Autowired
    JobRepo repo ;
    @Autowired
    ApplicantRepo applicantRepo ;

    public String postJob(Job job) {
        repo.save(job) ;
        return "saved" ;
    }

    public List<Job> viewAllJobs() {
        List<Job> allJobs = repo.findAll() ;
        return allJobs ;
    }

    public String updateJob(int id , Job updatedJob){
        Job currJob = repo.findById(id).get() ;
        currJob=updatedJob ;
        repo.save(currJob) ;
        return ("jobId " +id + " got updated to "+ currJob) ;
    }

    public String deleteJob(int id) {
        Job job = repo.findById(id).get() ;
        repo.delete(job);
        return ("deleted "+job) ;
    }


    public List<Applicant> viewAllApplicants(int jobId) {
        Job job = repo.findById(jobId).get() ;
        List<Applicant>applicants= job.getApplicants() ;
        return applicants ;
    }

    public List<Applicant> showShortListedCandidates(int jobId, int num) {
        List<Applicant>applicants= repo.findById(jobId).get().getApplicants() ;
        List<ApplicantATS>detailedApplicant = generateATS(applicants , jobId) ;

        // sort based on ATS score in case of tie prioritize guy with more experience
        detailedApplicant.sort((a, b) -> {
            if (b.getAtsScore() != a.getAtsScore()) {
                return b.getAtsScore() - a.getAtsScore();
            } else {
                return b.getExperience() - a.getExperience();
            }
        });

        List<Applicant>shortlistedApplicants=new ArrayList<>() ;
        int count =0 ;
        for(ApplicantATS applicantATS:detailedApplicant){
            int id = applicantATS.getId() ;
            int atsScore = applicantATS.getAtsScore() ;
            Applicant applicant = applicantRepo.findById(id).get() ;
            shortlistedApplicants.add(applicant) ;
            count ++ ;
            if(count ==num) break;
        }
        return shortlistedApplicants ;
    }

    private List<ApplicantATS> generateATS(List<Applicant> applicants , int jobId) {
        String requiredTechStack = repo.findById(jobId).get().getTechStack() ;
        List<String> requiredSkills = Arrays.asList(requiredTechStack.split("\\s*,\\s*"));
        List<ApplicantATS> applicantWithATS=new ArrayList<>() ;
        for(Applicant ap :applicants){
            String applicantTechStack = ap.getTechStack() ;
            List<String> applicantSkills = Arrays.asList(applicantTechStack.split("\\s*,\\s*"));

            // Count matching skills
            long matches = applicantSkills.stream()
                    .filter(requiredSkills::contains)
                    .count();
            int atsScore = (int) matches + ap.getExperience() ;
            ApplicantATS applicantATS = new ApplicantATS() ;
            applicantATS.setId(ap.getApplicantID());
            applicantATS.setAtsScore(atsScore);
            applicantATS.setExperience(ap.getExperience());
            applicantWithATS.add(applicantATS) ;
        }
        return applicantWithATS ;
    }
}
