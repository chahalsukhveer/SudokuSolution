
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="<c:url value="/resources/css/styles.css" />"></link>
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<style>
table {
	border: 1px solid black;
}

table {
	border-collapse: collapse;
}

input {
	width: 100%;
	padding: 10px;
	margin: 0px;
}
    div.container {
        margin: 15px;   
    }
    div.left, div.right {
        float: left;
        padding: 10px;    
    }
    div.left {
        background-color:orange;    
    }
    div.right {
        background-color: yellow;    
    }
</style>
<div>
<h2>Sudoku Solver</h2>
</div>

<div class="container">
<div class="left">
	<table id="gridTable" style="width: 300px" class="fss-table">

		<tbody>
			<c:forEach var="row" items="${grid}" varStatus="looprow">

				<c:choose>
					<c:when test="${(looprow.index+1)%3 == 0}">
						<tr class="table-row">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>



				<c:forEach var="col" items="${row}" varStatus="loopcol">

					<c:choose>
						<c:when test="${(loopcol.index+1)%3 == 0}">
							<td class="table-col"><input type="text" value="${col}"  disabled="disabled"/></td>
						</c:when>
						<c:otherwise>
							<td><input type="text" value="${col}" disabled="disabled"/></td>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="right">
	<table id="maskable" style="width: 300px" class="fss-table">

		<tbody>
			<c:forEach var="row" items="${maskGrid}" varStatus="looprow">

				<c:choose>
					<c:when test="${(looprow.index+1)%3 == 0}">
						<tr class="table-row">
					</c:when>
					<c:otherwise>
						<tr>
					</c:otherwise>
				</c:choose>



				<c:forEach var="col" items="${row}" varStatus="loopcol">

					<c:choose>
						<c:when test="${(loopcol.index+1)%3 == 0}">
							<td class="table-col"><input type="text" value="${col}" class="checkval" disabled="disabled"/></td>
						</c:when>
						<c:otherwise>
							<td><input type="text" value="${col}" class="checkval" disabled="disabled"/></td>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>

<script>
$(function () {
	var text_arr = $("input[class*='checkval']");
	$.each(text_arr, function(i, item) {  //i=index, item=element in array
	    if($(item).val() == 0){
	    	$(item).val("");
	    	$(item).removeAttr('disabled');
	    }
	  
	});
});
</script>
