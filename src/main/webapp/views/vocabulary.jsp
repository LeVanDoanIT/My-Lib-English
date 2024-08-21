<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Đoàn đẹpt trai</title>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<style type="text/css">
.sticky-div {
	z-index: 10;
	/* Thay đổi giá trị z-index để đảm bảo nó nằm trên các phần tử khác */
}

.overlay-div {
	z-index: 20;
	/* Giá trị z-index cao hơn để đảm bảo nó nằm trên sticky-div */
}
</style>

</head>

<body class="bg-light">
	<div id="div1"
		class="alert ${type_alert} alert-dismissible fade ${typeMessage} w-50 position-fixed top-0 end-0 overlay-div p-3 mt-1 left-1 me-5"
		role="alert">
		<strong>${message}</strong>
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
	<div
		class="container bg-white mt-0 d-flex justify-content-end position-sticky top-0 sticky-div">
		<form ng-app="searchName" ng-controller="searchNameCtrl" action=""
			class="d-flex">
			<div class="mb-3 mt-3 me-3">
				<input ng-model="name" type="text" class="form-control" name=""
					id="" aria-describedby="helpId" placeholder="Nhập tên từ vựng" />
			</div>
		</form>
		<form action="">
			<div class="mb-3 mt-3 me-3">
				<input type="text" class="form-control" name="" id=""
					aria-describedby="helpId" placeholder="Nhập nghĩa của từ" />
			</div>
		</form>
		<form action="">
			<select class="form-select mt-3 me-3" name="" id="">
				<option value="all">--- Chọn từ loại ---</option>
				<option value="Noun (Danh từ)">Noun (Danh từ)</option>
				<option value="Pronoun (Đại từ)">Pronoun (Đại từ)</option>
				<option value="Verb (Động từ)">Verb (Động từ)</option>
				<option value="Adjective (Tính từ)">Adjective (Tính từ)</option>
				<option value="Adverb (Trạng từ)">Adverb (Trạng từ)</option>
				<option value="Preposition (Giới từ)">Preposition (Giới từ)</option>
				<option value="Conjunction (Liên từ)">Conjunction (Liên từ)</option>
				<option value="Interjection (Thán từ)">Interjection (Thán
					từ)</option>
			</select>
		</form>
	</div>
	<main class="container pt-4">
		<div class="col-12 col-sm-3 fixed-top mt-5 ms-5 ps-3 pt-5 ms-3">
			<form:form action="/mylib/vocabulary/${action}" modelAttribute="word"
				class="mb-3">
				<div class="card text-start border-0">
					<div class="card-body">
						<form:input path="index" type="text" class="form-control collapse"
							name="word" id="" aria-describedby="helpId"
							placeholder="vocabulary" />
						<div class="mb-3">
							<label for="" class="form-label">Nhập tên từ vựng tiếng
								anh</label>
							<form:input path="name" type="text" class="form-control"
								name="word" id="" aria-describedby="helpId"
								placeholder="vocabulary" />
							<!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
						</div>
						<div class="mb-3">
							<label for="" class="form-label">Từ loại</label>
							<form:select class="form-select" name="type" id="" path="type">
								<form:option value="Chưa xác định">--- Chọn từ loại ---</form:option>
								<form:option value="Noun (Danh từ)">Noun (Danh từ)</form:option>
								<form:option value="Pronoun (Đại từ)">Pronoun (Đại từ)</form:option>
								<form:option value="Verb (Động từ)">Verb (Động từ)</form:option>
								<form:option value="Adjective (Tính từ)">Adjective (Tính từ)</form:option>
								<form:option value="Adverb (Trạng từ)">Adverb (Trạng từ)</form:option>
								<form:option value="Preposition (Giới từ)">Preposition (Giới
									từ)</form:option>
								<form:option value="Conjunction (Liên từ)">Conjunction (Liên
									từ)</form:option>
								<form:option value="Interjection (Thán từ)">Interjection
									(Thán từ)</form:option>
							</form:select>
						</div>
						<div class="mb-3">
							<label for="" class="form-label">Nghĩa của từ</label>
							<form:textarea class="form-control" name="descript" id=""
								rows="2" placeholder="mean" path="mean"></form:textarea>
							<!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
						</div>
						<div class="mb-3">
							<label for="" class="form-label">Ghi chú</label>
							<form:textarea class="form-control" name="descript" id=""
								rows="5" placeholder="description" path="descript"></form:textarea>
						</div>
						<a name="" id="" class="btn btn-outline-info float-end ms-3"
							href="/mylib/vocabulary/index" role="button">làm mới</a>
						<button type="submit"
							class="${type_btn_edit} btn btn-warning float-end">Cập
							nhật</button>
						<button type="submit"
							class="${type_btn_add} btn btn-success float-end">Thêm</button>

					</div>
				</div>
			</form:form>
		</div>
		<div class="col-12 col-sm-8 offset-4 mt-1">
			<div class="table-responsive">
				<table class="table">
					<thead class="table-">
						<tr class="">
							<th scope="col">STT</th>
							<th scope="row">Tên từ vựng</th>
							<th scope="col">Từ loại</th>
							<th scope="col">Nghĩa của từ</th>
							<th scope="col">Ghi chú</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${list_vocabulary}">
							<tr>
								<td class="">${list.getIndex()}</td>
								<td scope="row">${list.getName()}</td>
								<td>${list.getType()}</td>
								<td>${list.getMean()}</td>
								<td>${list.getDescript()}</td>
								<td class="text-center"><a name="" id=""
									class="btn btn-danger"
									href="/mylib/vocabulary/delete/${list.getIndex()}"
									role="button">Xoá</a> <a name="" id="" class="btn btn-warning"
									href="/mylib/vocabulary/edit/${list.getIndex()}" role="button">Sửa</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>
	<!-- Bootstrap JavaScript Libraries -->
	<script>
		var app = angular.module('searchName', []);
		app.controller('searchNameCtrl', function($scope) {
			$scope.name = "";
		});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>
</html>
