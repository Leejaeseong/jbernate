/****** Object: Procedure [dbo].[PR_TEST]   Script Date: 2015-04-03 오후 2:29:44 ******/
USE [MUNDI];
GO
IF EXISTS (SELECT * FROM dbo.sysobjects WHERE id = OBJECT_ID(N'[dbo].[PR_TEST]') AND type IN ( N'P' ))
BEGIN
DROP PROCEDURE [dbo].[PR_TEST];
END
GO
SET ANSI_NULLS ON;
GO
SET QUOTED_IDENTIFIER ON;
GO
CREATE PROCEDURE [dbo].[PR_TEST]
AS

    SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED    -- 필요하다면 고립화 수준을 낮춥니다.
    SET XACT_ABORT ON                                   -- 프로시저 내에서 트랜잭션을 처리한다면 설정 합니다.

    /*************************************************
        공통 변수 선언
    **************************************************/
    DECLARE   @ProcName     NVARCHAR(200)  = OBJECT_NAME(@@PROCID)              --// 프로세스ID
            , @StepID       VARCHAR(30)    = '0'                                --// STEP번호
            , @ErrorNum     NUMERIC(10)    = 0                                  --// 오류코드
            , @ErrorMsg     NVARCHAR(1000) = ''                                 --// 오류정보
            
BEGIN
    SET NOCOUNT ON                                      -- 특별한 이유가 없다면 무조건 설정 합니다
    
    BEGIN
        BEGIN TRY
            
            SELECT REMK, ACTUAL FROM RESULT_ALL;
            
            IF (@ErrorNum = 0)
            BEGIN
                SET @ErrorMsg = 'Successful completion of ['+CAST(@@ROWCOUNT AS VARCHAR(10))+']';
            END
            
        END TRY
        BEGIN CATCH
            --// 오류정보
            SELECT @ErrorNum = ERROR_NUMBER(), @ErrorMsg = @StepID+'.'+ERROR_MESSAGE();

            SET @StepID = '11';      --// STEP번호
        END CATCH
    END
    
    IF (@ErrorNum = 0)
    BEGIN
        SET @ErrorMsg = @StepID+'.'+N'Was successfully completed.';       --// 처리결과
    END   
            
    /* RETURN VALUE 설정 */    
    RETURN;
    

SET NOCOUNT OFF;

END

GO