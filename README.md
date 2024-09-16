# E-Commerce Microservices

This project is an application with a microservices architecture developed for a modern e-commerce platform. It allows for the independent development and deployment of different services, providing scalability and ease of maintenance.

## Table of Contents

- [Microservices](#microservices)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Microservices

The project consists of the following microservices:

- **Cart Service**: Manages users' carts.
- **Inventory Service**: Tracks product stock levels.
- **Notification Service**: Handles notification management.
- **User Service**: Manages user registration and authentication.
- **Review Service**: Manages product reviews.
- **Product Service**: Provides product information.
- **Order Service**: Manages order processes.
- **Payment Service**: Handles payment processing.
- **Discovery Server**: Allows microservices to discover each other (Eureka).

## Technologies

- **Backend**: Java, Spring Boot, Spring Cloud, Maven
- **Frontend**: React, Bootstrap, Axios
- **Database**: MySQL, PostgreSQL, MongoDB
- **Communication**: RESTful API
- **Others**: Docker, Git

## Installation

### Prerequisites

- **Java JDK 17**
- **Node.js v18**
- **Maven 3.9.6**
- **MySQL**
- **Docker** (optional)

### Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/e-commerce-microservices.git
   cd e-commerce-microservices
   ```

2. **Set Up the Database**

   Create the necessary databases in MySQL and adjust the user credentials in the `application.properties` files according to your settings.

3. **Start Backend Services**

   Navigate to each microservice folder and run the project with Maven:

   ```bash
   cd reviewservice
   mvn spring-boot:run
   ```

   Repeat the same steps for other services.

4. **Set Up and Start Frontend**

   ```bash
   cd frontend
   npm install
   npm start
   ```

   The application will be running at [http://localhost:3000](http://localhost:3000).

## Usage

- **Register and Login**: Users can register and log in.
- **Product Management**: Add, edit, and delete products.
- **Cart Management**: Add products to your cart and view your cart.
- **Order and Payment**: Place orders and process payments.
- **Reviews**: Add reviews to products and view existing reviews.

## Contributing

If you wish to contribute, please follow these steps:

1. **Fork** the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push your branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

## License

This project is licensed under the [Apache License 2.0](LICENSE).

---

## Contact

For questions or suggestions about the project, you can reach out to [email@example.com](mailto:email@example.com).
