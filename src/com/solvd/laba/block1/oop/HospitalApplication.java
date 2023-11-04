package com.solvd.laba.block1.oop;

import com.solvd.laba.block1.oop.model.Doctor;
import com.solvd.laba.block1.oop.model.Insurance;
import com.solvd.laba.block1.oop.model.Nurse;
import com.solvd.laba.block1.oop.model.Patient;
import com.solvd.laba.block1.oop.process.Appointment;
import com.solvd.laba.block1.oop.process.StaffManager;
import com.solvd.laba.block1.oop.service.Clinic;
import com.solvd.laba.block1.oop.service.Hospital;
import com.solvd.laba.block1.oop.service.PayOffice;
import com.solvd.laba.block1.oop.service.Registry;

import static com.solvd.laba.block1.oop.enums.WeekDay.WED;

public class HospitalApplication {

    public static void main(String[] args) {
        //Patient with insurance
        Patient patient1 =
                new Patient("Mike", "Brown", 30, "05077777777",
                        "Krasnova str, 21", "brown@gmail.com");
        System.out.println(patient1.toString());
        Insurance insuranceBrown = new Insurance("AA23456", "Grant", 0.4);
        patient1.setInsurance(insuranceBrown);

        //Staff
        Doctor doctor1 =
                new Doctor("Tom", "Black", 27, "06711111111", "Paladina avenue, 35",
                        "dentist", 100);
        System.out.println(doctor1.toString());
        Nurse nurse1 = new Nurse("Nina", "White", 24, "09000000001", "Popova, 2");

        //Schedule: "-1" - doctor doesn't work, "0" - free time slot, "1" - appointment is planed
        int[][] scheduleBlack = {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
        doctor1.setSchedule(scheduleBlack);
        Doctor[] doctors = new Doctor[20];
        doctors[0] = doctor1;
        Nurse[] nurses = new Nurse[20];
        StaffManager staff = new StaffManager(doctors, nurses);

        //Registry
        Registry registry = new Registry(staff);
        registry.registerPatient(patient1);
        Appointment appointment1 = registry.registerAppointment(doctor1, patient1, WED, 2);

        //Clinic
        Clinic clinic = new Clinic();
        String symptoms1 = "Strong pain in the lower 6th tooth";
        String diagnosis1 = "Pulpit. 6th tooth, lower row, on the right.";
        String recommendation1 = "Nerve removal, root canal filling. 6th tooth, lower row, on the right.";
        clinic.visitClinic(appointment1, symptoms1, diagnosis1, recommendation1);

        //PayOffice
        PayOffice payOffice = new PayOffice();
        payOffice.pay(appointment1);

        //Hospital
        Hospital hospital = new Hospital();
        hospital.addPatient(patient1);


    }

}
