This app is a Resource Server config for the Authorization Server app found here:
https://github.com/Ginitoru/AuthorizationServer.git

1. Framework used: Spring with SpringBoot 2.5.0.

2. Dependecies: Spring JPA, Spring web, Spring security, mysql driver, lombok.

3. Functionality:

   The app uses REST API to store cars and some details about this cars to a mySQL database.
   After the user is authenticated on the Authorization Server, the server will generate a JWT that 
   will be used by the user to access the endpoints of the Resource Server. The Resource Server  usses a simetric key to validate the token, and grants access to the user depending on his 
   autorization.    
