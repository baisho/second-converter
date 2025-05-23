# Second Converter API

This API converts time intervals from seconds into human-readable formats, and stores conversion data linked to users. You can also retrieve user and conversion history data.

---

## How to use the API

You can explore and test these ans other API endpoints using Swagger UI:
http://localhost:8080/swagger-ui/index.html

### Endpoints examples

GET /convert/{seconds}**
Converts the given number of seconds into a readable string.
Example:
  
GET /convert/3661
Response: "1 hour, 1 minute and 1 second"

POST /convert/{userId}/{seconds}
Saves the conversion for the specified user.

### H2 Database

The application uses an embedded H2 in-memory database that stores data during runtime:
http://localhost:8080/h2-console

Login details:
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password

Additional notes
The in-memory database resets on every application restart.
Swagger UI makes it easy to test GET and POST requests interactively.
To use a different database, update your application.properties accordingly.




