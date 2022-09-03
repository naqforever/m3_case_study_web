<%@ taglib prefix="cg" uri="customTag" %>

<cg:table header="Full Name,Identify Number,Phone,Email,Address" column="fullName,identifyNumber,phone,email,address" result="${result}"></cg:table>
<cg:addEdit name="employee" exclude="fullName,email" radio="[]"></cg:addEdit>