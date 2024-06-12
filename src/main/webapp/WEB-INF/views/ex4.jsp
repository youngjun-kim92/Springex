<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>

<!--  out : jstl의 문법인데 데이터를 출력하는데 사용하는 태그 -->
<h1><c:out value="${message}"></c:out></h1>
</body>
</html>