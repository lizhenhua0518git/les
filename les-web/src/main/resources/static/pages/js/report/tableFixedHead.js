;$(function () {
    var tableCont = $('.section-scroll tr th'); //获取th
    var tableCont_child = $('.section-scroll tr th > div'); //获取th下边的div
    var tableScroll = $('.section-scroll'); //获取滚动条同级的class
    var bottomLine = $('.section-scroll thead tr:last-child th');

    function scrollHandle() {
        var scrollTop = tableScroll.scrollTop();
        // 当滚动距离大于0时设置top及相应的样式
        if (scrollTop > 0) {
			bottomLine.show();
            tableCont.css({
                "top": scrollTop + 'px',
            });
            tableCont_child.css({
            	 
                "borderTop": "0.5px solid #e6e6e6",
                "marginTop": "-1px"
            });
        } else {
			bottomLine.hide();
            // 当滚动距离小于0时设置top及相应的样式
            tableCont.css({
                "top": scrollTop + 'px',
                "marginTop": "0",
            });
            tableCont_child.css({
                "border": "none",
                "marginTop": 0,
                "marginBottom": 0,
            });
        }
    }
    tableScroll.on('scroll', scrollHandle);
});