USE [CM];                                                            --// Schema 수정 부분 (1/2)
DECLARE @TAB_NM VARCHAR(30)
DECLARE @SQL NVARCHAR(MAX);
DECLARE CURSOR_01 CURSOR LOCAL FOR

    SELECT  name    AS TAB_NM
      FROM sys.objects WITH(NOLOCK) WHERE type = 'U';


OPEN CURSOR_01;

USE [MN];

FETCH NEXT FROM CURSOR_01  INTO @TAB_NM;

WHILE @@FETCH_STATUS = 0 BEGIN    --//WHILE1

  SET @SQL = 'CREATE SYNONYM ' + @TAB_NM + ' FOR CM.dbo.' + @TAB_NM; --// Schema 수정 부분 (2/2)
  --SET @SQL = 'DROP SYNONYM ' + @TAB_NM;
  PRINT '@TAB_NM = ' + @TAB_NM;
  
  BEGIN TRY
    EXECUTE sp_executesql @SQL;
  END TRY
  BEGIN CATCH
    PRINT 'CATCH = ' + @SQL;
    PRINT ERROR_MESSAGE();
  END CATCH

 -- 다음행 삽입
 FETCH NEXT FROM CURSOR_01  INTO @TAB_NM;
END; --//WHILE1 END

--//CURSOR CLOSE
CLOSE CURSOR_01;
DEALLOCATE CURSOR_01;