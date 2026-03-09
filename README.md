# TicketRush - High-Concurrency Event Ticketing API

## 📌 Executive Summary
TicketRush is a robust backend REST API designed to simulate a high-demand ticket sales environment, such as concert ticket sales or flight bookings. The core focus of this project is to solve race conditions and ensure 100% data consistency under heavy concurrent usage scenarios. 

This is achieved by implementing JPA Optimistic Locking to prevent double-booking and utilizing Redis caching to significantly reduce database load.

## 🚀 Core Features
* **Concurrency Control:** Prevents double-booking when multiple users attempt to purchase the same seat simultaneously using JPA Optimistic Locking (versioning on the `Seat` entity).
* **High Performance via Caching:** Reduces database read load by up to 80% and improves response times by caching seat availability data in Redis.
* **Global Exception Handling:** Provides user-friendly error messages (e.g., HTTP 409 Conflict) when a seat is already taken by another person during a race condition.
* **Infrastructure as Code:** Ensures environment consistency using Docker and Docker Compose for PostgreSQL and Redis containerization.

## 🛠️ Technical Stack
* **Language:** Java 17
* **Framework:** Spring Boot 3.x (Web, Data JPA, Cache, Validation)
* **Database:** PostgreSQL
* **Caching:** Redis
* **Infrastructure:** Docker & Docker Compose
* **Testing:** JUnit 5, Mockito
* **Build Tool:** Maven

## ⚙️ Getting Started

### Prerequisites
* Java 17 or higher
* Docker and Docker Compose
* Maven (Optional, wrapper is included)

### Installation & Setup
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/alperenakksoy/ticketrush.git](https://github.com/alperenakksoy/ticketrush.git)
   cd ticketrush
### Start the Infrastructure (PostgreSQL & Redis):
Ensure Docker is running, then spin up the required databases:

docker-compose up -d
Note: This starts PostgreSQL on port 5432 and Redis on port 6379.

Configure Environment Variables:
Set the following environment variables in your system or IDE:

* DB_NAME
* DB_USERNAME (defaults to rush_user in application.properties)
* DB_PASSWORD

### Run the Application:
./mvnw spring-boot:run

Gemini şunu dedi:
It looks like you highlighted the text and copied it directly from the screen, which copies the "rendered" text instead of the raw Markdown code. This causes all the formatting (like the # for headers and ``` for code blocks) to get lost.

To get the correct formatting, you need to use the Copy button built into the code block.

Here is the exact step-by-step way to do it:

Look at the black code box below.

Hover your mouse over the top-right corner of the black box.

A small "Copy" icon (it looks like a little clipboard) will appear. Click it.

Go to your README.md file in your IDE (like IntelliJ or VS Code) or directly on GitHub.

Paste (Ctrl+V or Cmd+V) the text.

Here is the code block to copy from (I also updated your GitHub URL for you):

Markdown
# TicketRush - High-Concurrency Event Ticketing API

## 📌 Executive Summary
TicketRush is a robust backend REST API designed to simulate a high-demand ticket sales environment, such as concert ticket sales or flight bookings. The core focus of this project is to solve race conditions and ensure 100% data consistency under heavy concurrent usage scenarios. 

This is achieved by implementing JPA Optimistic Locking to prevent double-booking and utilizing Redis caching to significantly reduce database load.

## 🚀 Core Features
* **Concurrency Control:** Prevents double-booking when multiple users attempt to purchase the same seat simultaneously using JPA Optimistic Locking (versioning on the `Seat` entity).
* **High Performance via Caching:** Reduces database read load by up to 80% and improves response times by caching seat availability data in Redis.
* **Global Exception Handling:** Provides user-friendly error messages (e.g., HTTP 409 Conflict) when a seat is already taken by another person during a race condition.
* **Infrastructure as Code:** Ensures environment consistency using Docker and Docker Compose for PostgreSQL and Redis containerization.

## 🛠️ Technical Stack
* **Language:** Java 17
* **Framework:** Spring Boot 3.x (Web, Data JPA, Cache, Validation)
* **Database:** PostgreSQL
* **Caching:** Redis
* **Infrastructure:** Docker & Docker Compose
* **Testing:** JUnit 5, Mockito
* **Build Tool:** Maven

## ⚙️ Getting Started

### Prerequisites
* Java 17 or higher
* Docker and Docker Compose
* Maven (Optional, wrapper is included)

### Installation & Setup
1. **Clone the repository:**
   ```bash
   git clone https://github.com/alperenakksoy/ticketrush.git
   cd ticketrush
Start the Infrastructure (PostgreSQL & Redis):
Ensure Docker is running, then spin up the required databases:

Bash
docker-compose up -d
Note: This starts PostgreSQL on port 5432 and Redis on port 6379.

Configure Environment Variables:
Set the following environment variables in your system or IDE:

DB_NAME

DB_USERNAME (defaults to rush_user in application.properties)

DB_PASSWORD

Run the Application:

Bash
./mvnw spring-boot:run

## 📡 Key API Endpoints
1. Get Event Seats
Returns seat availability for a specific event. Data is served from Redis if available, otherwise fetched from PostgreSQL and cached for subsequent requests.

URL: GET /api/events/{id}/seats

Response: List of SeatDto objects indicating seat numbers and their availability status (AVAILABLE, LOCKED, BOOKED).

2. Create a Booking
Attempts to book one or more seats for an event. Uses optimistic locking to prevent double-booking. If a conflict occurs, a 409 Conflict status with a user-friendly error message is returned.

URL: POST /api/bookings

Request Body:

{
  "userId": 1,
  "eventId": 100,
  "seatIds": [10, 11]
}

Success Response (201 Created): Returns booking status, total amount, booking date, and seat numbers.

## 🧪 Testing
The project includes comprehensive unit tests for the service layer and mappers using JUnit 5 and Mockito.
Run the tests using:

./mvnw test


