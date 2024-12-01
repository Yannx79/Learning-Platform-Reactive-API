-- Create the database
CREATE DATABASE IF NOT EXISTS db_e_learning_platform;
USE db_e_learning_platform;

-- Create the Users table
CREATE TABLE IF NOT EXISTS users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Role ENUM('Student', 'Instructor', 'Admin') NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the Courses table
CREATE TABLE IF NOT EXISTS courses (
    CourseID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(100) NOT NULL,
    Description TEXT,
    InstructorID INT NOT NULL, -- References Users.UserID but no foreign key
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the Lessons table
CREATE TABLE IF NOT EXISTS lessons (
    LessonID INT AUTO_INCREMENT PRIMARY KEY,
    CourseID INT NOT NULL, -- References Courses.CourseID but no foreign key
    Title VARCHAR(100) NOT NULL,
    Content TEXT,
    VideoURL VARCHAR(255),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the Enrollments table
CREATE TABLE IF NOT EXISTS enrollments (
    EnrollmentID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL, -- References Users.UserID but no foreign key
    CourseID INT NOT NULL, -- References Courses.CourseID but no foreign key
    EnrolledAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the Quizzes table
CREATE TABLE IF NOT EXISTS quizzes (
    QuizID INT AUTO_INCREMENT PRIMARY KEY,
    CourseID INT NOT NULL, -- References Courses.CourseID but no foreign key
    Title VARCHAR(100) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Optional: Insert sample data
INSERT INTO users (FullName, Email, Password, Role) VALUES
('Alice Johnson', 'alice@example.com', 'password123', 'Instructor'),
('Bob Smith', 'bob@example.com', 'password123', 'Student');

INSERT INTO courses (Title, Description, InstructorID) VALUES
('Introduction to Python', 'Learn the basics of Python programming.', 1);

INSERT INTO lessons (CourseID, Title, Content, VideoURL) VALUES
(1, 'Lesson 1: Variables and Data Types', 'This lesson covers variables and data types.', 'https://example.com/video1'),
(1, 'Lesson 2: Control Structures', 'This lesson covers loops and conditionals.', 'https://example.com/video2');

INSERT INTO enrollments (UserID, CourseID) VALUES
(2, 1);

INSERT INTO quizzes (CourseID, Title) VALUES
(1, 'Quiz 1: Basic Python Concepts');

-- Additional inserts for Users table
INSERT INTO users (FullName, Email, Password, Role) VALUES
('Charlie Brown', 'charlie@example.com', 'password123', 'Student'),
('Diana Prince', 'diana@example.com', 'password123', 'Instructor'),
('Ethan Hunt', 'ethan@example.com', 'password123', 'Student'),
('Fiona Gallagher', 'fiona@example.com', 'password123', 'Admin'),
('George Wilson', 'george@example.com', 'password123', 'Instructor');

-- Additional inserts for Courses table
INSERT INTO courses (Title, Description, InstructorID) VALUES
('Advanced Python', 'A deeper dive into Python programming concepts.', 2),
('Web Development Basics', 'Learn HTML, CSS, and JavaScript to build websites.', 5),
('Data Science with Python', 'Learn data analysis and visualization with Python.', 1),
('Introduction to Machine Learning', 'An introductory course on machine learning principles.', 2),
('JavaScript for Beginners', 'Learn the fundamentals of JavaScript programming.', 5);

-- Additional inserts for Lessons table
INSERT INTO lessons (CourseID, Title, Content, VideoURL) VALUES
(2, 'Lesson 1: Advanced Functions', 'Understand higher-order functions and decorators.', 'https://example.com/video3'),
(3, 'Lesson 1: HTML Basics', 'Learn about HTML structure and tags.', 'https://example.com/video4'),
(4, 'Lesson 1: Data Visualization', 'Introduction to Matplotlib and Seaborn.', 'https://example.com/video5'),
(5, 'Lesson 1: Linear Regression', 'Basics of linear regression in machine learning.', 'https://example.com/video6'),
(6, 'Lesson 1: Variables and Scopes', 'Learn about variable declaration and scoping in JavaScript.', 'https://example.com/video7');

-- Additional inserts for Enrollments table
INSERT INTO enrollments (UserID, CourseID) VALUES
(2, 2),
(3, 3),
(4, 4),
(3, 5),
(2, 6);

-- Additional inserts for Quizzes table
INSERT INTO quizzes (CourseID, Title) VALUES
(2, 'Quiz 2: Python Advanced Topics'),
(3, 'Quiz 1: HTML and CSS Basics'),
(4, 'Quiz 1: Data Visualization Techniques'),
(5, 'Quiz 1: Machine Learning Fundamentals'),
(6, 'Quiz 1: JavaScript Variables and Functions');
