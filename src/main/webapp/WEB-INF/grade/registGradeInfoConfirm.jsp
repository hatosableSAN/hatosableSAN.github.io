<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.SeatingArrangements" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<SeatingArrangements> SeatList = (List<SeatingArrangements>)request.getAttribute("SeatList"); %>
    <% String ClassDate=(String)request.getParameter("ClassDate");>
    <% String PeriodNum=(String)request.getParameter("PeriodNum");>
    <% String Comment=(String)request.getParameter("comment");>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>

  <body>
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">授業評価作成</h1>
    <br>
    授業日および授業全体のコメントを入力してください。<br>

    <form action="./RegistGradeInfoConfirm" method="post">
授業日：
      <%=ClassDate>
      <%=PeriodNum>限<br/>
授業コメント(400文字以内)
      <%=Comment%>
      </textarea>

        <input type="submit" value="評価を確定する">

    </form>
    
      <a href="./SeatingTop"><button align="center" name="regist_top">座席配置メニュートップへ戻る</button></a>
  </body>
</html>