DROP TABLE IF EXISTS Instrument_Manufactured;
DROP TABLE IF EXISTS Instrument_Deployed;
DROP TABLE IF EXISTS Instrument_Error;
DROP TABLE IF EXISTS Service_Engineer;
DROP TABLE IF EXISTS Service_Job;
DROP TABLE IF EXISTS Cartridge_Manufactured;
DROP TABLE IF EXISTS Patient_Info;
DROP TABLE IF EXISTS Clinical_Test_Instance;
DROP TABLE IF EXISTS Patient_Ground_Truth;

CREATE TABLE Instrument_Manufactured ( 
       instrument_id VARCHAR (20),
       manufactured_timestamp TIMESTAMP,
       manufactured_location TEXT,
       subsystem_1_id VARCHAR (20),
       subsystem_2_id VARCHAR (20),
       subsystem_3_id VARCHAR (20),
       PRIMARY KEY (instrument_id )
);

INSERT INTO Instrument_Manufactured (instrument_id, manufactured_timestamp, manufactured_location, 
                                                         subsystem_1_id, subsystem_2_id, subsystem_3_id)
       VALUES  	('2017040300001', '2017-04-03 13:01:00', 'Rochester, NY', 'sub1_001', 'sub2_001', 'sub3_001'),
        	('2017040300002', '2017-04-03 13:02:00', 'Rochester, NY', 'sub1_002', 'sub2_002', 'sub3_002'),
                ('2017040300003', '2017-04-03 13:02:00', 'Rochester, NY', 'sub1_002', 'sub2_002', 'sub3_002');

CREATE TABLE Instrument_Deployed (
       instrument_id VARCHAR (20),
       installation_timestamp TIMESTAMP,
       customer_id VARCHAR (20),
       customer_name VARCHAR (50),
       customer_location TEXT,
       contact_name VARCHAR (50),
       contact_telephone VARCHAR (25),
       contact_email VARCHAR (50),
       customer_since DATE DEFAULT '0000-00-00',
       assay_types_enabled VARCHAR (50),
       PRIMARY KEY (instrument_id )
);

INSERT INTO Instrument_Deployed (instrument_id, installation_timestamp, customer_id, customer_name, customer_location, 
                                    contact_name, contact_telephone, contact_email, customer_since, assay_types_enabled)
       VALUES  	('2017040300001', '2017-04-03 13:01:00', 'cust000101', 'Cowboys', 'Dallas, TX', 
                               'Jerry Jones', '555-123-4567', 'jerry@cowboys.com', '1989-02-14', '0010 0000'),
        	('2017040300002', '2017-04-03 14:02:30', 'cust000102', 'ACME_1', 'New York, NY', 
                               'Derek Jeter', '555-222-3333', 'jeter@yankees.com', '1995-03-21', '1010 0000');

CREATE TABLE Instrument_Error (
       error_counter BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
       instrument_id VARCHAR (20),
       instrument_error_code VARCHAR (20),
       instrument_error_timestamp TIMESTAMP,
       PRIMARY KEY (error_counter )
);

INSERT INTO Instrument_Error (instrument_id, instrument_error_code, instrument_error_timestamp)
       VALUES  	('2017040300001', '11111', '2017-04-04 13:00:00'),
        	('2017040300002', '22222', '2017-04-04 14:00:00'),
                ('2017040300001', '3333', '2017-04-04 15:00:00'),
                ('2017040300002', '4444', '2017-04-04 16:00:00');

CREATE TABLE Service_Engineer (
       service_engineer_id VARCHAR (20),
       service_engineer_location TEXT,
       service_engineer_name VARCHAR (50),
       service_engineer_telephone VARCHAR (25),
       service_engineer_email VARCHAR (50),
       PRIMARY KEY (service_engineer_id )
);

INSERT INTO Service_Engineer (service_engineer_id, service_engineer_location, service_engineer_name, 
                         service_engineer_telephone, service_engineer_email)
       VALUES  	('0000 0000 2000 0001', 'Rochester, NY', 'Joe Fixit', '555-456-1111', 'jfixit@sensodx.com'),
        	('0000 0000 2000 0027', 'Syracuse, NY', 'Tom Fixit', '555-456-2222', 'tfixit@sensodx.com');

