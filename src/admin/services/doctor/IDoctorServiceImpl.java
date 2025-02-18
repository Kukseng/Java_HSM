package admin.services.doctor;

import admin.models.doctor.Doctor;

import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;

public class IDoctorServiceImpl implements IDoctorService {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        final String BOLD_GREEN = "\033[1;92m";
        final String BRIGHT_GREEN = "\033[92m";
        final String RED = "\u001B[31m";

        int consoleWidth = 180;
        int tableWidth = 100;
        int leftPadding = (consoleWidth - tableWidth) / 2;
        String padding = " ".repeat(leftPadding);

    private final Scanner scanner = new Scanner(System.in);


    @Override
    public void adminLogin() {

    }

    @Override
    public void addDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, true)) return;

        if (findDoctorById(doctor.getDoctorId()).isPresent()) {
            System.out.println(RED + padding + "Error: A doctor with this ID already exists." + RESET);
            return;
        }

        doctors.add(doctor);
        System.out.println(BRIGHT_GREEN + padding + "✅ Doctor added successfully!" + RESET);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, false)) return;

        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getDoctorId().equalsIgnoreCase(doctor.getDoctorId())) {
                doctors.set(i, doctor);
                System.out.println(BRIGHT_GREEN + padding + "✅ Doctor updated successfully!" + RESET);
                return;
            }
        }
        System.out.println(BRIGHT_GREEN + padding + "Error: Doctor not found." + RESET);
    }

    @Override
    public void deleteDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, false)) return;

        Iterator<Doctor> iterator = doctors.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getDoctorId().equalsIgnoreCase(doctor.getDoctorId())) {
                iterator.remove();
                System.out.println(BRIGHT_GREEN + padding + "✅ Doctor deleted successfully!" + RESET);
                return;
            }
        }
        System.out.println(BRIGHT_GREEN + padding + "Error: Doctor not found." + RESET);
    }

    @Override
    public void getDoctorById() {
        if (doctors.isEmpty()) {
            System.out.println(RED + padding + "No doctors available." + RESET);
            return;
        }

        System.out.print(BRIGHT_GREEN + padding + "🔎 Enter Doctor ID to view details: " + RESET);
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println(RED + padding + "Error: Doctor ID cannot be empty." + RESET);
            return;
        }

        Optional<Doctor> doctor = findDoctorById(id);
        doctor.ifPresentOrElse(
                this::displayDoctorDetails,
                () -> System.out.println(BRIGHT_GREEN + padding + "Error: Doctor not found." + RESET)
        );
    }

    @Override
    public void getDoctorByName(String doctorName) {
        if (doctors.isEmpty()) {
            System.out.println(BRIGHT_GREEN + padding + "No doctors available." + RESET);
            return;
        }

        System.out.print(BRIGHT_GREEN + padding + "🔎 Enter Doctor name to view details: " + RESET);
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println(RED + "Error: Doctor name cannot be empty." + RESET);
            return;
        }

        Optional<Doctor> doctor = findDoctorByName(name);
        doctor.ifPresentOrElse(
                this::displayDoctorDetails,
                () -> System.out.println(RED + padding + "Error: Doctor not found." + RESET)
        );
    }

    @Override
    public void getDoctorBySpecialization() {
        if (doctors.isEmpty()) {
            System.out.println(RED + padding + "No doctors available." + RESET);
            return;
        }
        System.out.print(BRIGHT_GREEN + padding + "🔎 Enter Doctor specialization to view details: " + RESET);
        String specializationInput = scanner.nextLine().trim();

        if (specializationInput.isEmpty()) {
            System.out.println(RED + padding + "Error: Doctor specialization cannot be empty." + RESET);
            return;
        }

        Optional<Doctor> doctor = findDoctorBySpecialization(Doctor.Specialization.valueOf(specializationInput));
        doctor.ifPresentOrElse(
                this::displayDoctorDetails,
                () -> System.out.println(RED + padding + "Error: Doctor not found." + RESET)
        );
    }

    void doctorInfo(Doctor doctor) {
        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 50); // First column width
        table.setColumnWidth(1, 40, 90);   // Values

        table.addCell(BOLD_GREEN + "Field" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Value" + RESET, centerStyle);

        table.addCell(BRIGHT_GREEN + "ID" + RESET, centerStyle);
        table.addCell(doctor.getDoctorId(), centerStyle);

        table.addCell(BRIGHT_GREEN + "Name" + RESET, centerStyle);
        table.addCell(doctor.getDoctorName(), centerStyle);

        table.addCell(BRIGHT_GREEN + "Specialization" + RESET, centerStyle);
        table.addCell(doctor.getSpecialization().toString(), centerStyle);

        table.addCell(BRIGHT_GREEN + "Department" + RESET, centerStyle);
        table.addCell(doctor.getDepartment(), centerStyle);

        table.addCell(BRIGHT_GREEN + "Contact" + RESET, centerStyle);
        table.addCell(doctor.getContactDetails(), centerStyle);

        table.addCell(BRIGHT_GREEN + "Available" + RESET, centerStyle);
        table.addCell(doctor.getAvailable() ? "Yes" : "No", centerStyle);

        table.addCell(BRIGHT_GREEN + "Qualifications" + RESET, centerStyle);
        table.addCell(doctor.getQualifications(), centerStyle);

        table.addCell(BRIGHT_GREEN + "Experience" + RESET, centerStyle);
        table.addCell(doctor.getExperience() + " years", centerStyle);

        table.addCell(BRIGHT_GREEN + "Schedule" + RESET, centerStyle);
        table.addCell(doctor.getSchedule(), centerStyle);

        String[] tableLines = table.render().split("\n");
        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }
    }

    @Override
    public void displayDoctorDetails(Doctor doctor) {
        doctorInfo(doctor);
    }

    @Override
    public void viewAppointment() {
        if (doctors.isEmpty()) {
            System.out.println(RED + padding + "🥲 No doctors in the system to view appointments." + RESET);
            return;
        }

        Table table = new Table(3, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 10, 20);
        table.setColumnWidth(1, 30, 50);
        table.setColumnWidth(2, 30, 50);

        table.addCell(BOLD_GREEN + "ID" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Name" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Specialization" + RESET, centerStyle);

        for (Doctor doc : doctors) {
            table.addCell(doc.getDoctorId(), centerStyle);
            table.addCell(doc.getDoctorName(), centerStyle);
            table.addCell(doc.getSpecialization().toString(), centerStyle);
        }

        String[] tableLines = table.render().split("\n");
        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

        System.out.print(BRIGHT_GREEN + padding + "Enter Doctor ID to view appointments: " + RESET);
        String id = scanner.nextLine();

        Doctor selectedDoctor = null;
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(id)) {
                selectedDoctor = doctor;
                break;
            }
        }

        if (selectedDoctor == null) {
            System.out.println(BRIGHT_GREEN + padding + "Doctor with ID " + id + " not found." + RESET);
            return;
        }

        System.out.println(BRIGHT_GREEN + padding + "Appointments for Dr. " + selectedDoctor.getDoctorName() + ":" + RESET);
        System.out.println(BRIGHT_GREEN + padding + "No appointments scheduled (//waiting for the data from patient...)" + RESET);
    }

    @Override
    public void manageDoctorAvailability(Doctor doctor) {

        if (doctors.isEmpty()) {
            System.out.println(BRIGHT_GREEN + padding + "No doctors in the system to manage availability." + RESET);
            return;
        }

        Table table = new Table(3, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 10, 20); // ID column
        table.setColumnWidth(1, 30, 50); // Name column
        table.setColumnWidth(2, 15, 20); // Availability column

        table.addCell(BOLD_GREEN + "ID" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Name" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Available" + RESET, centerStyle);

        for (Doctor doc : doctors) {
            table.addCell(doc.getDoctorId(), centerStyle);
            table.addCell(doc.getDoctorName(), centerStyle);
            table.addCell(doc.getAvailable() ? "Yes" : "No", centerStyle);
        }

        String[] tableLines = table.render().split("\n");
        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

        System.out.print(BRIGHT_GREEN + padding + "Enter Doctor ID to manage availability: " + RESET);
        String id = scanner.nextLine();

        Doctor selectedDoctor = null;
        for (Doctor doc : doctors) {
            if (doc.getDoctorId().equals(id)) {
                selectedDoctor = doc;
                break;
            }
        }

        if (selectedDoctor == null) {
            System.out.println(BRIGHT_GREEN + padding + "Doctor with ID " + id + " not found." + RESET);
            return;
        }

        doctor = selectedDoctor;
        if (doctor.getAvailable()) {
            System.out.println(BRIGHT_GREEN + padding + "Doctor is available" + RESET);
        }
        System.out.println(BRIGHT_GREEN + padding + "Current availability status for Dr. " + doctor.getDoctorName() + ": " +
                (doctor.getAvailable() ? "Available" : "Not Available") + RESET);

        System.out.print(BRIGHT_GREEN + padding + "Do you want to change availability? (y/n): " + RESET);
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y")) {
            doctor.setAvailable(!doctor.getAvailable());
            System.out.println(BRIGHT_GREEN + padding + "Availability updated to: " +
                    (doctor.getAvailable() ? "Available" : "Not Available") + RESET);
        } else {
            System.out.println(BRIGHT_GREEN + padding + "Availability remains unchanged." + RESET);
        }
    }

    private boolean isValidDoctor(Doctor doctor, boolean isNewDoctor) {
        if (doctor == null) {
            System.out.println(BRIGHT_GREEN + padding + "Error: Doctor object is null." + RESET);
            return true;
        }
        if (doctor.getDoctorId() == null || doctor.getDoctorId().trim().isEmpty()) {
            System.out.println(BRIGHT_GREEN + padding + "Error: Doctor ID is required." + RESET);
            return true;
        }
        if (isNewDoctor && (doctor.getDoctorName() == null || doctor.getDoctorName().trim().isEmpty())) {
            System.out.println(BRIGHT_GREEN + padding + "Error: Doctor Name is required for new doctors." + RESET);
            return true;
        }
        return false;
    }

    public Optional<Doctor> findDoctorById(String id) {
        return doctors.stream()
                .filter(n -> n.getDoctorId().equalsIgnoreCase(id))
                .findFirst();
    }
    public Optional<Doctor> findDoctorByName(String name) {
        return doctors.stream()
                .filter(n -> n.getDoctorName().equalsIgnoreCase(name))
                .findFirst();
    }
    public Optional<Doctor> findDoctorBySpecialization(Doctor.Specialization specialization) {
        return doctors.stream()
                .filter(n -> n.getSpecialization() == specialization)
                .findFirst();
    }
}
