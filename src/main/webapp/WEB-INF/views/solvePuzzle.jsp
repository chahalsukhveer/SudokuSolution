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
	background-color: orange;
}

div.right {
	background-color: yellow;
}
</style>


<div class="container">
	<div class="left">
		<div>
			<h2>Puzzle grid</h2>
		</div>
		<table id="maskable" style="width: 300px" class="fss-table"
			data="${maskGrid.grid}">

			<tbody>
				<c:forEach var="row" items="${maskGrid.grid}" varStatus="looprow">

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
								<td class="table-col" row-id="${looprow.index}"
									col-id="${loopcol.index}"><input type="text"
									value="${col}" class="checkval,numeric" disabled="disabled" /></td>
							</c:when>
							<c:otherwise>
								<td><input type="text" value="${col}" class="checkval,numeric"
									disabled="disabled" /></td>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>