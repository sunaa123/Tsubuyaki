<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 共通のメニュー -->

<%@ page import="cc.shinbi.tsubuyaki.model.entity.User" %>
<%@ page import="cc.shinbi.tsubuyaki.model.Const" %>

<%
    User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
%>
<div id="menu">
        <a class="menuItem" href="top">ホーム</a>
<%
    if(user.isAdmin()) {
%>
        <a class="menuItem" href="user">ユーザー管理</a>
<%
    }
%>

        <a class="menuItem" href="logout">ログアウト</a>
</div>