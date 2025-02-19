package patient;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import patient.Appointment;

import java.util.*;

public class PatientController {
    static Scanner scanner = new Scanner(System.in);
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();
    static List<MedicalRecord> medicalRecords = new ArrayList<>();
    private static int nextPatientId = 1;
    private static int nextAppointmentId = 1;
    private static int nextRecordId = 1;

    final static String BLUE = "\u001B[34m";
    final static String RESET = "\u001B[0m";
    final static String BOLD_GREEN = "\033[1;92m";
    final static String BRIGHT_GREEN = "\033[92m";
    final static String RED = "\u001B[31m";

    static int consoleWidth = 180;
    static int tableWidth = 100;
    static int leftPadding = (consoleWidth - tableWidth) / 2;
    static String padding = " ".repeat(leftPadding);

    static void tableGenerator() {
        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 50); // First column width
        table.setColumnWidth(1, 40, 90); // Second column width


        table.addCell(BOLD_GREEN + "Option" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Action" + RESET, centerStyle);

        table.addCell(BRIGHT_GREEN + "1" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Patient Registration" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "2" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Appointment Booking" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "3" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Medical Records" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "4" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "5" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Search Doctor By Name" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "6" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Search Doctor By Specialization" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "7" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "View Appointment" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "8" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Manage Doctor Availability" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "0" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);


        String[] tableLines = table.render().split("\n");

        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

    }
//    static void tableGenerator1(){
//
//    }

    public static void main(String[] args) {
        while (true) {
            tableGenerator();
//            System.out.println("1. Patient Registration");
//            System.out.println("2. Appointment Booking");
//            System.out.println("3. Medical Records");
//            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input number (1-4)");
            }

            scanner.nextLine();

            switch (choice) {
                case 1:
                    handlePatientRegistration();
                    break;
                case 2:
                    handleAppointmentBooking();
                    break;
                case 3:
                    handleMedicalRecords();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    static void tableGen() {
        System.out.println("\n                                                  ===============Patient Handler System==============");

        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 50); // First column width
        table.setColumnWidth(1, 40, 90); // Second column width


        table.addCell(BOLD_GREEN + "Option" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Action" + RESET, centerStyle);

        table.addCell(BRIGHT_GREEN + "1" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Add New Patient" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "2" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Update patient details" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "3" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Delete patient record" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "4" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Search for patient" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "5" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "View all patients" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "6" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Back to main menu" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "5" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Search Doctor By Name" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "6" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Search Doctor By Specialization" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "7" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "View Appointment" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "8" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Manage Doctor Availability" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "0" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);


        String[] tableLines = table.render().split("\n");

        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

    }

    // --- Patient Registration ---
    private static void handlePatientRegistration() {
        while (true) {
            tableGen();
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please input number (1-6)");
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    updatePatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    searchPatient();
                    break;
                case 5:
                    viewPatients();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    public static void registerPatient() {
        int id = nextPatientId++;
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Contact Details: ");
        String contactDetails = scanner.nextLine();
        System.out.print("Enter Medical History: ");
        String medicalHistory = scanner.nextLine();

        patients.add(new Patient(id, name, age, gender, contactDetails, medicalHistory));
        System.out.println("Patient registered successfully!");
    }

    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            System.out.println("\n===== Patient List =====");
            for (Patient p : patients) {
                System.out.println(p);
            }
            System.out.println("========================");
        }
    }

    private static void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Patient p : patients) {
            if (p.id == id) {
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter New Gender: ");
                String gender = scanner.nextLine();
                System.out.print("Enter New Contact Details: ");
                String contactDetails = scanner.nextLine();
                System.out.print("Enter New Medical History: ");
                String medicalHistory = scanner.nextLine();

                p.updateDetails(name, age, gender, contactDetails, medicalHistory);
                System.out.println("Patient details updated successfully!");
                return;
            }
        }
        System.out.println("Patient ID not found.");
    }

