package admin.services.doctor;

import admin.models.doctor.Doctor;
import java.util.ArrayList;

public interface IDoctorService {
    ArrayList<Doctor> doctors = new ArrayList<>();
    void adminLogin();
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