package com.github.larsderidder.requestbuilder;

/**
 * Represents a create operation request and response pair.
 */
public class CreateOperation {

    /**
     * Request to create a new entity.
     *
     * @param <T> the entity type
     */
    public static class Request<T> extends OperationRequest<T> {

        /**
         * Builder for constructing create requests.
         *
         * @param <BT> the entity type
         */
        public static class Builder<BT> {
            private BT entity;
            private final Class<BT> entityType;

            /**
             * Creates a new builder for the given entity type.
             *
             * @param entityType the class of the entity
             */
            public Builder(Class<BT> entityType) {
                this.entityType = entityType;
            }

            /**
             * Sets the entity to be created.
             *
             * @param entity the entity instance
             * @return this builder
             */
            public Builder<BT> entity(BT entity) {
                this.entity = entity;
                return this;
            }

            /**
             * Builds the create request.
             *
             * @return the constructed request
             */
            public Request<BT> build() {
                return new Request<>(entityType, entity);
            }
        }

        private final T entity;

        /**
         * Default constructor for deserialization.
         */
        public Request() {
            this.entity = null;
        }

        /**
         * Creates a new create request.
         *
         * @param entityType the class of the entity
         * @param entity the entity to create
         */
        public Request(Class<T> entityType, T entity) {
            super(entityType);
            this.entity = entity;
        }

        /**
         * Gets the entity to be created.
         *
         * @return the entity
         */
        public T getEntity() {
            return entity;
        }
    }

    /**
     * Response from a create operation.
     *
     * @param <T> the entity type
     */
    public static class Response<T> extends OperationResponse {

        private final T entity;

        /**
         * Default constructor for deserialization.
         */
        public Response() {
            this.entity = null;
        }

        /**
         * Creates a response with the given status.
         *
         * @param status the operation status
         */
        public Response(Status status) {
            super(status);
            this.entity = null;
        }

        /**
         * Creates a response with status and entity.
         *
         * @param status the operation status
         * @param entity the created entity
         */
        public Response(Status status, T entity) {
            super(status);
            this.entity = entity;
        }

        /**
         * Creates a response with status and message.
         *
         * @param status the operation status
         * @param message descriptive message
         */
        public Response(Status status, String message) {
            super(status, message);
            this.entity = null;
        }

        /**
         * Creates a response with status, entity, and message.
         *
         * @param status the operation status
         * @param entity the created entity
         * @param message descriptive message
         */
        public Response(Status status, T entity, String message) {
            super(status, message);
            this.entity = entity;
        }

        /**
         * Creates a response with status, entity, message, and metadata.
         *
         * @param status the operation status
         * @param entity the created entity
         * @param message descriptive message
         * @param metadata additional result metadata
         */
        public Response(Status status, T entity, String message, ResultMetadata metadata) {
            super(status, message, metadata);
            this.entity = entity;
        }

        /**
         * Gets the created entity.
         *
         * @return the entity, or null if creation failed
         */
        public T getEntity() {
            return entity;
        }
    }
}
