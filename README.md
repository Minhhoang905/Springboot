# Tạo database
// Tỉnh
```
CREATE TABLE TINH (
	ID_TINH BIGINT PRIMARY KEY NOT NULL,
	TEN_TINH NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL
);
```
// Database cho table dbo.TINH (hiện tại chỉ có 3 tỉnh/thành)
```
INSERT INTO TINH (ID_TINH, TEN_TINH, MOTA) VALUES
(1, N'Đà Nẵng, N'Thành phố đáng sống ở Việt Nam'),
(2, N'Hồ Chí Minh', N'Sài Gòn đẹp lắm'),
(3, N'Hà Nội', N'Thủ đô của Việt Nam');
```
// Quận
```
CREATE TABLE QUAN (
	ID_QUAN BIGINT PRIMARY KEY NOT NULL,
	TEN_QUAN NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL,
	ID_TINH BIGINT NOT NULL
CONSTRAINT
	FK_QUAN_TINH FOREIGN KEY (ID_TINH) REFERENCES TINH(ID_TINH),
);
```
// Database cho talbe dbo.QUAN
```
-- Thêm các Quận cho Đà Nẵng (ID_TINH = 1)
INSERT INTO QUAN (ID_QUAN, TEN_QUAN, MOTA, ID_TINH) VALUES
(1, N'Hải Châu', N'Quận trung tâm của Đà Nẵng', 1),
(2, N'Sơn Trà', N'Quận ven biển nổi tiếng', 1),
(3, N'Ngũ Hành Sơn', N'Nổi tiếng với làng đá mỹ nghệ', 1),
(4, N'Cẩm Lệ', N'Quận mới thành lập', 1),
(5, N'Liên Chiểu', N'Khu vực phía Tây Bắc của thành phố', 1);

-- Thêm các Quận cho Hồ Chí Minh (ID_TINH = 2)
INSERT INTO QUAN (ID_QUAN, TEN_QUAN, MOTA, ID_TINH) VALUES
(6, N'Quận 1', N'Trung tâm kinh tế và tài chính', 2),
(7, N'Quận 3', N'Khu vực lâu đời của Sài Gòn', 2),
(8, N'Quận 5', N'Nổi tiếng với khu phố Tàu', 2),
(9, N'Quận Thủ Đức', N'Trung tâm công nghệ cao', 2),
(10, N'Quận Bình Thạnh', N'Nằm ven sông Sài Gòn', 2);

-- Thêm các Quận cho Hà Nội (ID_TINH = 3)
INSERT INTO QUAN (ID_QUAN, TEN_QUAN, MOTA, ID_TINH) VALUES
(11, N'Ba Đình', N'Quận trung tâm của Hà Nội', 3),
(12, N'Hoàn Kiếm', N'Thuộc khu vực phố cổ Hà Nội', 3),
(13, N'Đống Đa', N'Khu vực đông dân cư', 3),
(14, N'Tây Hồ', N'Nổi tiếng với Hồ Tây', 3),
(15, N'Cầu Giấy', N'Trung tâm giáo dục và kinh tế', 3);
```
// Xã
```
CREATE TABLE XA (
	ID_XA BIGINT PRIMARY KEY NOT NULL,
	TEN_XA NVARCHAR(50) NOT NULL,
	MOTA NVARCHAR(MAX) NULL,
	ID_QUAN BIGINT NOT NULL
CONSTRAINT
	FK_XA_QUAN FOREIGN KEY (ID_QUAN) REFERENCES QUAN(ID_QUAN),
);
```
// Database cho dbo.Xa
```
-- Xã/Phường của Đà Nẵng (ID_TINH = 1)
INSERT INTO XA (ID_XA, TEN_XA, MOTA, ID_QUAN) VALUES
(1, N'Phường Thạch Thang', N'Thuộc quận Hải Châu', 1),
(2, N'Phường Hòa Thuận Tây', N'Thuộc quận Hải Châu', 1),
(3, N'Phường An Hải Bắc', N'Thuộc quận Sơn Trà', 2),
(4, N'Phường Phước Mỹ', N'Gần biển Mỹ Khê', 2),
(5, N'Phường Hòa Hải', N'Nổi tiếng với làng đá mỹ nghệ Non Nước', 3),
(6, N'Phường Khuê Mỹ', N'Thuộc quận Ngũ Hành Sơn', 3),
(7, N'Phường Khuê Trung', N'Thuộc quận Cẩm Lệ', 4),
(8, N'Phường Hòa Phát', N'Thuộc quận Cẩm Lệ', 4),
(9, N'Phường Hòa Khánh Bắc', N'Thuộc quận Liên Chiểu', 5),
(10, N'Phường Hòa Khánh Nam', N'Thuộc quận Liên Chiểu', 5);

-- Xã/Phường của Hồ Chí Minh (ID_TINH = 2)
INSERT INTO XA (ID_XA, TEN_XA, MOTA, ID_QUAN) VALUES
(11, N'Phường Bến Nghé', N'Thuộc quận 1', 6),
(12, N'Phường Bến Thành', N'Thuộc quận 1', 6),
(13, N'Phường Tân Định', N'Nổi tiếng với nhà thờ Tân Định', 7),
(14, N'Phường Võ Thị Sáu', N'Thuộc quận 3', 7),
(15, N'Phường Chợ Lớn', N'Thuộc quận 5', 8),
(16, N'Phường Hòa Bình', N'Thuộc quận 5', 8),
(17, N'Phường Bình Thọ', N'Thuộc quận Thủ Đức', 9),
(18, N'Phường Linh Trung', N'Thuộc quận Thủ Đức', 9),
(19, N'Phường 13', N'Thuộc quận Bình Thạnh', 10),
(20, N'Phường 14', N'Thuộc quận Bình Thạnh', 10);

-- Xã/Phường của Hà Nội (ID_TINH = 3)
INSERT INTO XA (ID_XA, TEN_XA, MOTA, ID_QUAN) VALUES
(21, N'Phường Quán Thánh', N'Thuộc quận Ba Đình', 11),
(22, N'Phường Trúc Bạch', N'Nổi tiếng với hồ Trúc Bạch', 11),
(23, N'Phường Đồng Xuân', N'Gần chợ Đồng Xuân', 12),
(24, N'Phường Hàng Bạc', N'Nổi tiếng với phố cổ Hà Nội', 12),
(25, N'Phường Trung Tự', N'Thuộc quận Đống Đa', 13),
(26, N'Phường Láng Thượng', N'Thuộc quận Đống Đa', 13),
(27, N'Phường Bưởi', N'Thuộc quận Tây Hồ', 14),
(28, N'Phường Nhật Tân', N'Nổi tiếng với vườn đào Nhật Tân', 14),
(29, N'Phường Quan Hoa', N'Thuộc quận Cầu Giấy', 15),
(30, N'Phường Dịch Vọng', N'Thuộc quận Cầu Giấy', 15);

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
