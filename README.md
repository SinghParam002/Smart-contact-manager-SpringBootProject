# Smart Contact Manager System

A full-stack web application to manage contacts using **Spring Boot**, **MySQL**, **Thymeleaf**, and the **Spring Framework**. This project focuses on creating, reading, updating, and deleting contacts, along with secure user authentication.

## Features

- User authentication and authorization
- Add, edit, and delete contacts
- Display all contacts in a user-friendly UI
- RESTful APIs for managing contacts
- Integration with MySQL for persistent data storage
- Secure password handling using Spring Security

## Tech Stack

- **Backend:** Spring Boot, Spring Framework, Spring Security
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript
- **Database:** MySQL
- **Build Tool:** Maven

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/smart-contact-manager.git
   ```
2. Navigate to the project directory:
   ```bash
   cd smart-contact-manager
   ```
3. Configure your MySQL database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/contact_manager
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```
4. Build the project with Maven:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Usage

1. Open your web browser and go to `http://localhost:8080`
2. Register a new account or log in with existing credentials
3. Add, view, edit, and delete contacts from your personal dashboard

## Database Structure

- **User Table:** Stores user information (id, name, email, password)
- **Contact Table:** Stores contact details (id, name, email, phone, description, user_id)

## Screenshots

![Dashboard](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222914-08795ecc-c8f4-4d82-9605-65c448804f10.png?raw=true)
![Add Contact](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222923-1a3ed334-cec9-4293-a0d1-1a854a89f200.png?raw=true)
![Add Contact](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222933-f4307d24-894c-4818-9dcb-6813ebe39f23.png?raw=true)
![Add Contact](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222939-21d1ec57-8a35-4dff-a80a-113014e72428.png?raw=true)
![Add Contact](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222946-90b635b5-44e1-479e-b16f-d08493da9804.png?raw=true)
![Add Contact](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222952-90d7a6b2-8989-4432-bec1-9085e3dca39e.png?raw=true)
![Add Contact](https://github.com/SinghParam002/Smart-contact-manager-SpringBootProject/blob/main/142222903-9827c356-0f3a-4564-a8e4-2974e30a04e0.png?raw=true)



## Author

**Paramjeet Singh**
contact : 8923859863
Connect with me on [GitHub](https://github.com/SinghParam002) and [LinkedIn](https://linkedin.com/in/yourprofile).

---

⭐ Don’t forget to give a star if you like this project! ⭐

