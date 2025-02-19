package admin.controllers.admin;

import admin.controllers.doctor.DoctorController;

public class Admin extends DoctorController {

    DoctorController docC = new DoctorController();

}


/*
*   controllers
*       _admincontroller
*       _patient___
*       _doctror___
*   models
*       _admin
*       _patient
*       _doctor
*   services
*       _adminservice
*           _Iservicess__
*       _patientservice
*           _I____
        _doctorservice
            _____
    utils
    *   _tables
    *   ----
    *
   repository
   *    _postgres_db_connection
   *
   security
   *    _authentication
   *    _password_encryption::using base64
    * */
