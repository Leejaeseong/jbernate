/* ▣ Bootstrap ( UI bootstrap ) ▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣▣ */
var app	= angular.module('rootApp', [ 
		                              	'ui.bootstrap'		// 부트스트랩
		                              ,	'ngGrid'			// 그리드
//		                              ,	'ui.grid'			// 그리드
		                              , 'ngRoute'			// 라우터
		                              , 'ngResource'		// 리소스
		                              , 'ngEkathuwa'		// 모달창 
		                              , 'ngSanitize'		// csv파일 다운로드를 위해
		                              , 'ngCsv'				// csv파일 다운로드
		                              , 'angularFileUpload'	// 파일 업로드
		                            ] );