-- 新增員工資料
INSERT INTO employee(EMP_ID, NAME, EMAIL)
VALUES ("12345","IAN","123@GMAIL.COM")

-- 查詢員工資料
SELECT EMP_ID, NAME, EMAIL  FROM employee
WHERE EMP_ID = '12345';

-- 更新使用者(座位編號
UPDATE employee SET FLOOR_SEAT_SEQ = '1F1'
WHERE EMP_ID = '12345';
	
-- 新增座位資料
INSERT INTO seatingchart(FLOOR_SEAT_SEQ, FLOOR_NO, SEAT_NO, STATE) VALUES
	( '1F1', '1', '1', '空位');
    
-- 照座位排序
SELECT * FROM seatingchart
WHERE FLOOR_NO = 2
ORDER BY SEAT_NO DESC;

-- 合併座位及對應的員工id
SELECT seat.FLOOR_SEAT_SEQ, FLOOR_NO, SEAT_NO, STATE, EMP_ID, NAME, EMAIL
FROM seatingchart AS seat
JOIN employee AS emp
ON seat.FLOOR_SEAT_SEQ = emp.FLOOR_SEAT_SEQ
WHERE seat.FLOOR_SEAT_SEQ = '1F1';

-- 更新座位狀態(新增使用者
UPDATE seatingchart SET STATE = '已佔用'
WHERE FLOOR_SEAT_SEQ = '1F1';

-- 更新座位狀態(移除使用者
UPDATE seatingchart SET STATE = '空位'
WHERE FLOOR_SEAT_SEQ = '1F1';