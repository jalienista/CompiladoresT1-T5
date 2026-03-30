@echo off
echo Rodando Java...
java -cp ".;lib/antlr-4.13.2-complete.jar" main %1 %2
echo.
echo Terminou
pause