
$(document).ready(function(){

	var login = $('#loginform');
	var recover = $('#recoverform');
	var speed = 400;

	$('#to-recover').click(function(){
		
		$("#loginform").slideUp();
		$("#recoverform").fadeIn();
	});
	$('#to-login').click(function(){
		
		$("#recoverform").hide();
		$("#loginform").fadeIn();
	});
	
	
	$('#to-login').click(function(){
	
	});
    
    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);   
        var input_value = input.attr('placeholder');    
        $(input).wrap('<span class="simulation"></span>')
        $(input).after("<label>"+input.attr('placeholder')+"</label>");

        $(input).focus(function(){
             	$(this).next('label').text('');
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input_value) {
                $(this).next('label').text(input_value);
            }
        });

        $("span.simulation label").click(function(){
             	$(this).text('');
             	$(this).prev("input").focus();
        });
       
    });

        
        
    }
});