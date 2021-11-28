# getir-java-case-study

## Java Version

- OpenJDK 17 is used, but its also used with OpenJDK 11, but you will need to change version properties at pom.xml 

## Database

- MongoDB Atlas Cloud Database Used. So there is no requirement for local db.
- You can try it with your own cloud MongoDB With this Url 
> https://www.mongodb.com/atlas/database
- Given Connection url only available till 4 December 2021, so better be replace it with your own DB Connection Url.

## Restful Endpoints

- POST /books 
> Creates New Book
- PUT /books/stock-update 
> Updates Stock of Book 
- POST /customers 
> Creates New Customer
- GET /customers/{customerId}/orders?page_number=x&page_size=x 
> Gets Orders of Customer Id with Pagination params, default page_number = 0, default page_size = 20
- POST /orders 
> Creates New Order
- GET /orders/{order_id} 
> Gets Order by Id
- GET /orders?start_date=202x-xx-xx&end_date=202x-xx-xx 
> Gets orders between given dates
- GET /statistics/{customer_id}
> Gets Statistics Of a Customer By Customer Id

## Clean Code

- While writing the code, try to avoid repeating code lines, instead turned them into methods, also write methods for different operations, such as conversion or validation of Object to make code more readable as possible.

## Tests

- As a minimum 50% coverage Requirement of Task, only written tests enough to pass 50%
![image](https://user-images.githubusercontent.com/40670635/143788107-f11db271-692b-4489-8f3a-2e32f27439ef.png)

## Documentation

- Can be found here [Documentation.pdf](https://github.com/hcipek/getir-java-case-study/files/7614644/Belge.2.pdf)


## Containerize

Docker image can be pulled from terminal with this command  
> docker pull hakancipek/getirapp:latest

### Alternative: Creating Docker Image From Project;

>docker build -f Dockerfile -t getirapp:latest . (may require going to the root of project)

## Must Have Requirements

- Customer Controller
  - Will persist new customers
  - Will query all orders of the customer ( Paging sounds really nice )
- Book Controller
  - Will persist new book
  - Will update book’s stock
- Order Controller
  - Will persist new order (statuses may used)
    - Will update stock records.(Hint: what if it happens if 2 or more users tries to buy one last book at the same time)
  - Will query order by Id
  - List orders by date interval ( startDate - endDate )
- Statistics Controller
  - Will serve customer’s monthly order statistics
    - Total Order count
    - Total amount of all purchased orders
    - Total count of purchased books
- Validations - Please be sure your system error proof
- [Missing] Authentication - Please secure endpoints (for ex. bearer token)
- Responses - Please define success and error response models and use them
- Postman - Please prepare Postman request and share with us
> https://www.getpostman.com/collections/edd1e977eb36f9ea6cfb

## Nice to Have's

- Logging
- Open API Specification (Swagger sounds nice) (authentication not needed)
> After Run the App, check http://localhost:9661/


## How to Run Application

- Simply run the docker image.
> docker run -d --name getirapp -p 9661:9661 hakancipek/getirapp:latest

### Alternative

- Run the GetirApplication.java under the path getir/src/main/java/com/challenge/getir

## Swagger:

- Available at http://[host]:[port]/ by default

### Example for default properties;

- http://localhost:9661/

## Postman

- https://www.getpostman.com/collections/edd1e977eb36f9ea6cfb
