package com.laba.solvd.model;

public class Insurance {
    private String policyNumber;
    private String company;
    private double franchise;

    public Insurance(String policyNumber, String company, double franchise) {
        this.policyNumber = policyNumber;
        this.company = company;
        this.franchise = franchise;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCompany() {
        return company;
    }

    public double getFranchise() {
        return franchise;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setFranchise(double franchise) {
        this.franchise = franchise;
    }
}
