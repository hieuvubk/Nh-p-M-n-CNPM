Cài đặt phần mềm
 - Bấm vào nút Code trên github, chọn Download zip -> Giải nén file vừa tải về 
 - Yêu cầu sử dụng Netbeans, XAMP và MySQL .Link download : 
  + NetBeans IDE : https://www.apache.org/dyn/closer.cgi/netbeans/netbeans/12.1/Apache-NetBeans-12.1-bin-windows-x64.exe 
  + XAMPP : https://www.apachefriends.org/xampp-files/7.4.12/xampp-windows-x64-7.4.12-0-VC15-installer.exe
  + MySQL : https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-8.0.22.0.msi 
  + JDK : https://download.oracle.com/otn/java/jdk/14.0.2+12/205943a0976c4ed48cb16f1043c5c647/jdk-14.0.2_windows-x64_bin.exe 
 - Cấu hình XAMPP:
  + (Xem video hướng dẫn thêm thư viện và cấu hình xampp tại đây: https://www.youtube.com/watch?        v=7JotJulxYc4&feature=youtu.be&fbclid=IwAR1KZ1orz6ins5dOnDFV1ms8_D84mOGhA6yA_zacodFvef7C85AlDqNDRso
  + Vào phần config của Apache, đổi Listen 80thành Listen 8080
  + Tiếp theo tìm đến ServerName localhost:80 và thay thành ServerName localhost:8080 và lưu lại.
 
- Tạo Database:
  + Sau khi đã cấu hình database, truy cập đến địa chỉ localhost:8080/phpmyadmin
  + Tạo database mới có tên “nmcnpm”
  + Tải file sql về
  + Vào tab Import, upload file sql vừa tải lên và hoàn tất
- Chạy chương trình:
	+ Khởi động Netbeans
	+ Chọn File->Open Project ,tìm đến thư mục chứa project vừa tải ,chọn project và bấm Open Project
	+ Tải thư viện jdbc https://jar-download.com/artifacts/mysql/mysql-connector-java/8.0.11/source-code
	+ Ở mục Libraries, import file vừa tải về
	+ Chạy phần mềm
	+ Chọn các mục tương ứng để thao tác, dữ liệu thay đổi được xem trong database MySQL đã tạo
