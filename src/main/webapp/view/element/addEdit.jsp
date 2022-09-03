<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="addEditModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="/${name}">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Create ${name}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input name="id" hidden value="0">
                    <c:forEach items="${fields}" var="f">
                        <div class="mb-3">
                            <label class="form-label">${f.getName()}</label>
                            <input class="form-control" name="fullName"/>
                        </div>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </div>
        </form>
    </div>
</div>