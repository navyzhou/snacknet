//加入购物车
function addCart(gno) {
	var num = $("#goods_count").val() || 1;
	num = parseInt(num);
	
	var len = carts.length;
	 if (len > 0) {
		 for (var i = 0; i < len; i ++) {
			 if (carts[i].gno == gno) { // 说明该商品已经购买过，则加数量即可
				 $.post("cart", {op: "update", num : num, cno: carts[i].cno}, result => {
					 if (result.code == 200) {
						carts[i].num = parseInt(carts[i].num) + num;
						$("#iconfont").text(parseInt($("#iconfont").text()) + num);
						$("#popup_info").text("加入购物车成功...").css("color", "green");;
					 } else {
						$("#popup_info").text("加入购物车失败...").css("color", "red");
					 }
					 $('.popup_con').fadeIn('fast', function() {
						setTimeout(function(){
							$('.popup_con').fadeOut('fast',function(){
								
							});	
						}, 2000)
					});
				 }, "json")
				 return;
			 }
		 }
	}

	var mno = $.trim($("#login_mno").val());
	$.post("cart", {op: "add", num : num, gno: gno, mno: mno}, result => {
		if (result.code == 200) {
			carts.push({cno:result.data, gno:gno, num: num});
			$("#iconfont").text(parseInt($("#iconfont").text()) + num);
			$("#popup_info").text("加入购物车成功...").css("color", "green");;
		} else {
			$("#popup_info").text("加入购物车失败...").css("color", "red");
		}
		$('.popup_con').fadeIn('fast', function() {
			setTimeout(function(){
				$('.popup_con').fadeOut('fast',function(){
						
				});	
			}, 2000)
		});
	}, "json");
}