function numberDoubled(n){
	n = n+"";
	return n.length==1?"0"+n:n;
}

function date2string(d,sp){
	sp = sp || "-";

	var year = d.getFullYear();
	var month = d.getMonth()+1;
	var date = d.getDate();
	var hour = d.getHours();
	var min = d.getMinutes();
	var sec = d.getSeconds();

	return year + sp + month + sp + date + " " + numberDoubled(hour) + ":" + numberDoubled(min) + ":" + numberDoubled(sec);
}
