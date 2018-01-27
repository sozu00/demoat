
use demoat; 

drop procedure createDoctors;
drop procedure createPatient;
drop procedure createClinics;
drop procedure createRooms;
drop procedure createConsultations;
drop procedure createAppointment;
drop procedure createAll;

DELIMITER $$
CREATE PROCEDURE createDoctors()
BEGIN
    DECLARE i int DEFAULT 1;
    set i = 1;
    WHILE i <= 10 DO
        INSERT INTO doctor (email, name) VALUES ('@gmail.com', 'Doctor');
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE createPatient()
BEGIN
	DECLARE i int DEFAULT 1;
    WHILE i <= 10 DO
        INSERT INTO patient (name) VALUES ('Paciente');
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE createClinics()
BEGIN
    DECLARE i int DEFAULT 1;
    WHILE i <= 10 DO
        INSERT INTO clinic(id) VALUES (i);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE createRooms()
BEGIN
    DECLARE i int DEFAULT 1;
    WHILE i <= 10 DO
        INSERT INTO room(room_number, clinic_id) VALUES (i, i);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE createConsultations()
BEGIN
    DECLARE i int DEFAULT 1;
    WHILE i <= 10 DO
        INSERT INTO consultation(day, turn, doctor_id, room_id) VALUES (curdate(),"M", i,i);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE createAppointment()
BEGIN
    DECLARE i int DEFAULT 1;
    WHILE i <= 10 DO
        INSERT INTO appointment(consultation_id, patient_id) VALUES (i,i);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE createAll()
BEGIN
  DECLARE EXIT HANDLER FOR 1452 ROLLBACK;
  START TRANSACTION;
  	call createDoctors;
  	call createPatient;
  	call createClinics;
  	call createRooms;
  	call createConsultations;
  	call createAppointment;
  COMMIT;
END$$
DELIMITER ;

call createAll();
 