-- 新增員工資料
INSERT INTO employee(EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES
	( '77777', 'Mike', 'mike123@gmail.com', null),
	( '24001', 'Jack', 'jack123@gmail.com', null),
	( '24002', 'Tony', 'tony123@gmail.com', null),
	( '24003', 'Amy', 'amy123@gmail.com', null),
	( '24004', 'Lisa', 'lisa999@gmail.com', null),
	( '24005', 'Elsa', 'elsa0622@gmail.com', null),
	( '24006', 'John', 'john1312@gmail.com', null),
	( '24007', 'Alex', 'alexzzz@gmail.com', null),
	( '24008', 'Wick', 'wickkk@gmail.com', null),
	( '24009', 'Tommy', 'tommyyyyy@gmail.com', null);
	
-- 新增座位資料
INSERT INTO seatingchart(FLOOR_SEAT_SEQ, FLOOR_NO, SEAT_NO, STATE) VALUES
	( '1F1', '1', '1', '空位'),
	( '1F2', '1', '2', '空位'),
	( '1F3', '1', '3', '空位'),
	( '1F4', '1', '4', '空位'),
	( '2F1', '2', '1', '空位'),
	( '2F2', '2', '2', '空位'),
	( '2F3', '2', '3', '空位'),
	( '2F4', '2', '4', '空位'),
	( '3F1', '3', '1', '空位'),
	( '3F2', '3', '2', '空位'),
	( '3F3', '3', '3', '空位'),
	( '3F4', '3', '4', '空位'),
	( '4F1', '4', '1', '空位'),
	( '4F2', '4', '2', '空位'),
	( '4F3', '4', '3', '空位'),
	( '4F4', '4', '4', '空位');