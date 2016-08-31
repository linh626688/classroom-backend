Tạo chức năng Token Authentication 
Định nghĩa:
+ Interceptor dùng để bắt mọi request đến server
+ Có thể có nhiều interceptor
+ Token authentication có nghĩa là dùng 1 "token" dạng 1 chuỗi, để xác định 1 người dùng và cho phép truy cập vào API

Các bước tạo:
1. Tạo AuthenticationController
/login
/logout
/createUser

2. Tạo TokenAuthenticationInterceptor
Chỉ cần override preHandle (chặn trước)
Tham số Object handler (có thể) là method trong controller được đánh dấu cần authentication hoặc không

3. Tạo các annotation @NoAuthentication, @RequiredRoles
@NoAuthentication để by pass check authentication
@RequiredRoles check có role mới được access
Không đánh dấu annotation nào thì method controller đó required có token là được

4. Tạo JsonExceptionHandler để khi có lỗi từ interceptor bắn ra, chuyển thành dạng JSON bắn về cho phía client

======================================================================

Test thử

GET
localhost:8080/isGuest
Header: N/A hoặc Content-Type: application/json
Kết quả: Yes, guest is accessible

GET
localhost:8080/isRegistered
Header: N/A hoặc Content-Type: application/json
Kết quả:
{
  "statusCode": "401",
  "statusDescription": "Unauthorized",
  "errorMessage": "Required token",
  "originalExceptionType": "com.higgsup.fswd.classroommanager.exception.InterceptorException"
}

GET
localhost:8080/isRegistered
Header: Content-Type: application/json và auth-token: 0001
Kết quả: Yes, registered user is accessible

======================================================================

Ghi chú:
Nên dùng token là UUID hoặc 1 kỹ thuật gen random string ko trùng
Trong bài mẫu này có 2 roles, tùy từng nhóm có thể ko có role hoặc có nhiều hơn
Hệ thống User/Password/Token phải được tạo ra và lưu trong database