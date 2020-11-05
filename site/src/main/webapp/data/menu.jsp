<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴</title>
</head>
<body>
	<%--<img src="<%=request.getContextPath()%>/images/imaa.png">--%>
	<!-- 메뉴 로우시작 -->
	<div class="row">
		<div class="col-lg-6 col-md-12">
			<h1>Beverages</h1>
			<p>Cappucino $3.25</p>
			<p>Latte $3.35</p>
			<p>Espresso $2.00</p>
			<p>Mocha $3.50</p>
		</div>
		<div class="col-lg-6 col-md-12">
			<h1>파스타</h1>
			<p>크림불고기봉골레 $3.25</p>
			<p>봉골레 파스타 $3.35</p>
			<p>알리오 올리오 $2.00</p>
			<p>새우 알리오 올리오 $3.50</p>
		</div>
	</div>
	<!-- 메뉴끝 -->
	<div class="row">
		<div class="col">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>이름</th>
						<th>전화번호</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>김기자</td>
						<td>1111</td>
					</tr>
					<tr>
						<td>김기자</td>
						<td>1111</td>
					</tr>
					<tr>
						<td>김기자</td>
						<td>1111</td>
					</tr>
					<tr>
						<td>김기자</td>
						<td>1111</td>
					</tr>
				</tbody>
			</table>
			<span class="btn btn-warning">span</span>
			<a class="btn btn-info" href="#" role="button">Link  <span class="badge badge-light">돋</span></a>
			<button class="btn btn-outline-danger" type="submit">Button</button>
			<input class="btn btn-outline-dark " type="button" value="Input">
		</div>
	</div>
</body>
</html>
