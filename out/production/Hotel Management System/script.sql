DROP DATABASE IF EXISTS HotelManagement;
CREATE DATABASE IF NOT EXISTS HotelManagement;
SHOW DATABASES ;
USE HotelManagement;
#--------------------------------------------
DROP TABLE IF EXISTS Guest;
CREATE TABLE IF NOT EXISTS Guest(
    guestId VARCHAR (20) ,
    guestName VARCHAR (30) NOT NULL DEFAULT  'Unknown',
    address VARCHAR (30),
    nic VARCHAR (20),
    contact VARCHAR (20),
    CONSTRAINT PRIMARY KEY (guestId)
);
SHOW TABLES ;
DESCRIBE Guest;
#------------------------------------------------
DROP TABLE IF EXISTS Room;
CREATE TABLE IF NOT EXISTS Room(
    roomId VARCHAR (20) ,
    roomName VARCHAR (30) NOT NULL DEFAULT  'Unknown',
    roomType VARCHAR (30),
    floor VARCHAR (30),
    description VARCHAR (50),
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (roomId)
);
SHOW TABLES ;
DESCRIBE Room;
#--------------------------------------------------
DROP TABLE IF EXISTS MealPackage;
CREATE TABLE IF NOT EXISTS MealPackage(
    mealPackageId VARCHAR (20) ,
    mealPackageName VARCHAR (30) NOT NULL DEFAULT  'Unknown',
    mealPackageType VARCHAR (30),
    description VARCHAR (50),
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (mealPackageId)
);
SHOW TABLES ;
DESCRIBE MealPackage;
#----------------------------------------------------
DROP TABLE IF EXISTS Food;
CREATE TABLE IF NOT EXISTS Food(
    foodId VARCHAR (20) ,
    foodType VARCHAR (30) NOT NULL DEFAULT  'Unknown',
    qty INT,
    CONSTRAINT PRIMARY KEY (foodId)
);
SHOW TABLES ;
DESCRIBE Food;
#----------------------------------------------------
DROP TABLE IF EXISTS Bill;
CREATE TABLE IF NOT EXISTS Bill(
    billId VARCHAR (20) ,
    bookingCost DOUBLE ,
    mealPlanCost DOUBLE ,
    billDate DATE ,
    total DOUBLE ,
    CONSTRAINT PRIMARY KEY (billId)
);
SHOW TABLES ;
DESCRIBE Bill;
#-----------------------------------------------------
DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee(
    empId VARCHAR (20),
    empName VARCHAR (30),
    address VARCHAR (30),
    NIC VARCHAR (20),
    birthDay DATE ,
    contact VARCHAR (20),
    post VARCHAR (20),
     CONSTRAINT PRIMARY KEY (empId)
    );
SHOW TABLES ;
DESCRIBE Employee;
#---------------------------------
DROP TABLE IF EXISTS Login;
CREATE TABLE IF NOT EXISTS Login(
    password VARCHAR (20),
    userName VARCHAR (20),
    empId VARCHAR (20),
    CONSTRAINT PRIMARY KEY (password),
    CONSTRAINT FOREIGN KEY (empId) REFERENCES Employee(empId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE Login;
#------------------------------------------------------
DROP TABLE IF EXISTS BookingDetails;
CREATE TABLE IF NOT EXISTS BookingDetails(
    bookingId VARCHAR (20),
    roomId VARCHAR (20),
    guestId VARCHAR (20),
    checkingIn DATE ,
    checkingInTime VARCHAR (20),
    checkingOut DATE ,
    checkingOutTime VARCHAR (20),
    cost DOUBLE ,
    CONSTRAINT PRIMARY KEY (bookingId,roomId,guestId),
    CONSTRAINT FOREIGN KEY (roomId) REFERENCES Room(roomId) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT FOREIGN KEY (guestId) REFERENCES Guest(guestId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE BookingDetails;
#-------------------------------------------------------
DROP TABLE IF EXISTS MealPlan;
CREATE TABLE IF NOT EXISTS MealPlan(
    mealPlanId VARCHAR (20),
    mealPackageId VARCHAR (20),
    bookingId VARCHAR (20),
    guestId VARCHAR (20),
    mealPlanType VARCHAR (30),
    remarks VARCHAR (50),
    mealPlanPrice DOUBLE,
    extraPrice DOUBLE ,
    total DOUBLE ,
    CONSTRAINT PRIMARY KEY (mealPlanId,mealPackageId,bookingId,guestId),
    CONSTRAINT FOREIGN KEY (mealPackageId) REFERENCES MealPackage(mealPackageId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (bookingId) REFERENCES BookingDetails(bookingId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (guestId) REFERENCES Guest(guestId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE MealPlan;
#--------------------------------------------------------
DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment(
    billId VARCHAR (20) ,
    bookingCost DOUBLE ,
    mealPlanCost DOUBLE ,
    billDate DATE ,
    total DOUBLE ,
    CONSTRAINT PRIMARY KEY (billId)
);
SHOW TABLES ;
DESCRIBE Payment;
#-----------------------------------------------------------
DROP TABLE IF EXISTS StoreUsingDetails;
CREATE TABLE IF NOT EXISTS MealPackageDetails(
    mealPackageId VARCHAR (20),
    mealId VARCHAR (20),
    mealType VARCHAR (30),
    qty INT,
    CONSTRAINT PRIMARY KEY (mealPackageId,mealId),
    CONSTRAINT FOREIGN KEY (mealPackageId) REFERENCES MealPackage(mealPackageId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (mealId) REFERENCES Meal(mealId) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES ;
DESCRIBE MealPackageDetails;
#------------------------------------------------------------
DROP TABLE IF EXISTS AvailableRoom;
CREATE TABLE IF NOT EXISTS AvailableRoom(
    roomId VARCHAR (20) ,
    roomName VARCHAR (30) NOT NULL DEFAULT  'Unknown',
    roomType VARCHAR (30),
    floor VARCHAR (30),
    description VARCHAR (50),
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (roomId)
);
SHOW TABLES ;
DESCRIBE AvailableRoom;