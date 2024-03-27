<%@page import="java.text.Normalizer.Form"%>
<%@page import="VO.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
		request.setCharacterEncoding("utf-8");
		String contextPath = request.getContextPath();
	%>
<HTML>
<script>
	// 아래의 <select> option 에서 option하나를 선택하고 검색어를 입력해 
	//  검색 찾기를 눌렀을때 호출 되는 함수로 
	//  유효성 검사후 모두 입력하면 <form>의 전송이벤트를 실행시키는 함수 
	function fnSearch(){
		//입력한 검색어 값 얻기 
		let word = document.getElementById("word").value;
		
		//검색어를 입력하지 않았다면
		if(word == null || word == ""){
			alert("검색어를 입력하세요");
			//검색어를 입력할수 있는 <input>을 선택해서 포커스 강제로 설정
			document.getElementById("word").focus();
			//아래의 <form>태그로 false를 전달해서 onsubmit이벤트 차단
			return false;
		
		}else{//검색어를 입력 했다면
			
			//<form>을 선택해서 action속성에 적힌 서블릿으로 요청!(전송이벤트 실행!)
			//<form action="/board/searchlist.bo">
			//				1단계     2단계
			document.search.submit();
		}
	}
	// 글제목하나를 클릭했을때 글 번호를 매개변수로 받아 form태그로 글번호에 해당하는 글의 정보를 DB로부터 조회 요청을 컨트롤러로 넘김
	function fnRead(val){
		document.frmRead.action = "<%=contextPath%>/board/read.bo";
		document.frmRead.b_idx.value = val;
		document.frmRead.submit();
	}
	
</script>
<BODY>


<%
	
	//컨텍스트 주소 얻기 
	contextPath = request.getContextPath();
	//조회된 총 글 갯수 저장될 변수 
	int totalRecord = 0; // 2가서 보기
	int numPerPage = 3;  // 페이ㅣ지 번호 하나당 보여질 글의 갯수
	int pagePerBlock = 3; // 하나의 블럭당 묶여질 페이지번호 갯수
	
	int totalPage = 0; // 총 글의 갯수에 대한 총 페이지 번호 갯수 //[4] 가서 보기
	int totalBlock = 0; // 전체 총 페이지 번호 갯수에 대한 총 블럭 갯수 [9]
	int nowPage = 0; // 현재 사용자에게 보여지고 있는 페이지가 위치한 번호 저장 [7]
	int nowBlock = 0; //현재 사용자에게 보여지고 있는 페이지 번호가 속한 블럭 위치번호 [8]
	int beginPerPage = 0; // 각페이지 마다 보여지는 시작 글 번호 . [9]

	//조회된 글목록 얻기 
	//BoardController에서 재요청해서 전달한 request에 담긴 ArrayList배열 꺼내오기 	
	ArrayList list = (ArrayList)request.getAttribute("list");
  
	//조회된 총 글 갯수 얻기 
	totalRecord= (Integer)request.getAttribute("count");
	//페이지 번호를 클릭했다면 클릭한 페이지 번호를 얻어 nowPage변수에 저장
	if(request.getAttribute("nowPage") != null){
		nowPage = Integer.parseInt(request.getAttribute("nowPage").toString());
	}
	if(request.getAttribute("nowBlock") != null){
		nowBlock = Integer.parseInt(request.getAttribute("nowBlock").toString());
	}
	// 각 페이지 번호에 보여지는 글목록에서 가장 상위글에 대한 글 번호 구하기
	beginPerPage = nowPage * numPerPage;
	/* 
		페이지에서 시작 글번호는 가장 최신의 글이므로 
		글이 6개 있고 한페이지에 6개의 글이 보여지면 
		654321 로 해서 시작 글번호는 6이다 . 그래서 현재페이지 * 한페이지당 글의 갯수를 하면 구할 수 있음. 
	*/
	
	totalPage = (int)(Math.ceil((double)totalRecord / numPerPage));
	/* if(totalRecord % numPerPage != 0){
		totalPage = totalRecord / numPerPage +1 ;
	}else{
		totalPage = totalRecord / numPerPage;
	} */
	// 조회된 총글의 갯수의 총 페이지번호갯수에 대한 총 블럭 갯수 구하기
	totalBlock = (int)Math.ceil((double)totalPage / pagePerBlock);
%>

<!-- 글 제목 하나를 클릭해서 컨트롤러로 글 정보 조회 요청 하는 form태그 -->
<form action="" name="frmRead">
	<input type="hidden" name="b_idx">
	<input type="hidden" name="nowPage" value=<%=nowPage%>>
	<input type="hidden" name="nowBlock" value=<%=nowBlock%>>
</form>

<center><br>

<table align=center border=0 width=80%>
<tr>
	<td align=left>Total :  Articles(
		<font color=red>  <%=nowPage+1 %> / <%=totalPage %> Pages </font>)
	</td>
</tr>
</table>

<table align=center width=80% border=0 cellspacing=0 cellpadding=3>
<tr>
	<td align=center colspan=2>
		<table border=0 width=100% cellpadding=2 cellspacing=0>
			<tr align=center bgcolor=#D0D0D0 height=120%>
				<td align="left">번호</td>
				<td align="left">제목</td>
				<td align="left">이름</td>
				<td align="left">날짜</td>
				<td align="left">조회수</td>
			</tr>
