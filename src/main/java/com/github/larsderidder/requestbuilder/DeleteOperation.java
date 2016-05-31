package com.github.larsderidder.requestbuilder;

/**
 * Represents a delete operation request and response pair.
 */
public class DeleteOperation {

    /**
     * Request to delete an entity.
     *
     * @param <T> the entity type
     */
    public static class Request<T> extends OperationRequest<T> {

        /**
         * Builder for constructing delete requests.
         *
         * @param <BT> the entity type
         */
        public static class Builder<BT> {

            private final Class<BT> entityType;
            private String id;
            private String parentId;

            /**
             * Creates a new builder for the given entity type.
             *
             * @param entityType the class of the entity
             */
            public Builder(Class<BT> entityType) {
                this.entityType = entityType;
            }

            /**
             * Sets the primary identifier of the entity to delete.
             *
             * @param id the primary ID
             * @return this builder
             */
            public Builder<BT> id(String id) {
                this.id = id;
                return this;
            }

            /**
             * Sets the parent identifier for hierarchical deletes.
             *
             * @param parentId the parent ID
             * @return this builder
             */
            public Builder<BT> parentId(String parentId) {
                this.parentId = parentId;
                return this;
            }

            /**
             * Builds the delete request.
             *
             * @return the constructed request
             */
            public Request<BT> build() {
                return new Request<>(entityType, id, parentId);
            }
        }

        private final String id;
        private final String parentId;

        /**
         * Default constructor for deserialization.
         */
        public Request() {
            id = null;
            parentId = null;
        }

        /**
         * Creates a new delete request.
         *
         * @param entityType the class of the entity
         * @param id the primary identifier
         * @param parentId the parent identifier
         */
        public Request(Class<T> entityType, String id, String parentId) {
            super(entityType);
            this.id = id;
            this.parentId = parentId;
        }

        /**
         * Gets the primary identifier.
         *
         * @return the ID
         */
        public String getId() {
            return id;
        }

        /**
         * Gets the parent identifier.
         *
         * @return the parent ID
         */
        public String getParentId() {
            return parentId;
        }
    }

    /**
     * Response from a delete operation.
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

        /**
         * Creates a response with status and message.
         *
         * @param status the operation status
         * @param message descriptive message
         */
        public Response(Status status, String message) {
            super(status, message);
        }
    }
}
