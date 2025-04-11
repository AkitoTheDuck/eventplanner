package Assigner;

import DataWrapper.Company;
import DataWrapper.Student;

import java.util.ArrayList;

//Von Student
//finale liste(?) zuweisungen, welcher slot welche company, welcher wunsch
//sortiert nach slot a-e

public class Tuple {
    private String slot;
    private Company company;
    private String wunsch;

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setWunsch(String wunsch) {
        this.wunsch = wunsch;
    }

    public Tuple(String slot, Company company, String wunsch) {
        this.slot = slot;
        this.company = company;
        this.wunsch = wunsch;
    }

    public String getWunsch() {
        return this.wunsch;
    }
}
