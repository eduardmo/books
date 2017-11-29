/**
 * 
 */

$(function() {
	jQuery.validator.addMethod("greaterThan", function(value, element, params) {

		if (!/Invalid|NaN/.test(new Date(value))) {
			console.log($("'" + params + "'").val());
			return new Date(value) > new Date($(params).val());
		}
		return isNaN(value) && isNaN($(params).val())
				|| (Number(value) > Number($(params).val()));
	}, 'Second date must be greater than the first one.');
	jQuery.validator.addMethod("validPNC", function(value, element) {
		return /^[1-9]{1}[0-9]{12}$/.test(value);
	}, "Only Numerical Characters Allowed!");
	jQuery.validator.addMethod("validCardNumber", function(value, element) {
		return /^[A-Z]{2}[0-9]{6}$/.test(value);
	}, "Invalid Id Card Number format!");
	$("#formAdd").validate({
		rules : {
			employeePersonalNumericalCode : {
				required : true,
				minlength : 13,
				maxlength : 13,
				validPNC : true,
			},
			employeeFirstName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			employeeLastName : {
				required : true,
				minlength : 2,
				maxlength : 30,
			},
			employeeIdCardNumber : {
				required : true,
				validCardNumber : true,
			},
			username : {
				required : true,
				minlength : 5,
				maxlength : 15,
			},
			password : {
				required : true,
				minlength : 6,
			}
		},
		messages : {
			employeePersonalNumericalCode : {
				required : "Please enter a personal numerical code",
				minlength : "Exactly 13 characters allowed for the PNC!",
				maxlength : "Exactly 13 characters allowed for the PNC!"

			},
			employeeFirstName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			employeeLastName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			employeeIdCardNumber : {
				required : "Id Card Number is required!"
			},
			username : {
				required : "Username must be provided!",
				minlength : "Username has to be at least 5 characters!",
				maxlength : "Username can have atmost 15 characters!"
			},
			password : {
				required : "Password must be provided!",
				minlength : "Password has to be at least 6 characters!"
			}
		}
	});
	$("#formEdit").validate({
		rules : {
			employeeLastName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			employeeFirstName : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			employeeIdCardNumber : {
				required : true,
				validCardNumber : true
			},
			username : {
				required : true,
				minlength : 5,
				maxlength : 15
			},
			password : {
				required : false,
				minlength : 6
			}
		},
		messages : {
			employeeName : {
				required : "Name is required!",
				minlength : "Name has to be at least 3 characters!",
				maxlength : "Name can have atmost 30 characters!"
			},
			employeeIdCardNumber : {
				required : "Id Card Number is required!"
			},
			username : {
				required : "Username must be provided!",
				minlength : "Username has to be at least 5 characters!",
				maxlength : "Username can have atmost 15 characters!"
			},
			password : {
				minlength : "Password must be at least 6 characters"
			}
		},
		errorPlacement : function(error, element) {
			switch (element.attr("name")) {
			case "employeeFirstName":
				$("#fnameError").html(error);
				break;
			case "employeeLastName":
				$("#lnameError").html(error);
				break;
			case "employeeIdCardNumber":
				$("#idcardError").html(error);
				break;
			case "username":
				$("#usernameError").html(error);
				break;
			case "password":
				$("#passwordError").html(error);
				break;
			}
		}
	});
});