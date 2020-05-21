$(function(){
	$.get("member", {op:"check"}, result => {
		var str = "";
		if (result.code == 200) {
			str += '<a href="#">欢迎您 &nbsp;[' + result.data.nickname + ']</a>';
			str += '<a href="#">注销</a> <a href="register.html">注册</a>';
			str += "<input id='login_mno' type='hidden' value='" + result.data.mno + "'/>"
			
			getCartInfo(result.data.mno);
		} else { // 说明没有登录
			str += '<a href="login.html" title="点击登录">您好，请登录</a><a href="register.html" title="点击注册"> 免费注册 </a>';
		}
        $("#login_info").html("").append($(str));
	},"json");
})

var carts = [];
function getCartInfo(mno) {
	$.post("cart", {op:"get", mno:mno}, result => {
		if (result.code == 200) {
			var total = 0;
			carts = result.data;
			$.each(carts, function(index, item) {
				total += parseInt(item.num);
			})
			$("#iconfont").text(total);
		}
	}, "json")
}