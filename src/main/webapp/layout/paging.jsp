<%@ page import="java.util.stream.IntStream" %>
<%@ page import="m3.furama.model.Customer" %>
<%@ page import="m3.furama.util.Page" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-end">
        <li class="page-item disabled">
            <a class="page-link">Previous</a>
        </li>
        <% Page<Customer> p = (Page<Customer>) request.getAttribute("result");%>
        <c:set var="t" value="<%= IntStream.rangeClosed(1, p.getTotalPages()).boxed().collect(Collectors.toList()) %>"></c:set>
        <c:forEach var="i" items="${t}">
            <li class="page-item"><a class="page-link" href="#">${i}</a></li>
        </c:forEach>
        <li class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>