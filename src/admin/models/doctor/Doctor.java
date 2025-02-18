package admin.models.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Doctor {
    private String doctorId;
    private String doctorName;
    private Specialization specialization;
    private String contactDetails;
    private Boolean available;
    private String qualifications;
    private Integer experience;
    private String department;
    private String schedule;

    public enum Specialization {
        CARDIOLOGY,
        DERMATOLOGY,
        ENDOCRINOLOGY,
        GASTROENTEROLOGY,
        GENERAL_MEDICINE,
        NEUROLOGY,
        OBSTETRICS_GYNECOLOGY,
        ONCOLOGY,
        OPHTHALMOLOGY,
        ORTHOPEDICS,
        PEDIATRICS,
        PSYCHIATRY,
        PULMONOLOGY,
        RADIOLOGY,
        SURGERY,
        UROLOGY
    }
}
