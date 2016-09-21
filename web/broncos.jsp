<%@ page import="java.util.List" %>
<%@ page import="org.dronamraju.nfl.model.Game" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>NFL FUN</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
    <script src="bootstrap-3.3.7/js/jquery.min.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap.min.js"></script>

    <style>
        .headerStyle {
            margin-top: 10px;
            margin-bottom: 10px;
            font-size: 21px;
            font-weight: bold;
            color: #00394d;
            background-color: #ccf2ff;
            border: 1px solid #00394d;
            border-radius: 3px;
            text-align: center;
        }
    </style>

    <script>

        function addTwoNumber(str1, str2, str3){
            var a = document.getElementById(str1).value;
            var b = document.getElementById(str2).value;

            var x = Number(a) + Number(b);
            document.getElementById(str3).value = x;
        }

    </script>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row bg-primary well-sm">
        <ul>
            <li>
                <h5>Total 16 games</h5>
            </li>
            <li>
                <h5>
                    Pool: Top 3 who select highest number of winning teams and who's total score of the 2 teams equals or very close to the actual score will be the winners (50%, 30%, 20%).
                </h5>
            </li>
            <li>
                <h5>Other conditions...</h5>
            </li>
        </ul>
    </div>

    <br><br>
    <div class="row">
        <form action='addScore' method="post">
            <div class="panel panel-primary">
                <div class="panel-heading panel-title">
                    <div class="row">
                        <div class="col-lg-10">
                            <label>
                                <c:if test="${not empty requestScope.user}">
                                    <c:out value="${user.firstName} ${user.lastName}"/>
                                </c:if>
                            </label>
                        </div>
                        <div class="col-lg-2">
                            <button type="submit" class="btn-info">Save Scores</button>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-responsive">
                        <%
                            List gamesList = (List)request.getSession().getAttribute("games");
                            for(int i=0; i< gamesList.size(); i++) {
                        %>
                            <tr class="bg-info" style="color: #ffffff;">
                                <th width="4%"><%= i+1 %></th>
                                <th width="24%"><%= ((Game)gamesList.get(i)).getDate() + " " + ((Game)gamesList.get(i)).getTime()%></th>
                                <th width="24%">
                                    <label>
                                        <%= ((Game)gamesList.get(i)).getTeam1Name()%>
                                    </label>
                                    <input type="text"
                                           size="2"
                                           maxlength="2"
                                           id="<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score"
                                           name="<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score"
                                           value="<%= request.getAttribute(((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate() + "Score") %>"
                                           onchange="addTwoNumber('<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score', '<%= ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score', '<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>TotalScore')"
                                           style="color: #000000;">&nbsp;&nbsp;&nbsp;
                                    <input type="radio"
                                           name="optRadio<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate() %>"
                                           value="<%= request.getAttribute("optRadio" + ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate()) %>">
                                </th>
                                <th width="24%">
                                    <input type="radio"
                                           name="optRadio<%= ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>"
                                           value="<%= request.getAttribute("optRadio" + ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate()) %>">&nbsp;&nbsp;&nbsp;
                                    <input type="text"
                                           size="2"
                                           maxlength="2"
                                           id="<%= ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score"
                                           name="<%= ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score"
                                           value="<%= request.getAttribute(((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() + "Score") %>"
                                           onchange="addTwoNumber('<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score', '<%= ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>Score', '<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>TotalScore')"
                                           style="color: #000000;">
                                    <label>
                                        <%= ((Game)gamesList.get(i)).getTeam2Name()%>
                                    </label>
                                </th>
                                <th width="24%">
                                    Total:&nbsp;<input
                                        type="text"
                                        size="3"
                                        maxlength="3"
                                        id="<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>TotalScore"
                                        name="<%= ((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() %>TotalScore"
                                        value="<%= request.getAttribute(((Game)gamesList.get(i)).getTeam1Name() + "-" + ((Game)gamesList.get(i)).getTeam2Name() + "-" + ((Game)gamesList.get(i)).getDate() + "TotalScore") %>"
                                        style="color: #000000;">
                                </th>
                            </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>