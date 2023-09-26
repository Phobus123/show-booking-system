Show Booking System
==============================================================

The main file is [Main.java](src/main/java/org.example/Main).

Unit Testing
-----

* JUnit
* Mockito

Building an executable JAR
--------------------
### ShadowJAR
This project uses a library called [shadow](https://github.com/johnrengelman/shadow) to build an executable JAR with a self-contained manifest.

* How to build
    * Run Gradle Sync
    * Select Gradle -> Tasks -> shadow -> shadowJar
    * The JAR can be found at build/libs -> show_booking_system.jar

Command-line Instructions
-------------------------

### Running the executable JAR
```bash
java -jar show_booking_system.jar
```