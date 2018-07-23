title "### Cancer.server_task 2.x ###"
echo off
set APP_NAME=Cancer.server_task.jar
set CONFIG= -Dspring.config.location=../config/application.yml
echo "Starting the %APP_NAME%"
java -Xms512m -Xmx512m -jar %DEBUG_OPTS% %JMX_OPTS% %CONFIG% -jar ../jar/%APP_NAME%
goto end
:debug
echo "debug"
java -Xms512m -Xmx512m -server %DEBUG_OPTS% %CONFIG% -jar ../jar/%APP_NAME%
goto end
:jmx
java -Xms512m -Xmx512m -server %JMX_OPTS% %CONFIG% -jar ../jar/%APP_NAME%
goto end
:end
pause