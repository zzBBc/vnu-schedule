[![Build Status](https://travis-ci.com/zzBBc/vnu-schedule.svg?branch=master)](https://travis-ci.com/zzBBc/vnu-schedule)
# Cách sử dụng
- Đăng nhập trang đăng ký học của VNU sau đó get html, hoặc sử dụng tool của Nguyễn Bá Nghĩa get file AmazonLoggedIn.html
- Copy file vào trong thư mục vnu-schedule-tinchi
- Đổi phần mở rộng của file AmazonLoggedIn.html thành AmazonLoggedIn.txt.
- Chạy class CreateExcel trong package saveExcel
- Chạy class SetNameExcel trong package saveExcel
- Chạy class ScheduleExcel trong package schedule. Nhập mã môn học những môn muốn đăng ký tín chỉ vào console, yêu cầu nhập chính xác chữ in hoa. Mỗi môn học trên môn dòng. Sau khi nhập xong hết. Nhập "ok" để chương trình xử lý
- Đợi cho đến khi chương trình chạy xong, đây là phần xử lý chính nên sẽ là phần chạy lâu nhất.
- Chạy class OptimizeSchedule trong package optimizeSchedule
- Cuối cùng bạn có được file TKB.xlsx là file sẽ lưu trữ tất cả những thời khóa biểu để đăng ký phù hợp với yêu cầu của bạn.

# vnu-schedule-tinchi
Thời khóa biểu vnu trước khi đăng ký tín chỉ

# New feature
Sử dụng Jsoup cho sever để bóc dữ liệu từ html thay cho việc sử dụng tool NBN
Sử dụng json thay cho excel

# Cần có account VNU để get lịch trực tiếp từ trang dangkyhoc và parse to AmazonLoggedIn.txt
Sử dụng đoạn js của anh Nguyễn Bá Nghĩa vì mình chưa code đoạn này
Tham khảo link sau: https://www.youtube.com/watch?v=UbgG5kGFjls

* Mới làm lần đầu nên structure và comment của hơi linh tinh,...
Chưa sử dụng đến database như sql, nosql hay json mà thay vào đó sử dụng Excel
Design pattern cũng không ổn

* package dataSubject
# ArrayListSubject
Get thông tin của các môn học (Tên, Số Tín, Mã môn học, Thời gian học) từ file AmazonLoggedIn.txt ở trên. Số Tín thì không dùng đến....
Trả về object

# HandleString
Xóa dấu của chữ tiếng việt và xóa toàn bộ kí tự thừa để có thể dùng regex tách ra thông tin từ file trên

# InfoSubject
Tạo object cho môn học (Tên, Số Tín, Mã môn học, Thời gian học)

# TakeSubject
Tên hơi sai công dụng....
Các đoạn regex để match tách thông tin

* package saveExcel
# CreateExcel
Tạo khung cho Excel và lưu toàn bộ thông tin về các môn học từ ArrayListSubject thành file dataSubject.xls như một database

# SetNameSubject
Lấy tên môn học từ nameSubject.xls để add vào dataSubject.xls
Trong quá trình get Regex. Dạng của tên môn học giống với một số thành phần phụ khác. Vì vậy tuy đã lấy được toàn bộ thông tin khác hoàn toàn chính xác nhưng tên môn học thì không lấy được.
nameSubject.xls tự tạo hoàn toàn bằng tay....

* package schedule  **package chính
# ImportSubject
Nhập các môn muốn xếp lịch từ bàn phím và tạo thành 1 ArrayList tên các môn muốn học

# ScheduleExcel
Quá trình xử lý chính
ArrayList các môn học ->... -> ArrayList đã sắp xếp của các mã môn học

# CreateSchedule
Tạo khung và thêm tất cả các danh sách đã sắp xếp vào TKB.xls .
Đồng thời xóa những lịch trùng thời gian

* package combination
# combination
Thuật toán tham khảo để sắp xếp tổ hợp {
ArrayList(môn 1 mã 1, môn 1 mã 2, môn 1 mã 3),
ArrayList(môn 2 mã 1, môn 2 mã 2),
ArrayList(môn 3 mã 1),
.....
}
-> {
ArrayList(môn 1 mã 1, môn 2 mã 1, môn 3 mã 1,....),
ArrayList(môn 1 mã 1, môn 2 mã 1, môn 3 mã 2,....),
ArrayList(môn 1 mã 1, môn 2 mã 2, môn 3 mã 2,....),
.....
}

* package optimizeSchedule
# OptimizeSchedule
Trả về những lịch có nhiều buổi nghỉ nhất (Nghỉ cả 5 tiết)
Xóa bỏ toàn bộ số còn lại
