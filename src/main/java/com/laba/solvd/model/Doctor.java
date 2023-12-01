package com.laba.solvd.model;

import com.laba.solvd.enums.DoctorSpeciality;
import com.laba.solvd.enums.WeekDay;
import com.laba.solvd.interfaces.Evaluation;
import com.laba.solvd.interfaces.Worker;
import com.laba.solvd.process.Appointment;
import com.laba.solvd.process.MedicalReport;
import com.laba.solvd.process.StaffManager;
import com.laba.solvd.service.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class Doctor extends Person implements Worker, Evaluation {
    private static final Logger LOGGER = LogManager.getLogger(Doctor.class);

    private DoctorSpeciality speciality;
    private int[][] schedule;
    private int consultationCost;
    private Map<String, String> medBook;

    public Doctor(String firstName, String lastName, int age, String phoneNumber, String address,
                  DoctorSpeciality speciality, int consultationCost) {
        super(firstName, lastName, age, phoneNumber, address);
        this.speciality = speciality;
        this.consultationCost = consultationCost;
        this.schedule = new int[7][10];
    }

    public int getConsultationCost() {
        return consultationCost;
    }

    public void setConsultationCost(int consultationCost) {
        this.consultationCost = consultationCost;
    }

    public DoctorSpeciality getSpeciality() {
        return speciality;
    }

    public int[][] getSchedule() {
        return schedule;
    }

    public void setSpeciality(DoctorSpeciality speciality) {
        this.speciality = speciality;
    }

    public void setSchedule(int[][] schedule) {
        this.schedule = schedule;
    }

    public Map<String, String> getMedBook() {
        return medBook;
    }

    public void setMedBook(Map<String, String> medBook) {
        this.medBook = medBook;
    }

    @Override
    public void showContactInfo() {
        LOGGER.info("Address: " + this.address + "\n"
                + "Phone number: " + this.phoneNumber);
    }

    public boolean isTimeSlotFree(WeekDay weekDay, int timeSlot) {
        if (timeSlot >= 20) {
            LOGGER.warn("Incorrect time slot.");
            return false;
        }

        switch (weekDay) {
            case MON:
                if (schedule[0][timeSlot] == 0) {
                    return true;
                }
                break;
            case TUE:
                if (schedule[1][timeSlot] == 0) {
                    return true;
                }
                break;
            case WED:
                if (schedule[2][timeSlot] == 0) {
                    return true;
                }
                break;
            case THU:
                if (schedule[3][timeSlot] == 0) {
                    return true;
                }
                break;
            case FRI:
                if (schedule[4][timeSlot] == 0) {
                    return true;
                }
                break;
            case SAT:
                if (schedule[5][timeSlot] == 0) {
                    return true;
                }
                break;
            case SUN:
                if (schedule[6][timeSlot] == 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean chooseTimeSlot(WeekDay weekDay, int timeSlot) {
        if (isTimeSlotFree(weekDay, timeSlot)) {
            switch (weekDay) {
                case MON:
                    schedule[0][timeSlot] = 1;
                    return true;
                case TUE:
                    schedule[1][timeSlot] = 1;
                    return true;
                case WED:
                    schedule[2][timeSlot] = 1;
                    return true;
                case THU:
                    schedule[3][timeSlot] = 1;
                    return true;
                case FRI:
                    schedule[4][timeSlot] = 1;
                    return true;
                case SAT:
                    schedule[5][timeSlot] = 1;
                    return true;
                case SUN:
                    schedule[6][timeSlot] = 1;
                    return true;
            }

        } else {
            LOGGER.warn("The time slot isn't available.");
            return false;
        }
        return false;
    }

    @Override
    public void showProfessionalInfo() {
        LOGGER.info("Speciality: " + speciality + "\n" +
                "Cost of consultation: " + consultationCost + "$");
    }

    @Override
    public void showSchedule() {
        LOGGER.info("Monday: " + Arrays.toString(schedule[0]) + "\n" +
                "Tuesday: " + Arrays.toString(schedule[1]) + "\n" +
                "Wednesday: " + Arrays.toString(schedule[2]) + "\n" +
                "Thursday: " + Arrays.toString(schedule[3]) + "\n" +
                "Friday: " + Arrays.toString(schedule[4]) + "\n" +
                "Saturday: " + Arrays.toString(schedule[5]) + "\n" +
                "Sunday: " + Arrays.toString(schedule[6]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return firstName.equals(doctor.firstName)
                && lastName.equals(doctor.lastName)
                && speciality.equals(doctor.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, speciality);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", speciality='" + speciality.getDisplayName() + '\'' +
                ", consultationCost=" + consultationCost +
                '}';
    }

    public final MedicalReport makeConsultation(Appointment appointment, String symptom) {
        String diagnosis = makeDiagnosis(symptom);
        String recommendation = makeRecommendation(diagnosis);
        Random random = new Random();

        return new MedicalReport(random.nextInt(999), appointment, symptom, diagnosis,
                recommendation, decideToHospitalize());
    }

    private String makeDiagnosis(String symptom) {
        if (medBook.containsKey(symptom)) {
            return medBook.get(symptom);
        }
        return "Healthy";

    }

    private String makeRecommendation(String diagnosis) {
        String recommendation;
        switch (diagnosis) {
            case "Influenza":
                recommendation = "To take an antiviral remedy for the flu, a fever reducer. " +
                        "To drink plenty of fluids.";
                break;
            case "Pulpit":
                recommendation = "To clean and fill of dental canals.";
                break;
            case "Hypertension":
                recommendation = "To take medications to lower blood pressure.";
                break;
            case "Bronchitis":
                recommendation = "To breathe moist cool air. To drink plenty of fluids.";
                break;
            case "Stomach ulcer":
                recommendation = "To take medications for the healing of the stomach lining." +
                        "To follow a diet 2.";
                break;
            case "Chickenpox":
                recommendation = "To take shower frequently. To use creams to reduce skin itching." +
                        "To avoid contact with other people.";
                break;
            default:
                recommendation = "To enjoy life.";
        }

        return recommendation;

    }

    private boolean decideToHospitalize() {
        Random random = new Random();
        return random.nextBoolean();
    }

    @Override
    public double getRating() {
        int staffManagerMark = StaffManager.evaluateDoctor(this);
        double patientsMark;
        List<Appointment> appointments = Registry.getAppointmentListByDoctor(this);
        if (appointments.size() == 0) patientsMark = 0;
        else {
            patientsMark = (double) appointments.stream().mapToInt(appointment ->
                    appointment.getPatient().evaluate()).sum() / appointments.size();
        }

        return (staffManagerMark + patientsMark) / 2;
    }

}
