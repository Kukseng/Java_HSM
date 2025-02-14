package doctor;

import doctor.controllers.DoctorServiceImpl;
import doctor.models.Doctor;
import doctor.services.DoctorService;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorHandler {
    static void tableGenerator() {
        final String BLUE = "\u001B[34m";
        final String RESET = "\u001B[0m";
        final String YELLOW = "\u001B[33m";
        final String BOLD_GREEN = "\033[1;92m";
        final String BRIGHT_GREEN = "\033[92m";

        int consoleWidth = 180;

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
        table.addCell(BRIGHT_GREEN + "Exit" + RESET, centerStyle);

        int tableWidth = 100;
        int leftPadding = (consoleWidth - tableWidth) / 2;
        String padding = " ".repeat(leftPadding);

        String[] tableLines = table.render().split("\n");

        for (String line : tableLines) {
            System.out.println(BLUE + padding + line + RESET);
        }

    }

    public static void main(String[] args) {
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        Scanner scanner = new Scanner(System.in);
        int choice;
        final String CLEAR_SCREEN = "\033[2J";

        do {
            tableGenerator();

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("Enter Doctor ID: ");
                        String id = scanner.nextLine().trim();
                        if (id.isEmpty()) {
                            throw new IllegalArgumentException("Doctor ID cannot be empty");
                        }

                        System.out.print("Enter Doctor Name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            throw new IllegalArgumentException("Doctor name cannot be empty");
                        }

                        System.out.print("Enter Specialization (e.g., CARDIOLOGY): ");
                        String specializationInput = scanner.nextLine().trim();
                        Doctor.Specialization specialization;
                        try {
                            specialization = Doctor.Specialization.valueOf(specializationInput.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            throw new IllegalArgumentException("Invalid specialization. Please enter a valid specialization");
                        }

                        System.out.print("Enter Contact Details: ");
                        String contactDetails = scanner.nextLine().trim();
                        if (contactDetails.isEmpty()) {
                            throw new IllegalArgumentException("Contact details cannot be empty");
                        }

                        System.out.print("Is Doctor Available? (true/false): ");
                        boolean available;
                        try {
                            available = scanner.nextBoolean();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            throw new IllegalArgumentException("Please enter either 'true' or 'false' for availability");
                        }

                        System.out.print("Enter Qualifications: ");
                        String qualifications = scanner.nextLine().trim();
                        if (qualifications.isEmpty()) {
                            throw new IllegalArgumentException("Qualifications cannot be empty!");
                        }

                        System.out.print("Enter Experience (years): ");
                        int experience;
                        try {
                            experience = scanner.nextInt();
                            scanner.nextLine();
                            if (experience < 0) {
                                throw new IllegalArgumentException("Experience cannot be negative!");
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            throw new IllegalArgumentException("Please enter a valid number for experience");
                        }

                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine().trim();
                        if (department.isEmpty()) {
                            throw new IllegalArgumentException("Department cannot be empty!");
                        }

                        System.out.print("Enter Schedule: ");
                        String schedule = scanner.nextLine().trim();
                        if (schedule.isEmpty()) {
                            throw new IllegalArgumentException("Schedule cannot be empty!");
                        }

                        Doctor newDoctor = new Doctor(id, name, specialization, contactDetails,
                                available, qualifications, experience,
                                department, schedule);

                        try {
                            doctorService.addDoctor(newDoctor);
                            System.out.println("\nDoctor added successfully!");
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to add doctor to the system: " + e.getMessage());
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("\nError: " + e.getMessage());
                        System.out.println("Please try again with valid input.");
                    } catch (Exception e) {
                        System.out.println("\nAn unexpected error occurred: " + e.getMessage());
                        System.out.println("Please contact system administrator.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Doctor ID to update: ");
                    String id = scanner.nextLine();
                    Doctor doctorToUpdate = null;

                    for (Doctor doc : DoctorService.doctors) {
                        if (doc.getId().equals(id)) {
                            doctorToUpdate = doc;
                            break;
                        }
                    }

                    if (doctorToUpdate == null) {
                        System.out.println("Doctor not found.");
                        continue;
                    }

                    System.out.print("Enter new name (leave blank to keep unchanged): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        doctorToUpdate.setName(newName);
                    }

                    System.out.print("Enter new Specialization (leave blank to keep unchanged): ");
                    String specializationInput = scanner.nextLine();
                    if (!specializationInput.isEmpty()) {
                        doctorToUpdate.setSpecialization(Doctor.Specialization.valueOf(specializationInput.toUpperCase()));
                    }

                    System.out.print("Enter new Contact Details (leave blank to keep unchanged): ");
                    String newContactDetails = scanner.nextLine();
                    if (!newContactDetails.isEmpty()) {
                        doctorToUpdate.setPhoneNumber(newContactDetails);
                    }

                    doctorService.updateDoctor(doctorToUpdate);
                }
                case 3 -> {
                    System.out.print("Enter Doctor ID to delete: ");
                    String id = scanner.nextLine();
                    System.out.println();
                    System.out.print("Enter Doctor name to delete: ");
                    String name = scanner.nextLine();
                    Doctor doctorToDelete = null;

                    for (Doctor doc : DoctorService.doctors) {
                        if (doc.getId().equals(id) && doc.getName().equals(name)) {
                            doctorToDelete = doc;
                            break;
                        }
                    }

                    if (doctorToDelete != null) {
                        doctorService.deleteDoctor(doctorToDelete);
                    } else {
                        System.out.println("Doctor not found.");
                    }
                }
                case 4 -> {
                    doctorService.getDoctorById();
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);
    }

}

