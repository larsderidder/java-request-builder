package com.github.larsderidder.requestbuilder;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for the request builder pattern.
 */
public class RequestBuilderTest {

    static class TestEntity {
        private String id;
        private String name;

        public TestEntity() {}

        public TestEntity(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @Test
    public void testCreateRequest() {
        TestEntity entity = new TestEntity("123", "Test");

        CreateOperation.Request<TestEntity> request = OperationRequest.create(TestEntity.class)
                .entity(entity)
                .build();

        assertNotNull(request);
        assertEquals(TestEntity.class, request.getEntityType());
        assertEquals(entity, request.getEntity());
    }

    @Test
    public void testCreateResponse() {
        TestEntity entity = new TestEntity("123", "Test");

        CreateOperation.Response<TestEntity> response = new CreateOperation.Response<>(
                OperationResponse.Status.SUCCESS, entity
        );

        assertTrue(response.isSuccess());
        assertFalse(response.isFailure());
        assertEquals(entity, response.getEntity());
    }

    @Test
    public void testQueryRequest() {
        QueryOperation.Request<TestEntity> request = OperationRequest.query(TestEntity.class)
                .id("123")
                .parentId("parent-456")
                .referenceId("ref-789")
                .contextId("ctx-abc")
                .build();

        assertNotNull(request);
        assertEquals(TestEntity.class, request.getEntityType());
        assertEquals("123", request.getId());
        assertEquals("parent-456", request.getParentId());
        assertEquals("ref-789", request.getReferenceId());
        assertEquals("ctx-abc", request.getContextId());
    }

    @Test
    public void testQueryResponse() {
        List<TestEntity> results = Arrays.asList(
                new TestEntity("1", "First"),
                new TestEntity("2", "Second")
        );

        QueryOperation.Response<TestEntity> response = new QueryOperation.Response<>(
                OperationResponse.Status.SUCCESS, results
        );

        assertTrue(response.isSuccess());
        assertEquals(2, response.getResults().size());
        assertEquals("First", response.getResults().get(0).getName());
    }

    @Test
    public void testUpdateRequest() {
        TestEntity entity = new TestEntity("123", "Updated");

        UpdateOperation.Request<TestEntity> request = OperationRequest.update(TestEntity.class)
                .entity(entity)
                .build();

        assertNotNull(request);
        assertEquals(TestEntity.class, request.getEntityType());
        assertEquals(entity, request.getEntity());
    }

    @Test
    public void testUpdateResponse() {
        UpdateOperation.Response<TestEntity> response = new UpdateOperation.Response<>(
                OperationResponse.Status.SUCCESS
        );

        assertTrue(response.isSuccess());
        assertNull(response.getMessage());
    }

    @Test
    public void testDeleteRequest() {
        DeleteOperation.Request<TestEntity> request = OperationRequest.delete(TestEntity.class)
                .id("123")
                .parentId("parent-456")
                .build();

        assertNotNull(request);
        assertEquals(TestEntity.class, request.getEntityType());
        assertEquals("123", request.getId());
        assertEquals("parent-456", request.getParentId());
    }

    @Test
    public void testDeleteResponse() {
        DeleteOperation.Response<TestEntity> response = new DeleteOperation.Response<>(
                OperationResponse.Status.FAILURE,
                "Entity not found"
        );

        assertTrue(response.isFailure());
        assertEquals("Entity not found", response.getMessage());
    }

    @Test
    public void testResponseWithMetadata() {
        ResultMetadata metadata = new ResultMetadata("Test metadata") {};

        CreateOperation.Response<TestEntity> response = new CreateOperation.Response<>(
                OperationResponse.Status.SUCCESS,
                new TestEntity("123", "Test"),
                "Operation completed",
                metadata
        );

        assertTrue(response.isSuccess());
        assertEquals("Operation completed", response.getMessage());
        assertEquals("Test metadata", response.getMetadata().getDescription());
    }

    @Test
    public void testBuilderFluentAPI() {
        // Test that builder methods return the builder for chaining
        QueryOperation.Request<TestEntity> request = OperationRequest.query(TestEntity.class)
                .id("123")
                .parentId("456")
                .referenceId("789")
                .contextId("abc")
                .build();

        // All fields should be set from the fluent chain
        assertEquals("123", request.getId());
        assertEquals("456", request.getParentId());
        assertEquals("789", request.getReferenceId());
        assertEquals("abc", request.getContextId());
    }
}
