SELECT * FROM TT_ONE_TABLE ORDER BY SEQ DESC;

UPDATE TT_ONE_TABLE SET t_date = sysdate - 364;

DELETE FROM TT_ONE_TABLE WHERE SEQ <> 20;

SELECT LENGTH( T_BLOB ) FROM TT_ONE_TABLE ORDER BY SEQ DESC;

COMMIT;

SELECT * FROM TT_ONE_TABLE_V WHERE SEQ = 1;

SELECT '테스트 입니다' AS V_VARCHAR FROM TT_ONE_TABLE_V;