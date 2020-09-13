# Documentation of ATdITGruppe2 Repo

In this documentation you can find info about the content of this repository and how to run our app.

### How to get around in this repository

##### Folder [Doku](./Doku)

General info about how we organised ourselves and how we approached this project.

##### Folder [gemuese4you](./gemuese4you)

This is the folder containing our [code](./gemuese4you/src) and [java documentation](./gemuese4you/doc) as well as some Eclipse project files. In the subfolder src you can find our code.



All the other file and folder names should be self explanatory.



### How to run our app

In order to run our app you need to do the following things:

- Install MariaDB and run the SQL queries in the file [SQL Befehler_akt.docx](./SQL Befehle_akt). You can run them in the mySQL command line after you created the database "gemuese4You" in your HeidiSQL Client
- Then open the [gemuese4you](./gemuese4you) folder as a java project in eclipse. In the util.java class in package gemuese4you you need to change the credentials in method getConnection line 45 to your local  username and password. 
- If you start the app now it could be the case that it can not find some database driver. Then you have to right click on the project -> Build Path -> Configure Build Path then choose the tap Libraries on the top and remove the db java client under Classpath and then click on Add External JARs and add [this](./mariadb-java-client-2.6.2.jar) db client. After adding it to the classpath got to the tap Order and Export and tick the newly added db client.