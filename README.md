# Tạo database (chỉ tạo table, chưa đưa dữ liệu)
// Tỉnh
```
CREATE TABLE TINH (
	ID_TINH BIGINT PRIMARY KEY NOT NULL,
	TEN_TINH NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL
);
```
// Quận
```
CREATE TABLE QUAN (
	ID_QUAN BIGINT PRIMARY KEY NOT NULL,
	TEN_QUAN NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL
);
```
// Xã
```
CREATE TABLE XA (
	ID_XA BIGINT PRIMARY KEY NOT NULL,
	TEN_XA NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL
);
```
//Du lịch
```
CREATE TABLE DULICH (
	ID BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	DIA_DIEM NVARCHAR(50) NOT NULL,
	ID_TINH BIGINT NOT NULL,
	ID_QUAN BIGINT NOT NULL,
	ID_XA BIGINT NOT NULL,
	DIA_CHI_CU_THE NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL

CONSTRAINT fk_ID_TINH
	FOREIGN KEY (ID_TINH) REFERENCES TINH(ID_TINH),
CONSTRAINT fk_ID_QUAN
	FOREIGN KEY (ID_QUAN) REFERENCES QUAN(ID_QUAN),
CONSTRAINT fk_ID_XA
	FOREIGN KEY (ID_XA) REFERENCES XA(ID_XA),
);
```
// Login
```
CREATE TABLE LOGIN (
	id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	User_name nvarchar(20) NOT NULL,
	User_Password nvarchar(20) NOT NULL,
	ROLE nvarchar(20) NOT NULL
);
```
