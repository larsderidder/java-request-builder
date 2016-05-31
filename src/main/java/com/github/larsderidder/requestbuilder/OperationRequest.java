package com.github.larsderidder.requestbuilder;

/**
 * Base class for all operation requests. Provides factory methods for creating
 * strongly-typed request builders.
 *
 * @param <T> the entity type this request operates on
 */
public abstract class OperationRequest<T> {

    private final Class<T> entityType;

    /**
     * Default constructor for deserialization.
     */
    public OperationRequest() {
        entityType = null;
    }

    /**
     * Creates a new operation request for the given entity type.
     *
     * @param entityType the class of the entity
     */
    public OperationRequest(Class<T> entityType) {
        this.entityType = entityType;
    }

    /**
     * Gets the entity type this request operates on.
     *
     * @return the entity class
     */
    public Class<T> getEntityType() {
        return entityType;
    }

    /**
     * Creates a builder for create operations.
     *
     * @param <T> the entity type
     * @param entityType the class of the entity
     * @return a new create request builder
     */
    public static <T> CreateOperation.Request.Builder<T> create(Class<T> entityType) {
        return new CreateOperation.Request.Builder<>(entityType);
    }

    /**
     * Creates a builder for query operations.
     *
     * @param <T> the entity type
     * @param entityType the class of the entity
     * @return a new query request builder
     */
    public static <T> QueryOperation.Request.Builder<T> query(Class<T> entityType) {
        return new QueryOperation.Request.Builder<>(entityType);
    }

    /**
     * Creates a builder for update operations.
     *
     * @param <T> the entity type
     * @param entityType the class of the entity
     * @return a new update request builder
     */
    public static <T> UpdateOperation.Request.Builder<T> update(Class<T> entityType) {
        return new UpdateOperation.Request.Builder<>(entityType);
    }

    /**
     * Creates a builder for delete operations.
     *
     * @param <T> the entity type
     * @param entityType the class of the entity
     * @return a new delete request builder
     */
    public static <T> DeleteOperation.Request.Builder<T> delete(Class<T> entityType) {
        return new DeleteOperation.Request.Builder<>(entityType);
    }
}
