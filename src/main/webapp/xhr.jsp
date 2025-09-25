<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
        <script type="text/javascript">
            var showMessage = function (input) {
                var userName = encodeURI(input.value.trim());
                var userNameMessage = document.getElementById("userNameMessage");
                if (userName === "") {
                    userNameMessage.style.color = "red";
                    userNameMessage.innerHTML = "用户名不能为空！";
                    return;
                }

                var request = new XMLHttpRequest();
                request.open("POST", "<%= request.getContextPath() %>/XHRServlet");
                var data = "action=checkUserName&userName=" + userName;
                request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                request.send(data);

                request.onreadystatechange = function () {
                    if (request.readyState === 4 && request.status === 200) {
                        userNameMessage.innerHTML = request.responseText;
                    }
                }
            }
        </script>
    </head>
    <body>
        <input onblur="showMessage(this)" type='text'/><span id='userNameMessage'></span>
    </body>
</html>