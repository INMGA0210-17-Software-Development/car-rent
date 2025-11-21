# 汽车租赁平台 API 接口文档

## 概述

本文档描述了汽车租赁平台前端所需的所有后端API接口。所有接口都需要HTTPS协议，基础URL为：`https://your-api-domain.com/api`

## 认证方式

除了登录、注册等公开接口外，其他接口都需要在请求头中包含JWT Token：

```
Authorization: Bearer <your-jwt-token>
```

## 通用响应格式

所有API接口都遵循统一的响应格式：

```json
{
  "success": true,
  "message": "操作成功",
  "data": {
   
  }
}
```

错误响应格式：
```json
{
  "success": false,
  "message": "错误描述",
  "error": "详细错误信息"
}
```

---

## 1. 认证相关接口

### 1.1 用户登录
- **接口**: `POST /auth/login`
- **功能**: 用户登录认证
- **请求体**:
```json
{
  "email": "user@example.com",
  "password": "password123",
  "rememberMe": false
}
```
- **响应**:
```json
{
  "success": true,
  "token": "jwt-token-string",
  "user": {
    "id": 1,
    "email": "user@example.com",
    "name": "用户姓名",
    "role": "rentee",
    "phone": "+36 20 123 4567"
  }
}
```

### 1.2 用户注册
- **接口**: `POST /auth/register`
- **功能**: 新用户注册
- **请求体**:
```json
{
  "fullName": "张三",
  "email": "zhangsan@example.com",
  "password": "password123",
  "userType": "rentee"
}
```
- **响应**:
```json
{
  "success": true,
  "message": "注册成功，请检查邮箱验证账户",
  "user": {
    "id": 2,
    "email": "zhangsan@example.com",
    "name": "张三",
    "role": "rentee"
  }
}
```

### 1.3 社交登录
- **接口**: `POST /auth/social-login`
- **功能**: 第三方社交平台登录
- **请求体**:
```json
{
  "provider": "google",
  "token": "social-platform-token"
}
```

### 1.4 忘记密码
- **接口**: `POST /auth/forgot-password`
- **功能**: 发送密码重置邮件
- **请求体**:
```json
{
  "email": "user@example.com"
}
```

### 1.5 重置密码
- **接口**: `POST /auth/reset-password`
- **功能**: 使用重置令牌设置新密码
- **请求体**:
```json
{
  "token": "reset-token",
  "newPassword": "newpassword123"
}
```

### 1.6 验证邮箱
- **接口**: `POST /auth/verify-email`
- **功能**: 验证用户邮箱
- **请求体**:
```json
{
  "token": "verification-token"
}
```

### 1.7 获取当前用户信息
- **接口**: `GET /auth/me`
- **功能**: 获取当前登录用户信息
- **响应**:
```json
{
  "success": true,
  "user": {
    "id": 1,
    "email": "user@example.com",
    "name": "用户姓名",
    "role": "rentee",
    "phone": "+36 20 123 4567"
  }
}
```

### 1.8 退出登录
- **接口**: `POST /auth/logout`
- **功能**: 用户退出登录

---

## 2. 汽车相关接口

### 2.1 获取所有可租赁汽车列表
- **接口**: `GET /cars`
- **功能**: 获取首页展示的所有可租赁汽车
- **查询参数**:
    - `page`: 页码 (默认: 1)
    - `limit`: 每页数量 (默认: 20)
    - `search`: 搜索关键词 (汽车名称或位置)
    - `location`: 位置筛选
    - `carType`: 汽车类型筛选 (Luxury, Sports, SUV, Electric, Family)
    - `priceSort`: 价格排序 (Price Ascending, Price Descending)
    - `transmission`: 变速箱类型 (Automatic, Manual)
    - `availableOnly`: 仅显示可用汽车 (true/false)
- **响应**:
```json
{
  "success": true,
  "cars": [
    {
      "id": 1,
      "name": "Mercedes-Benz S-Class",
      "price": 299,
      "image": "https://example.com/car-image.jpg",
      "category": "Luxury",
      "available": true,
      "rating": 4.9,
      "reviews": 127,
      "location": "Budapest V. District",
      "passengers": 5,
      "fuel": "Petrol",
      "transmission": "Automatic"
    }
  ],
  "total": 50,
  "page": 1,
  "limit": 20
}
```

