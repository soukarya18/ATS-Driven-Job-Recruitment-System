package com.example.JobRecruitmentWeb.model;

public class ApplicantATS {
    private int id ;
    private int AtsScore ;
    private int experience ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAtsScore() {
        return AtsScore;
    }

    public void setAtsScore(int atsScore) {
        AtsScore = atsScore;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "ApplicantATS{" +
                "id=" + id +
                ", AtsScore=" + AtsScore +
                '}';
    }
}
