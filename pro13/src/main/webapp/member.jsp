<%@page import="pro13.MemberDAO"%>
<%@page import="pro13.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
	member.jsp설명
	- memberForm.html(회원가입을 위해 입력한 정보를 요청하는 화면)에서 입력한 가입할 정보들을
	  
	  request내장객체 메모리에서 가져온 후 ~~~~~
	  
	  MemberBean클래스의 객체  생성 후 각 인스턴스변수에 저장 시킵니다.

	  그런 다음 MemberDAO객체를 생성해서 addMember(MemberVO vo)메소드 호출시~~
	  이메소드의 매개변수 vo로 MemberBean객체의 주소를 전달 시킵니다.
	  
	  addMember메소드 내부에서 회원가입정보를 DB의 t_member테이블에 INSERT시킨후~~~
	  
	  INSERT에 성공하면!!! 다시~~~ MemberDAO객체의 listMembers()메소드를 호출해서!
	  
	  DB의 t_member테이블에 저장된 가입된 회원정보들을 조회해 와서 현재 member.jsp에 목록으로 출력(응답)합니다.
	  
 --%>
<%
	//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
	//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");

	//2. 요청한 값 얻기 (가입을 위해 입력한 값들을 request객체 메모리에서 꺼내오기)
	String id = request.getParameter("id");//입력한 아이디 얻기
	String pwd = request.getParameter("pwd");//입력한 비밀번호 얻기 
	String name = request.getParameter("name");//입력한 이름 얻기 
	String email = request.getParameter("email");//입력한 이메일 얻기 
	
	//3.요청한 값들을 통해 비즈니로직처리(DB와연결후 작업처리)한  웹브라우저로 응답할 값 마련
	// MemberBean객체를 생성하는 동시에 생성자를 호출해 각 인스턴스변수값 초기화 
	MemberBean memberBean = new MemberBean(id,pwd,name,email);

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





















