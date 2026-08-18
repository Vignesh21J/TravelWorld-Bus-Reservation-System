# 🚌 Bus Reservation System

A console-based **Bus Reservation System** developed in **Java**, initially using Object-Oriented Programming and Java Collections, and later upgraded to a **JDBC + MySQL application using the DAO design pattern**.

The project demonstrates the evolution of a simple in-memory Java application into a persistent, database-driven application.

---

## 📌 Project Overview

The application allows users to:

- Search buses by source and destination
- Book bus tickets
- Check bus availability for a journey date
- View booked ticket details
- Cancel bookings
- Handle ticket fare and payment
- Generate unique booking IDs

The project was developed in two major versions:

### Version 1.0 — Java OOP

The initial version was implemented using:

- Java OOP
- `ArrayList`
- In-memory bus and booking data
- Object relationships
- Booking ID generation
- Bus search
- Ticket booking
- Booking retrieval
- Booking cancellation
- Seat availability checking

### Version 2.0 — JDBC + DAO

Version 1.0 was upgraded into a database-driven application using:

- JDBC
- MySQL
- DAO Design Pattern
- SQL
- Primary Keys
- Foreign Keys
- Database normalization
- Persistent data storage

---

## ⚙️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| OOP | Object-oriented design |
| JDBC | Database connectivity |
| MySQL | Data persistence |
| SQL | Database operations |
| DAO Pattern | Separation of database access logic |
| Console I/O | User interaction |

---

## 🏗️ Project Architecture

```text
                    Main.java
                       │
              ┌────────┴────────┐
              ▼                 ▼
          BusDAO.java      BookingDAO.java
              │                 │
              └────────┬────────┘
                       ▼
                  DBConn.java
                       │
                       ▼
                     JDBC
                       │
                       ▼
                    MySQL
                   /     \
                  ▼       ▼
                BUS     BOOKING

Responsibilities

Main.java

Application entry point

Menu handling

User interaction

Application flow


Bus.java

Bus model

Bus attributes

Bus information display

Bus search functionality


Booking.java

Booking model

Passenger information

Booking ID generation

Date handling

Booking information display


DBConn.java

JDBC database connection


BusDAO.java

Retrieve all buses

Search buses

Retrieve a bus by bus number


BookingDAO.java

Save booking

Retrieve booking

Cancel booking

Check bus availability



---

📁 Project Structure

Bus-Reservation-System
│
├── src
│   └── busresv
│       ├── Main.java
│       ├── Bus.java
│       ├── Booking.java
│       ├── DBConn.java
│       ├── BusDAO.java
│       └── BookingDAO.java
│
├── database
│   └── bus_reservation_system.sql
│
├── .gitignore
└── README.md


---

🗄️ Database Design

The application uses two main relational tables:

BUS
 │
 │ 1
 │
 │
 │ N
 ▼
BOOKING

BUS

Stores information about each bus:

Bus number

Capacity

A/C status

Ticket price

Traveling duration

Driver name

Source

Destination

Creation timestamp


BOOKING

Stores information about each reservation:

Booking ID

Passenger name

Age

Gender

Bus number

Journey date

Amount paid

Creation timestamp


Relationship

BUS.bus_no
     ▲
     │
     │ Foreign Key
     │
BOOKING.bus_no

A bus can have multiple bookings, while each booking belongs to one bus.


---

📐 Database Normalization

The database design avoids unnecessarily duplicating bus information in every booking.

For example, information such as:

Source
Destination
Duration
Driver
Ticket Price
A/C Status
Capacity

is maintained in the BUS table.

The BOOKING table stores only the bus_no as the reference to the selected bus.

When required, bus information is retrieved using a SQL JOIN.

This keeps the database structure cleaner and reduces data duplication.


---

✨ Features

🚌 Bus Management

View all available buses

Search buses by source and destination

Retrieve a bus using its bus number


🎫 Booking

Select a bus

Select journey date

Check availability

Display ticket price

Validate payment

Enter passenger details

Generate unique booking ID

Persist booking in MySQL


🔎 Booking Retrieval

Search booking using Booking ID

Display passenger and bus details


❌ Booking Cancellation

Search booking by Booking ID

Ask for cancellation confirmation

Remove booking from the database

---

🔌 JDBC & DAO Implementation

All database operations are separated into DAO classes.

BusDAO

getAllBuses()
searchBuses()
getBusByBusNo()

BookingDAO

saveBooking()
getBookingById()
cancelBooking()
isBusAvailable()

This keeps SQL and database-related code separate from the application's main flow.


---

🔐 Database Constraints

The project uses:

Primary Key

Foreign Key

NOT NULL

DEFAULT CURRENT_TIMESTAMP


These constraints help maintain data integrity and consistency.


---

🔄 Version Evolution

Version 1.0
Java OOP
   │
   ├── ArrayList
   ├── Bus Objects
   ├── Booking Objects
   ├── Search Bus
   ├── Book Ticket
   ├── View Booking
   ├── Cancel Booking
   └── Availability Check
           │
           ▼
Version 2.0
JDBC + DAO + MySQL
   │
   ├── Persistent Bus Data
   ├── Persistent Booking Data
   ├── BusDAO
   ├── BookingDAO
   ├── DBConn
   ├── SQL Queries
   ├── JOIN
   ├── Primary Key
   ├── Foreign Key
   └── Database-based Availability


---

▶️ How to Run

Prerequisites

Java JDK

MySQL Server

MySQL Connector/J

Eclipse, IntelliJ IDEA, or another Java IDE


Setup

1. Clone the repository.


2. Create the required MySQL database and tables using the SQL script provided in the database folder.


3. Configure the MySQL URL, username, and password in DBConn.java.


4. Add the MySQL Connector/J dependency to the project.


5. Run Main.java.



---

🎯 Learning Objectives

This project was developed to understand and practice:

Java OOP

Encapsulation

Constructors

Object relationships

Collections

Exception handling

JDBC

SQL

MySQL

PreparedStatement

ResultSet

DAO Design Pattern

Primary Key and Foreign Key

Database normalization

One-to-many relationships

Persistent data storage



---

🚀 Future Enhancements

Planned improvements for future versions:

Admin login and Admin Module

View all bookings by the Admin

Add, update, and delete buses by Admin

Booking status management

Seat number allocation

Transaction management


---

👨‍💻 Author

Vignesh

Java | JDBC | MySQL | SQL | OOP | DAO
