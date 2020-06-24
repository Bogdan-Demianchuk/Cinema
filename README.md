# Сinema ticket management
[Project purpose](#purpose)<br>
[Project structure](#structure)<br>
[Available functions](#avaiable_functions)<br>
[The technology used in this project](#technology)<br>
[For developer](#developer-start)<br>
[Authors](#authors)

# <a name="purpose"></a>Project purpose
This is an implementation REST API with the basic functions of manage movie theater ticket orders

# <a name="structure"></a>Project Structure
   The project has a structure of two layers. There are layers of service and DAO. The service layer is responsible for the business logic of the application, the DAO layer responsible for CRUD operations with entities in the database. Also, the project has controllers for managing requests from clients.

# <a name="avvailable_functions"></a>Available Functions
For **without being authenticated** users
* Inject data (GET : "/inject-data")
* Register (POST : "/register")

For users with a **USER role only**<br>
* View shopping cards (GET : "/shoppingcarts/by-user")
* Adding movie session to shopping cards (GET : "/shoppingcarts/addmoviesession")
* Make an order (GET : "/orders/complete")
* View orders (GET : "/orders")
* View all cinema halls (GET : "/cinemahalls")
* View all movies (GET : "/movies")
* View all available movie sessions (GET : "/moviesessions/available")

For users with a **ADMIN role only**<br>
For all users plus

* Add cinema hall (POST : "/cinemahalls")
* View all cinema halls (GET : "/cinemahalls")
* Add movie (POST : "/movies")
* View all movies (GET : "/movies")
* Add movie session (POST : "/moviesessions")
* View all available movie sessions (GET : "/moviesessions/available")
* View user info by email (GET : "/users/byemail")

For POST method you must use JSON format in request body 
>Register
```sh
  {"email" : "***", "password" : "***", "repeatPassword" : "***"}
 ```
> Add cinema hall<br>
```sh
{"capacity":***,"description":"***"}
```
> Add movie
```sh
 {"title":"***","description":"***"}
 ```
> Add movie session
```sh
  {"movieId":***,"cinemaHallId":***,"showTime":"****-**-**T**:**"}
example {"movieId":1,"cinemaHallId":2,"showTime":"2020-09-05T16:00"}
```
# <a name="technology"></a>Technology
* Java 11
* Maven 4.0.0
* maven-checkstyle-plugin 3.1.1
* jackson 2.11.0
* hibernate-validator 6.1.0
* hibernate cor, java8 5.4.16
* mysql-connector-java 8.0.20
* Log4j 2.13.0
* spring-context, spring-orm, spring-webmvc, spring-security-core, spring-security-config, spring-security-web 5.2.5.RELEASE
* javax.servlet-api 3.1.0
* Tomcat 9.0.34

# <a name="developer-start"></a>For developer
To test this project you need to have installed:

* Java 11+
* Tomcat
* MySQL 

To start
* Open the project in your IDE.
* Add it as maven project.
* Configure Tomcat
    * add an artifact
    * add Java SDK (prefer version 11 or higher)
* Change a path in **src/main/resources/log4.properties** on line 12. It has to reach your logFile.
* Run the project.
* If you launch this project for the first time: 
     Do request (GET : "/inject-data") to create default users and each models entity in db . By default, there are one user with the USER role (login = "user@com", password = "111111") 
and one with an ADMIN role (login = "admin@com", password = "111111")*.

To work with MySQL you need to:
1. At src/main/resources/db.properties file on line 3-5 use username, password and URL for your DB to create a Connection.
if you want use another DB change the connection driver for your DB on line 2.
# <a name="authors"></a>Authors
[Bogdan Demianchuk](https://github.com/Bogdan-Demianchuk)