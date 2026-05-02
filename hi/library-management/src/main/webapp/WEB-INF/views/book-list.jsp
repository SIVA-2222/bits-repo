<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="View and manage our extensive book collection">
    <title>Book Catalog - Digital Library Hub</title>
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
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/authors">Authors</a></li>
                <li><a href="${pageContext.request.contextPath}/books" class="active">Books</a></li>
                <li><a href="${pageContext.request.contextPath}/books/with-authors">Books &amp; Authors</a></li>
            </ul>
        </div>
    </nav>

    <div class="container fade-in">
        <!-- Page Header -->
        <div class="page-header">
            <div>
                <h1>&#128214; Book Inventory</h1>
                <p class="subtitle">Full list of titles currently stored in our collection</p>
            </div>
            <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary" id="add-book-btn">
                + Add New Book
            </a>
        </div>

        <!-- Success / Error Messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success" id="success-alert">
                &#10004; ${successMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" id="error-alert">
                &#10060; ${errorMessage}
            </div>
        </c:if>

        <!-- Books Table -->
        <div class="card">
            <c:choose>
                <c:when test="${not empty books}">
                    <div class="table-wrapper">
                        <table id="books-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>ISBN</th>
                                    <th>Genre</th>
                                    <th>Year</th>
                                    <th>Price ($)</th>
                                    <th>Author</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="book" items="${books}">
                                    <tr>
                                        <td><span class="badge badge-primary">#${book.id}</span></td>
                                        <td><strong>${book.title}</strong></td>
                                        <td><code>${book.isbn}</code></td>
                                        <td><span class="badge badge-secondary">${book.genre}</span></td>
                                        <td>${book.publishedYear}</td>
                                        <td>$${book.price}</td>
                                        <td>${book.author.name}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/books/edit/${book.id}"
                                               class="btn btn-warning btn-sm" id="edit-book-${book.id}">
                                                &#9998; Edit
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="icon">&#128214;</div>
                        <p>No books found. Add your first book to get started!</p>
                        <a href="${pageContext.request.contextPath}/books/add" class="btn btn-primary">
                            + Add Book
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2025 Digital Library Hub | Created using Spring Boot &amp; JPA</p>
    </footer>

</body>
</html>
