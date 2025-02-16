package doctor;

import doctor.controllers.DoctorServiceImpl;
import doctor.models.Doctor;
import doctor.models.SpecializationTable;
import doctor.services.DoctorService;

import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorHandler {
    final static String BLUE = "\u001B[34m";
    final static String RESET = "\u001B[0m";
    final static String BOLD_GREEN = "\033[1;92m";
    final static String BRIGHT_GREEN = "\033[92m";

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
        table.addCell(BRIGHT_GREEN + "Add Doctor" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "2" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Update Doctor" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "3" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Delete Doctor" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "4" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Show Doctor Information" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "5" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "View Appointment" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "6" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Manage Doctor Availability" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "0" + RESET, centerStyle);
        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);



        String[] tableLines = table.render().split("\n");

        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

    }

    public static void main(String[] args) {
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        SpecializationTable spt = new SpecializationTable();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        do {
            tableGenerator();

            int leftPadding = (consoleWidth - tableWidth) / 2;
            String padding = " ".repeat(leftPadding);
            System.out.print(BRIGHT_GREEN + padding + "Enter your choice: " + RESET);

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(BRIGHT_GREEN + padding + "Please enter a number (0-5)" + RESET);
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print(BRIGHT_GREEN + padding + "Enter Doctor ID: " + RESET);
                        String id = scanner.nextLine().trim();
                        if (id.isEmpty()) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Doctor ID cannot be empty" + RESET);
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Enter Doctor Name: " + RESET);
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Doctor name cannot be empty");
                        }

                        spt.specializationGenerator();
                        System.out.print(BRIGHT_GREEN + padding + "Enter Specialization (e.g., CARDIOLOGY): " + RESET);

                        String specializationInput = scanner.nextLine().trim();
                        Doctor.Specialization specialization;
                        try {
                            specialization = Doctor.Specialization.valueOf(specializationInput.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Invalid specialization. Please enter a valid specialization");
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Enter Contact Details: " + RESET);
                        String contactDetails = scanner.nextLine().trim();
                        if (contactDetails.isEmpty()) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Contact details cannot be empty" + RESET);
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Is Doctor Available? (true/false): " + RESET);
                        boolean available;
                        try {
                            available = scanner.nextBoolean();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Please enter either 'true' or 'false' for availability" + RESET);
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Enter Qualifications: " + RESET);
                        String qualifications = scanner.nextLine().trim();
                        if (qualifications.isEmpty()) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Qualifications cannot be empty!" + RESET);
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Enter Experience (years): " + RESET);
                        int experience;
                        try {
                            experience = scanner.nextInt();
                            scanner.nextLine();
                            if (experience < 0) {
                                throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Experience cannot be negative!" + RESET);
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Please enter a valid number for experience" + RESET);
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Enter Department: " + RESET);
                        String department = scanner.nextLine().trim();
                        if (department.isEmpty()) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Department cannot be empty!" + RESET);
                        }

                        System.out.print(BRIGHT_GREEN + padding + "Enter Schedule: " + RESET);
                        String schedule = scanner.nextLine().trim();
                        if (schedule.isEmpty()) {
                            throw new IllegalArgumentException(BRIGHT_GREEN + padding + "Schedule cannot be empty!" + RESET);
                        }

                        Doctor newDoctor = new Doctor(id, name, specialization, contactDetails,
                                available, qualifications, experience,
                                department, schedule);

                        try {
                            doctorService.addDoctor(newDoctor);
                        } catch (Exception e) {
                            throw new RuntimeException(BRIGHT_GREEN + padding + "Failed to add doctor to the system: " + e.getMessage());
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("\nError: " + e.getMessage());
                        System.out.println(BRIGHT_GREEN + padding + "Please try again with valid input." + RESET);
                    } catch (Exception e) {
                        System.out.println("\nAn unexpected error occurred: " + e.getMessage());
                        System.out.println(BRIGHT_GREEN + padding + "Please contact system administrator." + RESET);
                    }
                }
                case 2 -> {
                    System.out.print(BRIGHT_GREEN + padding + "Enter Doctor ID to update: " + RESET);
                    String id = scanner.nextLine();
                    Doctor doctorToUpdate = null;

                    for (Doctor doc : DoctorService.doctors) {
                        if (doc.getDoctorId().equals(id)) {
                            doctorToUpdate = doc;
                            break;
                        }
                    }

                    if (doctorToUpdate == null) {
                        System.out.println(BRIGHT_GREEN + padding + "Doctor not found." + RESET);
                        continue;
                    }

                    System.out.print(BRIGHT_GREEN + padding + "Enter new name (leave blank to keep unchanged): " + RESET);
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        doctorToUpdate.setDoctorName(newName);
                    }

                    System.out.print(BRIGHT_GREEN + padding + "Enter new Specialization (leave blank to keep unchanged): " + RESET);
                    String specializationInput = scanner.nextLine();
                    if (!specializationInput.isEmpty()) {
                        doctorToUpdate.setSpecialization(Doctor.Specialization.valueOf(specializationInput.toUpperCase()));
                    }

                    System.out.print(BRIGHT_GREEN + padding + "Enter new Contact Details (leave blank to keep unchanged): " + RESET);
                    String newContactDetails = scanner.nextLine();
                    if (!newContactDetails.isEmpty()) {
                        doctorToUpdate.setContactDetails(newContactDetails);
                    }

                    doctorService.updateDoctor(doctorToUpdate);
                }
                case 3 -> {
                    System.out.print(BRIGHT_GREEN + padding + "Enter Doctor ID to delete: " + RESET);
                    String id = scanner.nextLine();
                    System.out.println();
                    System.out.print(BRIGHT_GREEN + padding + "Enter Doctor name to delete: " + RESET);
                    String name = scanner.nextLine();
                    Doctor doctorToDelete = null;

                    for (Doctor doc : DoctorService.doctors) {
                        if (doc.getDoctorId().equals(id) && doc.getDoctorName().equals(name)) {
                            doctorToDelete = doc;
                            break;
                        }
                    }

                    if (doctorToDelete != null) {
                        doctorService.deleteDoctor(doctorToDelete);
                    } else {
                        System.out.println(BRIGHT_GREEN + padding + "Doctor not found." + RESET);
                    }
                }
                case 4 -> doctorService.getDoctorById();
                case 5 -> doctorService.viewAppointment();
                case 6 -> doctorService.manageDoctorAvailability(new Doctor());
                case 0 -> {
                    System.out.println(BRIGHT_GREEN + padding + "Exiting..." + RESET);
                    scanner.close();
                    return;
                }
                default -> System.out.println(BRIGHT_GREEN + padding + "Invalid choice, try again." + RESET);
            }
        } while (choice != 0);
    }

}

