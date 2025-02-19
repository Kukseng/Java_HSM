package patient;

public class Appointment {
    int appointmentId;
    int patientId;
    String doctor;
    String date;
    String time;

    public Appointment(int appointmentId, int patientId, String doctor, String date, String time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public void reschedule(String date, String time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient ID: " + patientId + ", Doctor: " + doctor + ", Date: " + date + ", Time: " + time;
    }
}
