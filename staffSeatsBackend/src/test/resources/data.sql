INSERT INTO employee(EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES
	( '24001', 'Jack', 'jack123@gmail.com', '1F1'),
	( '24002', 'Tony', 'tony123@gmail.com', null),
	( '24003', 'Amy', 'amy123@gmail.com', null),
	( '24004', 'Lisa', 'lisa999@gmail.com', '2F3'),
	( '24005', 'Elsa', 'elsa0622@gmail.com', '1F3');
	
INSERT INTO seatingchart(FLOOR_SEAT_SEQ, FLOOR_NO, SEAT_NO, STATE) VALUES
	( '1F1', '1', '1', '已佔用'),
	( '1F2', '1', '2', '空位'),
	( '1F3', '1', '3', '已佔用'),
	( '1F4', '1', '4', '空位'),
	( '2F1', '2', '1', '空位'),
	( '2F2', '2', '2', '空位'),
	( '2F3', '2', '3', '已佔用'),
	( '2F4', '2', '4', '空位');