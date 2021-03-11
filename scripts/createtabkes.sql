CREATE TABLE User_account (
    User_ID INT(10) NOT NULL AUTO_INCREMENT,
    User_name VARCHAR(255) NOT NULL,
    User_email VARCHAR(255) NOT NULL,
    User_phone VARCHAR(25) NOT NULL,
    Role VARCHAR(255) NOT NULL,
    PRIMARY KEY (User_ID)
);
CREATE TABLE Card_details (
    Card_ID INT(10) NOT NULL AUTO_INCREMENT,
    Card_type VARCHAR(255) NOT NULL,
    Expiry_date DATE NOT NULL,
    Last_four_digits VARCHAR(4) NOT NULL,
    PRIMARY KEY (Card_ID)
);
CREATE TABLE `Customer` (
  `Account_no` int NOT NULL AUTO_INCREMENT,
  `Customer_type` varchar(25) NOT NULL,
  `Customer_name` varchar(255) NOT NULL,
  `Customer_address` varchar(255) NOT NULL,
  `Customer_phone` varchar(25) NOT NULL,
  `Discount_type` varchar(255) DEFAULT NULL,
  `Flexible_discount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Account_no`)
);
CREATE TABLE Payment (
    Payment_ID INT(10) NOT NULL AUTO_INCREMENT,
    Payment_type VARCHAR(25) NOT NULL,
    Payment_amount DOUBLE NOT NULL,
    CustomerAccount_no INT(10) NOT NULL,
    Card_detailsCard_ID INT(10),
    PRIMARY KEY (Payment_ID),
    FOREIGN KEY (CustomerAccount_no)
        REFERENCES Customer (Account_no),
    FOREIGN KEY (Card_detailsCard_ID)
        REFERENCES Card_details (Card_ID)
);
CREATE TABLE Job (
    Job_ID INT(10) NOT NULL AUTO_INCREMENT,
    Job_desc INT(10) NOT NULL,
    Urgency_level VARCHAR(25) NOT NULL,
    Status VARCHAR(255) NOT NULL,
    `Start` DATETIME NOT NULL,
    Special_instruction VARCHAR(255),
    Completion_date_time DATETIME NULL,
    Subtotal_price DOUBLE,
    Total_discount DOUBLE,
    Total_price DOUBLE,
    Payment_deadline DATETIME NULL,
    Completion_deadline DATETIME NOT NULL,
    CustomerAccount_no INT(10) NOT NULL,
    PaymentPayment_ID INT(10),
    PRIMARY KEY (Job_ID),
    FOREIGN KEY (CustomerAccount_no)
        REFERENCES Customer (Account_no),
    FOREIGN KEY (PaymentPayment_ID)
        REFERENCES Payment (Payment_ID)
);
CREATE TABLE Alert (
    Alert_ID INT(10) NOT NULL AUTO_INCREMENT,
    Alert_name VARCHAR(255) NOT NULL,
    Alert_message VARCHAR(255) NOT NULL,
    JobJob_ID INT(10) NOT NULL,
    PRIMARY KEY (Alert_ID),
    FOREIGN KEY (JobJob_ID)
        REFERENCES Job (Job_ID)
);
CREATE TABLE Task (
    Task_ID INT(10) NOT NULL AUTO_INCREMENT,
    Task_status VARCHAR(255) NOT NULL,
    Task_Department VARCHAR(255) NOT NULL,
    Discount_rate DOUBLE,
    Task_start DATETIME NOT NULL,
    Task_completion DATETIME NULL,
    User_accountUser_ID INT(10) NOT NULL,
    Task_CatalogCatalog_ID INT(10) NOT NULL,
    JobJob_ID INT(10) NOT NULL,
    PRIMARY KEY (Task_ID),
    FOREIGN KEY (User_accountUser_ID)
        REFERENCES User_account (User_ID),
    FOREIGN KEY (Task_CatalogCatalog_ID)
        REFERENCES Task_Catalog (Catalog_ID),
    FOREIGN KEY (JobJob_ID)
        REFERENCES Job (Job_ID)
);
CREATE TABLE Task_Catalog (
    Catalog_ID INT(10) NOT NULL AUTO_INCREMENT,
    Task_name VARCHAR(255) NOT NULL,
    Price DOUBLE NOT NULL,
    PRIMARY KEY (Catalog_ID)
);
