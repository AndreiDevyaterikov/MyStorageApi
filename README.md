# MyStorageApi

## Technologies
* Maven 3.8.6 version [Download Maven](https://lists.apache.org/thread/44817jckpzy7gtrkds9xfrgybmrrbm1z)
* Java 17 version

## Setup
* Set Maven in setting in Intellij Idea -> File | Settings | Build, Execution, Deployment | Build Tools | Maven
* Set JDK in project settings
* Build project
* Execute scripts in sql files

![image](https://user-images.githubusercontent.com/60696068/201762497-297ffa95-17d0-487d-8ef1-6a0a8467158b.png)

* Optionally, change db and url settings
```yaml
springdoc:
  swagger-ui:
    path: /my-storage-api.html
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres?currentSchema="public"
    username: postgres
    password: root
```    

* Run project and go to [Swagger](http://localhost:8080/my-storage-api.html)
