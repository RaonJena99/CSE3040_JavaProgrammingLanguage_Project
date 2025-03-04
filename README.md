# README: Java Programming Projects

## Project 1: Vehicle Management System
### Overview
This project implements a Vehicle Management System using Java's object-oriented programming principles. It includes a base class `Vehicle`, two subclasses `Car` and `Motorcycle`, custom exception handling, and a `VehicleManager` class to manage vehicle records.

### Features
1. **Encapsulation and Inheritance**
   - The `Vehicle` class has private fields (`brand`, `model`, `year`) with getter and setter methods.
   - The `Car` and `Motorcycle` classes inherit from `Vehicle`, adding unique attributes (`seats` for cars, `hasSidecar` for motorcycles).

2. **Custom Exceptions**
   - `InvalidVehicleDetailException`: Ensures `year >= 1886` and `seats > 0`.
   - `DuplicateVehicleException`: Prevents duplicate vehicle entries in `VehicleManager`.
   - `VehicleNotFoundException`: Handles cases where a searched vehicle does not exist.

3. **VehicleManager Class**
   - Stores vehicles in a `List<Vehicle>`.
   - Methods to add, remove, search, and print all vehicles.

### Class Breakdown
#### 1. `Vehicle` Class
- Private fields: `brand`, `model`, `year`.
- Constructor enforces a valid `year`.
- `toString()` method for readable output.

#### 2. `Car` and `Motorcycle` Classes
- `Car` includes `seats` with validation.
- `Motorcycle` includes `hasSidecar`.
- `toString()` overrides to provide formatted output.

#### 3. Exception Classes
- `InvalidVehicleDetailException`
- `DuplicateVehicleException`
- `VehicleNotFoundException`

#### 4. `VehicleManager` Class
- Uses an `ArrayList` for vehicle management.
- Provides methods to add, remove, and search for vehicles.
- Throws exceptions for duplicate and non-existent vehicles.

### How to Run
1. Compile all Java files.
2. Create instances of `Car` and `Motorcycle`.
3. Use `VehicleManager` to add and manage vehicles.
4. Handle exceptions appropriately.

---

## Project 2: Bank Account Management System
### Overview
This project simulates a banking system with different account types. It ensures data integrity through encapsulation and custom exceptions.

### Features
1. **Encapsulation and Inheritance**
   - The `BankAccount` class has private fields (`accountNumber`, `balance`).
   - `SavingsAccount` and `CheckingAccount` inherit from `BankAccount` with additional functionalities.

2. **Custom Exceptions**
   - `InsufficientBalanceException`: Prevents withdrawals exceeding the balance.
   - `DuplicateAccountException`: Ensures unique account numbers.
   - `AccountNotFoundException`: Handles missing account searches.

3. **BankManager Class**
   - Uses a `HashMap<String, BankAccount>` for account management.
   - Methods for adding, searching, depositing, and withdrawing funds.

### Class Breakdown
#### 1. `BankAccount` Class
- Private fields: `accountNumber`, `balance`.
- `deposit()` and `withdraw()` methods with validation.
- Ensures balance modifications only through these methods.

#### 2. `SavingsAccount` and `CheckingAccount` Classes
- `SavingsAccount`: Adds an `interestRate` and `applyInterest()`.
- `CheckingAccount`: Adds an `overdraftLimit` and modifies `withdraw()`.

#### 3. Exception Classes
- `InsufficientBalanceException`
- `DuplicateAccountException`
- `AccountNotFoundException`

#### 4. `BankManager` Class
- Uses a `HashMap` for efficient account retrieval.
- Implements methods for deposit, withdrawal, and account searching.
- Enforces unique accounts and handles missing accounts.

### How to Run
1. Compile all Java files.
2. Create instances of `BankAccount`, `SavingsAccount`, `CheckingAccount`.
3. Use `BankManager` to add and manage accounts.
4. Handle exceptions appropriately.

---

## Project 3: Multi-Client Ticket Booking System
### Overview
This project implements a multi-threaded client-server ticket booking system. The server manages seat availability, and clients interact with it to book seats.

### Features
1. **Multi-threading**
   - The server handles multiple clients using `ExecutorService`.
   - Clients run as separate threads.

2. **Synchronized Seat Management**
   - Uses a `synchronized` block to ensure thread safety.
   - Prevents multiple clients from booking the same seat.

3. **Client-Server Communication**
   - Clients request seat availability.
   - The server processes bookings and updates available seats.

### Class Breakdown
#### 1. `TicketServer` Class
- Uses a `ServerSocket` to listen for client connections.
- Stores available seats in `List<Integer>`.
- Uses `synchronized` blocks to ensure seat consistency.
- Spawns a new `TicketHandler` thread for each client.

#### 2. `TicketHandler` Class
- Reads client seat requests.
- Validates and books seats.
- Communicates responses back to the client.

#### 3. `TicketClient` Class
- Connects to `TicketServer`.
- Requests available seats and attempts to book a random seat.
- Handles booking confirmation or retry logic.

### How to Run
1. Compile `TicketServer.java` and `TicketClient.java`.
2. Start `TicketServer` first.
3. Run multiple instances of `TicketClient`.
4. Observe seat allocation in the server console.

---

## Conclusion
These projects demonstrate core Java concepts such as encapsulation, inheritance, exception handling, multi-threading, and network programming. Each project ensures data integrity and efficiency while providing a structured and scalable solution.

