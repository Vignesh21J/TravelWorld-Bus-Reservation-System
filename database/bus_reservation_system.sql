CREATE DATABASE bus_reservation_system;
USE bus_reservation_system;

-- CREATE TABLE Bus (
-- 	bus_no VARCHAR(20) PRIMARY KEY,
--     is_ac BOOLEAN NOT NULL,
--     capacity INT NOT NULL,
--     price INT NOT NULL,
--     driver_name VARCHAR(60) NOT NULL,
--     from_location VARCHAR(60) NOT NULL,
--     to_location VARCHAR(60) NOT NULL,
--     duration VARCHAR(20) NOT NULL
-- );

-- CREATE TABLE BOOKING (
-- 	booking_id VARCHAR(20) PRIMARY KEY,
--     passenger_name VARCHAR(60) NOT NULL,
--     age INT NOT NULL,
--     gender VARCHAR(20) NOT NULL,
--     bus_no VARCHAR(20) NOT NULL,
--     journey_date DATE NOT NULL,
--     FOREIGN KEY(bus_no) REFERENCES BUS(bus_no)
-- );

-- SHOW TABLES;


-- INSERT INTO BUS VALUES
-- ('0001', FALSE, 3, 700, 'K AnbuMani', 'Madurai', 'Chennai', '10 hrs'),

-- ('TN58AB0002', TRUE, 70, 900, 'S Raman', 'Madurai', 'Chennai', '8 hrs'),

-- ('TN11AB0001', TRUE, 60, 800, 'R Muthu', 'Chennai', 'Madurai', '9 hrs'),

-- ('TN11AB0002', FALSE, 50, 750, 'M Kamatchi', 'Chennai', 'Madurai', '11 hrs'),

-- ('TN42AB0001', TRUE, 60, 500, 'M Rajan', 'Chennai', 'Coimbatore', '6 hrs'),

-- ('TN42AB0002', TRUE, 50, 400, 'K Senthil', 'Coimbatore', 'Chennai', '7 hrs');


-- select * from BUS;

-- ALTER TABLE BUS 
-- ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- ALTER TABLE BOOKING
-- ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;


DROP TABLE BOOKING;
DROP TABLE BUS;



-- NEW / UPDATED
CREATE TABLE BUS (
	bus_no VARCHAR(20) PRIMARY KEY,
    capacity INT NOT NULL,
    is_ac BOOLEAN NOT NULL,
    ticket_price DOUBLE NOT NULL,
    traveling_duration VARCHAR(20) NOT NULL,
    driver_name VARCHAR(60) NOT NULL,
    from_location VARCHAR(60) NOT NULL,
    to_location VARCHAR(60) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE BOOKING (
	booking_id VARCHAR(20) PRIMARY KEY,
    passenger_name VARCHAR(60) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    bus_no VARCHAR(20) NOT NULL,
    journey_date DATE NOT NULL,
    amount_paid DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(bus_no) REFERENCES BUS(bus_no)
);

SHOW TABLES;

INSERT INTO BUS (bus_no, capacity, is_ac, ticket_price, traveling_duration, driver_name, from_location, to_location)
VALUES ('0001', 2, TRUE, 1200.00, '8 hrs','Ram', 'Madurai', 'Chennai'), ('0002', 3, TRUE, 1000.00, '6 hrs', 'Sentil', 'Chennai', 'Madurai'),
		('TN11AB0001', 60, TRUE, 850.00, '9 hrs', 'R Muthu', 'Madurai', 'Chennai'), ('TN11AB0002', 50, FALSE, 750.00, '11 hrs', 'M Kamatchi', 'Chennai', 'Madurai'),
		('TN42AB0001', 60, TRUE, 500.00, '6 hrs', 'M Rajan', 'Coimbatore', 'Chennai'), ('TN42AB0002', 45, FALSE, 550.00, '7 hrs', 'K Senthil', 'Coimbatore', 'Chennai'),
        ('TN69AB0001', 60, TRUE, 1000.00, '10 hrs', 'B Sekar', 'Bengaluru', 'Chennai'), ('TN69AB0002', 45, TRUE, 950.00, '11 hrs', 'P Suresh', 'Chennai', 'Bengaluru');

-- select * from BUS;
SELECT * FROM BUS
ORDER BY created_at ASC;