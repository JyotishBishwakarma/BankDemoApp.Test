--FOOTERS
INSERT INTO dbo.nextGenLinks (testcase, linkName, url) VALUES ('footer', 'Verisk Corporate Home','https://www.verisk.com/');
INSERT INTO dbo.nextGenLinks (testcase, linkName, url) VALUES ('footer', 'Terms and Conditions','https://www.verisk.com/privacy-trademarks-use/verisk-analytics-conditions-of-use/');
INSERT INTO dbo.nextGenLinks (testcase, linkName, url) VALUES ('footer', 'Privacy and Security Policy','https://www.verisk.com/privacy-trademarks-use/iso-online-privacy-notice/');
INSERT INTO dbo.nextGenLinks (testcase, linkName, url) VALUES ('footer', 'Privacy Notice','http://www.verisk.com/privacy-trademarks-use/iso-online-privacy-notice.html');
INSERT INTO dbo.nextGenLinks (testcase, linkName, url) VALUES ('footer', 'Contact Us','https://www.verisk.com/contact-verisk/');
INSERT INTO dbo.nextGenLinks (testcase, linkName, url) VALUES ('header', 'Support','https://www.verisk.com/contact-verisk/');

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------FLV-------------------------------------------------------------------------------------------

INSERT INTO dbo.flvData (environment, url, userid, userPassword, filingId) VALUES ('Q','http://productsq.iso.com/flv/#/', 'flvtest06', 'monkey1', 'CR-2017-ORRSA' );

INSERT INTO dbo.flvData (environment, url, userid, userPassword, filingId) VALUES ('A','http://productsu.iso.com/flv/#/', 'flvtest06', 'monkey1', 'CR-2017-ORRSA' );

INSERT INTO dbo.flvData (environment, url, userid, userPassword, filingId) VALUES ('P','http://products.iso.com/flv/#/', 'parprodcirc', 'monkey14', 'BP-2017-RBGS1' );

----------------------------------------------------------------------------------------------FLV-------------------------------------------------------------------------------------------
--Domains
INSERT INTO dbo.domain (environment, domaintype, domain) values ('T', 'I', 'https://www5t.iso.com/');
INSERT INTO dbo.domain (environment, domaintype, domain) values ('A', 'I', 'http://protuctsu.iso.com/ei-admin');
INSERT INTO dbo.domain (environment, domaintype, domain) values ('P', 'I', 'http://nextgen.iso.com/ei-admin');

