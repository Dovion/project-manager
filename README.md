# Project manager
**Test task from the employer**

Данный сервис представляет собой аналог доски для задач с возможностью добавления выполняемых проектов и подпроектов и выполняемых задач в их составе.

### **Зависимости проекта:**
* Spring Boot Data
* Spring Boot Validation
* Spring Boot Web
* Spring Boot Security
* H2 Database
* ModelMapper
* Springdoc-openapi
* Lombok

### **Как запустить  с помощью IDE:**
1. Склонировать проект
2. Локально запустить проект через среду разработки, поддерживающую JDK 17
4. Перейти на страницу с документацией: http://localhost:8090/swagger-ui/index.html#/
* Если требуется доступ к базе данных, то необходимо воспользоваться консолью H2 (JDBC URL: *jdbc:h2:file:./db/project*): http://localhost:8090/h2-console/
4. Осуществить работу с эндпоинтами сервиса в соответствии с документацией. (Авторизацию осуществлять лишь с помощью "Authorize" в Swagger)

### **Как запустить  с помощью Docker:**
1. Склонировать проект
2. Собрать и запустить образ с помощью docker-compose.yml из корня каталога project-manager (порт 8090)
4. Перейти на страницу с документацией: http://localhost:8090/swagger-ui/index.html#/
* Если требуется доступ к базе данных, то необходимо воспользоваться консолью H2 (JDBC URL: *jdbc:h2:file:./db/project*): http://localhost:8090/h2-console/
4. Осуществить работу с эндпоинтами сервиса в соответствии с документацией. (Авторизацию осуществлять лишь с помощью "Authorize" в Swagger)

### **Данные для авторизации:**
1. Администратор
* Логин: admin
* Пароль: password
2. Пользователь
* Логин: user
* Пароль: password

### **Данные для подключения к консоли H2:**

![H2](https://sun9-21.userapi.com/impg/h65OC_EgWptz47BYuPkRychn-US7jlxNFk6syA/wSSLWSS86sU.jpg?size=489x404&quality=96&sign=b86074e9b6487f46f3df9b70696ee084&type=album)
