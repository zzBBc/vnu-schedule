[![Build Status](https://travis-ci.com/zzBBc/vnu-schedule.svg?branch=master)](https://travis-ci.com/zzBBc/vnu-schedule)
# Cách sử dụng
- Sửa file app.js trong folder phantomjs, sửa value LoginName và Password thành tài khoản vnu của bạn
- Mở cmd trong thư mục trên, chạy lệnh: ./phantomjs app.js
- Chạy MainClass trong package main. Nhập mã môn học những môn muốn đăng ký tín chỉ vào console, yêu cầu nhập chính xác chữ in hoa. Mỗi môn học trên môn dòng. Sau khi nhập xong hết. Nhập "ok" để chương trình xử lý
- Đợi cho đến khi chương trình chạy xong.
- Cuối cùng bạn có được file TKB.xlsx trong folder ./data là file sẽ lưu trữ tất cả những thời khóa biểu để đăng ký phù hợp với yêu cầu của bạn.

# vnu-schedule
Tạo thời khóa biểu khả dụng của VNU trước khi đăng ký tín chỉ, tạo thuận lợi cho việc đăng ký môn

# New feature
- Sử dụng Jsoup cho sever để bóc dữ liệu từ html thay cho việc sử dụng tool NBN
- Sử dụng json thay cho excel ✅

# Cần có account VNU để get lịch trực tiếp từ trang dangkyhoc và parse to AmazonLoggedIn.txt
Sử dụng đoạn js của anh Nguyễn Bá Nghĩa vì mình chưa code đoạn này
Tham khảo link sau: https://www.youtube.com/watch?v=UbgG5kGFjls

* Mới làm lần đầu nên structure và comment hơi linh tinh,...
Sử dụng json làm data
Design pattern cũng không ổn

* package combination
# GeneratePermutations
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

* package dataSubject
# ListSubject
Get thông tin của các môn học (Tên, Số Tín, Mã môn học, Thời gian học) từ file AmazonLoggedIn.txt ở trên. Số Tín thì không dùng đến....
Trả về Student object

# HandleString
Xóa dấu của chữ tiếng việt và xóa toàn bộ kí tự thừa để có thể dùng regex tách ra thông tin từ file trên

# TakeSubject
Tên hơi sai công dụng....
Các đoạn regex để bóc tách thông tin

* package json
# Time
Object lưu thông tin về thời gian, địa điểm từng môn học

# Subject, , NameSubject
Object lưu thông tin về tên, số tín, mã môn học, thời gian

# DriverToSubject
Parse data cho dataSubject.json

# InputJson
Map json to pojo

* package optimizeSchedule
# OptimizeSchedule
Trả về những lịch có nhiều buổi nghỉ nhất (Nghỉ cả 5 tiết)
Xóa bỏ toàn bộ số còn lại


* package schedule
# ImportSubject
Nhập các môn muốn xếp lịch từ bàn phím và tạo thành 1 ArrayList tên các môn muốn học

# ScheduleExcel
Quá trình xử lý chính
List các môn học ->... -> ArrayList đã sắp xếp của các mã môn học

# CreateSchedule
Tạo khung và thêm tất cả các danh sách đã sắp xếp vào TKB.xls .
Đồng thời xóa những lịch trùng thời gian

* main
# MainClass
Run it!!!!!
