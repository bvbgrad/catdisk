<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js">
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>catdisk home</title>
</head>
<body>
	Welcome to the disk catalog application.
	<br> Greeting: ${greeting}
	<br>
	<br> Disk information:
	<br> ${diskVolName}
	<br> ${diskSerialNumber}
	<br> ${diskDir}
	<br>

	<p>
		<a href="<c:url value='/scan' />">Scan the disk</a>
	</p>

    <c:url var="url" value="/scanDir" />
    <form action="${url}" method="post">
	   <input type="file" name="scanFile">
	   <input type="submit" value="Scan" name="submitScanDir" />
	</form>

	<br> Under Construction (as of ${currentDate})
	<br>
</body>
</html>
