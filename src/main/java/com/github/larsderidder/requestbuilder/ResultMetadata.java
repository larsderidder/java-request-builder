package com.github.larsderidder.requestbuilder;

/**
 * Abstract base class for result metadata. Extend this class to provide
 * operation-specific result information beyond success/failure status.
 */
public abstract class ResultMetadata {

    protected final String description;

    /**
     * Default constructor for deserialization.
     */
    public ResultMetadata() {
        this.description = null;
    }

    /**
     * Creates metadata with a description.
     *
     * @param description textual description of the result
     */
    public ResultMetadata(String description) {
        this.description = description;
    }

    /**
     * Gets the result description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}
