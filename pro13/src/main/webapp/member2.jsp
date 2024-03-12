<%@page import="pro13.MemberDAO"%>
<%@page import="pro13.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");

	//2. 요청한 값 얻기 (가입을 위해 입력한 값들을 request객체 메모리에서 꺼내오기)
	String id = request.getParameter("id");//입력한 아이디 얻기
	String pwd = request.getParameter("pwd");//입력한 비밀번호 얻기 
	String name = request.getParameter("name");//입력한 이름 얻기 
	String email = request.getParameter("email");//입력한 이메일 얻기 
	%>
	<%--
		useBean 액션 태그는 자바코드 객체 생성구문을 대채해서 작성할 수 있는 태그
		id속성 : 생성한 객체의 참조변수명을 지정해 객체 식별
		class속성 : 사용하려는 자바빈역할을 하는 구현체의 패키지명을 포함한 클래스명
		scope속성 : 자바빈역할을 하는 VO 또는 DTO객체 생성 후 저장될 내장객체 메모리 영역 종류를 적는다
		scope = "page" -> 현재 member2.jsp 내부에서만 page내장객체 메모리에 저장된 MemberBean객체 공유
		"session" -> session 내장객체 에 MemberBean객체 저장 -> 브라우저 창이 닫힐때까지 한 애플리케이션에서 사용 가능
		"request" -> MemberBean객체를 request 객체에 저장 , 다른 서버페이지를 요청할때 마다 다른 서버페이지 request객체에 저장된 
		MemberBean 객체를 요청 받은 다른 서버페이지에서 공유해서 사용
		"application" -> 하나의 웹프로젝트가 종료되기 전까지 유지하는 application에 저장된 객체를 모든 jsp에서 공유해 사용 가능
		<jsp:useBean id="참조변수명" class="구현체의 패키지명을 포함한 경로" scope="page"></jsp:useBean>
	 --%>
	<jsp:useBean id="memberBean" class="pro13.MemberBean" scope="page"></jsp:useBean>
<%
	//useBean태그로 생성한 MemberBean객체의 setter메소드로 입력한 정보들을 각 변수에 저장
	memberBean.setId(id);
	memberBean.setPwd(pwd);
	memberBean.setName(name);
	memberBean.setEmail(email);
	//MemberDAO객체를 생성해서 addMember메소드 호출시 addMember메소드의 매개변수로
	//DB에 insert할 정보가 저장된 MemberBean객체의 주소 전달 
	MemberDAO memberDAO = new MemberDAO();
			  memberDAO.addMember(memberBean);
	
	//DB에 insert 성공하면 다시 조회해서 가져와 보여주자
	List membersList = memberDAO.listMembers();//SELECT
%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center" width="100%">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%">이름</td>
			<td width="11%">이메일</td>
			<td width="5%">가입일</td>
		</tr>
<%	//t_member테이블에 조회 한 회원정보가 없으면?(t_member테이블에 저장된 회원이 없으면?)
	if(  membersList.size() == 0  ){
%>		
		 <tr>
			<td colspan="5">등록된 회원이 없습니다.</td>
		 </tr>
<%
	}else{//t_member테이블에 조회한 회원이 있다면?
		  //(ArrayList배열에 조회된 MemberBean객체들이 저장되어 있으면?)
		
		  //for반복문을 이용해 ArrayList배열에 저장된 MemberBean객체의 갯수 만큼 반복해서 
		  //하나씩 얻은 후~~~~~~~~~
		  //얻은 MemberBean객체의 각 인스턴스변수의 회원정보 데이터를 getter메소드들을 호출해 최종얻어
		  //한 사람 정보씩 한행에 출력
		  for(int i=0;  i<membersList.size();  i++){
			  
			  MemberBean bean = (MemberBean)membersList.get(i);
%>			  
			<tr align="center">
				<td width="7%"><%=bean.getId()%></td>
				<td width="7%"><%=bean.getPwd()%></td>
				<td width="5%"><%=bean.getName()%></td>
				<td width="11%"><%=bean.getEmail()%></td>
				<td width="5%"><%=bean.getJoinDate()%></td>
			</tr>			  
<%			  			  
		  }//for
	}//else
%>		
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		

</body>
</html>





















