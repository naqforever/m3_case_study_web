<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cg" uri="customPagingTag" %>
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
            <td>${i.count}</td>
            <td>${c.fullName}</td>
            <td>${c.birthday}</td>
            <td>${c.email}</td>
            <td>${c.customerType.name}</td>
            <td>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal"
                        onclick="openDeleteModal(${c.id})">
                    Delete
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <cg:paging name="customer" params="${result}" search="${by}:${val}"></cg:paging>

<script>
    function openDeleteModal(id){
        // document.getElementById("hdId").value = id;
    }
</script>
