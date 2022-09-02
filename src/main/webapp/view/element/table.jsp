<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <thead>
    <th>#</th>
    <c:forEach var="h" items="${headers}">
        <th>${h}</th>
    </c:forEach>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${result.getContent()}" var="r" varStatus="i">
        <tr>
            <td>${i.count + (result.getNumber() -1) * result.getPageSize()}</td>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td>4</td>
            <td>
<%--                <button onclick="showEditCustomer('${r.id}', '${r.fullName}', '${r.birthday}', '${r.gender}'--%>
<%--                        , '${r.identifyNumber}', '${r.phone}', '${r.email}', '${r.address}', '${r.customerTypeId}')"--%>
<%--                        class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEditModal">Edit--%>
<%--                </button>--%>
<%--                <button onclick="deleteItem('${r.id}','${r.fullName}')" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
