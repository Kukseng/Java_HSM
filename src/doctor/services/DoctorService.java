package doctor.services;

import doctor.models.Doctor;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;

public interface DoctorService {
    ArrayList<Doctor> doctors = new ArrayList<>();
    void addDoctor(Doctor doctor);
    void updateDoctor(Doctor doctor);
    void deleteDoctor(Doctor doctor);
    void getDoctorById();
    void getDoctorByName(String doctorName);
    void getDoctorBySpecialization();
    void displayDoctorDetails(Doctor doctor);
    void viewAppointment();
    void manageDoctorAvailability(Doctor doctor);
}