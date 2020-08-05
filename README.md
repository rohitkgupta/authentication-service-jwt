#authentication-service Project written in Java, SpringBoot, JWT and gradle for build.

Steps to build & run:

./gradlew clean build

docker build -t=authentication-service .

docker run -p 8080:8080 authentication-service:latest


Test:
POST http://localhost:8080/authenticate
Body=
{
    "username" : "admin",
    "password" : "password"
}

Note: I have not added user table. There is only one user admin. We can easly point to any user database later.

Access Resource:
GET http://localhost:8080/hello
Header=Authorization:Bearer < token here >


To Refresh token:
POST localhost:8080/token/refresh
Body={
    "username" : "admin",
    "password" : "password",
    "refreshToken" : "< token to invalidate >"
}


Note: If user refresh token, I am storing old token in a data structure(TreeSet) to invalidate request with older token. We can optimize this storage by storing these token till they have validity.
