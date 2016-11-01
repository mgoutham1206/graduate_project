/**
 * 
 */

$.ajax({
				type : "GET",
				url : "/Pool/getquestions",
				data : "",
				//cache: false,
				success : function(data) {
					console.log(data.length);
					$('#streakCheckbox').empty();
					$.each(data, function(index, value) {
						$('#streakCheckbox').append(
								'<input type="checkbox" value="' + value + '" /> '
										+ value + '<br />');
					});

				}
			});