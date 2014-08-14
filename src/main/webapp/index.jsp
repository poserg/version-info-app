<%@ page import="com.github.poserg.VersionInfo" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<div id="versionPanel">
Версия: <%= VersionInfo.getInstance().getFullVersion() %>
</div>
</body>
</html>
