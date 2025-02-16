package doctor.services;

import doctor.models.Doctor;
import java.util.ArrayList;
import java.time.LocalDateTime;

public interface DoctorService {
    ArrayList<Doctor> doctors = new ArrayList<>();
    void addDoctor(Doctor doctor);
    void updateDoctor(Doctor doctor);
    void deleteDoctor(Doctor doctor);
    void getDoctorById();
    void displayDoctorDetails(Doctor doctor);
}