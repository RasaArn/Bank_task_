CREATE TABLE bankUsers (
    fullName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    personalId BIGINT(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL,
    accountNumber BIGINT(36) NOT NULL DEFAULT 100000000000000, -- will use it instead user ID
    PRIMARY KEY (accountNumber),
    UNIQUE KEY (accountNumber)
);

-- add new user:
-- id INT NOT NULL AUTO_INCREMENT,
SELECT * FROM bankUsers;
DROP TABLE bankUsers;





/*CREATE TABLE bankUsers(
	UserID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fullName varchar (100) NOT NULL,
    password varchar (12),
    phoneNumber BIGINT(8),
    email varchar (50),
    CONSTRAINT password_format CHECK (password REGEXP '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*+]).{8,}$')
);

INSERT INTO bankUsers (fullName, password, phoneNumber, email) VALUES ('Petras Petraitis', 'Bank123*', '86000000', 'petras@mail.com');
INSERT INTO bankUsers (fullName, password, phoneNumber, email) VALUES ('Test Petraitis', 'Bank123+', '86000001', 'test@mail.com');



/*
The CONSTRAINT clause specifies a regular expression check on the password column using the REGEXP operator. The regular expression ^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*]).{8,}$ requires that the password must:

^ start at the beginning of the string
(?=.*?[A-Z]) contain at least one uppercase letter
(?=.*?[a-z]) contain at least one lowercase letter
(?=.*?[0-9]) contain at least one number
(?=.*?[!@#$%^&*]) contain at least one special character
.{8,} be at least 8 characters long
$ end at the end of the string
*/