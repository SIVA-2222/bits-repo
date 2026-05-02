# Library Management System

This is a simple Spring Boot app I built to manage a library's collection of books and authors. You can use it to add new records, view what's in the database, and edit details like book titles or author bios.

## What's inside:
- **Backend**: Spring Boot 3 with Spring Data JPA
- **Frontend**: JSP pages with JSTL and custom CSS
- **Database**: H2 (runs in memory)
- **Built with**: Maven and Java 17

## How to get it running:
1.  **Build the project**:
    ```bash
    mvn clean install
    ```
2.  **Run the app**:
    ```bash
    mvn spring-boot:run
    ```
3.  **Check it out**:
    Open your browser and go to: **http://localhost:8080**

## Running the tests:
If you want to check if everything is working correctly, you can run the JUnit tests:
```bash
mvn test
```

## Useful Links:
| Page | Where to find it |
|------|-----------------|
| Home Page | http://localhost:8080/ |
| Author List | http://localhost:8080/authors |
| Book List | http://localhost:8080/books |
| Books with Authors | http://localhost:8080/books/with-authors |
| Database Console | http://localhost:8080/h2-console |


