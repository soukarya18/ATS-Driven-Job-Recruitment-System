package com.example.JobRecruitmentWeb.repo;

import com.example.JobRecruitmentWeb.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepo extends JpaRepository<Applicant , Integer> {
}
