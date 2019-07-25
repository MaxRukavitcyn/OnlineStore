<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 26.07.2019
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div ${user.setLastName("Hand")}>
    <div>Hello: ${user.name} ${user.lastName}</div>
</div>
<script>
    window.user = {};
    user = ${user.toString()};

</script>
</body>
</html>
