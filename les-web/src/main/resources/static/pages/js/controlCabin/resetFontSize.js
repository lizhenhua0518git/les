/**
* 重置root字体大小
* @author	Mr Gao
* @date		2019.06.18
*/

;(function(){
	'use strict';
	
	var docEl = document.documentElement;
	docEl.style.fontSize = (docEl.clientWidth/19.2) + "px";
	window.onresize = function(){
		docEl.style.fontSize = (docEl.clientWidth/19.2) + "px";
	};
})();