/** 
 * 배열간 Property 합침
 * */
function collectProp( val1, val2 ) {
	var d = {};
	for(p in val1){
        d[p] = val1[p];
    }
	for(p in val2){
        d[p] = val2[p];
    }
	
	return d;
}