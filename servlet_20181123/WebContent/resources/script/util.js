/**
 * 
 */
function toDay() {
	var d = new Date();
	var result = (d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate());
	return result; //년-월-일
}

function dDay(someday) {
	//주의) 09:00 기준으로 계산된다.
	//(클라이언트) 시스템의 오늘 날짜
	var today = new Date();
	//기준 날짜에 대한 날짜 객체
	var targetday = new Date(someday);
	//날짜 - 날짜 연산 시 차이 계산 -> 1000/1초 계산
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