CREATE TABLE Service_Job (
       service_job_counter BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
       instrument_id VARCHAR (20),
       service_categories VARCHAR (50),
       comment TEXT,
       service_engineer_id VARCHAR (20),
       service_complete_timestamp TIMESTAMP,
       PRIMARY KEY (service_job_counter )
);

INSERT INTO Service_Job (instrument_id, service_categories, comment, service_engineer_id, service_complete_timestamp )
       VALUES  	('2017040300001', '1001 0000 1111 0111', 'Machine was very dirty inside', 
                                                          '0000 0000 2000 0001', '2017-04-04 15:00:00'),
        	('2017040300002', '1011 0000 1111 0100', 'Optics was mis-alligned', 
                                                          '0000 0000 2000 0027', '2017-05-03 15:00:00');

CREATE TABLE Cartridge_Manufactured (
       cartridge_id VARCHAR (20),
       manufactured_timestamp TIMESTAMP,
       manufactured_location TEXT,
       assay_type VARCHAR (50),
       subsystem_1_id VARCHAR (20),
       subsystem_2_id VARCHAR (20),
       subsystem_3_id VARCHAR (20),
       PRIMARY KEY (cartridge_id )
);

INSERT INTO Cartridge_Manufactured (cartridge_id, manufactured_timestamp, manufactured_location, assay_type,
                                         subsystem_1_id, subsystem_2_id, subsystem_3_id)
       VALUES  	('2017040300001', '2017-04-04 15:00:00', 'Perinton, NY', '0000 0100 0000 0000',
                                          '0000000010000001', '0000000020000001', '0000000030000001'),
        	('2017040300002', '2017-03-29 08:59:00', 'Perinton, NY', '0010 0000 0000 0000',
                                          '0000000010000002', '0000000020000002', '0000000030000002');


CREATE TABLE Patient_Info (
       patient_id VARCHAR (20),
       patient_dob DATE,
       patient_gender CHAR (1),
       patient_race VARCHAR (25),
       patient_location TEXT, 
       PRIMARY KEY (patient_id )
);

INSERT INTO Patient_Info (patient_id, patient_dob, patient_gender, patient_race, patient_location )
       VALUES  	('0000 0000 2000 0001p', '1960-11-12', 'M', 'White', 'Rochester, NY'),
        	('0000 0000 2000 0002p', '1955-12-22', 'F', 'Hispanic', 'Bimghamton, NY');

CREATE TABLE Clinical_Test_Instance (
       clinical_test_counter BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
       cartridge_id VARCHAR (20),
       instrument_id VARCHAR (20),
       patient_id VARCHAR (20),
       technician_id VARCHAR (20),
       doctor_id VARCHAR (20),
       raw_assay_data VARCHAR (100),
       analysis_result VARCHAR (100),
       clinical_test_timestamp TIMESTAMP,
       PRIMARY KEY (clinical_test_counter )
);

INSERT INTO Clinical_Test_Instance (cartridge_id, instrument_id, patient_id, technician_id, doctor_id, 
                                                         raw_assay_data, analysis_result, clinical_test_timestamp)
       VALUES  	('2017040300001c', '2017040300001', '0000 0000 1000 0001p', '0000 1000 1000 0001t', '1000 1000 1000 0001d', 
                     'reference to image', '0.1234567890', '2017-04-03 13:01:00'),
        	('2017040300002c', '2017040300002', '0000 0000 1000 0002p', '0000 1000 1000 0002t', '1000 1000 1200 0001d', 
                     'reference to image', '0.5379', '2017-04-05 13:23:00');

CREATE TABLE Patient_Ground_Truth (
       ground_truth_counter BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
       patient_id VARCHAR (20),
       clinical_description TEXT,
       clinical_state VARCHAR (25),
       ground_truth_timestamp TIMESTAMP,
       PRIMARY KEY (ground_truth_counter )
);

INSERT INTO Patient_Ground_Truth (patient_id, clinical_description, clinical_state, ground_truth_timestamp)
       VALUES  	('0000 0000 2000 0001p', 'deceased', '1234 2345 3456 4567', '2017-04-05 13:23:00'),
        	('0000 0000 2000 0002p', 'complete remission', '1000 2345 3456 0003', '2017-04-07 09:55:00');

