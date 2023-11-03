package com.solvd.laba.block1.task2;

import com.solvd.laba.block1.task2.model.Doctor;
import com.solvd.laba.block1.task2.model.Insurance;
import com.solvd.laba.block1.task2.model.Patient;

public class HospitalApplication {

    public static void main(String[] args){
        Patient patient1 =
                new Patient("Mike","Brown", 30, "05077777777", "Krasnova str, 21");
        Insurance insuranceBrown = new Insurance("AA23456", "Grant", 0.4);
        patient1.setInsurance(insuranceBrown);

        Doctor doctor1 =
                new Doctor("Tom", "Black", 27, "06711111111", "Paladina avenue, 35",
                        "dentist");

    }

}
