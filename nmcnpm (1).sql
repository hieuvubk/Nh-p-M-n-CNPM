-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 07, 2020 lúc 10:33 AM
-- Phiên bản máy phục vụ: 10.4.14-MariaDB
-- Phiên bản PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `nmcnpm`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hocsinh`
--

CREATE TABLE `hocsinh` (
  `SoHoKhau` varchar(10) NOT NULL,
  `HoTen` varchar(30) NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(30) DEFAULT NULL,
  `Truong` varchar(30) DEFAULT NULL,
  `Lop` int(11) DEFAULT NULL,
  `ThanhTich` varchar(30) DEFAULT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hocsinh`
--

INSERT INTO `hocsinh` (`SoHoKhau`, `HoTen`, `NgaySinh`, `DiaChi`, `Truong`, `Lop`, `ThanhTich`) VALUES
-- ('1', 'Nguyen Van B', '2000-05-20', 'Ha Noi', 'BK', 12, 'Gioi'),
-- ('1', 'Vũ Đức Hiếu', '2000-05-21', 'Vũ Xuân Thiều', 'BK', 10, 'Kha');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hogiadinh`
--

CREATE TABLE `hogiadinh` (
  `SoHoKhau` varchar(1) NOT NULL,
  `HoTenChuHo` varchar(30) DEFAULT NULL,
  `SoNha` varchar(11) DEFAULT NULL,
  `Pho` varchar(30) DEFAULT NULL,
  `Phuong` varchar(30) DEFAULT NULL,
  `Quan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hogiadinh`
--

INSERT INTO `hogiadinh` (`SoHoKhau`, `HoTenChuHo`, `SoNha`, `Pho`, `Phuong`, `Quan`) VALUES
('1', 'Vũ Quang Tạo', '14a', 'Vũ Xuân Thiều', 'Sài Đồng', 'Long Biên')
('3', 'Vũ Văn F', '16', 'Xuân Thiều', 'Sài Đồng', 'Long Biên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoi`
--

CREATE TABLE `nguoi` (
  `HoTen` varchar(30) NOT NULL,
  `SoHoKhau` varchar(1) NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(50) DEFAULT NULL,
  `QueQuan` varchar(50) DEFAULT NULL,
  `NoiSinh` varchar(50) DEFAULT NULL,
  `NgheNghiep` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nguoi`
--

INSERT INTO `nguoi` (`HoTen`, `SoHoKhau`, `NgaySinh`, `DiaChi`, `QueQuan`, `NoiSinh`, `NgheNghiep`) VALUES
('Nguyễn Văn A', '1', '2010-05-20', 'Ha Noi', 'Hưng Yên', 'Hưng Yên', 'Nha Bao'),
('Nguyễn Văn B', '1', '2005-05-20', 'Ha Noi', 'Hưng Yên', 'Hưng Yên', 'Học Sinh'),
('Nguyễn Văn C', '1', '2009-05-20', 'Ha Noi', 'Hưng Yên', 'Hưng Yên', 'Nha Bao'),
('Nguyễn Văn D', '1', '2000-05-20', 'Ha Noi', 'Hưng Yên', 'Hưng Yên', 'Nha Bao'),
('Vũ Đức Hiếu', '1', '2000-05-21', 'Vũ Xuân Thiều', 'Hưng Yên', 'Hưng Yên', 'Học Sinh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phatqua`
--

CREATE TABLE `phatqua` (
  `MaSuKien` varchar(10) NOT NULL,
  `HoTen` varchar(30) NOT NULL,
  `SoHoKhau` varchar(10) NOT NULL,
  `MaQua` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `qua`
--

CREATE TABLE `qua` (
  `MaQua` varchar(10) NOT NULL,
  `Ten` varchar(30) DEFAULT NULL,
  `Gia` int(11) DEFAULT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quantrivien`
--

CREATE TABLE `quantrivien` (
  `TaiKhoan` varchar(30) NOT NULL,
  `MatKhau` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `quantrivien` (`TaiKhoan`, `MatKhau`) VALUES
('admin', 123456);
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sukien`
--

CREATE TABLE `sukien` (
  `MaSuKien` varchar(10) NOT NULL,
  `TenSuKien` varchar(30) DEFAULT NULL,
  `ThoiGian` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hocsinh`
--
ALTER TABLE `hocsinh`
  ADD PRIMARY KEY (`SoHoKhau`,`HoTen`);

--
-- Chỉ mục cho bảng `hogiadinh`
--
ALTER TABLE `hogiadinh`
  ADD PRIMARY KEY (`SoHoKhau`);

--
-- Chỉ mục cho bảng `nguoi`
--
ALTER TABLE `nguoi`
  ADD PRIMARY KEY (`HoTen`,`SoHoKhau`),
  ADD KEY `SoHoKhau` (`SoHoKhau`);

--
-- Chỉ mục cho bảng `phatqua`
--
ALTER TABLE `phatqua`
  ADD PRIMARY KEY (`MaSuKien`,`HoTen`,`SoHoKhau`),
  ADD KEY `SoHoKhau` (`SoHoKhau`,`HoTen`);

--
-- Chỉ mục cho bảng `qua`
--
ALTER TABLE `qua`
  ADD PRIMARY KEY (`MaQua`);

--
-- Chỉ mục cho bảng `quantrivien`
--
ALTER TABLE `quantrivien`
  ADD PRIMARY KEY (`TaiKhoan`);

--
-- Chỉ mục cho bảng `sukien`
--
ALTER TABLE `sukien`
  ADD PRIMARY KEY (`MaSuKien`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hocsinh`
--
ALTER TABLE `hocsinh`
  ADD CONSTRAINT `hocsinh_ibfk_1` FOREIGN KEY (`SoHoKhau`) REFERENCES `hogiadinh` (`SoHoKhau`);

--
-- Các ràng buộc cho bảng `nguoi`
--
ALTER TABLE `nguoi`
  ADD CONSTRAINT `SoHoKhau` FOREIGN KEY (`SoHoKhau`) REFERENCES `hogiadinh` (`SoHoKhau`);

--
-- Các ràng buộc cho bảng `phatqua`
--
ALTER TABLE `phatqua`
  ADD CONSTRAINT `phatqua_ibfk_1` FOREIGN KEY (`MaSuKien`) REFERENCES `sukien` (`MaSuKien`),
  ADD CONSTRAINT `phatqua_ibfk_2` FOREIGN KEY (`SoHoKhau`,`HoTen`) REFERENCES `nguoi` (`SoHoKhau`, `HoTen`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
