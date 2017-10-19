// JavaScript Document

/*
	 * jQuery placeholder, fix for IE6,7,8,9
	 * @author JENA
	 * @since 20131115.1504
	 * @website ishere.cn
	 */
	var JPlaceHolder = {
	    //检测
	    _check : function(){
	        return 'placeholder' in document.createElement('input');
	    },
	    //初始化
	    init : function(){
	        if(!this._check()){
	            this.fix();
	        }
	    },
	    //修复
	    fix : function(){
	        jQuery(":input[placeholder]").each(function(index, element) {
	            var self = $(this), txt = self.attr('placeholder');
	            self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
	            var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
	            var span = self.val()=="" ? "<span></span>" : "<span style='display:none'></span>";
	            
	            var holder = $(span).text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, lienHeight:h, paddingLeft:paddingleft, 'font-size':'14px',color:'#aaa','font-family':'黑体'}).appendTo(self.parent());
				$('.log_inner input').next('span').css('padding-top','15px');
				$('.log_inner #randCheckCode').parent('div').css({'float':'left','margin-right':'10px'});
				$('.main_search li div').css({'float':'left'});
				$('.main_search li.time input').eq(1).next('span').css({'left':'0px'});
				$('.main_search li.money input').eq(0).next('span').css({'left':'0px'});
				$('.main_search li.money input').eq(1).next('span').css({'left':'0px'});
				$('.main_search input').css({'float':'left','display':'inline'});
				$('.main_search input').next('span').css('padding-top','10px');
				$('.main_search span').css({'float':'left','margin-left':'0px'});
				$('.main_search li label').css({'float':'left','line-height':"44px"});
				$('.main_search li div').next('span').css({'padding-top':'10px','float':'left'});
				$('.main_search li div.select').find("span").css('padding-top','0px')
				$('.account_box form p div input').next('span').css('padding-top','10px');
				$('.account_box form p div').next('span').css({'float':'left','line-height':'44px'});
				
	            self.focusin(function(e) {
	                holder.hide();
	            }).focusout(function(e) {
	                if(!self.val()){
	                    holder.show();
	                }
	            });
	            holder.click(function(e) {
	                holder.hide();
	                self.focus();
	            });
	        });
	    }
	};
	//执行
	jQuery(function(){
	    JPlaceHolder.init();   
	});