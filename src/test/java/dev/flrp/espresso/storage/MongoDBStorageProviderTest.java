package dev.flrp.espresso.storage;

import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.MongoDBStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MongoDBStorageProviderTest {

    @Container
    private static final MongoDBContainer container = new MongoDBContainer("mongo:6.0")
            .withExposedPorts(27017);

    private MongoDBStorageProvider provider;
    private final Logger logger = Logger.getLogger("TestLogger");
    private static final String COLLECTION_NAME = "test";

    @BeforeAll
    void setup() throws ProviderException {
        String host = container.getHost();
        int port = container.getMappedPort(27017);
        String database = "test";

        provider = new MongoDBStorageProvider(logger, host, port, database, StorageType.MONGODB);
        String connectionUri = "mongodb://" + host + ":" + port + "/" + database;
        provider.open(connectionUri);
        provider.createCollection(COLLECTION_NAME);
    }

    @AfterAll
    void cleanup() throws ProviderException {
        provider.dropCollection(COLLECTION_NAME);
        provider.close();
    }

    @BeforeEach
    void resetCollection() throws ProviderException {
        provider.deleteDocuments(COLLECTION_NAME, new HashMap<>());
    }

    @Test
    void testConnection() {
        assertTrue(provider.isConnected());
    }

    @Test
    void testAllDocumentOperations() throws ProviderException {
        // Insert
        Map<String, Object> employee = new HashMap<>();
        employee.put("id", 1);
        employee.put("name", "Espresso");
        employee.put("age", 20);
        employee.put("department", "IT");
        employee.put("created_at", System.currentTimeMillis());
        employee.put("updated_at", System.currentTimeMillis());

        provider.insertDocument(COLLECTION_NAME, employee);

        // Find
        List<Map<String, Object>> employees = provider.findDocuments(COLLECTION_NAME, new HashMap<>());
        assertEquals(1, employees.size());

        Map<String, Object> foundEmployee = employees.get(0);
        assertEquals(1, foundEmployee.get("id"));
        assertEquals("Espresso", foundEmployee.get("name"));
        assertEquals(20, foundEmployee.get("age"));
        assertEquals("IT", foundEmployee.get("department"));

        // Update
        Map<String, Object> updateQuery = new HashMap<>();
        updateQuery.put("id", 1);

        Map<String, Object> updateData = new HashMap<>();
        updateData.put("age", 22);
        updateData.put("department", "Sales");
        updateData.put("updated_at", System.currentTimeMillis());

        provider.updateDocuments(COLLECTION_NAME, updateQuery, updateData);

        // Find updated
        List<Map<String, Object>> updatedEmployees = provider.findDocuments(COLLECTION_NAME, updateQuery);
        assertEquals(1, updatedEmployees.size());

        Map<String, Object> updatedEmployee = updatedEmployees.get(0);
        assertEquals(1, updatedEmployee.get("id"));
        assertEquals("Espresso", updatedEmployee.get("name"));
        assertEquals(22, updatedEmployee.get("age"));
        assertEquals("Sales", updatedEmployee.get("department"));

        // Find with condition
        Map<String, Object> ageQuery = new HashMap<>();
        ageQuery.put("age", 22);
        List<Map<String, Object>> ageFilteredEmployees = provider.findDocuments(COLLECTION_NAME, ageQuery);
        assertEquals(1, ageFilteredEmployees.size());
        assertEquals("Espresso", ageFilteredEmployees.get(0).get("name"));

        // Delete
        provider.deleteDocuments(COLLECTION_NAME, updateQuery);

        // Verify empty
        List<Map<String, Object>> clearedEmployees = provider.findDocuments(COLLECTION_NAME, new HashMap<>());
        assertTrue(clearedEmployees.isEmpty());
    }

    @Test
    void testMultipleDocuments() throws ProviderException {
        // Insert multiple documents
        List<Map<String, Object>> employees = new ArrayList<>();

        Map<String, Object> employee1 = new HashMap<>();
        employee1.put("id", 1);
        employee1.put("name", "Espresso");
        employee1.put("age", 20);
        employee1.put("department", "IT");
        employee1.put("created_at", System.currentTimeMillis());
        employee1.put("updated_at", System.currentTimeMillis());

        Map<String, Object> employee2 = new HashMap<>();
        employee2.put("id", 2);
        employee2.put("name", "Java");
        employee2.put("age", 25);
        employee2.put("department", "HR");
        employee2.put("created_at", System.currentTimeMillis());
        employee2.put("updated_at", System.currentTimeMillis());

        Map<String, Object> employee3 = new HashMap<>();
        employee3.put("id", 3);
        employee3.put("name", "Kotlin");
        employee3.put("age", 30);
        employee3.put("department", "Sales");
        employee3.put("created_at", System.currentTimeMillis());
        employee3.put("updated_at", System.currentTimeMillis());

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        for (Map<String, Object> emp : employees) {
            provider.insertDocument(COLLECTION_NAME, emp);
        }

        // Find all
        List<Map<String, Object>> allEmployees = provider.findDocuments(COLLECTION_NAME, new HashMap<>());
        assertEquals(3, allEmployees.size());

        // Find by department
        Map<String, Object> itQuery = new HashMap<>();
        itQuery.put("department", "IT");
        List<Map<String, Object>> itEmployees = provider.findDocuments(COLLECTION_NAME, itQuery);
        assertEquals(1, itEmployees.size());
        assertEquals("Espresso", itEmployees.get(0).get("name"));

        // Update all IT employees
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("department", "Engineering");
        updateData.put("updated_at", System.currentTimeMillis());
        provider.updateDocuments(COLLECTION_NAME, itQuery, updateData);

        // Verify update
        Map<String, Object> engineeringQuery = new HashMap<>();
        engineeringQuery.put("department", "Engineering");
        List<Map<String, Object>> engineeringEmployees = provider.findDocuments(COLLECTION_NAME, engineeringQuery);
        assertEquals(1, engineeringEmployees.size());
        assertEquals("Espresso", engineeringEmployees.get(0).get("name"));

        // Delete by age condition
        Map<String, Object> ageQuery = new HashMap<>();
        ageQuery.put("age", 25);
        provider.deleteDocuments(COLLECTION_NAME, ageQuery);

        // Verify deletion
        List<Map<String, Object>> remainingEmployees = provider.findDocuments(COLLECTION_NAME, new HashMap<>());
        assertEquals(2, remainingEmployees.size());
    }

    @Test
    void testCollectionOperations() throws ProviderException {
        String testCollection = "test_collection";

        // Create collection
        provider.createCollection(testCollection);

        // Insert document
        Map<String, Object> document = new HashMap<>();
        document.put("test", "value");
        provider.insertDocument(testCollection, document);

        // Verify document exists
        List<Map<String, Object>> documents = provider.findDocuments(testCollection, new HashMap<>());
        assertEquals(1, documents.size());
        assertEquals("value", documents.get(0).get("test"));

        // Drop collection
        provider.dropCollection(testCollection);

        // Verify collection is dropped (should throw exception or return empty)
        try {
            List<Map<String, Object>> emptyDocuments = provider.findDocuments(testCollection, new HashMap<>());
            assertTrue(emptyDocuments.isEmpty());
        } catch (Exception e) {
            // Expected behavior when collection doesn't exist
        }
    }

}
