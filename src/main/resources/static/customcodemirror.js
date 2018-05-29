'use strict';

var myCodeMirror = {};

var prvCode = getStoredValue('myPrvCode');
var prvLang = getStoredValue('myPrvLang');

var langCodeMapping = {
    java: "public class HelloWorld { \n" + "  public static void main(String[] args) {\n" + "     System.out.println(\"Hello, Wor\");\n" + "  }" + "\n}",
    nodejs: "var a = 10; \n" + "var b = 20;\n" + "console.log(a + b);\n",
    python: "print('hello')"
};

var defaultLang = "java";

function loadCodePrv() {
    if (prvCode != null) {
        return prvCode;
    } else {
        return getdefaultCodeByLanguage(defaultLang);
    }
}

function loadLangPrv() {
    if (prvLang != null) {
        return prvLang;
    } else {
        return defaultLang;
    }
}

function storeValue(key, value) {
    if (localStorage) {
        localStorage.setItem(key, value);
    } else {
        $.cookies.set(key, value);
    }
}

function getStoredValue(key) {
    if (localStorage) {
        return localStorage.getItem(key);
    } else {
        return $.cookies.get(key);
    }
}

function findOnStoredVale(key) {
    if (localStorage) {
        var values = localStorage.valueOf();
        return values[key];
    }
    return null;
}

window.onbeforeunload = function() {
    storeValue("myPrvCode", myCodeMirror.getValue());
    storeValue("myPrvLang", getPrvLang());
}

function getPrvLang() {
    var scope = angular.element(document.querySelector('#outer')).scope();
    return scope.selectedLanguage;
}

function loadCode(code, language) {
    myCodeMirror = CodeMirror(function(elt) {
        myTextArea.parentNode.replaceChild(elt, myTextArea);
    }, {
        value: code,
        lineNumbers: true,
        extraKeys: {
            "Ctrl-Space": "autocomplete"
        },
        tabSize: 10,
        styleActiveLine: true,
        matchBrackets: true,
        mode: "javascript"
    });
    var mac = CodeMirror.keyMap.default == CodeMirror.keyMap.macDefault;
    CodeMirror.keyMap.default[(mac ? "Cmd" : "Ctrl") + "-Space"] = "autocomplete";
    myCodeMirror.setOption("theme", getdefaultThemes()[25]);
    
}

function changeCode(code) {
    myCodeMirror.setValue(code);
}

function getdefaultCodeByLanguage(language) {
    return langCodeMapping[language];
}

function getdefaultThemes() {	
	var themes = ["default",
	    "3024-day",
	    "3024-night",
	    "abcdef",
	    "ambiance",
	    "base16-dark",
	    "base16-light",
	    "bespin",
	    "blackboard",
	    "cobalt",
	    "colorforth",
	    "dracula",
	    "duotone-dark",
	    "duotone-light",
	    "eclipse",
	    "elegant",
	    "erlang-dark",
	    "hopscotch",
	    "icecoder",
	    "idea",
	    "isotope",
	    "lesser-dark",
	    "liquibyte",
	    "lucario",
	    "material",
	    "mbo",
	    "mdn-like",
	    "midnight",
	    "monokai",
	    "neat",
	    "neo",
	    "night",
	    "oceanic-next",
	    "panda-syntax",
	    "paraiso-dark",
	    "paraiso-light",
	    "pastel-on-dark",
	    "railscasts",
	    "rubyblue",
	    "seti",
	    "shadowfox",
	    "solarized dark",
	    "solarized light",
	    "the-matrix",
	    "tomorrow-night-bright",
	    "tomorrow-night-eighties",
	    "ttcn",
	    "twilight",
	    "vibrant-ink",
	    "xq-dark",
	    "xq-light",
	    "yeti",
	    "zenburn"];
	return themes;
}
