set installutil="C:\Windows\Microsoft.NET\Framework\v4.0.30319\installutil.exe"
set service=WindowsService1.exe
%installutil% %~dp0\bin\%service%
pause