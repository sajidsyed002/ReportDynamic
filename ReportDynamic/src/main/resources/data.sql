insert into HEDGE_DATA (ID,FUND_NAME,MANAGER,MANAGER_CONTACT,STRATEGY,GEO_FOCUS,
FUND_INCEPTION,MANAGER_FOUNDED) values
('1', 'HEDGE','Asset Management','Jack F','Multi-Strategy','Global','1991','1989'),
('2', 'PPF','Savings Management','Denver S','Single-Strategy','Global','1995','1988'),
('3', 'EPF','Savings Management','Rick J','Multi-Strategy','Global','1992','1987'),
('4', 'ELSS','Asset Management','Denver H','Multi-Strategy','Global','1993','1986'),
('5', 'ICICI','Prudential Fund','Carl J','Multi-Strategy','Global','1994','1985'),
('6', 'HDFC','Asset Management','Peter H','Multi-Strategy','Global','1997','1984'),
('7', 'SBI','Asset Management','Mike T','Multi-Strategy','Global','1996','1983'),
('8', 'QUANT','Mutual Fund','Shane W','Multi-Strategy','Global','1998','1982');

insert into SCHEDULE_CONFIG (report_id, cron_expression) values
(1,'0/5 * * * * ?'),
(2,'0 54 11 4 6 ?'),
(3,'0 0 */10 * * ?');


insert into METADATA_CONFIG (METADATA_NAME) values
('color'),
('fontStyle'),
('dataLength'),
('fontSize'),
('bgColor'),
('align'),
('border'),
('height');
