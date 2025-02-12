package doctor;

import doctor.controllers.DoctorServiceImpl;
import doctor.models.Doctor;
import doctor.services.DoctorService;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.CellStyle;

import java.util.Scanner;

public class DoctorHandler {
    public void operation() {
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        Scanner scanner = new Scanner(System.in);

        Table table = new Table(2, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        while (true) {
            table.addCell("     Option      ", centerStyle);
            table.addCell("         Action                     ", centerStyle);
            table.addCell("     1       ", centerStyle);
            table.addCell("             Add Doctor                     ", centerStyle);
            table.addCell("     2       ", centerStyle);
            table.addCell("                 Update Doctor                      ", centerStyle);
            table.addCell("     3       ", centerStyle);
            table.addCell("                  Delete Doctor                       ", centerStyle);
            table.addCell("     4       ", centerStyle);
            table.addCell("                    Search Doctor By ID                    ", centerStyle);
            table.addCell("     5       ", centerStyle);
            table.addCell("         Exit                       ", centerStyle);

            System.out.println(table.render());
            System.out.print("Enter your choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Doctor ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Doctor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Specialization (e.g., CARDIOLOGY): ");
                    String specializationInput = scanner.nextLine();
                    Doctor.Specialization specialization = Doctor.Specialization.valueOf(specializationInput.toUpperCase());
                    System.out.print("Enter Contact Details: ");
                    String contactDetails = scanner.nextLine();
                    System.out.print("Is Doctor Available? (true/false): ");
                    boolean available = scanner.nextBoolean();
                    scanner.nextLine();
                    System.out.print("Enter Qualifications: ");
                    String qualifications = scanner.nextLine();
                    System.out.print("Enter Experience (years): ");
                    int experience = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter Schedule: ");
                    String schedule = scanner.nextLine();

                    Doctor newDoctor = new Doctor(id, name, specialization, contactDetails, available, qualifications, experience, department, schedule);
                    doctorService.addDoctor(newDoctor);
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
                        doctorToUpdate.setContactDetails(newContactDetails);
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
                case 4 -> doctorService.getDoctorById();
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}

