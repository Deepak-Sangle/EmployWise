# EmployWise

## Installation and Setup

#### CouchDb Setup 
- I have used CouchDB as mentioned in the assignment. 
- The database is going to run localhost:5984. In order to run CouchDb, one has to download and install it from [here](http://couchdb.apache.org/). 
- Give and Remember the username and password you set for CouchDB. 
- Replace line no. 36 and 37 in EmployeeRepository.java with these username and password.
- You can access the CouchDB dashboard [here](http://localhost:5984/_utils/) after installing it succesfully.

#### Spring Boot Setup
- Run `mvn clean install` to build the project.
- Run the EmployWiseApplication.java file using IntelliJ IDEA or using the command `java -jar target/employwise-0.0.1-SNAPSHOT.jar`
- The application will start running at `http://localhost:8080` in the embeded Tomcat Server.

## API Documentation

### `POST /employee/create` 
- used to create a new employee.
  - Input JSON Structure
  ```json
    {
        "name": "John Doe",
        "phoneNumber": "1234567890",
        "email": "johndoe@gmail.com",
        "reportsTo" : "15b2fc0cb1e7bc8755f46efc4000a27f",
        "profileImage" : "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
    }
  ```
  - I have assumed that `name`, `phoneNumber`, `email` and `reportsTo` are mandatory fields. `profileImage` is optional.
  - This API also emails the reporting manager about the new employee.
    - For this, you do not hae to customize anything. You can use my emailId and application specific password as far as you do not disclose it to anyone.

### `GET /employee/get-all-employees?size=5&page=1&sortBy=name` 
- is used to get the details of all employees.
  - There is no input JSON Structure for this API.
  - Fields `size`, `page` and `sortBy` are all optional. 
    - These values can be provided in any permutation and it will query accordingly.
    - For eg. You can provide only `sortBy` and it will return all employees with sorted data.
    - If you do not provide size, it will return all the documents.
    - If you specify `page` and `size`, it will return next `size` number of documents from `page*size`th document. Remember `page` starts from 0.
    - Default value of `page` is 0 and default value of `sortBy` is 'none'.

### `DELETE /employee/delete/{id}` 
- is used to delete the employeen with given ID.
  - There is no input JSON Structure for this API.
  - If employee with given id does not exist, it will return appropriate error message.

### `GET /employee/get-nth-manager/{n}/{id}` 
- is used to get the details of the nth manager of the employee with ID {id}.
  - There is no input JSON Structure for this API.
  - If such manager do not exist, it returns appropriate error message.

### `PUT /employee/update/{id}` 
- is used to update the details of the employee with ID {id}.
- Input JSON Structure
  ```json
    {
        "name": "John Doe",
        "phoneNumber": "1234567890",
        "email": "johndoe@gmail.com",
        "reportsTo" : "15b2fc0cb1e7bc8755f46efc4000a27f",
        "profileImage" : "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
    }
  ```
  - I have assumed that `name`, `phoneNumber`, `email` and `reportsTo`, and `profileImage` are all optional.
  - You obviously can not change the employee ID.