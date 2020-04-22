"# TestTask" 
____

For app should be configured PostgreSQL DB and create database "names" schema
 "names". Or you can use other DB name and change it in queries in src/main/java/task/db/UserDao
 .java.
   
Valid "in memory" login&pass are: "userDto" - "pass" and "admin" - "admin". There are in Secrets class.
For SMTP service change "email" and "password" (Mb change message subject) in src/main/java/task
/smtp/SmtpService.java.

Endpoints
-
1. PUT http://localhost:8080/v1/name 
Request : { 
            "Name": <String>, 
            "Age": <Integer> 
          }  
Response 200 Request succeeded or 400 Badly formatted.

2. GET http://localhost:8080/v1/auth BasicAuth
    (username - "userDto", password - "pass" or username - "admin", password - "admin")
    Response 200 Authorization success or 401 Invalid authentication credentials.
 
3. GET http://localhost:8080/v1/name/
   Response 200 request succeeded. The structure below is returned.
   {
    	"Names": ["name1", "name2", … , "nameN"]
   }
   
4. SMTP Client. PUT http://localhost:8080/v1/message/smtp/ 
   	Request { 
   		       "message": <String>, 
   		       "address": <String> 
   	        }  
    Response 200 Message "message" sent to "address" or 400 Bad request.
    
     

   
