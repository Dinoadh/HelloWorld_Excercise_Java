# Hello World

The HelloWorld Application is built on JAVA using Spring Boot Plugin. It exposes web application API which listens on port 8080. The application can be started using with or without Docker.

Go to Project Folder
```
cd {project.dir}/HelloWorld
```
#### To Run the Application with Docker
Run the Following Command On `Linux` Terminal
```
Approach#1 mvn clean package spring-boot:build-image && docker run -p 8080:8080 -t helloworld:0.0.1
Approach#2 mvn clean package && docker build . -t helloworld:0.0.1 && docker run -p 8080:8080 -t helloworld:0.0.1
```
Run the Following Command on `Windows` Command Prompt
```
Approach#1 mvn clean package spring-boot:build-image && docker run -p 8080:8080 -t helloworld:0.0.1
Approach#2 mvn clean package && docker build . -t helloworld:0.0.1 && docker run -p 8080:8080 -t helloworld:0.0.1
```
#### To Run the Application without Docker
Run the Following command on `Linux` Terminal
```
./mvnw package && java -jar target/HelloWorld-0.0.1.jar
```
Run the Following command on `Windows` Terminal
```
mvnw package && java -jar target/HelloWorld-0.0.1.jar
```

Following Application URL are now accessible
1. http://localhost:8080
2. http://localhost:8080/healthz
    This URL will give following details as per the health of application
    ```
    {
        "status": "OK",
        "version": "0.0.1",
        "uptime": "up since 2020-08-04 08:00:05"
     }
    ```
This tells about the health of the application, however I would like to add few more information into this which is explained below
- JVM Memory Size & Used : This information is vital for any java application which can tell if the application is using too much of memory
- CPU Usages : CPU usages which can be helpful if the application is having too many context switches and it is engaging CPU a lot
- Disk Usages : Disk Usages details can tell how much percentage of disk is available on the server which will help if the application is writing more than expected data on disk
- No Of Thread (Live & Peak) : This information can tell how many thread an application is creating and what is its peak.
- GC Details : Garbage collection is essential for any JVM application and this detail can tell how frequently major and minor GC is running and if any failure reported during GC

## CICD Process

__Branching Strategy__
* Master Branch :- This branch will always contain the `stable` version of the product and the access of commit to this branch will be restricted.
* Dev Branch :- This branch will be containing `unstable` version of the product and all ongoing feature development will be merged to this branch. When Dev testing is done on this branch the code will be merged to Master branch to be ready for release.
* Feature Branches :- There will be branch for each of the feature and when it any feature is developed, the developer would need to raise pull request for merging his changes into Dev branch.
* Release Branches :- Based on release strategy like (release on time interval (1-2week) or end of development cycle) code will be merged into Release branch from Master branch and then relase will be generated for UAT, subsequently for PREPROD and PROD.
* Hotfix Branches :- When a release is deployed in UAT/PREPROD or PROD if any bug or issue is raised that will be fixed in hotfix branch subsequently it will be merged to Dev branch. After testing the Dev branch will be merged to Master to be ready for Release.

__CICD Tools__
* For Version Control :- [GitHub](https://www.github.com)
* For Buld :- Maven Wrapper
* For CI Process :- [Travis CI](https://travis-ci.org/)
* for CD Process :- [DockerHub](https://hub.docker.com/) & [Heroku](https://www.heroku.com/)
* For Code Coverage :- [CodeCov](https://codecov.io/)
* For Code Analysis :- [SonarQube](https://www.sonarqube.org/)

__Stages in CICD Pipeline__
1. Create a branch for a feature, do the changes on local and commit the changes.
2. Push the Changes to GitHub repository on the same remote branch.
3. Travis CI will take pull of this change and will run the maven build for same.
4. CodeCov and SonarQube will provide the report and will raise alert if any dip in code coverage threshold or code quality threshold.
5. If Everything is successful then raise a Pull Request to merge this changes in Development branch.
6. Travis CI will again do a maven clean build.
7. CodeCov and SonarQube will also provide the report to make sure there is no dip in threshold.
8. A docker image with snapshot tag will be created and then uploaded to dockerhub.
9. Travis CI will also send the outgoing webhook to Heroku to deploy on staging environment.

__Purpose of CICD Stages__
1. The first stage is Build process which ensures that any changes pushed to remote branch should not have build error.
2. Checks on CodeCov ensures that whatever feature any developer is working on has all the Unit Test Case available and running fine. 
3. Sonarqube ensures that the code developer has developed is following coding and design practices. The code is easy to read and debug.
4. Heroku ensures the Staging/UAT/PREPROD and PROD environment is UP and running with latest successful builds.
5. Dockerhub is a registry and this is used for deploying image on cloud