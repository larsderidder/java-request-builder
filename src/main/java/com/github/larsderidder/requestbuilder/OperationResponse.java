package com.github.larsderidder.requestbuilder;

/**
 * Base class for all operation responses. Encapsulates success/failure state,
 * optional message, and optional result metadata.
 */
public class OperationResponse {

    /**
     * Enumeration of possible response types.
     */
    public enum Status {
        /** Operation completed successfully */
        SUCCESS,
        /** Operation failed */
        FAILURE
    }

    private Status status;
    private String message;
    private ResultMetadata metadata;

    /**
     * Default constructor for deserialization.
     */
    public OperationResponse() {
        this.status = null;
        this.message = null;
        this.metadata = null;
    }

    /**
     * Creates a response with the given status.
     *
     * @param status the operation status
     */
    public OperationResponse(Status status) {
        this.status = status;
        this.message = null;
        this.metadata = null;
    }

    /**
     * Creates a response with status and message.
     *
     * @param status the operation status
     * @param message descriptive message
     */
    public OperationResponse(Status status, String message) {
        this.status = status;
        this.message = message;
        this.metadata = null;
    }

    /**
     * Creates a response with status, message, and metadata.
     *
     * @param status the operation status
     * @param message descriptive message
     * @param metadata additional result metadata
     */
    public OperationResponse(Status status, String message, ResultMetadata metadata) {
        this.status = status;
        this.message = message;
        this.metadata = metadata;
    }

    /**
     * Gets the operation status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the operation status.
     *
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Checks if the operation was successful.
     *
     * @return true if status is SUCCESS
     */
    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    /**
     * Checks if the operation failed.
     *
     * @return true if status is FAILURE
     */
    public boolean isFailure() {
        return status == Status.FAILURE;
    }

    /**
     * Gets the descriptive message.
     *
     * @return the message, or null if not set
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the descriptive message.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the result metadata.
     *
     * @return the metadata, or null if not set
     */
    public ResultMetadata getMetadata() {
        return metadata;
    }

    /**
     * Sets the result metadata.
     *
     * @param metadata the metadata to set
     */
    public void setMetadata(ResultMetadata metadata) {
        this.metadata = metadata;
    }
}
