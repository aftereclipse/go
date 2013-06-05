var Global={
		
		TIMEOUT : 3000,
		
		/*设置顶部导航高亮*/
		setNavActive: function(index){
			$('#globalNav li').removeClass('active');
			$('#globalNav li:nth-child('+index+')').addClass('active');
		}

		
};
