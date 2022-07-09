package com.example.med;

public class MyDoctor {
    private String docName;
    private String docProfession;
    private String docStudy;
    private String docExperience;
    private Integer docImage;


    public MyDoctor(String docName, String docProfession, String docStudy, String docExperience, Integer docImage) {
        this.docName = docName;
        this.docProfession = docProfession;
        this.docStudy = docStudy;
        this.docImage = docImage;
        this.docExperience = docExperience;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocProfession() {
        return docProfession;
    }

    public void setDocProfession(String docProfession) {
        this.docProfession = docProfession;
    }

    public String getDocStudy() {
        return docStudy;
    }

    public void setDocStudy(String docStudy) {
        this.docStudy = docStudy;
    }

    public String getDocExperience() {
        return docExperience;
    }

    public void setDocExperience(String docExperience) {
        this.docExperience = docExperience;
    }

    public Integer getDocImage() {
        return docImage;
    }

    public void setDocImage(Integer docImage) {
        this.docImage = docImage;
    }
}
