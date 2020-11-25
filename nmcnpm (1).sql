-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 25, 2020 lúc 12:31 PM
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
-- Cấu trúc bảng cho bảng `hogiadinh`
--

CREATE TABLE `hogiadinh` (
  `SoHoKhau` char(1) NOT NULL,
  `HoTenChuHo` varchar(30) DEFAULT NULL,
  `SoNha` int(11) DEFAULT NULL,
  `Pho` varchar(30) DEFAULT NULL,
  `Phuong` varchar(30) DEFAULT NULL,
  `Quan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoi`
--

CREATE TABLE `nguoi` (
  `HoTen` varchar(30) NOT NULL,
  `SoHoKhau` char(1) NOT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(50) DEFAULT NULL,
  `QueQuan` varchar(50) DEFAULT NULL,
  `NoiSinh` varchar(50) DEFAULT NULL,
  `NgheNghiep` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `qua`
--

CREATE TABLE `qua` (
  `MaQua` char(10) NOT NULL,
  `Ten` varchar(30) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `Gia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quantrivien`
--

CREATE TABLE `quantrivien` (
  `TaiKhoan` char(30) NOT NULL,
  `MatKhau` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

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
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `nguoi`
--
ALTER TABLE `nguoi`
  ADD CONSTRAINT `SoHoKhau` FOREIGN KEY (`SoHoKhau`) REFERENCES `hogiadinh` (`SoHoKhau`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
