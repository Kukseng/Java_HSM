package doctor.controllers;

import doctor.models.Doctor;
import doctor.services.DoctorService;

import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;

public class DoctorServiceImpl implements DoctorService {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void addDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, true)) return;

        if (findDoctorById(doctor.getId()).isPresent()) {
            System.out.println("Error: A doctor with this ID already exists.");
            return;
        }

        doctors.add(doctor);
        System.out.println("✅ Doctor added successfully!");
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, false)) return;

        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equalsIgnoreCase(doctor.getId())) {
                doctors.set(i, doctor);
                System.out.println("✅ Doctor updated successfully!");
                return;
            }
        }
        System.out.println("Error: Doctor not found.");
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, false)) return;

        Iterator<Doctor> iterator = doctors.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equalsIgnoreCase(doctor.getId())) {
                iterator.remove();
                System.out.println("✅ Doctor deleted successfully!");
                return;
            }
        }
        System.out.println("Error: Doctor not found.");
    }

    @Override
    public void getDoctorById() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.print("🔎 Enter Doctor ID to view details: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Error: Doctor ID cannot be empty.");
            return;
        }

        Optional<Doctor> doctor = findDoctorById(id);
        doctor.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Error: Doctor not found.")
        );
    }

    // 🔹 Helper Method: Validates Doctor Data
    private boolean isValidDoctor(Doctor doctor, boolean isNewDoctor) {
        if (doctor == null) {
            System.out.println("Error: Doctor object is null.");
            return true;
        }
        if (doctor.getId() == null || doctor.getId().trim().isEmpty()) {
            System.out.println("Error: Doctor ID is required.");
            return true;
        }
        if (isNewDoctor && (doctor.getName() == null || doctor.getName().trim().isEmpty())) {
            System.out.println("Error: Doctor Name is required for new doctors.");
            return true;
        }
        return false;
    }

    private Optional<Doctor> findDoctorById(String id) {
        return doctors.stream()
                .filter(d -> d.getId().equalsIgnoreCase(id))
                .findFirst();
    }

}