### 2.2 获取汽车详情
- **接口**: `GET /cars/:id`
- **功能**: 获取指定汽车的详细信息
- **响应**:
```json
{
  "success": true,
  "car": {
    "id": 1,
    "name": "Mercedes-Benz S-Class",
    "description": "豪华轿车，适合商务出行",
    "price": 299,
    "image": "https://example.com/car-image.jpg",
    "category": "Luxury",
    "available": true,
    "rating": 4.9,
    "reviews": 127,
    "location": "Budapest V. District",
    "passengers": 5,
    "fuel": "Petrol",
    "transmission": "Automatic",
    "features": ["Air Conditioning", "GPS Navigation", "Bluetooth"],
    "ownerId": 10,
    "ownerName": "车主姓名"
  }
}
```

### 2.3 获取用户拥有的汽车列表
- **接口**: `GET /cars/my-cars`
- **功能**: 获取当前用户拥有的汽车列表 (我的汽车页面)
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "cars": [
    {
      "id": 1,
      "name": "Mercedes-Benz S-Class",
      "image": "https://example.com/car-image.jpg",
      "location": "Budapest V. District",
      "passengers": 5,
      "fuel": "Petrol",
      "transmission": "Automatic",
      "status": "Available",
      "totalRentals": 89,
      "rating": 4.9,
      "reviews": 127
    }
  ]
}
```

### 2.4 添加新汽车
- **接口**: `POST /cars`
- **功能**: 用户添加新的汽车到平台
- **需要认证**: 是
- **请求体**:
```json
{
  "name": "BMW X5",
  "location": "Budapest II. District",
  "image": "https://example.com/car-image.jpg",
  "passengers": 7,
  "fuel": "Diesel",
  "transmission": "Automatic",
  "description": "宽敞的SUV，适合家庭出行",
  "features": ["Air Conditioning", "GPS Navigation", "Bluetooth"],
  "dailyRate": 350
}
```
- **响应**:
```json
{
  "success": true,
  "car": {
    "id": 10,
    "name": "BMW X5",
    "location": "Budapest II. District",
    "image": "https://example.com/car-image.jpg",
    "passengers": 7,
    "fuel": "Diesel",
    "transmission": "Automatic",
    "description": "宽敞的SUV，适合家庭出行",
    "features": ["Air Conditioning", "GPS Navigation", "Bluetooth"],
    "dailyRate": 350,
    "status": "Available",
    "totalRentals": 0,
    "rating": 0,
    "reviews": 0
  }
}
```

### 2.5 更新汽车信息
- **接口**: `PUT /cars/:id`
- **功能**: 更新汽车信息
- **需要认证**: 是
- **请求体**: 同添加汽车接口

### 2.6 删除汽车
- **接口**: `DELETE /cars/:id`
- **功能**: 删除汽车
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "message": "汽车已成功删除"
}
```

---

## 3. 预订相关接口

