<%@page import="VO.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<HTML>
<script>
	function check(){
		if(document.search.keyWord.value == ""){
			alert("검색어를 입력하세요.");
			document.search.keyWord.focus();
			return;
		}
		document.search.submit();
	}
</script>
<BODY>
<%
	int totalRecord =0;
	//BoardController에서 재요청해 전달한 request에 담긴 ArrayList(글목록) 꺼내오기
	ArrayList list = (ArrayList)request.getAttribute("list");
	//조회된 글 갯수 얻기
	totalRecord = (Integer)request.getAttribute("count");
	String contextPath = request.getContextPath();
%>
<center><br>
<table align=center border=0 width=80%>
<tr>
	<td align=left>Total :  Articles(
		<font color=red>  1 / 10 Pages </font>)
	</td>
</tr>
</table>

<table align=center width=80% border=0 cellspacing=0 cellpadding=3>
<tr>
	<td align=center colspan=2>
		<table border=0 width=100% cellpadding=2 cellspacing=0>
			<tr align=center bgcolor=#D0D0D0 height=120%>
				<td> 번호 </td>
				<td> 제목 </td>
				<td> 이름 </td>
				<td> 날짜 </td>
				<td> 조회수 </td>
			</tr>
			<%
			//게시판 board테이블에서 조회된 글이 없다면
				if(list.isEmpty()){
			%>
				<tr align="center">
					<td colspan="5">등록된 글이 없습니다.</td>
				</tr>
			<%		
				}else{ //게시판 board테이블에 조회된 글이 있다면 ?
					
					for(int i=0;i<list.size();i++){
						BoardVO vo = (BoardVO)list.get(i);
			%>
			
					}
						<tr>
							<td><%=vo.getB_idx()%></td>
							<td><%=vo.getB_title() %></td>
							<td><%=vo.getB_name() %></td>
							<td><%=vo.getB_date() %></td>
							<td><%=vo.getB_cnt() %></td>
						</tr>
			<%			
				}
			}
			%>
		</table>
	</td>
</tr>
<tr>
	<td><BR><BR></td>
</tr>
<tr>
	<td align="left">Go to Page </td>
	<td align=right>
	<%
		String id = (String)session.getAttribute("id");
		if(id == null){ %> <!-- 미로그인시 글쓰기 이미지 버튼 감춤 -->
		<input type="image" id="newContent" src="<%=contextPath%>/boarders/images/write.gif"
		       onclick="location.href='<%=contextPath%>/board/write.bo'"
		       style="visibility: hidden;">
		
	<%
		}else{ %>
			<input type="image" id="newContent" src="<%=contextPath%>/boarders/images/write.gif"
		       onclick="location.href='<%=contextPath%>/board/write.bo'">
	<%
	}
	%>
	</td>
</tr>
</table>
<BR>
<%-- fnSearch()함수의 리턴값이 false이면  action속성에 적힌 컨트롤러 요청을 하지 않습니다. --%>
<form action="<%=contextPath%>/board/seachlist.bo" name="search" method="post"  onsubmit="return fnSearch();">
	<table border=0 width=527 align=center cellpadding=4 cellspacing=0>
	<tr>
		<td align=center valign=bottom>
			<select name="keyField" size="1">
				<option value="name"> 이름
				<option value="subject"> 제목
				<option value="content"> 내용
			</select>

			<input type="text" size="16" name="keyWord" >
			<input type="button" value="찾기" onClick="check()">
			<input type="hidden" name="page" value= "0">
		</td>
	</tr>
	</table>
</form>
<a href="<%=contextPath%>/board/list.bo">[처음으로]</a>
</center>	
</BODY>
</HTML>