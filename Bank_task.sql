CREATE TABLE bankUsers (
    fullName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    personalId BIGINT(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    balance DOUBLE NOT NULL,
    accountNumber BIGINT(36) NOT NULL AUTO_INCREMENT, -- used insteda of ID. Each new user gets new unique number when is registered
    PRIMARY KEY (accountNumber),
    UNIQUE KEY (accountNumber)
) AUTO_INCREMENT=1000000000000;



SELECT * FROM bankUsers;
DROP TABLE bankUsers;





