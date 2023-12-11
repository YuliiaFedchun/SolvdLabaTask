package com.laba.solvd.oop;

import com.laba.solvd.oop.enums.DoctorSpeciality;
import com.laba.solvd.oop.enums.WeekDay;
import com.laba.solvd.oop.exception.BankIsNotAvailableException;
import com.laba.solvd.oop.exception.DoctorIsNotFoundException;
import com.laba.solvd.oop.exception.IllegalAppointmentIdException;
import com.laba.solvd.oop.exception.IllegalMedicalReportIdException;
import com.laba.solvd.oop.interfaces.Department;
import com.laba.solvd.oop.interfaces.functional.TriFunction;
import com.laba.solvd.oop.model.Doctor;
import com.laba.solvd.oop.model.Insurance;
import com.laba.solvd.oop.model.Nurse;
import com.laba.solvd.oop.model.Patient;
import com.laba.solvd.oop.process.Receipt;
import com.laba.solvd.oop.process.StaffManager;
import com.laba.solvd.oop.service.Clinic;
import com.laba.solvd.oop.service.Hospital;
import com.laba.solvd.oop.service.PayOffice;
import com.laba.solvd.oop.service.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.laba.solvd.oop.enums.WeekDay.WED;

public class HospitalApplication {
    static {
        System.setProperty("log4j2.configurationFile", "log4j2.xml");
    }

    private static final Logger LOGGER = LogManager.getLogger(HospitalApplication.class);

    public static void main(String[] args) throws DoctorIsNotFoundException, IllegalAppointmentIdException,
            IllegalMedicalReportIdException, BankIsNotAvailableException {

        //Patient with insurance
        Patient patient1 =
                new Patient("Mike", "Brown", 30, "05077777777",
                        "Krasnova str, 21", "brown@gmail.com");
        Patient patient2 =
                new Patient("Anna", "Aaron", 30, "05055555555",
                        "Stepova str, 1", "aaron@gmail.com");
        LOGGER.info(patient1);
        Insurance insuranceBrown = new Insurance("AA23456", "Grant", 0.4);
        patient1.setInsurance(insuranceBrown);

        //Staff
        Doctor doctor1 =
                new Doctor("Tom", "Black", 27, "06711111111", "Paladina avenue, 35",
                        DoctorSpeciality.DENTIST, 100);
        Doctor doctor2 =
                new Doctor("Henry", "Been", 29, "06711343434", "Pirogova street, 32",
                        DoctorSpeciality.ORTHOPAEDIST, 150);
        Doctor doctor3 =
                new Doctor("Mike", "Butler", 40, "06711567834", "The first street, 1",
                        DoctorSpeciality.NEUROLOGIST, 200);
        LOGGER.info(doctor1);
        Nurse nurse1 = new Nurse("Nina", "White", 24, "09000000001", "Popova, 2", 5);

        //Schedule: "-1" - doctor doesn't work, "0" - free time slot, "1" - appointment is planed
        int[][] scheduleBlack = {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
        int[][] scheduleBeen = {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
        doctor1.setSchedule(scheduleBlack);
        doctor2.setSchedule(scheduleBeen);
        doctor3.setSchedule(scheduleBeen);

        //medical directory with symptoms and diagnosis
        Map<String, String> medBook = new HashMap<>();
        medBook.put("Fever", "Influenza");
        medBook.put("Toothache", "Pulpit");
        medBook.put("Headache", "Hypertension");
        medBook.put("Stomachache", "Stomach ulcer");
        medBook.put("Cough", "Bronchitis");
        medBook.put("Rash", "Chickenpox");
        doctor1.setMedBook(medBook);
        doctor2.setMedBook(medBook);
        doctor3.setMedBook(medBook);

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);

        List<Nurse> nurses = new ArrayList<>();
        nurses.add(nurse1);
        StaffManager staff = new StaffManager(doctors, nurses);
        LOGGER.info("Doctors by speciality " + DoctorSpeciality.DENTIST.getDisplayName() + ": " +
                staff.getDoctorsBySpeciality(DoctorSpeciality.DENTIST));
        staff.rewardBestDoctor();

        //Registry
        Registry registry = new Registry();
        registry.registerPatient(patient1);
        int appointment1Id = registry.registerAppointment(doctor1, patient1, WED, 2);
        //int appointment2Id = registry.registerAppointment(doctor2,patient1,SAT,3);
        Registry.findAppointmentById(appointment1Id).print();

        //Clinic
        Clinic clinic = new Clinic();
        String symptom1 = "Cough";
        int medicalReport1Id = clinic.admitPatient(appointment1Id, patient1, symptom1);
        Clinic.findMedicalReportById(medicalReport1Id).print();

        //PayOffice
        PayOffice payOffice = new PayOffice();
        Receipt receipt1 = payOffice.acceptPayment(appointment1Id);
        receipt1.print();
        payOffice.calculateProfit();


        //Hospital
        Hospital hospital = new Hospital();
        hospital.hospitalizePatient(medicalReport1Id, patient1);
        for (Patient patient :
                hospital.getPatientsInHospital()) {
            LOGGER.info(patient + " is in the hospital.");
        }

        LOGGER.info("The doctor's " + doctor1.getLastName() + " rating is " + doctor1.getRating());

        //TriFunction usage
        TriFunction<List<Department>, WeekDay, Integer, List<String>> func = (departments, weekDay, time) ->
                departments.stream()
                        .filter(department -> department.isOpened(weekDay, time))
                        .map(department -> department.getName())
                        .collect(Collectors.toList());

        List<Department> allHospital = new ArrayList<>();
        allHospital.add(registry);
        allHospital.add(payOffice);
        allHospital.add(clinic);
        allHospital.add(hospital);
        LOGGER.info(WeekDay.FRI.getDisplayName() + " 12:00." +
                func.apply(allHospital, WeekDay.FRI, 12) + " are opened.");


    }

}
