# Java Request Builder

Type-safe fluent builder pattern for CRUD operations with generic request/response handling. This library provides a clean abstraction for building operation requests without coupling to specific storage or communication layers.

## Features

- **Type-safe builders** for Create, Read (Query), Update, and Delete operations
- **Generic** - works with any entity type
- **Fluent API** for readable request construction
- **Storage-agnostic** - no dependencies on databases or frameworks
- **Serialization-friendly** with default constructors for JSON/XML mapping
- **Zero dependencies** - pure Java implementation

## Installation

### Maven

```xml
<dependency>
    <groupId>com.github.larsderidder</groupId>
    <artifactId>request-builder</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```gradle
implementation 'com.github.larsderidder:request-builder:1.0.0'
```

## Usage

### Create Operation

```java
import com.github.larsderidder.requestbuilder.OperationRequest;
import com.github.larsderidder.requestbuilder.CreateOperation;

// Build a create request
User newUser = new User("john@example.com", "John Doe");
CreateOperation.Request<User> request = OperationRequest.create(User.class)
    .entity(newUser)
    .build();

// Handle the response
CreateOperation.Response<User> response = handler.handle(request);
if (response.isSuccess()) {
    User createdUser = response.getEntity();
    System.out.println("Created: " + createdUser.getId());
}
```

### Query Operation

```java
import com.github.larsderidder.requestbuilder.QueryOperation;

// Query by primary ID
QueryOperation.Request<User> request = OperationRequest.query(User.class)
    .id("user-123")
    .build();

// Query with multiple identifiers
QueryOperation.Request<Order> request = OperationRequest.query(Order.class)
    .parentId("customer-456")
    .contextId("2016-Q1")
    .build();

// Handle the response
QueryOperation.Response<User> response = handler.handle(request);
if (response.isSuccess()) {
    List<User> users = response.getResults();
    users.forEach(user -> System.out.println(user.getName()));
}
```

### Update Operation

```java
import com.github.larsderidder.requestbuilder.UpdateOperation;

// Build an update request
user.setEmail("newemail@example.com");
UpdateOperation.Request<User> request = OperationRequest.update(User.class)
    .entity(user)
    .build();

// Handle the response
UpdateOperation.Response<User> response = handler.handle(request);
if (response.isSuccess()) {
    System.out.println("Update successful");
} else {
    System.err.println("Update failed: " + response.getMessage());
}
```

### Delete Operation

```java
import com.github.larsderidder.requestbuilder.DeleteOperation;

// Delete by ID
DeleteOperation.Request<User> request = OperationRequest.delete(User.class)
    .id("user-123")
    .build();

// Delete with parent context
DeleteOperation.Request<Comment> request = OperationRequest.delete(Comment.class)
    .id("comment-789")
    .parentId("post-456")
    .build();

// Handle the response
DeleteOperation.Response<User> response = handler.handle(request);
if (response.isFailure()) {
    System.err.println("Delete failed: " + response.getMessage());
}
```

### Response Handling

All responses extend `OperationResponse` with consistent status handling:

```java
if (response.isSuccess()) {
    // Operation completed successfully
} else if (response.isFailure()) {
    // Operation failed
    String errorMessage = response.getMessage();
}

// Access optional metadata
ResultMetadata metadata = response.getMetadata();
```

### Custom Result Metadata

Extend `ResultMetadata` for operation-specific information:

```java
public class ValidationResult extends ResultMetadata {
    private final List<String> errors;

    public ValidationResult(List<String> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
```

## Architecture

The library follows a clean separation of concerns:

- **OperationRequest** - Base class for all requests with type information
- **OperationResponse** - Standard response envelope with success/failure status
- **Request Builders** - Fluent API for constructing type-safe requests
- **Operation Classes** - Nested Request/Response pairs for each CRUD operation

### Design Principles

1. **Immutability** - Request objects are immutable after building
2. **Type Safety** - Generics ensure compile-time type checking
3. **Builder Pattern** - Fluent API for readable request construction
4. **No Framework Coupling** - Pure Java with no external dependencies
5. **Serialization Support** - Default constructors for JSON/XML marshaling

## Integration Examples

### With REST APIs

```java
public class RequestHandler {
    private final RestTemplate restTemplate;

    public <T> CreateOperation.Response<T> handle(CreateOperation.Request<T> request) {
        String json = serialize(request);
        String responseJson = restTemplate.postForObject("/api/create", json, String.class);
        return deserialize(responseJson, CreateOperation.Response.class);
    }
}
```

### With Message Queues

```java
public class MessageQueueHandler {
    private final MessageProducer producer;

    public <T> void handle(OperationRequest<T> request) {
        String json = serialize(request);
        producer.send("operations-queue", json);
    }
}
```

### With Direct Data Access

```java
public class DatabaseHandler {
    private final EntityManager em;

    public <T> CreateOperation.Response<T> handle(CreateOperation.Request<T> request) {
        try {
            T entity = request.getEntity();
            em.persist(entity);
            return new CreateOperation.Response<>(Status.SUCCESS, entity);
        } catch (Exception e) {
            return new CreateOperation.Response<>(Status.FAILURE, e.getMessage());
        }
    }
}
```

## Requirements

- Java 8 or higher
- No additional runtime dependencies

## Development

### Building from Source

```bash
mvn clean install
```

### Running Tests

```bash
mvn test
```

### Generating Javadoc

```bash
mvn javadoc:javadoc
```

## License

MIT License - see [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.
