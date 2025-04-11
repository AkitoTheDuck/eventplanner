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

    public Tuple(String slot, Company company, String wunsch) {
        this.slot = slot;
        this.company = company;
        this.wunsch = wunsch;
    }

    public String getWunsch() {
        return this.wunsch;
    }
}