    private static void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.id == id) {
                iterator.remove();
                System.out.println("Patient record deleted successfully!");
                return;
            }
        }
        System.out.println("Patient ID not found.");
    }

    private static void searchPatient() {
        System.out.print("Enter Patient ID or Name to search: ");
        String input = scanner.nextLine();

        for (Patient p : patients) {
            if (String.valueOf(p.id).equals(input) || p.name.equalsIgnoreCase(input)) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Patient not found.");
    }
    static void tableG() {
        System.out.println("\n                                                  ===============Patient Handler System==============");

        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 20, 50); // First column width
        table.setColumnWidth(1, 40, 90); // Second column width


        table.addCell(BOLD_GREEN + "Option" + RESET, centerStyle);
        table.addCell(BOLD_GREEN + "Action" + RESET, centerStyle);

        table.addCell(BRIGHT_GREEN + "1" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Patient Registration" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "2" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Appointment Booking" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "3" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Medical Records" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "4" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "5" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Search Doctor By Name" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "6" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Search Doctor By Specialization" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "7" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "View Appointment" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "8" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Manage Doctor Availability" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "0" + RESET, centerStyle);
//        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);


        String[] tableLines = table.render().split("\n");

        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

    }

    // --- Appointment Booking ---
    private static void handleAppointmentBooking() {
        while (true) {
            System.out.println("\n=========== Appointment Booking ==========");
            System.out.println("1. Schedule appointment");
            System.out.println("2. View appointments");
            System.out.println("3. Reschedule appointment");
            System.out.println("4. Cancel appointment");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    scheduleAppointment();
                    break;
                case 2:
                    viewAppointments();
                    break;
                case 3:
                    rescheduleAppointment();
                    break;
                case 4:
                    cancelAppointment();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void scheduleAppointment() {
        int appointmentId = nextAppointmentId++;
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter Time (HH:MM): ");
        String time = scanner.nextLine();

        appointments.add(new Appointment(appointmentId, patientId, doctor, date, time));
        System.out.println("Appointment scheduled successfully! ID: " + appointmentId);
    }

    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(a);
            }
        }
    }

    private static void rescheduleAppointment() {
        System.out.print("Enter Appointment ID to reschedule: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        for (Appointment a : appointments) {
            if (a.appointmentId == appointmentId) {
                System.out.print("Enter New Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Enter New Time (HH:MM): ");
                String time = scanner.nextLine();

                a.reschedule(date, time);
                System.out.println("Appointment rescheduled successfully!");
                return;
            }
        }
        System.out.println("Appointment ID not found.");
    }

    private static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment a = iterator.next();
            if (a.appointmentId == appointmentId) {
                iterator.remove();
                System.out.println("Appointment canceled successfully!");
                return;
            }
        }
        System.out.println("Appointment ID not found.");
    }

    // --- Medical Records ---
    private static void handleMedicalRecords() {
        while (true) {
            System.out.println("\n============= Medical Records ============");
            System.out.println("1. Add medical record");
            System.out.println("2. View medical records");
            System.out.println("3. Update medical record");
            System.out.println("4. Delete medical record");
            System.out.println("5. Back to main menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMedicalRecord();
                    break;
                case 2:
                    viewMedicalRecords();
                    break;
                case 3:
                    updateMedicalRecord();
                    break;
                case 4:
                    deleteMedicalRecord();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void addMedicalRecord() {
        int recordId = nextRecordId++;
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter Prescriptions: ");
        String prescriptions = scanner.nextLine();
        System.out.print("Enter Test Results: ");
        String testResults = scanner.nextLine();

        medicalRecords.add(new MedicalRecord(recordId, patientId, diagnosis, prescriptions, testResults));
        System.out.println("Medical record added successfully! ID: " + recordId);
    }

    private static void viewMedicalRecords() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records available.");
        } else {
            for (MedicalRecord m : medicalRecords) {
                System.out.println(m);
            }
        }
    }

    private static void updateMedicalRecord() {
        System.out.print("Enter Record ID to update: ");
        int recordId = scanner.nextInt();
        scanner.nextLine();

        for (MedicalRecord m : medicalRecords) {
            if (m.recordId == recordId) {
                System.out.print("Enter New Diagnosis: ");
                String diagnosis = scanner.nextLine();
                System.out.print("Enter New Prescriptions: ");
                String prescriptions = scanner.nextLine();
                System.out.print("Enter New Test Results: ");
                String testResults = scanner.nextLine();

                m.updateRecord(diagnosis, prescriptions, testResults);
                System.out.println("Medical record updated successfully!");
                return;
            }
        }
        System.out.println("Record ID not found.");
    }

    private static void deleteMedicalRecord() {
        System.out.print("Enter Record ID to delete: ");
        int recordId = scanner.nextInt();
        scanner.nextLine();

        Iterator<MedicalRecord> iterator = medicalRecords.iterator();
        while (iterator.hasNext()) {
            MedicalRecord m = iterator.next();
            if (m.recordId == recordId) {
                iterator.remove();
                System.out.println("Medical record deleted successfully!");
                return;
            }
        }
        System.out.println("Record ID not found.");
    }
}
