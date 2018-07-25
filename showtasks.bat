call runcrud

echo off
set BROWSER=chrome.exe
start %BROWSER% -new-tab "https://www.google.pl/"
start %BROWSER% -new-tab "http://localhost:8888/tasks_frontend/"