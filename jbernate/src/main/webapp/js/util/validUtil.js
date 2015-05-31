/** 
 * 공백 체크
 * return 	true 	: 공백/null/"null"/undefined
 *  		false 	: 공백이 아님
 * */
function chkBlank( val ) {
	
	if( !val || val == null || val == undefined || val == '' || val == "\"\"" || val == "''" ) 	return true;
	else 																						return false;
}

