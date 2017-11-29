/**
 * 
 */

$(function() {
	jQuery.validator.addMethod("positive", function(value, element) {
		return /^[0-9]*$/.test(value);
	}, "Must Be a Positive Integer!");
	jQuery.validator.addMethod("validFloat", function(value, element) {
		return /^[0-9]+\.[0-9]+$/.test(value);
	}, "Number must be positive float (i.e. 123.45)!");
	$("#formAdd").validate({
		rules : {
			title : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			author : {
				required : true,
				minlength : 3,
				maxlength : 30,
			},
			genre : {
				required : true,
				minlength : 3,
				maxlength : 15,
			},
			quantity : {
				required : true,
				positive : true
			},
			price : {
				required : true,
				validFloat : true
			}
		},
		messages : {
			title : {
				required : "Please Enter A Title",
				minlength: "At least 3 characters required",
				maxlength: "At most 30 characters allowed"
			},
			author : {
				required : "Please Enter An Author",
				minlength: "At least 3 characters required",
				maxlength: "At most 30 characters allowed"
			},
			genre : {
				required : "Please Enter A Genre",
				minlength: "At least 3 characters required",
				maxlength: "At most 30 characters allowed"
			},
			quantity : {
				required : "Please Enter A Quantity"
			},
			price : {
				required : "Please Enter A Price"
			}
			
		}
	});
	$("#formSell").validate({
		rules : {
			qt: {
				required: true,
				positive: true
			}
		},
		messages : {
			qt: {
				required: "Please enter a quantity"
			}
			
		}
	});
});
