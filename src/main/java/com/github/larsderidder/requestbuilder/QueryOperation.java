package com.github.larsderidder.requestbuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a query operation request and response pair.
 */
public class QueryOperation {

    /**
     * Response from a query operation containing a list of matching entities.
     *
     * @param <T> the entity type
     */
    public static class Response<T> extends OperationResponse {

        private final List<T> results;

        public Response() {
            super();
            results = new ArrayList<>();
        }

        public Response(Status status, List<T> results) {
            super(status);
            this.results = results != null ? results : new ArrayList<>();
        }

        public List<T> getResults() {
            return results;
        }

        public boolean isEmpty() {
            return results.isEmpty();
        }

        public int size() {
            return results.size();
        }
    }

    /**
     * Request to query entities by various identifiers.
     *
     * @param <T> the entity type
     */
    public static class Request<T> extends OperationRequest<T> {

        /**
         * Builder for constructing query requests.
         *
         * @param <BT> the entity type
         */
        public static class Builder<BT> {

            private final Class<BT> entityType;
            private String contextId;
            private String id;
            private String parentId;
            private String referenceId;

            public Builder(Class<BT> entityType) {
                this.entityType = entityType;
            }

            public Request<BT> build() {
                return new Request<>(entityType, id, parentId, referenceId, contextId);
            }

            public Builder<BT> contextId(String contextId) {
                this.contextId = contextId;
                return this;
            }

            public Builder<BT> id(String id) {
                this.id = id;
                return this;
            }

            public Builder<BT> parentId(String parentId) {
                this.parentId = parentId;
                return this;
            }

            public Builder<BT> referenceId(String referenceId) {
                this.referenceId = referenceId;
                return this;
            }

            public Builder<BT> withIdentifiers(String id, String parentId) {
                this.id = id;
                this.parentId = parentId;
                return this;
            }
        }

        private final String contextId;
        private final String id;
        private final String parentId;
        private final String referenceId;

        public Request() {
            contextId = null;
            id = null;
            parentId = null;
            referenceId = null;
        }

        public Request(Class<T> entityType, String id, String parentId,
                       String referenceId, String contextId) {
            super(entityType);
            this.contextId = contextId;
            this.id = id;
            this.parentId = parentId;
            this.referenceId = referenceId;
        }

        public String getContextId() {
            return contextId;
        }

        public String getId() {
            return id;
        }

        public String getParentId() {
            return parentId;
        }

        public String getReferenceId() {
            return referenceId;
        }

        public boolean hasContextId() {
            return contextId != null && !contextId.isEmpty();
        }

        public boolean hasId() {
            return id != null && !id.isEmpty();
        }
    }
}
