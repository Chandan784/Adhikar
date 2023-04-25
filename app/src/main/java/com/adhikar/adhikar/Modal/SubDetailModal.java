package com.adhikar.adhikar.Modal;

public class SubDetailModal {
    String title,benifit,details,criteria,process,documents,audio,type;
    int sl;

    public SubDetailModal(String title, String benifit, String details, String criteria, String process, String documents, String audio, String type, int sl) {
        this.title = title;
        this.benifit = benifit;
        this.details = details;
        this.criteria = criteria;
        this.process = process;
        this.documents = documents;
        this.audio = audio;
        this.type = type;
        this.sl = sl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBenifit() {
        return benifit;
    }

    public void setBenifit(String benifit) {
        this.benifit = benifit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
