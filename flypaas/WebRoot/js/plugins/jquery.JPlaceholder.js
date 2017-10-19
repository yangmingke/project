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
	        jQuery(':input[placeholder]').each(function(index, element) {
	            var self = $(this), txt = self.attr('placeholder');
	            self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
	            var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
	            var span = self.val()=="" ? "<span></span>" : "<span style='display:none'></span>";
	            var holder = $(span).text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, lienHeight:h, paddingLeft:paddingleft, 'font-size':'14px',color:'#aaa','font-family':'黑体'}).appendTo(self.parent());
				//$('div input').eq(0).next('span').css('top','10px');
				$('input').parent('div').find('span').css('top','10px');
				$('.log_box #log_username').next('span').css('top','10px');
				$('.log_box #log_password').next('span').css('top','0px');
				$('.log_box .code input').css('float','left');
				$('.log_box .code input,.pwd_box .text input').next('span').css('top','10px');
				$('.reset_pwd .text input').next('span').css('top','10px');
				$('.reg_info .code div').css('float','left');
				$('.reg_info .code div span').css({'left':'0px','top':'10px'});
				$('.reg_info .msg_code_txt input').next('span').css({'top':'10px','left':'83px'});
				$('.main_top input').next('span').css('top','0px');
				$('.main_top #appform input').next('span').css('top','10px');
				$('li.time div input').next('span').css('top','10px');
				$('.main_top li.time div').css('float','left');
				$('.main_top li.time div span').css({'left':'0px','top':'0px'});
				$('.msg_box .select span').css('top','0px');
				$('.online_exp form div span').css({'left':'92px','top':'0px'});
				$('.online_exp form div span').css({'left':'92px','top':'0px'});
				$('.online_exp .code div').css('float','left');				
				$('.online_exp .code div span').css({'left':'0px','top':'0px'});
				$('.online_exp .msg_code_txt div span').css({'left':'0px','top':'0px'});
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