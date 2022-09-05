<%@ taglib prefix="cg" uri="customTag" %>

<cg:table header="Full Name,Identify Number,Phone,Email,Address" column="fullName,identifyNumber,phone,email,address" result="${result}"></cg:table>
<cg:addEdit name="employee" exclude="fullName,email" radio="[{\"field\":\"gender\",\"data\":[{\"key\":\"Male\",\"value\":\"true\"},{\"key\":\"Female\",\"value\":\"false\"}]}]"
select="positionId:position,degreeId:degree,departmentId:department"></c ></cg:addEdit>