# htmlextractor
An HTML Extractor


This project is compatible with JDK 1.8. 

# Running the project from maven
-----------------------------------------------------------------------------------------------------------------
1. Install maven on the relevant computer if it does not exist
2. Set all relevant environment variables to ensure a proper maven setup

The following environment variables need setting in the system path

 * JAVA_HOME, this should point to the installation directory of **jdk 1.8**
 * Add the bin directory of the installed maven directory to the PATH environment variable

3. After setting the above, confirm that maven is available in the command prompt/shell issuing the **mvn -v**    command The result should show the maven installation detail (If so, all is good).

4. Download this project and extract into a directory, or pull the project down using a git client.
5. In the command prompt/shell, change directory to the extracted project, example **C:\htmlextractor**
6. enter this command to clean and compile : **mvn clean compile**
7. enter this command to run the app : **mvn exec:java**
8. enter the following command to run the test: **mvn test**