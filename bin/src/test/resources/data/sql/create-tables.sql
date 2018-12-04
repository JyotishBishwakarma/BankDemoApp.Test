DROP table dbo.nextGenLinks IF EXISTS CASCADE;
DROP table dbo.domain IF EXISTS CASCADE;
DROP table dbo.flvData IF EXISTS CASCADE;

CREATE TABLE dbo.nextGenLinks (testcase VARCHAR(50), linkName VARCHAR(50), url VARCHAR(1000));
CREATE TABLE dbo.domain (environment char(1), domaintype char(1), domain VARCHAR(1000));
CREATE TABLE dbo.flvData (environment char(1), url VARCHAR(1000), userid VARCHAR(50), userPassword VARCHAR(50), filingId VARCHAR(50));
