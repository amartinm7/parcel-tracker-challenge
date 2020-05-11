[![BCH compliance](https://bettercodehub.com/edge/badge/amartinm7/parcel-tracker-challenge?branch=master)](https://bettercodehub.com/)

![packlink](./_media/packlink.png)

# parcel-tracker-challenge

This is an app to test an REST API where you can send a shipment and tracking this shipment.

First of all, before running the app, review that you have installed the next tools:
- install Java v8 or higher version (https://www.java.com/en/download/help/download_options.xml)
- install gradle v5 (https://gradle.org/install/)
- optionally, install docker (https://docs.docker.com/install/#support)

After that, follow the next steps specifies in the rest of the document.

Once you have compiled and run the app, you can see the REST API in the next url:
- http://localhost:8085/swagger-ui.html


## ![springboot](./_media/icons/springboot.png) run springboot app
To start, install the gradle wrapper is you didn't do it previously and boot the app: 
```bash
gradle wrapper
./gradlew build
./gradlew bootRun
```

or run the application using the java command line, using the fat jar:
```bash
java -jar -Dspring.profiles.active=dev build/libs/parcel-tracker-rest-service-0.1.0.jar
```
You can specify the profile using the -Dspring.profiles.active parameter. If you don't specify any, by default is 'dev' in our case.
If you have installed another Java higher version different than 8, you have to specify the the -source and -target command flags as the 8 version.   

## ![docker](./_media/icons/docker.png) Dockerize the app
From the project root folder exec the commands to create a docker image and run it:

for macosx start the docker daemon
```bash
killall Docker && open /Applications/Docker.app
```

then execute the next commands in order to create the docker image and run it:
```bash
docker build -f docker/Dockerfile . -t parceltracker
docker run -p 8085:8085 parceltracker
```

to stop the application first we have to stop the docker process and then kill the docker process:
```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean the docker images from the system:
```bash
docker images
docker rmi PID
``` 

Once you have the dockerized app is really easy bring it to the cloud. You look for a cloud provider to deploy it and host it.

## ![swagger](./_media/icons/swagger.png) Swagger
You can see the swagger documentation in the following url:
- http://localhost:8085/swagger-ui.html

## Test code covarage using Jacoco
To see the report with the code coverage for the testing, simply open the report: 
- build/reports/jacoco/test/html/index.html

## Code Quality coverage using checkstyle and findbugs
To see the reports simply open the report: 
- build/reports/checkstyle/main.html
- build/reports/checkstyle/test.html

You can execute the report on demand:
```bash
./gradlew check
```

## Cucumber test report
To see the reports simply open the report: 
- target/cucumber/index.html

## actuator for checking the availability of the system
Under the next urls you can see how is the system running:
* http://localhost:8085/actuator/health
* http://localhost:8085/actuator/info


## Some useful Tips

## Closing ports
Closing ports, useful when the service is running and you want to kill it.
```
sudo lsof -i :8085
sudo kill -9 PID
```
