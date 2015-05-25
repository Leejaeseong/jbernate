/** 관리자 여부 */
var con_is_admin = false;	// 상수 : 관리자 여부

/** 메시지 > 오류 > 데이터 로딩 실패 */
var con_msg_err_load_data	= "데이터 로딩에 실패하였습니다";

/** 메시지 > 알림 > 변경된 데이터가 존재 */
var con_msg_cof_exist_data_change	= "변경된데이터가 존재합니다.\r\n계속하시겠습니까?";

/** 메시지 > 알림 > 필수값 입력 필요 */
var con_msg_val_essential	= "필수값을 입력해 주세요";

/** 메시지 > 알림 > 저장 */
var con_msg_cof_save	= "저장하시겠습니까?";

/** 그리드 기본 옵션 */
var con_option_grid = {
	  enablePinning			: true	// 고정핀
	, enableCellEditOnFocus	: true	// Single click으로 수정 가능
//	, multiSelect 			: false	// 다중 선택 가능 여부( 클릭만 해도 선택되어 false 처리 함 ) => selectWithCheckboxOnly로 대체
	, enableColumnResize 	: true	// 컬럼 폭 조정 가능 여부
	, showSelectionCheckbox	: true	// 체크박스
	, selectWithCheckboxOnly: true	// 체크를 하면 Row가 선택됨
};

/** 신규데이터 seq 최소값 */
var con_i_min_seq = 10000000000;