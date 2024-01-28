CREATE TABLE IF NOT EXISTS `employee` (
  `EMP_ID` varchar(5) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `FLOOR_SEAT_SEQ` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`)
);

CREATE TABLE  IF NOT EXISTS `seatingchart` (
  `FLOOR_SEAT_SEQ` varchar(45) NOT NULL,
  `FLOOR_NO` varchar(45) NOT NULL,
  `SEAT_NO` varchar(45) NOT NULL,
  `STATE` varchar(5) DEFAULT '空位',
  PRIMARY KEY (`FLOOR_SEAT_SEQ`)
);
