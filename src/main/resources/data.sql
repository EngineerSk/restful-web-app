INSERT INTO user_details (id, birth_date, name)
VALUES (10002, current_date(), 'ORIADE'),
(10001, current_date(), 'DHIKRULLAH'),
(10003, current_date(), 'ENGINEER SK');

INSERT INTO post (id, description, user_id)
VALUES (20001, 'I want to learn AWS', 10003),
(20002, 'I want to learn DevOps', 10002),
(20003, 'I want to get AWS Certified', 10003),
(20004, 'I want to learn MultiCloud', 10001);
