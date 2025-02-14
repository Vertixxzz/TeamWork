# Restaurant Application README

This document provides an overview of the core components of the Restaurant Application. The application is built using the MVC pattern with repository-based data access, and it facilitates operations such as user registration and login, menu management, order placement, and seat reservation.

--------------------------------------------------
## CLASS: User

### Description:
The `User` class represents a user within the restaurant system. It stores essential user information, including credentials and the user role.

### Attributes:
- **id**: Integer  
  Unique identifier for the user.
- **username**: String  
  The username of the user.
- **password**: String  
  The user's password.
- **role**: Enum `Role`  
  The role of the user (ADMIN, MANAGER, CUSTOMER).

### Constructors:
- **Default Constructor**:  
  Initializes a User object with default values.
- **Parameterized Constructor (excluding id)**:  
  Initializes a User object with specified username, password, and role.
- **Parameterized Constructor (all fields)**:  
  Initializes a User object including the user id.

### Methods:
- **Getters and Setters**:  
  Allow access and modification of the user's attributes.
- **toString()**:  
  Returns a string representation of the User object.

--------------------------------------------------
## CLASS: Menu

### Description:
The `Menu` class represents a dish offered by the restaurant. It contains details about the dish such as its name, price, and category.

### Attributes:
- **id**: Integer  
  Unique identifier for the menu item.
- **itemName**: String  
  The name of the dish.
- **price**: Double  
  The price of the dish.
- **category**: Category  
  The category to which the dish belongs (e.g., Appetizer, Main Course).

### Constructors:
- **Default Constructor**:  
  Creates an empty Menu object.
- **Parameterized Constructors**:  
  Initialize a Menu object with specific values for itemName, price, and category.

### Methods:
- **Getters and Setters**:  
  Provide access to modify each attribute.
- **toString()**:  
  Returns a formatted string representation of the Menu item.

--------------------------------------------------
## CLASS: Category

### Description:
The `Category` class is used to categorize menu items. Categories help group dishes into types like Starters, Main Courses, and Desserts.

### Attributes:
- **id**: Integer  
  Unique identifier for the category.
- **name**: String  
  The name of the category.

### Constructors:
- **Default Constructor**:  
  Initializes an empty Category.
- **Parameterized Constructor**:  
  Creates a Category with a specified name (and id if provided).

### Methods:
- **Getters and Setters**:  
  Allow access and modification of category attributes.
- **toString()**:  
  Returns a string representation of the Category.

--------------------------------------------------
## CLASS: Order

### Description:
The `Order` class encapsulates the details of an order placed by a user. It maintains the list of ordered items and the total price.

### Attributes:
- **id**: Integer  
  Unique identifier for the order.
- **userId**: Integer  
  Identifier of the user who placed the order.
- **orderItems**: List of `OrderItem`  
  A list containing individual order items.
- **totalPrice**: Double  
  The total price of the order.

### Constructors:
- **Default Constructor**:  
  Creates an empty Order object.
- **Parameterized Constructor**:  
  Initializes an Order with a user id, list of order items, and total price.

### Methods:
- **Getters and Setters**:  
  Provide access and modification of the order details.
- **toString()**:  
  Returns a summary of the order.

--------------------------------------------------
## CLASS: OrderItem

### Description:
The `OrderItem` class represents an individual item within an order, specifying the dish, its quantity, and its association with a particular order.

### Attributes:
- **id**: Integer  
  Unique identifier for the order item.
- **orderId**: Integer  
  Identifier of the order to which this item belongs.
- **menuId**: Integer  
  Identifier of the menu item.
- **quantity**: Integer  
  The number of units of the menu item ordered.

### Constructors:
- **Default Constructor**:  
  Creates an empty OrderItem.
- **Parameterized Constructor**:  
  Initializes an OrderItem with specific orderId, menuId, and quantity.

### Methods:
- **Getters and Setters**:  
  Allow access and modification of the order item attributes.
- **toString()**:  
  Returns a string representation of the OrderItem.

--------------------------------------------------
## CLASS: Seat

### Description:
The `Seat` class models the seating available in the restaurant. It is used to view seat status and perform seat reservations.

### Attributes:
- **id**: Integer  
  Unique identifier for the seat.
- **seatNumber**: Integer  
  The number assigned to the seat.
- **reserved**: Boolean  
  Indicates whether the seat is reserved.
- **reservedByName**: String  
  The name of the user who reserved the seat (if applicable).

### Constructors:
- **Default Constructor**:  
  Initializes a Seat object with default values.
- **Parameterized Constructor**:  
  Initializes a Seat object with a specific seat number and reservation status.

### Methods:
- **Getters and Setters**:  
  Provide access to the seat attributes.
- **toString()**:  
  Returns a formatted string representing the seat status.

--------------------------------------------------
## CLASS: CurrentUser

### Description:
The `CurrentUser` class is a singleton utility that manages the currently logged-in user. It ensures that only one active user session exists during runtime.

### Attributes:
- **id**: Integer  
  Identifier of the currently logged-in user.
- **username**: String  
  The username of the current user.
- **role**: Role  
  The role of the current user.

### Constructors:
- **Constructors**:  
  Various constructors initialize the CurrentUser, defaulting the role to CUSTOMER if not specified.

### Methods:
- **Getters and Setters**:  
  Allow access and modification of the current user information.
- **toString()**:  
  Provides a string summary of the current user.

--------------------------------------------------
## CONTROLLERS & REPOSITORIES

### Controllers:
- **MenuController**:  
  Handles displaying the menu and adding new dishes. Adding dishes is restricted to users with ADMIN or MANAGER roles.
- **OrderController**:  
  Manages order placement and viewing orders. Only authenticated users can place orders.
- **RegistrationController**:  
  Facilitates user registration.
- **UserController**:  
  Manages user login and logout.
- **SeatController**:  
  Handles seat viewing and reservation functionalities.

### Repositories:
The application uses repositories to abstract database operations:
- **MenuRepository**:  
  Manages CRUD operations for menu items.
- **OrderRepository**:  
  Handles order creation and retrieval.
- **RegistrationRepository**:  
  Processes user registration and fetches user details.
- **SeatRepository**:  
  Manages seat data and reservations.
- **CurrentUserRepository**:  
  Stores and provides access to the currently logged-in user.

### Data Layer:
- **PostgresDB**:  
  A singleton class that manages connections to the PostgreSQL database. It provides a consistent way to obtain database connections throughout the application.

--------------------------------------------------
# Application Flow and Purpose

The Restaurant Application is designed to streamline restaurant operations through:
- **User Registration and Authentication**:  
  Users can register, login, and logout.
- **Menu Management**:  
  The system displays a menu of available dishes; authorized roles can add new dishes.
- **Order Placement**:  
  Authenticated users can place orders and view their order history.
- **Seat Reservation**:  
  Users can view available seats and reserve them.
- **Role-Based Access Control**:  
  Operations like adding menu items are restricted to users with appropriate roles (ADMIN, MANAGER).

--------------------------------------------------
# How to Run

1. **Database Setup**:  
   Ensure PostgreSQL is installed and running. Update the connection details in `PostgresDB` as needed.
2. **Compile and Execute**:  
   Compile the project using your preferred IDE or build tool (e.g., Maven or Gradle) and run the `Main` class.
3. **User Interaction**:  
   The application runs in the console, presenting a menu-driven interface for users to register, login, view the menu, place orders, and reserve seats.

Enjoy managing your restaurant with this application!


