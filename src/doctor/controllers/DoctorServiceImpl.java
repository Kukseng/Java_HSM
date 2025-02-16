package doctor.controllers;

import doctor.models.Doctor;
import doctor.services.DoctorService;

import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;

public class DoctorServiceImpl implements DoctorService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addDoctor(Doctor doctor) {
        if (isValidDoctor(doctor, true)) return;

        if (findDoctorById(doctor.getDoctorId()).isPresent()) {
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
            if (doctors.get(i).getDoctorId().equalsIgnoreCase(doctor.getDoctorId())) {
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
            if (iterator.next().getDoctorId().equalsIgnoreCase(doctor.getDoctorId())) {
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
                this::displayDoctorDetails,
                () -> System.out.println("Error: Doctor not found.")
        );
    }
    void doctorInfo(Doctor doctor) {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        final String YELLOW = "\u001B[33m";
        final String BOLD_GREEN = "\033[1;92m";
        final String BRIGHT_GREEN = "\033[92m";
        int consoleWidth = 180;

        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 50); // First column width
        table.setColumnWidth(1, 40, 90);   // Values

        table.addCell(BOLD_GREEN  + "Field" + RESET, centerStyle);
        table.addCell(BOLD_GREEN  + "Value" + RESET, centerStyle);

        table.addCell(BRIGHT_GREEN  + "ID" + RESET, centerStyle);
        table.addCell(doctor.getDoctorId(), centerStyle);

        table.addCell(BRIGHT_GREEN  + "Name" + RESET, centerStyle);
        table.addCell(doctor.getDoctorName(), centerStyle);

        table.addCell(BRIGHT_GREEN  + "Specialization" + RESET, centerStyle);
        table.addCell(doctor.getSpecialization().toString(), centerStyle);

        table.addCell(BRIGHT_GREEN  + "Department" + RESET, centerStyle);
        table.addCell(doctor.getDepartment(), centerStyle);

        table.addCell(BRIGHT_GREEN  + "Contact" + RESET, centerStyle);
        table.addCell(doctor.getContactDetails(), centerStyle);

        table.addCell(BRIGHT_GREEN  + "Available" + RESET, centerStyle);
        table.addCell(doctor.isAvailable() ? "Yes" : "No", centerStyle);

        table.addCell(BRIGHT_GREEN  + "Qualifications" + RESET, centerStyle);
        table.addCell(doctor.getQualifications(), centerStyle);

        table.addCell(BRIGHT_GREEN  + "Experience" + RESET, centerStyle);
        table.addCell(doctor.getExperience() + " years", centerStyle);

        table.addCell(BRIGHT_GREEN  + "Schedule" + RESET, centerStyle);
        table.addCell(doctor.getSchedule(), centerStyle);

        int tableWidth = 100;  // Adjust based on your column widths
        int leftPadding = (consoleWidth - tableWidth) / 2;
        String padding = " ".repeat(leftPadding);

        String[] tableLines = table.render().split("\n");
        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }
    }
    // For single doctor display
    @Override
    public void displayDoctorDetails(Doctor doctor) {
        doctorInfo(doctor);
    }

    // 🔹 Helper Method: Validates Doctor Data
    private boolean isValidDoctor(Doctor doctor, boolean isNewDoctor) {
        if (doctor == null) {
            System.out.println("Error: Doctor object is null.");
            return true;
        }
        if (doctor.getDoctorId() == null || doctor.getDoctorId().trim().isEmpty()) {
            System.out.println("Error: Doctor ID is required.");
            return true;
        }
        if (isNewDoctor && (doctor.getDoctorName() == null || doctor.getDoctorName().trim().isEmpty())) {
            System.out.println("Error: Doctor Name is required for new doctors.");
            return true;
        }
        return false;
    }

    // 🔹 Helper Method: Finds Doctor by ID (Case-Insensitive)
    public Optional<Doctor> findDoctorById(String id) {
        return doctors.stream()
                .filter(d -> d.getDoctorId().equalsIgnoreCase(id))
                .findFirst();
    }

}
