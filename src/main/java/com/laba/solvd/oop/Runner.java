package com.laba.solvd.oop;

import com.laba.solvd.oop.enums.DoctorSpeciality;
import com.laba.solvd.oop.model.Doctor;
import com.laba.solvd.oop.process.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> doctorClass = Class.forName("com.laba.solvd.oop.model.Doctor");

        Constructor<?> doctorConstructor =
                doctorClass.getDeclaredConstructor(String.class, String.class, int.class, String.class, String.class,
                        DoctorSpeciality.class, int.class);
        Doctor doctor = (Doctor) doctorConstructor.newInstance("Henry", "Green", 40, "067000000",
                "Bogolyubova street, 1", DoctorSpeciality.DERMATOLOGIST, 300);

        Field consultationCostField = doctorClass.getDeclaredField("consultationCost");
        consultationCostField.setAccessible(true);
        consultationCostField.set(doctor, 400);
        LOGGER.info(doctor);
        LOGGER.info("Access modifier of the consultationCost field: " +
                Modifier.toString(consultationCostField.getModifiers()));


        Method showProfessionalInfoMethod = doctorClass.getDeclaredMethod("getSpeciality");
        showProfessionalInfoMethod.setAccessible(true);
        LOGGER.info("Doctor's speciality: " + showProfessionalInfoMethod.invoke(doctor));

        Method makeConsultationMethod =
                doctorClass.getDeclaredMethod("makeConsultation", Appointment.class, String.class);
        Class<?>[] paramTypes = makeConsultationMethod.getParameterTypes();
        LOGGER.info("Method's parameters:");
        for (Class<?> paramType : paramTypes) {
            LOGGER.info(paramType.getName());
        }
        LOGGER.info("Return type: " + makeConsultationMethod.getReturnType());


    }
}
