@echo off
if exist all_code.txt del all_code.txt

for /R %%f in (*.java) do (
    echo ---- 파일: %%f ---- >> all_code.txt
    type "%%f" >> all_code.txt
    echo. >> all_code.txt
)

for /R %%f in (*.html) do (
    echo ---- 파일: %%f ---- >> all_code.txt
    type "%%f" >> all_code.txt
    echo. >> all_code.txt
)
start notepad all_code.txt