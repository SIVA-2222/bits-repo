<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Add or update author profiles in the hub">
    <title>
        <c:choose>
            <c:when test="${author.id != null}">Edit Author</c:when>
            <c:otherwise>Add Author</c:otherwise>
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
                <h1>
                    <c:choose>
                        <c:when test="${author.id != null}">&#9998; Edit Author</c:when>
                        <c:otherwise>+ Add New Author</c:otherwise>
                    </c:choose>
                </h1>
                <p class="subtitle">
                    <c:choose>
                        <c:when test="${author.id != null}">Update the details of an existing author</c:when>
                        <c:otherwise>Fill in the form below to register a new author</c:otherwise>
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

        <!-- Author Form -->
        <div class="form-container">
            <div class="card">
                <c:choose>
                    <c:when test="${author.id != null}">
                        <c:set var="formAction" value="${pageContext.request.contextPath}/authors/update/${author.id}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="formAction" value="${pageContext.request.contextPath}/authors/save"/>
                    </c:otherwise>
                </c:choose>

                <form:form method="POST" action="${formAction}" modelAttribute="author" id="author-form">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="name">Author Name *</label>
                            <form:input path="name" id="name" cssClass="form-control"
                                        placeholder="Enter full name" required="true"/>
                            <form:errors path="name" cssClass="error-text"/>
                        </div>
                        <div class="form-group">
                            <label for="email">Email Address</label>
                            <form:input path="email" id="email" cssClass="form-control"
                                        placeholder="author@example.com" type="email"/>
                            <form:errors path="email" cssClass="error-text"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nationality">Nationality</label>
                        <form:input path="nationality" id="nationality" cssClass="form-control"
                                    placeholder="e.g., British, American, Indian"/>
                        <form:errors path="nationality" cssClass="error-text"/>
                    </div>

                    <div class="form-group">
                        <label for="biography">Biography</label>
                        <form:textarea path="biography" id="biography" cssClass="form-control"
                                       placeholder="Write a short biography about the author..."
                                       rows="4"/>
                        <form:errors path="biography" cssClass="error-text"/>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-success" id="save-author-btn">
                            <c:choose>
                                <c:when test="${author.id != null}">&#10004; Update Author</c:when>
                                <c:otherwise>&#10004; Save Author</c:otherwise>
                            </c:choose>
                        </button>
                        <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary" id="cancel-btn">
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
