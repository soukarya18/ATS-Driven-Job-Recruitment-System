package com.example.JobRecruitmentWeb.repo;

import com.example.JobRecruitmentWeb.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

}
