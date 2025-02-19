package admin.services.patient;

import patient.Patient;

import java.util.ArrayList;
import java.util.List;

public interface IPatientService {
    List<Patient> doctors = new ArrayList<>();
    void adminLogin();
    void addDoctor(Patient patient);
    void updateDoctor(Patient patient);
    void deleteDoctor(Patient patient);
    void getDoctorById();
}
