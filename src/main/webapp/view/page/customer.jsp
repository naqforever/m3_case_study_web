<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cg" uri="customTag" %>

<div class="table-title mt-3">
    <div class="row">
        <div class="col-8">
            <h3><b>Customer</b></h3>
        </div>
        <div class="col-4">
            <a href="#addEditModal" class="btn btn-success float-end me-2 add" data-toggle="modal"><span>Add New</span></a>
        </div>

    </div>
</div>

<table class="table">
    <thead>
    <th>#</th>
    <th>Full Name</th>
    <th>Birthday</th>
    <th>Email</th>
    <th>Customer Type</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${result.getContent()}" var="c" varStatus="i">
        <tr>
            <td>${i.count + (result.getNumber() -1) * result.getPageSize()}</td>
            <td>${c.fullName}</td>
            <td>${c.birthday}</td>
            <td>${c.email}</td>
            <td>${c.customerType.name}</td>
            <td>
                <cg:delete idItem="${c.id}" nameItem="${c.fullName}"></cg:delete>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <cg:paging name="customer" params="${result}" search="${by}:${val}"></cg:paging>
