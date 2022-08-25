<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
    <title><tiles:getAsString name="title" /></title>
    <title>Furama Resort</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <tiles:insertAttribute name="header" />
    <main class="row">
        <tiles:insertAttribute name="menu" />
        <div class="col-11">
            <tiles:insertAttribute name="body" />
        </div>
    </main>
</div>

<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>