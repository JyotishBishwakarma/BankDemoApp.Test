DROP table dbo.customer_details IF EXISTS CASCADE;


CREATE TABLE dbo.customer_details (name VARCHAR(50), gender VARCHAR(50), dob VARCHAR(50),address VARCHAR(100),city VARCHAR(100),state VARCHAR(100),pin VARCHAR(100),mobno VARCHAR(100),email VARCHAR(100),password VARCHAR(100));

CREATE TABLE dbo.user_login (username VARCHAR(50),password VARCHAR(50));

