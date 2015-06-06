/** 그리드 기본 옵션 */
var con_option_grid = {
	  enablePinning			: true	// 고정핀
	, enableCellEditOnFocus	: true	// Single click으로 수정 가능
//	, multiSelect 			: false	// 다중 선택 가능 여부( 클릭만 해도 선택되어 false 처리 함 ) => selectWithCheckboxOnly로 대체
	, enableColumnResize 	: true	// 컬럼 폭 조정 가능 여부
	, showSelectionCheckbox	: true	// 체크박스
	, selectWithCheckboxOnly: true	// 체크를 하면 Row가 선택됨
	, primaryKey : "seq"
};

/** 신규데이터 seq 최소값 */
var con_i_max_seq = -1;