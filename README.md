# MAS Global Client demo for testing request
Create a web page (view) using the following guidelines:
You can use the front-end technologies you are familiar with (JSP, JavaScript, HTML, etc.)
- The view must contain a textbox, so the user can type the id of a particular
employee.
- The view must contain a Get Employees button
- If the textbox is empty, when the Get Employees button is clicked, retrieve the
information for all the employees including the calculated Annual Salaries by calling
your API
- If the textbox has the id of a given employee, retrieve only the information for that
particular employee including the calculated Annual Salary by calling your API
- Information must be displayed in a table.

## Requisites
Before start build and deploy this project you shold be sure that you have already installed and configured the 
following dependencies:

- Be sure that remote API configuration is pointing to your API running proper API version application, please see 
``` src/main/resources/application.properties ``` 
- Apache Tomcat 7 server or higher (recommended Tomcat 9, you can download from [here](https://tomcat.apache.org/download-90.cgi)) 
- Apache Maven client 3.6.0 or higher (you can download from [here](https://maven.apache.org/download.cgi))

## Execution Instructions

1. Clone repository using the following command: ``` git clone https://github.com/jhonnyquest/masglobal-demo.git ```
2. Once you have cloned project then execute the following maven command: ``` mvn clean install ```
3. Copy file ``` {project-root}/target/masglobal.demo.client.jar ``` to your Tomcat server and deploy it.

## Features
- Dependencies manager: Maven 4.0.0
- Main Framework: Spring 2.1.3.RELEASE, Spark 2.8.0
- Web HTML5 templates: SparkJava Velocity 2.7.1
- Documentation: Javadoc
- Java Version: 1.8
- Logging: Logback

*Any comment or suggestion please let me know by email: jhonnymunoz@gmail.com*
