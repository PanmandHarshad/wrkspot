CREATE TABLE IF NOT EXISTS customer (
    customer_id VARCHAR(255) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    spending_limit DECIMAL(19, 2) NOT NULL,
    mobile_number VARCHAR(10),
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS address (
    id BIGINT AUTO_INCREMENT,
    type VARCHAR(255) NOT NULL,
    address1 VARCHAR(255) NOT NULL,
    address2 VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    customer_id VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE IF NOT EXISTS userinfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);


--INSERT INTO customerdb.customer (customerid, firstName, lastName, age, spendingLimit, mobileNumber) VALUES
--('CUST001', 'Alice', 'Smith', 28, 50000.00, '1234567890'),
--('CUST002', 'Bob', 'Brown', 35, 120000.50, '2345678901'),
--('CUST003', 'Charlie', 'Davis', 40, 75000.25, '3456789012'),
--('CUST004', 'David', 'Wilson', 22, 30000.75, '4567890123'),
--('CUST005', 'Eve', 'Moore', 29, 95000.10, '5678901234'),
--('CUST006', 'Frank', 'Taylor', 33, 45000.80, '6789012345'),
--('CUST007', 'Grace', 'Anderson', 27, 60000.30, '7890123456'),
--('CUST008', 'Hank', 'Thomas', 50, 200000.00, '8901234567'),
--('CUST009', 'Ivy', 'Jackson', 38, 105000.45, '9012345678'),
--('CUST010', 'Jack', 'White', 45, 500000.00, '0123456789'),
--('CUST011', 'Kathy', 'Harris', 26, 32000.90, '1234509876'),
--('CUST012', 'Leo', 'Martin', 37, 150000.00, '2345609876'),
--('CUST013', 'Mona', 'Garcia', 42, 70000.00, '3456709876'),
--('CUST014', 'Nina', 'Martinez', 30, 85000.50, '4567809876'),
--('CUST015', 'Oscar', 'Robinson', 34, 93000.00, '5678909876');


--INSERT INTO wrkspot.address (type, address1, address2, city, state, zipCode, customer_id) VALUES
--('Home', '123 Main St', 'Apt 1A', 'Chicago', 'IL', '62701', 'CUST001'),
--('Work', '456 Elm St', 'Suite 200', 'Naperville', 'IL', '62702', 'CUST001'),
--('Home', '789 Oak St', '', 'Peoria', 'IL', '62703', 'CUST002'),
--('Home', '123 Maple St', '', 'Rockford', 'IL', '62704', 'CUST003'),
--('Work', '456 Pine St', '', 'Evanston', 'IL', '62705', 'CUST003'),
--('Home', '789 Birch St', '', 'Joliet', 'IL', '62706', 'CUST004'),
--('Home', '123 Cedar St', '', 'Aurora', 'IL', '62707', 'CUST005'),
--('Work', '456 Walnut St', '', 'St. Louis', 'MO', '62708', 'CUST005'),
--('Home', '789 Chestnut St', '', 'Indianapolis', 'IN', '62709', 'CUST006'),
--('Home', '123 Ash St', '', 'Madison', 'WI', '62710', 'CUST007'),
--('Home', '456 Redwood St', '', 'Des Moines', 'IA', '62711', 'CUST008'),
--('Home', '789 Cypress St', '', 'Minneapolis', 'MN', '62712', 'CUST009'),
--('Work', '123 Poplar St', '', 'Detroit', 'MI', '62713', 'CUST009'),
--('Home', '456 Willow St', '', 'Cincinnati', 'OH', '62714', 'CUST010'),
--('Home', '789 Fir St', '', 'Louisville', 'KY', '62715', 'CUST011'),
--('Home', '123 Pine St', '', 'Nashville', 'TN', '62716', 'CUST012'),
--('Home', '456 Spruce St', '', 'Atlanta', 'GA', '62717', 'CUST013'),
--('Work', '789 Juniper St', '', 'Birmingham', 'AL', '62718', 'CUST013'),
--('Home', '123 Yew St', '', 'New Orleans', 'LA', '62719', 'CUST014'),
--('Home', '456 Elder St', '', 'Miami', 'FL', '62720', 'CUST015');
--
---- Additional addresses for customers with two addresses
--INSERT INTO wrkspot.address (type, address1, address2, city, state, zipCode, customer_id) VALUES
--('Home', '123 Apple St', 'Apt 2B', 'Tampa', 'FL', '62721', 'CUST006'),
--('Home', '456 Banana St', 'Apt 3C', 'Orlando', 'FL', '62722', 'CUST007'),
--('Work', '789 Cherry St', 'Suite 101', 'Charlotte', 'NC', '62723', 'CUST008'),
--('Home', '123 Date St', '', 'Raleigh', 'NC', '62724', 'CUST009'),
--('Home', '456 Fig St', '', 'Charleston', 'SC', '62725', 'CUST010');
