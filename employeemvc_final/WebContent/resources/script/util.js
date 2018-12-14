/**
 * 
 */
function toDay() {
	var d = new Date();
	var result = (d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate());
	return result; //년-월-일
}

function dDay(someday) {
	var today = new Date();
	var targetday = new Date(someday);
	var b = Math.floor((today - targetday)/1000/60/60/24);
	var result = "D" + ((b>0)?"+"+b:b);
	return result; //D+1, D0, D-1
}

function dDay_(someday) {
	var today = new Date();
	var targetday = new Date(someday);
	var result = Math.floor((today - targetday)/1000/60/60/24);
	return result; //1, 0, -1
}

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1) ) + min;
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

