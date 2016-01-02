# omny-spring-app

##task description:
A table that contains users information: first name, last name, home address, created date

Build a rest API to get a user information by user id
Given user id pull user information

1) One a single jsp-page display the user information using a user get method

2) Write a batch job that will take following parameters: createdDays( day on which user information was created )
If the user was created on december 6th 2015, if the batch job passes -3 so lets say today is 9th December, the job should pull all users were created on 6th December and dump the list of those users in a file


##Project decription:
###Technology stack:
- Spring boot, MVC, JPA
- JSP, Bootstrap
- Log4j, Maven

###Features:
1) Welcome-page represent the list of all available users in table:
![image](http://prntscr.com/9l9atn)

2) to get user`s info you can click on appropriate link (in previous table) or enter ulr localhost\user\{user_id}:
![image](http://prntscr.com/9l9cpc)

3) also for operating with user`s info you can use rest-services (description is below), for example, to get info about user with id=1: 
![image](http://prntscr.com/9l9mjs)

4) to get list of users, created *n* days ago, enter this *n* to input-field on welcome-pages -- and it will be downloaded the csv-file (if there are exists such records):
![image](http://prntscr.com/9l9imp)

##How to run: 
before run check the database-properties in [application.properties](https://github.com/LamronNu/omny-spring-app/blob/master/src/main/resources/application.properties)
and set your url, login and password:

```txt
spring.datasource.url=jdbc:mysql://[your_url]
spring.datasource.username=[your_login]
spring.datasource.password=[your_password]
```
Then start application using command line:
```
mvn clean spring-boot:run
```

<a name="0_contents">
##Rest-API specification:</a><br/>
<a href="#1_getUserInfo">1. Get user info</a><br/>

<a name="1_getUserInfo">
####1. Get user info:
</a><a href="#0_contents">↑Up</a>

####Request-method:
GET
####URL: 
server:port\\rest\user\{id}
####Parameters:
- id -- id of user

####Description:
Return the full information about user or nothing if there are no user with such id.

####Example:

#####Reguest:
http://localhost:8080/rest/user/2
#####Response:
```json
{
"id":2,
"firstName":"Kate",
"lastName":"Mayson",
"homeAddress":"New York",
"createdDate":{"year":2015,"era":1,"dayOfYear":341,"dayOfWeek":1,"dayOfMonth":7,"weekyear":2015,"yearOfEra":2015,"hourOfDay":0,"millisOfDay":0,"minuteOfDay":0,"secondOfDay":0,"centuryOfEra":20,"yearOfCentury":15,"minuteOfHour":0,"weekOfWeekyear":50,"secondOfMinute":0,"monthOfYear":12,"millisOfSecond":0,"millis":1449439200000,"zone":{"uncachedZone":{"cachable":true,"fixed":false,"id":"Europe/Helsinki"},"fixed":false,"id":"Europe/Helsinki"},"chronology":{"zone":{"uncachedZone":{"cachable":true,"fixed":false,"id":"Europe/Helsinki"},"fixed":false,"id":"Europe/Helsinki"}},"equalNow":false,"beforeNow":true,"afterNow":false}
}

```
