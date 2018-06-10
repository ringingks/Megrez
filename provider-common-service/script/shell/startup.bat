title "### Megrez.provider_common_service 2.x ###"
set JAR_NAME=Megrez.provider_common_service.jar
set JAR_FILEPATH=C:\work\Megrez\2.x\provider_common_service
java -jar -Xms512m -Xmx1024m -Dspring.config.location=%JAR_FILEPATH%\config\application.yml %JAR_FILEPATH%\jar\%JAR_NAME%