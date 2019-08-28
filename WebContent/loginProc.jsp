<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="jsboard.MemberMgr"/> 
<%
request.setCharacterEncoding("UTF-8");
String id= request.getParameter("id");
String pwd= request.getParameter("pwd");
String msg="로그인에 실패하였습니다.";
String location="login.jsp";

boolean result = mMgr.loginMember(id, pwd);
if(result){
   session.setAttribute("idKey", id);   // session.invalidate();
   msg="로그인에 성공하였습니다.";
}
%>

<script>
  alert("<%=msg%>");
  location.href="<%=location%>";
</script>

















