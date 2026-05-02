<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Add or update book records in the catalog">
    <title>
        <c:choose>
            <c:when test="${book.id != null}">Edit Book</c:when>
            <c:otherwise>Add Book</c:otherwise>
        </c:choose>
        - Digital Library Hub
    </title>
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
                <h1>
                    <c:choose>
                        <c:when test="${book.id != null}">&#9998; Edit Book</c:when>
                        <c:otherwise>+ Add New Book</c:otherwise>
                    </c:choose>
                </h1>
                <p class="subtitle">
                    <c:choose>
                        <c:when test="${book.id != null}">Update the details of an existing book</c:when>
                        <c:otherwise>Fill in the form below to add a new book to the library</c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>

        <!-- Error Message -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" id="form-error-alert">
                &#10060; ${errorMessage}
            </div>
        </c:if>

        <!-- Book Form -->
        <div class="form-container">
            <div class="card">
                <c:choose>
                    <c:when test="${book.id != null}">
                        <c:set var="formAction" value="${pageContext.request.contextPath}/books/update/${book.id}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="formAction" value="${pageContext.request.contextPath}/books/save"/>
                    </c:otherwise>
                </c:choose>

                <form:form method="POST" action="${formAction}" modelAttribute="book" id="book-form">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="title">Book Title *</label>
                            <form:input path="title" id="title" cssClass="form-control"
                                        placeholder="Enter book title" required="true"/>
                            <form:errors path="title" cssClass="error-text"/>
                        </div>
                        <div class="form-group">
                            <label for="isbn">ISBN *</label>
                            <form:input path="isbn" id="isbn" cssClass="form-control"
                                        placeholder="e.g., 978-0451524935" required="true"/>
                            <form:errors path="isbn" cssClass="error-text"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="genre">Genre</label>
                            <form:input path="genre" id="genre" cssClass="form-control"
                                        placeholder="e.g., Fiction, Mystery, Romance"/>
                            <form:errors path="genre" cssClass="error-text"/>
                        </div>
                        <div class="form-group">
                            <label for="publishedYear">Published Year</label>
                            <form:input path="publishedYear" id="publishedYear" cssClass="form-control"
                                        placeholder="e.g., 2024" type="number" min="1000" max="2100"/>
                            <form:errors path="publishedYear" cssClass="error-text"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="price">Price ($)</label>
                            <form:input path="price" id="price" cssClass="form-control"
                                        placeholder="e.g., 12.99" type="number" step="0.01" min="0"/>
                            <form:errors path="price" cssClass="error-text"/>
                        </div>
                        <div class="form-group">
                            <label for="authorId">Author *</label>
                            <select name="authorId" id="authorId" class="form-control" required>
                                <option value="">-- Select an Author --</option>
                                <c:forEach var="author" items="${authors}">
                                    <option value="${author.id}"
                                        <c:if test="${book.author != null && book.author.id == author.id}">selected</c:if>>
                                        ${author.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success" id="save-book-btn">
                            <c:choose>
                                <c:when test="${book.id != null}">&#10004; Update Book</c:when>
                                <c:otherwise>&#10004; Save Book</c:otherwise>
                            </c:choose>
                        </button>
                        <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary" id="cancel-btn">
                            Cancel
                        </a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2025 Digital Library Hub | Created using Spring Boot &amp; JPA</p>
    </footer>

</body>
</html>
