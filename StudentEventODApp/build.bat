@echo off
setlocal enabledelayedexpansion

:: Set paths
set SRC=src\main\java
set OUT=out
set LIB=lib\*

:: Set colors
set BLUE=0B
set RED=0C
set GREEN=0A
set RESET=07

:: Set password
set ADMIN_PASS=1234

:: MENU
:MENU
cls
color %BLUE%
echo.
echo *************************************************************************
echo                 SRI KRISHNA ARTS AND SCIENCE COLLEGE                  
echo                   OD REQUEST MANAGEMENT SYSTEM                          
echo **************************************************************************

echo.
echo [1]  Open OD Request Form
echo [2]  Open Admin Portal (Password Protected)
echo [3]  Clean All OD Requests (Admin Only)
echo [4]  Exit
echo.
set /p choice=Enter your choice [1/2/3/4]: 

if "%choice%"=="1" (
    call :COMPILE
    if %ERRORLEVEL% NEQ 0 goto END
    echo.
    echo  Launching OD Request Form...
    java -cp "%LIB%;%OUT%" utils.Main
    pause
    goto MENU

) else if "%choice%"=="2" (
    call :COMPILE
    if %ERRORLEVEL% NEQ 0 goto END
    color %RED%
    echo.
    set /p "adminpass= Enter admin password: "
    if "!adminpass!"=="%ADMIN_PASS%" (
        color %GREEN%
        echo  Access granted!
        timeout /t 1 >nul
        java -cp "%LIB%;%OUT%" utils.AdminPortal
    ) else (
        echo  Incorrect password. Access denied.
    )
    pause
    goto MENU

) else if "%choice%"=="3" (
    call :COMPILE
    if %ERRORLEVEL% NEQ 0 goto END
    color %RED%
    echo.
    set /p "cleanpass= Enter admin password to clean data: "
    if "!cleanpass!"=="%ADMIN_PASS%" (
        echo   This will delete all OD requests. Are you sure? (y/n)
        set /p confirm=
        if /i "!confirm!"=="y" (
            java -cp "%LIB%;%OUT%" utils.CleanTable
            echo  All OD request data has been cleared.
        ) else (
            echo  Operation cancelled.
        )
    ) else (
        echo  Incorrect password. Access denied.
    )
    pause
    goto MENU

) else if "%choice%"=="4" (
    goto END
) else (
    echo  Invalid choice.
    pause
    goto MENU
)

:: Compile function
:COMPILE
echo.
echo  Compiling Java source files...
javac -encoding UTF-8 -cp "%LIB%;." -d %OUT% ^
%SRC%\model\*.java ^
%SRC%\db\*.java ^
%SRC%\utils\*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo  Compilation failed. Please check the errors above.
    pause
    exit /b 1
)
echo  Compilation successful.
exit /b 0

:END
echo.
color %RESET%
echo  Exiting application. Thank you!
pause
endlocal
