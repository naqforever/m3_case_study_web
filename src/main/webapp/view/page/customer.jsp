<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cg" uri="customTag" %>

<div class="table-title mt-3">
    <div class="row">
        <div class="col">
            <h3><b>Customer</b></h3>
        </div>
        <div class="col-4">
            <button onclick="resetCustomerForm()" class="btn btn-success float-end me-2" data-bs-toggle="modal"
                    data-bs-target="#addEditModal">Add New
            </button>
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
    <c:forEach items="${result.getContent()}" var="r" varStatus="i">
        <tr>
            <td>${i.count + (result.getNumber() -1) * result.getPageSize()}</td>
            <td>${r.fullName}</td>
            <td>${r.birthday}</td>
            <td>${r.email}</td>
            <td>${r.customerType.name}</td>
            <td>
                <button onclick="showEditCustomer('${r.id}', '${r.fullName}', '${r.birthday}', '${r.gender}'
                        , '${r.identifyNumber}', '${r.phone}', '${r.email}', '${r.address}', '${r.customerTypeId}')"
                        class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEditModal">Edit
                </button>
                <button onclick="deleteItem('${r.id}')" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<cg:paging params="${result}" search="${by}:${val}"></cg:paging>
<cg:delete></cg:delete>

<div class="modal fade" id="addEditModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="/customer">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Create Customer</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input name="id" hidden value="0">
                    <div class="mb-3">
                        <label class="form-label">Full Name</label>
                        <input class="form-control" name="fullName"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Birthday</label>
                        <input type="date" class="form-control" name="birthday"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Gender</label>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="rdMale" name="gender" value="true">Male
                            <label class="form-check-label" for="rdMale"></label>
                        </div>
                        <div class="form-check">
                            <input type="radio" class="form-check-input" id="rdFemale" name="gender" value="false">Female
                            <label class="form-check-label" for="rdFemale"></label>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Identify Number</label>
                        <input class="form-control" name="identifyNumber"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Phone</label>
                        <input class="form-control" name="phone"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input class="form-control" name="email"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Address</label>
                        <input class="form-control" name="address"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Customer Type</label>
                        <select name="customerType" class="form-select" aria-label="Default select example">
                            <option>--- select customer type ---</option>
                            <c:forEach var="ct" items="${customerTypes}">
                                <option value="${ct.id}"
                                >${ct.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>
