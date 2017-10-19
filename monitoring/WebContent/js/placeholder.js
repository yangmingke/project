$(function(){
	IEPlaceholder();	
});

/**
 * 解决placeholder IE兼容问题
 */
function IEPlaceholder(){
	if(!('placeholder' in document.createElement('input')) ){
		$('[placeholder]').each(function(){   
			var that = $(this);
			var text= that.attr('placeholder');   
			that.removeAttr("placeholder");
			if(that.val()==""){
				that.val(text).addClass('placeholder');
			}
			that.focus(function(){   
				if(that.val()==text){   
					that.val("").removeClass('placeholder');   
				}   
			})   
			.blur(function(){   
				if(that.val()==""){   
  					that.val(text).addClass('placeholder');   
				}   
			})   
		});   
	}
}