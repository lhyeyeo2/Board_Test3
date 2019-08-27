<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8") %>


<jsp:useBean id="mgr" class="board.MemberMgr"/>
<jsp:useBean id="bean" class="jsboard.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
</jsp:useBean>