<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
    </div>
    <h2 align="center">座席配置の更新に失敗しました。<br>再度やり直してください</h2>
    <div class="center">
      <a href="./SeatingTop"><button align="center" class="backbtn_middle" name="regist_top">座席配置メニュートップへ戻る</button></a>
    </div>
  </body>
</html>