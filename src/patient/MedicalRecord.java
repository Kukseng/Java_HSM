package patient;

public class MedicalRecord {
    int recordId;
    int patientId;
    String diagnosis;
    String prescriptions;
    String testResults;

    public MedicalRecord(int recordId, int patientId, String diagnosis, String prescriptions, String testResults) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.testResults = testResults;
    }

    public void updateRecord(String diagnosis, String prescriptions, String testResults) {
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.testResults = testResults;
    }

    @Override
    public String toString() {
        return "Record ID: " + recordId + ", Patient ID: " + patientId + ", Diagnosis: " + diagnosis + ", Prescriptions: " + prescriptions + ", Test Results: " + testResults;
    }
}
