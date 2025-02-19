package patient;

public class Patient {
    int id;
    String name;
    int age;
    String gender;
    String contactDetails;
    String medicalHistory;

    public Patient(int id, String name, int age, String gender, String contactDetails, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.medicalHistory = medicalHistory;
    }

    public void updateDetails(String name, int age, String gender, String contactDetails, String medicalHistory) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactDetails = contactDetails;
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Contact: " + contactDetails + ", Medical History: " + medicalHistory;
    }
}
