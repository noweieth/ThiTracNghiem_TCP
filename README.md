# ThiTracNghiem_TCP
Lap trinh mang socket

Viết chương trình client – server sử dụng hệ quản trị cơ sở dữ liệu sql server để thực hiện chương trình làm bài trắc nghiệm. (cơ sở dữ liệu kèm theo).
Yêu cầu: 
Client: 
B1: client đăng nhập tài khoản user và pass. (nếu kiểm tra có trong bảng SINHVIEN thì cho thực hiện các yêu cầu tiếp theo, nếu không thi báo tài khoản không đúng, vui lòng nhập lại)

B2: Thực hiện làm bài trắc nghiệm nhận câu hỏi và câu trả lời từ server (mỗi lần nhận 1 câu hỏi và 4 đáp án), sau đó sẽ chọn phương án trả lời và nhận tiếp tục các câu hỏi tiếp theo. Khi làm xong 10 câu hỏi thì trên màn hình sẽ nhận tổng hợp các đáp án của các câu hỏi đã làm và tổng điểm đạt được. (bộ đề trắc nhiệm sẽ được lấy trong bảng BODE)

Ví dụ:

Về cấu trúc hiển thị câu hỏi và đáp án câu hỏi như sau:
1) Mạng máy tính (compute netword) so với hệ thống tập trung multi-user: 

a)	Dễ phát triển hệ thống      b) Tăng độ tin cậy      c) Tiết kiệm chi phí      d) Tất cả đều đúng

Về cấu trúc hiển thị đáp án và tổng điểm như sau:

1 – A

2 – B

….

10 – C

Tổng điểm làm bài tập của bạn là: 8 điểm

Server:

B1) kiểm tra user pass client gửi lên nếu đúng thì sẽ thực hiện bước 2, nếu sai thì báo cho client.

B2) sau khi đăng nhập thành công thì Server sẽ lấy ngẫu nhiên các câu hỏi trong bảng BODE trả về cho client để làm bài. Sau khi thực hiện hết 10 câu hỏi sẽ tự động tính điểm và trả đáp án về cho client, Kết quả đạt đc sẽ lưu trong bảng BangDiem (các trường có nôi dung như sau LAN =1, BAITHI = Thứ 1, NGAYTHI = ngày hiện tại, MaSV = Lấy từ bảng sinh viên, Điểm = kết quả đã làm được).
