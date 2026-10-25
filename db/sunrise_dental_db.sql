-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 04, 2026 at 10:06 AM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sunrise_dental_db`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `sp_GenerateBill`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_GenerateBill` (IN `p_appointment_no` VARCHAR(20), IN `p_bill_no` VARCHAR(20))   BEGIN
    DECLARE v_consultation_fee DOUBLE;
    DECLARE v_treatment_cost DOUBLE;
    DECLARE v_total_cost DOUBLE;
    DECLARE v_appointment_id INT;

    -- Fetch fees and IDs
    SELECT a.id, d.consultation_fee, t.treatment_cost
    INTO v_appointment_id, v_consultation_fee, v_treatment_cost
    FROM appointment a
    JOIN dentist d ON a.dentist_id = d.id
    JOIN treatment t ON a.treatment_id = t.id
    WHERE a.appointment_no = p_appointment_no;

    -- Calculate total cost
    SET v_total_cost = v_consultation_fee + v_treatment_cost;

    -- Insert record into bill table
    INSERT INTO bill (bill_no, appointment_id, total_cost)
    VALUES (p_bill_no, v_appointment_id, v_total_cost);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `appointment_no` varchar(20) NOT NULL,
  `appt_date` date NOT NULL,
  `appt_time` varchar(10) NOT NULL,
  `patient_id` int NOT NULL,
  `dentist_id` int NOT NULL,
  `treatment_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `appointment_no` (`appointment_no`),
  KEY `patient_id` (`patient_id`),
  KEY `dentist_id` (`dentist_id`),
  KEY `treatment_id` (`treatment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_no`, `appt_date`, `appt_time`, `patient_id`, `dentist_id`, `treatment_id`) VALUES
(14, 'A006', '2026-08-25', '09:00 AM', 15, 2, 3),
(13, 'A005', '2026-08-25', '09:30 AM', 16, 1, 1),
(12, 'A004', '2026-08-24', '11:40', 15, 2, 2),
(11, 'A003', '2026-08-24', '11:30', 14, 2, 3),
(10, 'A002', '2026-08-24', '11:30', 13, 1, 3),
(9, 'A001', '2026-08-23', '11:30', 12, 1, 1),
(15, 'A007', '2026-08-24', '11:00 AM', 15, 2, 4),
(16, 'A008', '2026-08-25', '11:30 AM', 15, 2, 2),
(17, 'A009', '2026-08-27', '10:30 AM', 15, 2, 2),
(18, 'A010', '2026-08-31', '10:30 AM', 17, 2, 2),
(19, 'A011', '2026-08-31', '11:00 AM', 18, 2, 4),
(20, 'A012', '2026-09-01', '10:30 AM', 19, 2, 4),
(21, 'A013', '2026-09-02', '09:00 AM', 20, 1, 1),
(22, 'A014', '2026-09-01', '11:30 AM', 15, 2, 3),
(23, 'A015', '2026-09-01', '11:00 AM', 21, 2, 3),
(24, 'A016', '2026-09-03', '10:00 AM', 22, 2, 3),
(25, 'A017', '2026-09-02', '10:30 AM', 15, 2, 3),
(26, 'A018', '2026-09-02', '11:00 AM', 15, 1, 1),
(27, 'A019', '2026-09-02', '10:00 AM', 15, 2, 3),
(28, 'A020', '2026-09-02', '10:00 AM', 15, 1, 3),
(29, 'A021', '2026-09-03', '10:30 AM', 23, 2, 2),
(30, 'A022', '2026-09-05', '10:00 AM', 24, 2, 3),
(31, 'A023', '2026-09-02', '09:00 AM', 15, 2, 2),
(32, 'A024', '2026-09-05', '09:00 AM', 25, 1, 3),
(33, 'A025', '2026-09-02', '11:30 AM', 26, 1, 1),
(34, 'A026', '2026-09-03', '11:00 AM', 27, 2, 2),
(35, 'A027', '2026-09-04', '10:30 AM', 28, 1, 1),
(36, 'A028', '2026-09-04', '11:00 AM', 29, 1, 1),
(37, 'A029', '2026-09-04', '09:00 AM', 30, 1, 1),
(38, 'A030', '2026-09-04', '11:00 AM', 31, 2, 2),
(39, 'A031', '2026-09-16', '10:00 AM', 33, 2, 2),
(40, 'A032', '2026-09-24', '10:00 AM', 34, 1, 3),
(41, 'A033', '2026-09-04', '11:30 AM', 35, 2, 2);

--
-- Triggers `appointment`
--
DROP TRIGGER IF EXISTS `prevent_double_booking`;
DELIMITER $$
CREATE TRIGGER `prevent_double_booking` BEFORE INSERT ON `appointment` FOR EACH ROW BEGIN
    DECLARE conflict_count INT;
    
    -- Check if the dentist already has an appointment at the requested date and time
    SELECT COUNT(*) INTO conflict_count
    FROM appointment
    WHERE dentist_id = NEW.dentist_id
      AND appt_date = NEW.appt_date
      AND appt_time = NEW.appt_time;
      
    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Booking Error: The selected Dentist is already booked for this Date and Time.';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bill_no` varchar(20) NOT NULL,
  `appointment_id` int NOT NULL,
  `total_cost` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bill_no` (`bill_no`),
  KEY `appointment_id` (`appointment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id`, `bill_no`, `appointment_id`, `total_cost`) VALUES
(3, 'B001', 9, 4000),
(4, 'B002', 10, 16500),
(5, 'B003', 11, 16500),
(6, 'B004', 12, 5500),
(7, 'B005', 13, 4000),
(8, 'B006', 14, 16500),
(9, 'B007', 15, 10000),
(10, 'B008', 16, 5500),
(11, 'B009', 17, 5500),
(12, 'B010', 18, 5500),
(13, 'B011', 19, 10000),
(14, 'B012', 31, 5500),
(15, 'B013', 32, 16500),
(16, 'B014', 29, 5500),
(17, 'B015', 33, 4000),
(18, 'B016', 34, 5500),
(19, 'B017', 35, 4000),
(20, 'B018', 36, 4000),
(21, 'B019', 37, 4000),
(22, 'B020', 38, 5500),
(23, 'B021', 41, 5500);

-- --------------------------------------------------------

--
-- Table structure for table `dentist`
--

DROP TABLE IF EXISTS `dentist`;
CREATE TABLE IF NOT EXISTS `dentist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dentist_id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact_number` varchar(15) DEFAULT NULL,
  `consultation_fee` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dentist_id` (`dentist_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dentist`
--

INSERT INTO `dentist` (`id`, `dentist_id`, `name`, `address`, `contact_number`, `consultation_fee`) VALUES
(1, 'DEN001', 'Dr. Smith', 'Colombo 03', '0719876543', 1500),
(2, 'DEN002', 'Dr. Lakmal', 'Colombo 04', '0719876545', 1500);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact_number` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient_id` (`patient_id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `patient_id`, `name`, `address`, `contact_number`, `email`) VALUES
(18, 'P1788181281083', 'asdsads', 'asdsadasd', '0898989898', NULL),
(17, 'P1788179916148', 'testffdd', 'testtt', '0787183233', NULL),
(16, 'P1787553052668', 'peri', 'asds', '0787155678', NULL),
(15, 'P1787550870858', 'laka', 'asdasds', '1234567890', NULL),
(14, 'P1787550504254', 'isurus', 'asdasd', '0808080823', NULL),
(13, 'P1787502157933', 'Lakmal', '457/A, panadura', '0879587322', NULL),
(12, 'P1787499984745', 'Isuru', 'Panadura,sri lanka', '0787195874', NULL),
(19, 'P1788238646029', 'perieis', 'Panadura, Galle', '0781111111', NULL),
(20, 'P1788245974769', 'sdss', 'asdsad', '2323223232', NULL),
(21, 'P1788257517572', 'lakmal', 'Colombo, Sri Lanka', '0771234567', NULL),
(22, 'P1788326016333', 'isurr', 'testing address', '0888888113', 'monsterj98cc@gmail.com'),
(23, 'P1788328827693', 'Isuru Lakmal', 'Colombo, Sri Lanka', '0717654321', 'sunrisedental002@gmail.com'),
(24, 'P1788329069265', 'Isuru Peiris', 'Colombo, Sri Lanka', '0761234567', 'sunrisedental002@gmail.com'),
(25, 'P1788337793208', 'Isuru Lakmal', 'Colombo, Sri Lanka', '2134567890', 'sunrisedental002@gmail.com'),
(26, 'P1788340533779', 'Isuru Lakmal', 'Panadura, Sri Lanka', '0787453213', 'sunrisedental002@gmail.com'),
(27, 'P1788409128362', 'samanthi', 'Colombo, Sri Lanka', '0987654321', 'sunrisedental002@gmail.com'),
(28, 'P1788495860025', 'Samanthi Sepalika', 'Colombo, Sri Lanka', '0789654321', 'sunrisedental002@gmail.com'),
(29, 'P1788496747296', 'asdsad', 'asdsadsa', '1234567123', ''),
(30, 'P1788505351962', 'Madusha Thamoji', 'Panadura, Sri Lanka', '0713949051', 'sunrisedental002@gmail.com'),
(31, 'P1788506183995', 'Kaveesha Sonali', 'Kandy, Sri Lanka', '0779998888', ''),
(32, 'P1788508241799', 'testuser', 'asds', '0781234567', ''),
(33, 'P1788512910460', 'Savindi', 'addadad', '0787654123', 'sunrisedental002@gmail.com'),
(34, 'P1788512962761', 'anoma', 'tstse', '0761234756', ''),
(35, 'P1788513239063', 'newnew', 'asdas', '0876543212', '');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `contact_number` varchar(15) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `name`, `address`, `contact_number`, `username`, `password`) VALUES
(1, 'Admin User', 'Clinic HQ', '0771234567', 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
CREATE TABLE IF NOT EXISTS `treatment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `treatment_id` varchar(20) NOT NULL,
  `treatment_type` varchar(100) NOT NULL,
  `treatment_cost` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `treatment_id` (`treatment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `treatment`
--

INSERT INTO `treatment` (`id`, `treatment_id`, `treatment_type`, `treatment_cost`) VALUES
(1, 'TRT001', 'General Cleaning', 2500),
(2, 'TRT002', 'Tooth Extraction', 4000),
(3, 'TRT003', 'Root Canal', 15000),
(4, 'TRT004', 'Teeth Whitening', 8500);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