### 3.1 获取用户的预订列表
- **接口**: `GET /bookings/my-bookings`
- **功能**: 获取用户的预订列表 (消息页面使用)
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "bookedByYou": [
    {
      "bookingId": 1,
      "carId": 1,
      "carName": "Mercedes-Benz S-Class",
      "carImage": "https://example.com/car-image.jpg",
      "ownerId": 101,
      "ownerName": "László Kovács",
      "location": "Budapest V. District",
      "startDate": "2025-11-15",
      "endDate": "2025-11-17",
      "startTime": "10:00",
      "endTime": "18:00",
      "price": 598
    }
  ],
  "bookedBySomeone": [
    {
      "bookingId": 3,
      "carId": 3,
      "carName": "BMW X5 M Sport",
      "carImage": "https://example.com/car-image.jpg",
      "renterId": 201,
      "renterName": "Zsófia Varga",
      "location": "Budapest II. District",
      "startDate": "2025-11-16",
      "endDate": "2025-11-18",
      "startTime": "08:00",
      "endTime": "20:00",
      "price": 720
    }
  ]
}
```

### 3.2 创建新预订
- **接口**: `POST /bookings`
- **功能**: 创建新的汽车预订
- **需要认证**: 是
- **请求体**:
```json
{
  "carId": 1,
  "startDate": "2025-11-20",
  "endDate": "2025-11-22",
  "startTime": "09:00",
  "endTime": "17:00"
}
```

### 3.3 取消预订
- **接口**: `DELETE /bookings/:id`
- **功能**: 取消预订
- **需要认证**: 是

---

## 4. 租赁记录相关接口

### 4.1 获取所有租赁记录
- **接口**: `GET /rentals/all`
- **功能**: 获取用户所有汽车的租赁记录 (汽车列表管理页面)
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "rentals": [
    {
      "id": 1,
      "carId": 1,
      "carName": "Mercedes-Benz S-Class",
      "carImage": "https://example.com/car-image.jpg",
      "renterName": "János Kovács",
      "renterEmail": "janos.kovacs@email.com",
      "location": "Budapest V. District",
      "startDate": "2025-11-13T10:00:00Z",
      "endDate": "2025-11-16T10:00:00Z",
      "duration": 3,
      "dailyRate": 299,
      "totalAmount": 897,
      "status": "Completed"
    }
  ],
  "summary": {
    "totalRevenue": 3074,
    "activeRentals": 1,
    "upcomingRentals": 2
  }
}
```

---

## 5. 消息相关接口

### 5.1 获取指定预订的聊天消息
- **接口**: `GET /messages/booking/:bookingId`
- **功能**: 获取指定预订的聊天消息记录
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "messages": [
    {
      "id": 1,
      "senderId": 1,
      "receiverId": 101,
      "content": "Hi! I'm interested in renting your car.",
      "timestamp": "2025-11-10T10:30:00Z",
      "senderName": "You"
    },
    {
      "id": 2,
      "senderId": 101,
      "receiverId": 1,
      "content": "Hello! The car is available. When would you like to pick it up?",
      "timestamp": "2025-11-10T10:32:00Z",
      "senderName": "László Kovács"
    }
  ]
}
```

### 5.2 发送消息
- **接口**: `POST /messages`
- **功能**: 发送聊天消息
- **需要认证**: 是
- **请求体**:
```json
{
  "bookingId": 1,
  "receiverId": 101,
  "content": "Great! I'd like to pick it up on Nov 15 at 10:00 AM."
}
```
- **响应**:
```json
{
  "success": true,
  "message": {
    "id": 3,
    "senderId": 1,
    "receiverId": 101,
    "content": "Great! I'd like to pick it up on Nov 15 at 10:00 AM.",
    "timestamp": "2025-11-10T10:35:00Z",
    "senderName": "You"
  }
}
```

---

## 6. 评论相关接口

### 6.1 获取用户的评论列表
- **接口**: `GET /reviews/my-reviews`
- **功能**: 获取用户写的所有评论 (我的评论页面)
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "reviews": [
    {
      "id": 1,
      "carId": 1,
      "carName": "Mercedes-Benz S-Class",
      "carImage": "https://example.com/car-image.jpg",
      "location": "Budapest V. District",
      "date": "2024-10-15T00:00:00Z",
      "rating": 5,
      "reviewText": "Absolutely loved this car! The ride was smooth and comfortable."
    }
  ],
  "statistics": {
    "totalReviews": 4,
    "averageRating": 4.5,
    "fiveStarReviews": 2
  }
}
```

### 6.2 添加评论
- **接口**: `POST /reviews`
- **功能**: 添加新评论
- **需要认证**: 是
- **请求体**:
```json
{
  "carId": 1,
  "rating": 5,
  "reviewText": "Excellent car and service!"
}
```

### 6.3 更新评论
- **接口**: `PUT /reviews/:id`
- **功能**: 更新评论
- **需要认证**: 是
- **请求体**:
```json
{
  "rating": 4.5,
  "reviewText": "Updated review text"
}
```

### 6.4 删除评论
- **接口**: `DELETE /reviews/:id`
- **功能**: 删除评论
- **需要认证**: 是

---

## 7. 用户相关接口

