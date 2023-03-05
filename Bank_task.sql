CREATE TABLE bankUsers(
	UserID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fullname varchar (100) NOT NULL,
    password varchar (12),
    phoneNumber varchar (8),
    email varchar (50),
    CONSTRAINT password_format CHECK (password REGEXP '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*+]).{8,}$')
);

INSERT INTO bankUsers (fullname, password, phoneNumber, email) VALUES ('Petras Petraitis', 'Bank123*', '86000000', 'petras@mail.com');
INSERT INTO bankUsers (fullname, password, phoneNumber, email) VALUES ('Test Petraitis', 'Bank123+', '86000001', 'test@mail.com');

SELECT * FROM bankUsers;
DROP TABLE bankUsers;

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