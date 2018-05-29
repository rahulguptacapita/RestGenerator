'use strict';

function cacheApiCalls(program, response) {
	var codeWithRemovedLines = doSomethingWithCode(program);
    storeValue(codeWithRemovedLines, JSON.stringify(response));
}

function findProgramOnCache(program) {	
	var codeWithRemovedLines = doSomethingWithCode(program);
    var response = findOnStoredVale(codeWithRemovedLines);
    if (response !== undefined) {
        return JSON.parse(response);
    }
    return undefined;
}

function doSomethingWithCode(program) {
	
	var codeWithRemovedLines = program.code.replace(/(\r\n|\n|\r)/gm, " ");
	codeWithRemovedLines = codeWithRemovedLines.replace(/\s+/g," ");
	codeWithRemovedLines = codeWithRemovedLines + "X_Y_Z_A_B_C_D_S_R_T" + program.lang;    
	
	return codeWithRemovedLines;
}