### 7.1 获取用户资料
- **接口**: `GET /users/profile`
- **功能**: 获取用户个人资料 (个人资料页面)
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "personalInfo": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "+36 20 123 4567"
  },
  "licenseInfo": {
    "number": "AB123456",
    "issueDate": "2020-01-15",
    "expiryDate": "2030-01-15"
  },
  "paymentMethods": [
    {
      "id": 1,
      "brand": "Visa",
      "last4": "4242",
      "expires": "12/25",
      "isDefault": true
    }
  ]
}
```

### 7.2 更新用户资料
- **接口**: `PUT /users/profile`
- **功能**: 更新用户个人资料
- **需要认证**: 是
- **请求体**:
```json
{
  "personalInfo": {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phone": "+36 20 123 4567"
  },
  "licenseInfo": {
    "number": "AB123456",
    "issueDate": "2020-01-15",
    "expiryDate": "2030-01-15"
  }
}
```

### 7.3 获取用户设置
- **接口**: `GET /users/settings`
- **功能**: 获取用户设置 (设置页面)
- **需要认证**: 是
- **响应**:
```json
{
  "success": true,
  "settings": {
    "notifications": {
      "emailNotifications": true,
      "bookingReminders": true,
      "promotionalEmails": false
    },
    "regionalSettings": {
      "language": "English",
      "currency": "USD ($)"
    }
  }
}
```

### 7.4 更新用户设置
- **接口**: `PUT /users/settings`
- **功能**: 更新用户设置
- **需要认证**: 是
- **请求体**:
```json
{
  "notifications": {
    "emailNotifications": true,
    "bookingReminders": true,
    "promotionalEmails": false
  },
  "regionalSettings": {
    "language": "English",
    "currency": "USD ($)"
  }
}
```

### 7.5 添加支付方式
- **接口**: `POST /users/payment-methods`
- **功能**: 添加新的支付方式
- **需要认证**: 是
- **请求体**:
```json
{
  "cardNumber": "4242424242424242",
  "expiryMonth": 12,
  "expiryYear": 2025,
  "cvv": "123",
  "cardholderName": "John Doe"
}
```
- **响应**:
```json
{
  "success": true,
  "paymentMethod": {
    "id": 2,
    "brand": "Visa",
    "last4": "4242",
    "expires": "12/25",
    "isDefault": false
  }
}
```

### 7.6 设置默认支付方式
- **接口**: `PUT /users/payment-methods/:id/set-default`
- **功能**: 设置默认支付方式
- **需要认证**: 是

### 7.7 删除支付方式
- **接口**: `DELETE /users/payment-methods/:id`
- **功能**: 删除支付方式
- **需要认证**: 是

---

## 错误代码说明

| 状态码 | 说明 |
|--------|------|
| 200 | 请求成功 |
| 201 | 创建成功 |
| 400 | 请求参数错误 |
| 401 | 未授权，需要登录 |
| 403 | 禁止访问，权限不足 |
| 404 | 资源不存在 |
| 409 | 资源冲突 |
| 422 | 请求参数验证失败 |
| 500 | 服务器内部错误 |

## 数据类型说明

### 汽车状态 (Car Status)
- `Available`: 可用
- `Rented`: 已租出
- `Maintenance`: 维护中

### 预订状态 (Booking Status)
- `Pending`: 待确认
- `Confirmed`: 已确认
- `Active`: 进行中
- `Completed`: 已完成
- `Cancelled`: 已取消

### 租赁状态 (Rental Status)
- `Upcoming`: 即将开始
- `Active`: 进行中
- `Completed`: 已完成

### 用户角色 (User Role)
- `rentee`: 租客
- `admin`: 管理员

## 注意事项

1. 所有日期时间字段都使用ISO 8601格式 (YYYY-MM-DDTHH:mm:ssZ)
2. 价格字段都以美元为单位，保留两位小数
3. 图片URL需要支持HTTPS协议
4. 分页查询默认每页20条记录，最大100条
5. 搜索功能支持模糊匹配
6. 所有敏感信息（如密码、支付信息）需要加密传输和存储
7. API需要支持跨域请求 (CORS)
8. 建议实现请求频率限制以防止滥用
