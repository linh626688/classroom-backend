Bước 3: Tạo file Application.java
Bước 4: Đánh dấu @SpringBootApplication
Bước 5: Tạo các package 
com.higgsup.fswd.classroommanager.controller
com.higgsup.fswd.classroommanager.model
com.higgsup.fswd.classroommanager.service
com.higgsup.fswd.classroommanager.repository
Bước 6: Tạo model
ClassRoom
Student
Asset
Bước 7: Tạo controller
Chú ý: 
Nếu dùng @RestController thì mặc định mọi dữ liệu trả về đều 
được convert sang JSON (sử dụng thư viện Jackson tích hợp)
Nếu dùng @Controller, để convert về JSON, mỗi đầu hàm
sẽ phải có @ResponseBody
Bước 8: Tạo repository
ClassRoomRepository
StudentRepository
AssetRepository
Chú ý: là interface, ko phải class; extend từ CrudRepository để
có sẵn các hàm cơ bản
Bước 9: Tạo service
ClassRoomManagerService
Bước 10: tạo file application.properties trong resources
Chế độ đang đặt mẫu là create-drop; mỗi lần chạy sẽ xóa và 
tạo mới database.

Các loại entity khác, các em tự tạo; các logic liên quan cũng vậy. 
Code mẫu kết nối database h2

Mỗi resource thường sẽ có các links:
self - chính nó
parent - mức cha 
children - mức con

Trong bài toán của chúng ta:
/classes/FSWD01/students/1/assets/1
