
-- state--
INSERT INTO state (id, state_desc) VALUES (1, 'IDLE');
INSERT INTO state (id, state_desc) VALUES (2, 'LOADING');
INSERT INTO state (id, state_desc) VALUES (3, 'LOADED');
INSERT INTO state (id, state_desc) VALUES (4, 'DELIVERING');
INSERT INTO state (id, state_desc) VALUES (5, 'DELIVERED');
INSERT INTO state (id, state_desc) VALUES (6, 'RETURNING');


-- model----
INSERT INTO model (id, model_desc) VALUES (1, 'Lightweight');
INSERT INTO model (id, model_desc) VALUES (2, 'Middleweight');
INSERT INTO model (id, model_desc) VALUES (3, 'Cruiserweight');
INSERT INTO model (id, model_desc) VALUES (4, 'Heavyweight');


--drone-----
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D001', 15, 'Lightweight', 'IDLE', 400);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D002', 60, 'Middleweight', 'LOADED,', 300);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D003', 72, 'Cruiserweight', 'RETURNING).', 500);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D004', 82, 'Cruiserweight', 'IDLE', 100);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D005', 55, 'Heavyweight', 'DELIVERED,', 400);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D006', 60, 'Lightweight', 'IDLE', 250);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D007', 32, 'Lightweight', 'LOADING',500);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D008', 98, 'Middleweight', 'IDLE', 100);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D009', 32, 'Cruiserweight', 'LOADING',500);
INSERT INTO DRONE (serial_number, capacity, model, state, weight) VALUES('D0010', 92, 'Heavyweight', 'IDLE', 100);