<!doctype html>
<%@ page import="java.time.LocalDate" %>
<html lang="en">

<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
	
</script>

<style>
.card {
	border: none;
	height: 600px
}

.forms-inputs {
	position: relative
}

.forms-inputs span {
	position: absolute;
	top: -18px;
	left: 10px;
	background-color: #fff;
	padding: 5px 10px;
	font-size: 15px
}

.forms-inputs input {
	height: 50px;
	border: 2px solid #eee
}

.btn {
	height: 40px
}

#rowAdder {
	margin-left: 17px;
}

.totaltax {
	color: blue;
}
</style>
</head>

<body class="h-100 bg-dark">
	<div class="container mt-5">
		<div class="row d-flex justify-content-center">
			<div class="col-md-8">
				<div class="card px-5 py-5" id="form1">
					<form action="getTax" method="post">
						<div id="row">
							<h3 class="mb-5 text-uppercase text-center">Congestion Tax
								Calculator</h3>
							<h5 class="text-uppercase">Select Vehicle</h5>

							<select class="selectpicker form-control m-input" name="Vehicle"
								required>
								<option value="select" disabled>Select</option>
								<option value="Car">Car</option>
								<option value="Busses">Busses</option>
								<option value="Emergency">Emergency Vehicle</option>
								<option value="Diplomat">Diplomat Vehicle</option>
								<option value="Motorcycle">Motorcycle</option>
								<option value="Military">Military Vehicle</option>
								<option value="Foreign">Foreign vehicle</option>
								<option value="Tractor">Tractor</option>
							</select>

							<div class="input-group m-3">
								<div class="input-group-prepend">
									<button class="btn btn-danger" id="DeleteRow" type="button">
										<i class="bi bi-trash"></i> Delete
									</button>
								</div>
								<div>
									<input type="datetime-local" name="date" id="date"
										class="form-control m-input" required  min="2022-01-01T00:00" max= "">
								</div>
							</div>
						</div>

						<div id="newinput"></div>
						<button id="rowAdder" type="button" class="btn btn-dark">
							<span class="bi bi-plus-square-dotted"> </span> ADD
						</button>
						<button type="submit" class="btn btn-primary">Calculate</button>
						<br>

						<div>
							<h5 class="text alert alert-primary mt-5">Total Toll Tax:
								${Result} SEK</h5>
							<h6 class="text-small  alert-danger">${error}</h6>
							<h6 class="text-small  alert-success">${TollFree}</h6>
							<p id="dateExeed"></p>
						</div>
					</form>

					<script type="text/javascript">
						$("#rowAdder")
								.click(
										function() {
											newRowAdd = '<div id="row"> <div class="input-group m-3">'
													+ '<div class="input-group-prepend">'
													+ '<button class="btn btn-danger" id="DeleteRow" type="button">'
													+ '<i class="bi bi-trash"></i> Delete</button> </div>'
													+ '<input type="datetime-local" name="date" class="form-control m-input" required> </div> </div>';

											$('#newinput').append(newRowAdd);
										});

						$("body").on("click", "#DeleteRow", function() {
							$(this).parents("#row").remove();
						})
					</script>
					
				</div>
			</div>
		</div>
	</div>
</body>

</html>
