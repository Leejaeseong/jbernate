/* ▣ Bootstrap ( UI bootstrap ) ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
var app			= angular.module('rootApp', [ 'ui.bootstrap','ngGrid' ] );

/**
 * Left tree menu fold
 */
$('.tree-toggle').click(function () {
	$(this).parent().children('ul.tree').toggle(200);
});