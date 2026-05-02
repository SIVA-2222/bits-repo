<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Advanced Cataloging Hub - Streamline Author and Book Management">
    <title>Digital Library Hub - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <!-- Navigation Bar -->
    <nav class="navbar">
        <div class="navbar-content">
            <a href="${pageContext.request.contextPath}/" class="navbar-brand">
                <span class="icon">&#128218;</span> Library Hub
            </a>
            <ul class="navbar-links">
                <li><a href="${pageContext.request.contextPath}/" class="active">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/authors">Authors</a></li>
                <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
                <li><a href="${pageContext.request.contextPath}/books/with-authors">Books &amp; Authors</a></li>
            </ul>
        </div>
    </nav>

    <!-- Hero Section -->
    <div class="container fade-in">
        <div class="hero">
            <h1>Digital Library<br>Registry</h1>
            <p>An integrated Spring Boot solution designed for sophisticated bibliographic oversight. 
               Leveraging JPA, MVC architecture, and custom JSP views.</p>

            <div class="hero-cards">
                <a href="${pageContext.request.contextPath}/authors" class="hero-card" id="manage-authors-card">
                    <span class="icon">&#9997;</span>
                    <h3>Author Directory</h3>
                    <p>Curate detailed profiles and manage biographical data for all registered writers.</p>
                </a>
                <a href="${pageContext.request.contextPath}/books" class="hero-card" id="manage-books-card">
                    <span class="icon">&#128214;</span>
                    <h3>Book Inventory</h3>
                    <p>Maintain an extensive catalog including ISBN tracking and categorical classification.</p>
                </a>
                <a href="${pageContext.request.contextPath}/books/with-authors" class="hero-card" id="join-view-card">
                    <span class="icon">&#128279;</span>
                    <h3>Relational Insights</h3>
                    <p>Explore interconnected data points via high-performance JPQL aggregate queries.</p>
                </a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2025 Library Management System | Built with Spring Boot, JPA &amp; JSP</p>
    </footer>

</body>
</html>
