package patient;

import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import patient.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.print("Choose an option: ");

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

        // Validate Patient Name (Use existing getValidText method)
        String name = getValidText("Enter Patient Name (Letters only): ");

        // Validate Age (Numbers Only, Positive)
        int age;
        while (true) {
            try {
                System.out.print("Enter Age (Numbers only): ");
                age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (age > 0) break;
                else System.out.println("Invalid input! Age must be a positive number.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Age must be a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Validate Gender (Male, Female, Other)
        String gender;
        while (true) {
            System.out.print("Enter Gender (Male, Female, Other): ");
            gender = scanner.nextLine().trim();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter Male, Female, or Other.");
            }
        }

        // Validate Contact Details (Numbers Only)
        String contactDetails;
        while (true) {
            System.out.print("Enter Contact Details (Numbers only): ");
            contactDetails = scanner.nextLine();
            if (contactDetails.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid input! Contact details must contain numbers only.");
            }
        }

        // Validate Medical History (Use existing getValidText method)
        String medicalHistory = getValidText("Enter Medical History (Letters only): ");

        // Register Patient
        patients.add(new Patient(id, name, age, gender, contactDetails, medicalHistory));
        System.out.println("Patient registered successfully! ID: " + id);
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
        int id;

        // Validate Patient ID (Numbers Only)
        while (true) {
            try {
                System.out.print("Enter Patient ID to update (Numbers only): ");
                id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Patient ID must be a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Search for the patient
        for (Patient p : patients) {
            if (p.id == id) {
                // Validate Name
                String name = getValidText("Enter New Name (Letters only): ");

                // Validate Age
                int age;
                while (true) {
                    try {
                        System.out.print("Enter New Age (Numbers only): ");
                        age = scanner.nextInt();
                        scanner.nextLine();
                        if (age > 0) break;
                        else System.out.println("Invalid input! Age must be a positive number.");
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Age must be a number.");
                        scanner.nextLine(); // Clear invalid input
                    }
                }

                // Validate Gender
                String gender;
                while (true) {
                    System.out.print("Enter New Gender (Male, Female, Other): ");
                    gender = scanner.nextLine().trim();
                    if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Other")) {
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter Male, Female, or Other.");
                    }
                }

                // Validate Contact Details (Numbers Only)
                String contactDetails;
                while (true) {
                    System.out.print("Enter New Contact Details (Numbers only): ");
                    contactDetails = scanner.nextLine();
                    if (contactDetails.matches("\\d+")) {
                        break;
                    } else {
                        System.out.println("Invalid input! Contact details must contain numbers only.");
                    }
                }

                // Validate Medical History
                String medicalHistory = getValidText("Enter New Medical History (Letters only): ");

                // Update Patient Details
                p.updateDetails(name, age, gender, contactDetails, medicalHistory);
                System.out.println("Patient details updated successfully!");
                return;
            }
        }

        System.out.println("Patient ID not found.");
    }
    private static void deletePatient() {
        int id;

        // Validate Patient ID (Numbers Only)
        while (true) {
            try {
                System.out.print("Enter Patient ID to delete (Numbers only): ");
                id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break; // Exit loop if valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Patient ID must be a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Iterate through patients and remove if found
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
        System.out.print("Enter Patient ID (Numbers only) or Name (Letters only) to search: ");
        String input = scanner.nextLine().trim();

        // Check if input is a number (Patient ID search)
        if (input.matches("\\d+")) {
            int id = Integer.parseInt(input);

            for (Patient p : patients) {
                if (p.id == id) {
                    System.out.println(p);
                    return;
                }
            }
        }
        // Check if input is a valid name (Letters Only)
        else if (input.matches("[a-zA-Z ]+")) {
            for (Patient p : patients) {
                if (p.name.equalsIgnoreCase(input)) {
                    System.out.println(p);
                    return;
                }
            }
        }
        else {
            System.out.println("Invalid input! Please enter a valid Patient ID (numbers) or Name (letters).");
            return;
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

        // Patient ID Validation (Only Integer)
        int patientId = getValidInteger("Enter Patient ID (numbers only): ");

        // Doctor Name Validation (Only Letters)
        String doctor = getValidString("Enter Doctor Name (letters only): ");

        // Date Validation (YYYY-MM-DD)
        String date;
        do {
            System.out.print("Enter Date (YYYY-MM-DD): ");
            date = scanner.nextLine();
            if (!isValidDate(date)) {
                System.out.println("❌ Invalid date format! Please use YYYY-MM-DD.");
            }
        } while (!isValidDate(date));

        // Time Validation (HH:MM)
        String time;
        do {
            System.out.print("Enter Time (HH:MM): ");
            time = scanner.nextLine();
            if (!isValidTime(time)) {
                System.out.println("❌ Invalid time format! Please use HH:MM.");
            }
        } while (!isValidTime(time));

        // Store the appointment
        appointments.add(new Appointment(appointmentId, patientId, doctor, date, time));
        System.out.println("✅ Appointment scheduled successfully! ID: " + appointmentId);
    }

    // Method to get a valid integer (Patient ID)
    private static int getValidInteger(String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {  // Only digits allowed
                number = Integer.parseInt(input);
                break;
            } else {
                System.out.println("❌ Invalid input! Please enter a number.");
            }
        }
        return number;
    }

    // Method to get a valid string (Doctor Name)
    private static String getValidString(String prompt) {
        String text;
        while (true) {
            System.out.print(prompt);
            text = scanner.nextLine();
            if (text.matches("[a-zA-Z ]+")) {  // Only letters and spaces allowed
                break;
            } else {
                System.out.println("❌ Invalid input! Name should contain only letters.");
            }
        }
        return text;
    }

    // Date Validation
    private static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Time Validation
    private static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
        int appointmentId = getValidInteger("Enter Appointment ID (numbers only): ");

        for (Appointment a : appointments) {
            if (a.appointmentId == appointmentId) {
                String date;
                do {
                    System.out.print("Enter New Date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    if (!isValidDate(date)) {
                        System.out.println("❌ Invalid date format! Please use YYYY-MM-DD.");
                    }
                } while (!isValidDate(date));

                String time;
                do {
                    System.out.print("Enter New Time (HH:MM): ");
                    time = scanner.nextLine();
                    if (!isValidTime(time)) {
                        System.out.println("❌ Invalid time format! Please use HH:MM.");
                    }
                } while (!isValidTime(time));

                a.reschedule(date, time);
                System.out.println("✅ Appointment rescheduled successfully!");
                return;
            }
        }
        System.out.println("❌ Appointment ID not found.");
    }

    private static void cancelAppointment() {
        int appointmentId = getValidInteger("Enter Appointment ID (numbers only): ");

        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment a = iterator.next();
            if (a.appointmentId == appointmentId) {
                iterator.remove();
                System.out.println("✅ Appointment canceled successfully!");
                return;
            }
        }
        System.out.println("❌ Appointment ID not found.");
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
        int patientId;

        // Validate Patient ID (Numbers Only)
        while (true) {
            try {
                System.out.print("Enter Patient ID (Numbers only): ");
                patientId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break; // Exit loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Patient ID must be a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Validate text inputs (Letters only)
        String diagnosis = getValidText("Enter Diagnosis (Letters only): ");
        String prescriptions = getValidText("Enter Prescriptions (Letters only): ");
        String testResults = getValidText("Enter Test Results (Letters only): ");

        // Assuming MedicalRecord is a class that stores medical records
        medicalRecords.add(new MedicalRecord(recordId, patientId, diagnosis, prescriptions, testResults));
        System.out.println("Medical record added successfully! ID: " + recordId);
    }

    // Method to validate text input (Only letters and spaces allowed)
    private static String getValidText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.matches("[a-zA-Z ]+")) {
                return input;
            } else {
                System.out.println("Invalid input! Please enter letters only.");
            }
        }
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
        int recordId;

        // Validate Record ID (Numbers Only)
        while (true) {
            try {
                System.out.print("Enter Record ID to update (Numbers only): ");
                recordId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break; // Exit loop if valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Record ID must be a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Search for the record
        for (MedicalRecord m : medicalRecords) {
            if (m.recordId == recordId) {
                String diagnosis = getValidText("Enter New Diagnosis (Letters only): ");
                String prescriptions = getValidText("Enter New Prescriptions (Letters only): ");
                String testResults = getValidText("Enter New Test Results (Letters only): ");

                m.updateRecord(diagnosis, prescriptions, testResults);
                System.out.println("Medical record updated successfully!");
                return;
            }
        }

        System.out.println("Record ID not found.");
    }

//    // Method to validate text input (Only letters and spaces allowed)
//    private static String getValidText(String prompt) {
//        while (true) {
//            System.out.print(prompt);
//            String input = scanner.nextLine();
//            if (input.matches("[a-zA-Z ]+")) {
//                return input;
//            } else {
//                System.out.println("Invalid input! Please enter letters only.");
//            }
//        }
//    }

    private static void deleteMedicalRecord() {
        int recordId;

        // Validate Record ID (Numbers Only)
        while (true) {
            try {
                System.out.print("Enter Record ID to delete (Numbers only): ");
                recordId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break; // Exit loop if valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Record ID must be a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        // Iterate through medical records and remove if found
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
