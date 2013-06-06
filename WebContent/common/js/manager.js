var Global={
		
		TIMEOUT : 3000,
		
		/*设置顶部导航高亮*/
		setNavActive: function(index){
			$('#globalNav li').removeClass('active');
			$('#globalNav li:nth-child('+index+')').addClass('active');
		}

		
};

$(function(){
	//确认弹窗
	$('.confirm-trigger').click(function(){
		var self = $(this);
		var url = self.attr('url');
		var tip = self.attr('tip');
		$('.modal-body').html(tip);
		$('.confirm-ok-trigger').click(function(){
			document.location.href = url;
		});
	});
});