# parcel-tracker-challenge
parcel-tracker-challenge


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
you can specify the profile using the -Dspring.profiles.active parameter. If you don't specify any, by default is 'dev' in our case.


## ![swagger](./_media/icons/docker.png) Dockerize the app
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
- http://localhost:8080/swagger-ui.html



## Some useful Tips

## Closing ports
Closing ports, useful when the service is running and you want to kill it.
```
sudo lsof -i :8085
sudo kill -9 PID
```