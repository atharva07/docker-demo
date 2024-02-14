@echo off 
cmd /c call "D:/docker-udemy/workspace/03-automation-framework/selenium-docker/pom.xml@tmp/durable-8b7ac906/jenkins-main.bat" > "D:/docker-udemy/workspace/03-automation-framework/selenium-docker/pom.xml@tmp/durable-8b7ac906/jenkins-log.txt" 2>&1
echo %ERRORLEVEL% > "D:/docker-udemy/workspace/03-automation-framework/selenium-docker/pom.xml@tmp/durable-8b7ac906/jenkins-result.txt"
