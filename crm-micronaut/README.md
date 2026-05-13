# CRM客户管理系统

基于 Micronaut 框架构建的客户关系管理系统。

## 技术栈

- **框架**: Micronaut 4.2.3
- **数据库**: H2 (内存数据库)
- **ORM**: Hibernate / JPA
- **前端**: Bootstrap 5 + Font Awesome
- **构建工具**: Maven

## 功能特性

- ✅ 客户信息管理（CRUD）
- ✅ 客户状态切换（活跃/非活跃）
- ✅ 客户搜索功能
- ✅ 统计数据展示
- ✅ 预设示例数据

## 快速开始

### 环境要求

- Java 17+
- Maven 3.8+

### 运行项目

```bash
cd crm-micronaut

# 编译项目
mvn compile

# 运行项目
mvn mn:run

# 或者使用打包后的JAR运行
mvn package -DskipTests
java -jar target/crm-micronaut-1.0.0.jar
```

### 访问地址

- **前端页面**: http://localhost:8080
- **API 文档**: http://localhost:8080/swagger-ui

## API 端点

| 端点 | 方法 | 描述 |
|------|------|------|
| `/api/customers` | GET | 获取所有客户 |
| `/api/customers/{id}` | GET | 获取单个客户 |
| `/api/customers` | POST | 创建新客户 |
| `/api/customers/{id}` | PUT | 更新客户信息 |
| `/api/customers/{id}` | DELETE | 删除客户 |
| `/api/customers/{id}/toggle-status` | PATCH | 切换客户状态 |
| `/api/customers/search` | GET | 搜索客户 |
| `/api/customers/stats` | GET | 获取统计数据 |

## 项目结构

```
src/main/java/com/crm/demo/
├── Application.java          # 主入口类
├── controller/
│   ├── HomeController.java   # 首页控制器
│   └── CustomerController.java # REST API 控制器
├── service/
│   └── CustomerService.java  # 业务逻辑层
├── repository/
│   └── CustomerRepository.java # 数据访问层
├── entity/
│   ├── Customer.java         # 客户实体
│   └── CustomerStatus.java   # 状态枚举
└── config/
    └── DataInitializer.java  # 数据初始化
```

## 配置说明

配置文件位于 `src/main/resources/application.yml`：

```yaml
micronaut:
  application:
    name: crm-micronaut
  server:
    port: 8080

datasources:
  default:
    url: jdbc:h2:mem:crm;MODE=MYSQL
    driverClassName: org.h2.Driver
    username: sa
    password: ""
```

## License

MIT License
