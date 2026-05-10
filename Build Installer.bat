@echo off
echo =========================================
echo   JustCode Installer Builder
echo =========================================
echo.

:: Prompt the user for the version number
set /p APP_VER="Enter the version number (e.g., 0.2.0): "

echo.
echo Building JustCode v%APP_VER%...
echo This might take a minute...

:: Run the jpackage command
"C:\Users\Raunak\AppData\Local\Programs\Eclipse Adoptium\jdk-25.0.2.10-hotspot\bin\jpackage.exe" ^
  --input out/artifacts/JustCode_jar ^
  --name JustCode ^
  --main-jar JustCode.jar ^
  --type exe ^
  --app-version %APP_VER% ^
  --win-shortcut ^
  --win-menu ^
  --win-dir-chooser

echo.
echo =========================================
echo   Success! Installer created.
echo =========================================
pause