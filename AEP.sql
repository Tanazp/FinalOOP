CREATE DATABASE IF NOT EXISTS AEP;
USE AEP;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_type ENUM('Professional', 'Institution') NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE AcademicProfessionals (
    professional_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    current_institution VARCHAR(100),
    academic_position VARCHAR(100),
    education_background TEXT,
    expertise_area TEXT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE AcademicInstitutions (
    institution_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    institution_name VARCHAR(100),
    address TEXT,
    courses_offered TEXT,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    institution_id INT,
    course_title VARCHAR(100),
    course_code VARCHAR(20),
    term ENUM('24F', '24S', '24W'),
    outline TEXT,
    schedule ENUM('Morning', 'Afternoon', 'Evening'),
    delivery_method ENUM('In-Person', 'Remote', 'Hybrid'),
    compensation DECIMAL(10,2),
    preferred_qualifications TEXT,
    FOREIGN KEY (institution_id) REFERENCES AcademicInstitutions(institution_id) ON DELETE CASCADE
);

CREATE TABLE Requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    professional_id INT,
    status ENUM('Pending', 'Accepted', 'Rejected') DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES Courses(course_id) ON DELETE CASCADE,
    FOREIGN KEY (professional_id) REFERENCES AcademicProfessionals(professional_id) ON DELETE CASCADE
);

CREATE TABLE Notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    professional_id INT,
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (professional_id) REFERENCES AcademicProfessionals(professional_id) ON DELETE CASCADE
);

