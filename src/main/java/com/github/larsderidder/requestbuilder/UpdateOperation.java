package com.github.larsderidder.requestbuilder;

/**
 * Represents an update operation request and response pair.
 */
public class UpdateOperation {

    /**
     * Request to update an existing entity.
     *
     * @param <T> the entity type
     */
    public static class Request<T> extends OperationRequest<T> {

        /**
         * Builder for constructing update requests.
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
             * Sets the entity to be updated.
             *
             * @param entity the entity instance with updated values
             * @return this builder
             */
            public Builder<BT> entity(BT entity) {
                this.entity = entity;
                return this;
            }

            /**
             * Builds the update request.
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
            entity = null;
        }

        /**
         * Creates a new update request.
         *
         * @param entityType the class of the entity
         * @param entity the entity to update
         */
        public Request(Class<T> entityType, T entity) {
            super(entityType);
            this.entity = entity;
        }

        /**
         * Gets the entity to be updated.
         *
         * @return the entity
         */
        public T getEntity() {
            return entity;
        }
    }

    /**
     * Response from an update operation.
     *
     * @param <T> the entity type
     */
    public static class Response<T> extends OperationResponse {

        /**
         * Default constructor for deserialization.
         */
        public Response() {
            super();
        }

        /**
         * Creates a response with the given status.
         *
         * @param status the operation status
         */
        public Response(Status status) {
            super(status);
        }
    }
}
