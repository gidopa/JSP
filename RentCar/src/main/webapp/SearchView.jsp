<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 전체라이브러리에 속한 core에 속한 태그들 사용을 위해  반드시 작성해야 하는 한줄 --%>   
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
    
<% request.setCharacterEncoding("UTF-8");  %>        
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$('#searchBtn').click(function(){
				
				$.ajax({
						type:"get", //HTTP get메소드 요청 
						url : "<%=request.getContextPath()%>/NaverSearchAPI.do",
						data : { 
								 keyword : $('#keyword').val(), //입력한 검색어 요청
								 startNum : $('#startNum option:selected').val() //검색 시작 위치 요청
  					           },
  					    dataType : "json", //응답 데이터 형식
  					    success : function(data){
  					    	//요청에 성공했을때 검색된 응답할 정보를 서블릿으로 부터 전달 받아 사용 
  					    	console.log(data,"");
  					    	
  					    	var str = "";
  					    	
  					    	$.each(data.items , function(index, item){
  					    		
  					    		str += "<ul>";
  					    		str += 		"<li>" + (index + 1)  + "</li>";
  					    		str += 		"<li>" + item.title + "</li>";
  					    		str += 		"<li>" + item.link + "</li>";
  					    		str += 		"<li>" + item.description + "</li>";
  					    		str += 		"<li>" + item.bloggername + "</li>";		
  					    		str += 		"<li><a href='"+ item.link + "' target='_blank'>바로가기</a></li>";	
  					    		str += 		"<li>" + item.postdate + "</li>";  					    		
  					    		str += "</ul>";			    		 					    		
  					    	});
  					    	
  					    	$("#searchResult").appned(str);
		    	
  					    }
  					           
				});	
			});		
		});
	</script>
	<script type="text/javascript">
    $(function(){
        // responseBody 변수에 저장된 JSON 문자열
        var responseBody = '${responseBody}'; 
        
		var data = JSON.parse(responseBody)     
        
        var str = "";
        $.each(data.items , function(index, item){
            str += "<ul>";
            str += "<li>" + (index + 1)  + "</li>";
            str += "<li>" + item.title + "</li>";
            str += "<li>" + item.link + "</li>";
            str += "<li>" + item.description + "</li>";
            str += "<li>" + item.bloggername + "</li>";
            str += "<li><a href='"+ item.link + "' target='_blank'>바로가기</a></li>";
            str += "<li>" + item.postdate + "</li>";
            str += "</ul>";
        });
        
        // 결과를 div에 출력
        $("#searchResult").html(str);
    });
</script>
</head>
<body>
	<div>
		<div>
			<!-- <form id="searchFrm">
				한 페이지에 10씩 조회결과 출력됨<br>
				<select id="startNum">
					<option value="1">1페이지</option>
					<option value="11">2페이지</option>
					<option value="21">3페이지</option>
					<option value="31">4페이지</option>
					<option value="41">5페이지</option>					
				</select>
				<input type="text" id="keyword" placeholder="검색어를 입력하세요."/>
				<button type="button"  id="searchBtn">검색 요청</button>
			</form> -->
		</div>
		<div class="row" id="searchResult">
			${responseBody }
		</div>
	</div>
</body>
</html>
















