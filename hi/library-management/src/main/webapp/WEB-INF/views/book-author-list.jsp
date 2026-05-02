<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Explore relational data using a specialized join query">
    <title>Relational Insights - Digital Library Hub</title>
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
                <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
                <li><a href="${pageContext.request.contextPath}/books/with-authors" class="active">Books &amp; Authors</a></li>
            </ul>
        </div>
    </nav>

    <div class="container fade-in">
        <!-- Page Header -->
        <div class="page-header">
            <div>
                <h1>&#128279; Relational Insights</h1>
                <p class="subtitle">An integrated view that combines title details with their respective creator profiles</p>
            </div>
        </div>

        <!-- Info Alert about the query -->
        <div class="alert alert-warning" style="margin-bottom: 1.5rem;">
            &#128161; <strong>Custom Query:</strong> This view uses a JPQL INNER JOIN:
            <code style="background: rgba(0,0,0,0.2); padding: 2px 6px; border-radius: 4px; margin-left: 4px;">
                SELECT ... FROM Book b INNER JOIN b.author a
            </code>
        </div>

        <!-- Inner Join Results Table -->
        <div class="card">
            <c:choose>
                <c:when test="${not empty booksWithAuthors}">
                    <div class="table-wrapper">
                        <table id="books-authors-table">
                            <thead>
                                <tr>
                                    <th>Book ID</th>
                                    <th>Book Title</th>
                                    <th>ISBN</th>
                                    <th>Genre</th>
                                    <th>Year</th>
                                    <th>Price ($)</th>
                                    <th>Author ID</th>
                                    <th>Author Name</th>
                                    <th>Author Email</th>
                                    <th>Nationality</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${booksWithAuthors}">
                                    <tr>
                                        <td><span class="badge badge-primary">#${item.bookId}</span></td>
                                        <td><strong>${item.bookTitle}</strong></td>
                                        <td><code>${item.isbn}</code></td>
                                        <td><span class="badge badge-secondary">${item.genre}</span></td>
                                        <td>${item.publishedYear}</td>
                                        <td>$${item.price}</td>
                                        <td><span class="badge badge-primary">#${item.authorId}</span></td>
                                        <td><strong>${item.authorName}</strong></td>
                                        <td>${item.authorEmail}</td>
                                        <td><span class="badge badge-secondary">${item.authorNationality}</span></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="empty-state">
                        <div class="icon">&#128279;</div>
                        <p>No data found. Make sure both books and authors exist in the database.</p>
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
