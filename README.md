# UserManagementSystem-SpringSecurity
Project: Securing a RESTful API with Spring Security and JWT

How to set up the project:
Update the DataBase configuration properties in the application.properties file

How to run the application:
1. Create the DB in MySQL
2. Run the UserManagementApiApplication class.
3. Specify the active profile (dev,test,prod) in application.properties file

NOTE: The server port and logs level will change according with the active profile:
dev -> server port: 8081, logs level: DEBUG
test -> server port: 8082, logs level: INFO
prod -> server port: 8080, logs level: WARN

LOGS for dev and test profiles will be showed on the console and for prod profile will be saved in "logs/app.log" file. You can modify this name in logback-spring.xml file.

API endpoints and sample requests for testing:

All the endpoints with this annotation @PreAuthorize("hasRole('ADMIN')") are able for ADMIN users.
To dientify the user logged is necessary a token that will be obtain with /login endpoint and you need to add it in the Authorization section  when you select Bearer token type on each endpoint that need authorization.

Token example: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huIiwiaWF0IjoxNzUwMTU0NTU2LCJleHAiOjE3NTAxNTgxNTZ9.bCUicFIxjj3GJ6ZlU2LVCMeykU6VpCaOHqfOZvkrlsji0scwcbODJI0zDEOb59-Hbada3aXAYEWBVJEROrMdFw

For ROLES:
CREATE:
 - POST -> http://localhost:8080/roles
 - BodyRequest:
{
  "name": "ADMIN"
}
- Needs Bearer token


READ:
- GET -> http://localhost:8080/roles/1 (get roles by ID)
- GET -> http://localhost:8080/roles (get all roles)

UPDATE:
- PUT -> http://localhost:8080/roles/1
 - BodyRequest:
{
  "name": "USER"
}
- Needs Bearer token

DELETE:
- DELETE -> http://localhost:8080/roles/1
- Needs Bearer token

For USERS:
CREATE:
 - POST -> http://localhost:8080/users/register
 - BodyRequest:
{
  "userName": "john",
  "password": "password123",
  "role": {
    "name": "ADMIN"
  }
}
- Needs Bearer token

READ:
 - POST -> http://localhost:8080/users/login
 - BodyRequest:
{
  "userName": "john",
  "password": "password123"
}

- GET -> http://localhost:8080/users/profile (Needs Bearer token)
- GET -> http://localhost:8080/users/1 (get user profile by ID but if the user is not ADMIN just can get its own profile)

DELETE:
- DELETE -> http://localhost:8080/users/2
- Needs Bearer token