<%
	//게시판 board테이블에서 조회된 글이 없다면?
	 if(list.isEmpty()){ 
%>		 
			<tr align="center">
				<td colspan="5">등록된 글이 없습니다.</td>
			</tr>
<%		 
	 }else{//게시판 board테이블에 조회된 글이 있다면?(BoardVO객체들이 ArrayList배열에 저장되어 있다면?)
		 
		for(int cnt=beginPerPage;   cnt<(beginPerPage+numPerPage);   cnt++){
			
			if(cnt == totalRecord){
				break;
			}
			  // [ BoardVO, BoardVO, BoardVO, BoardVO, BaordVO, BoardVO, BoardVO ]
			  //     0           1      2        3         4       5          6
			  
			//ArrayList배열에 저장된 BoardVO객체를 얻어 출력 
			BoardVO vo  = (BoardVO)list.get(cnt);
			  
			  int level	= vo.getB_level();//들여쓰기 정도 레벨 값 (주글 또는 답변글)
%>			
			<tr>
				<td align="left"><%=vo.getB_idx()%></td>
				<td>								
<%-- 					<%for(int j=0;  j<level*7;  j++){%> --%>
<!-- 							&nbsp; -->
<%-- 					<%}%> --%>
					<%
						int width = 0; //답변글에 대한 빈공백이미지의 들여쓰기 너비값이 저장될 변수 
						//글의 들여쓰기 정도 b_level열의 값이 0보다 크다면?(답글이라면)
						if(level > 0){
							
							width = level * 10;  //<img>태그의 width속성의 값으로 너비 설정
					%>
						<img src="<%=contextPath%>/boarders/images/level.gif" width="<%=width%>" height="15">
						<img src="<%=contextPath%>/boarders/images/re.gif">
					<%		
						}
					%>
					
					
					
					<%-- 글제목 하나를 클릭했을때 글번호를 이용해 글하나조회하여 보여주자 --%>
					<a href="javascript:fnRead('<%=vo.getB_idx()%>')">
						<%=vo.getB_title()%>
					</a>
				</td>
				<td align="left"><%=vo.getB_name()%></td>
				<td align="left"><%=vo.getB_date()%></td>
				<td align="left"><%=vo.getB_cnt()%></td>
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
	<td align="left">Go to Page 
	<%
		if(totalRecord != 0){ //조회한 글 갯수가 0이 아니면
			if(nowBlock > 0){
	%>			
			<a href="<%=contextPath%>/board/list.bo?nowBlock=<%=nowBlock-1%>&nowPage=<%=((nowBlock-1)*pagePerBlock)%>">
				◀ 이전 <%=pagePerBlock%>개
			</a>
	<%		
			}
			for(int i=0;i<pagePerBlock;i++){
	%>
		<a href="<%=contextPath%>/board/list.bo?nowBlock=<%=nowBlock%>&nowPage=<%=((nowBlock)*pagePerBlock)+i%>">
				&nbsp;&nbsp;<%=((nowBlock)*pagePerBlock)+1+i%>
				<%
					if((nowBlock * pagePerBlock +1 +i) == totalPage) break;
				%>
				</a>
	<%			
			}
			if(totalBlock > nowBlock+1){
	%>
	<a href="<%=contextPath%>/board/list.bo?nowBlock=<%=nowBlock+1%>&nowPage=<%=((nowBlock+1)*pagePerBlock)%>">
				▶ 다음<%=pagePerBlock %>개
				</a>
	<%			
			}
		}
	%>
	</td>
	<td align=right>
	<%
		//로그인 했으면 아래의 글쓰기 버튼이 보이게 처리 하고
		//로그인 안했으면 글쓰기 버튼 안보이게 숨기자 
		String id = (String)session.getAttribute("id");
		
		if(id == null){//미로그인시 (글쓰기 이미지 버튼 감춤)
	%>		
		<input type="image"
			   id="newContent"
			   src="<%=contextPath%>/boarders/images/write.gif"
			   onclick="location.href='<%=contextPath%>/board/write.bo'"
			   style="visibility: hidden;">	
	<%		
		}else{//로그인시 (글쓰기 이미지 버튼 노출)
	%>		
		<input type="image"
			   id="newContent"
			   src="<%=contextPath%>/boarders/images/write.gif"
			   onclick="location.href='<%=contextPath%>/board/write.bo'">		
	<%		
		}
	%>
		
	</td>
</tr>
</table>
<BR>

<%-- fnSearch()함수의 리턴값이 false이면  action속성에 적힌 컨트롤러 요청을 하지 않습니다. --%>
<form action="<%=contextPath%>/board/seachlist.bo" 
      name="search" 
      method="post"
      onsubmit="return fnSearch();">
	
	<table border=0 width=527 align=center cellpadding=4 cellspacing=0>
	<tr>
		<td align=center valign=bottom>
			<select name="keyField" size="1">
			
				<option value="name"> 이름
				<option value="subject"> 제목
				<option value="content"> 내용
			</select>
			 <input type="text" size="16" name="keyWord"  id="word"> 
			
			<input type="submit" value="찾기">
			
			<input type="hidden" name="nowPage" value= "0">
			<input type="hidden" name="nowBlock" value= "0">
		</td>
	</tr>
	</table>
</form>
	<a href="<%=contextPath%>/board/list.bo?nowBlock=0&nowPage=0">[처음으로]</a>

</center>	
</BODY>
</HTML>











