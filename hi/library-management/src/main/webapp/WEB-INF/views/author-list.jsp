<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Browse and update the registry of library authors">
    <title>Author Registry - Digital Library Hub</title>
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
                <li><a href="${pageContext.request.contextPath}/authors" class="active">Authors</a></li>
                <li><a href="${pageContext.request.contextPath}/books">Books</a></li>
                <li><a href="${pageContext.request.contextPath}/books/with-authors">Books &amp; Authors</a></li>
            </ul>
        </div>
    </nav>

    <div class="container fade-in">
        <!-- Page Header -->
        <div class="page-header">
            <div>
                <h1>&#9997; Author Registry</h1>
                <p class="subtitle">Complete list of writers currently in our library system</p>
            </div>
            <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary" id="add-author-btn">
                + Add New Author
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

        <!-- Authors Table -->
        <div class="card">
            <c:choose>
                <c:when test="${not empty authors}">
                    <div class="table-wrapper">
                        <table id="authors-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Nationality</th>
                                    <th>Biography</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="author" items="${authors}">
                                    <tr>
                                        <td><span class="badge badge-primary">#${author.id}</span></td>
                                        <td><strong>${author.name}</strong></td>
                                        <td>${author.email}</td>
                                        <td><span class="badge badge-secondary">${author.nationality}</span></td>
                                        <td>${author.biography}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/authors/edit/${author.id}"
                                               class="btn btn-warning btn-sm" id="edit-author-${author.id}">
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
                        <div class="icon">&#128100;</div>
                        <p>No authors found. Add your first author to get started!</p>
                        <a href="${pageContext.request.contextPath}/authors/add" class="btn btn-primary">
                            + Add Author
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
