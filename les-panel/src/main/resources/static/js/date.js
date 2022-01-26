(function(doc){
	'use strict';
	
	function date(){
		var tmpDate = new Date();
		var date = tmpDate.getDate();
		var month= tmpDate.getMonth() + 1 ;
		var year= tmpDate.getFullYear();
		var h=tmpDate.getHours();
		var m=tmpDate.getMinutes();
		var s = tmpDate.getSeconds();
		if(m<10){m = "0" + m;}
		if(s<10){s = "0" + s;}
		
		var today = tmpDate.getDay();
//		var text = ['日','一','二','三','四','五','六'];
		
		var dateStr = year + "/" + month + "/" + date  + " " + h + ":" + m + ":" + s;
		doc.getElementById("date").innerHTML = dateStr;
	}
	
	setInterval(date,1000);
})(document);