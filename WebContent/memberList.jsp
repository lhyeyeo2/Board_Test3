<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="jsboard.MemberBean"%>

<jsp:useBean id="mMgr" class="jsboard.MemberMgr"/> 
<%
Vector<MemberBean> vlist = new Vector<MemberBean>();
vlist = mMgr.membersInfo();
%>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록조회</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffcc">
<table>
<tr>
<td>id</td>
<td>passwd</td>
<td>name</td>
<td>gender</td>
<td>birthday</td>
<td>email</td>
<td>zipcode</td>
<td>address</td>
<td>hobby</td>
<td>job</td>
</tr>
<%
for(int i=0; i<vlist.size(); i++) {
	MemberBean bean = vlist.get(i);
	%>
		<tr>
		<td><%= bean.getId() %></td>
		<td><%= bean.getPwd() %></td>
		<td><%= bean.getName() %></td>
		<td><%= bean.getGender() %></td>
		<td><%= bean.getBirthday() %></td>
		<td><%= bean.getEmail() %></td>
		<td><%= bean.getZipcode() %></td> 
		<td><%= bean.getAddress() %></td>
		<td>hobby</td>
		<td><%= bean.getJob() %></td>
		</tr>
	<%
}
%>
</table>
<a href="login.jsp">로그인 화면으로 이동</a>
</body>
</html>
























