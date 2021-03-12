For the purpose of this project I used Spring Boot with JPA, Vaadin and H2 database.
To build and run this project you can simply import it to your ide (in my case IntelliJ) and import dependencies using **npm i**.
The database settings are stored in application.properties file.
Username is **"user"** and password is **"password"**

- Usage:  
The application works with **localhost:8080 address**.
All CRUD operations can be done by user interface.
You can browse all notes on **/main** address and find specific note version history by passing the note id on **/archive** address.
Additionally you can log into database on **/db** address with credentials mentioned earlier.
