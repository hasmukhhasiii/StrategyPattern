package org.example.StrategyPattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StudentDataTest {

    @Mock
    private StudentSearchStrategy mockSearch;

    @InjectMocks
    private StudentSearchContext searchContext;

    private final String url = "jdbc:mysql://localhost:3306/marklist";
    private final String user = "root";
    private final String password = "123456";

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Ensures mocks are initialized
        searchContext = new StudentSearchContext(); // Manually initializing to avoid NullPointerException
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testSearchByAdmissionNumber() {
        String input = "1001\n"; // Simulating user input
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        searchContext.setSearchStrategy(mockSearch);
        searchContext.executeSearch("1001", url, user, password);

        verify(mockSearch, times(1)).search("1001", url, user, password);
    }

    @Test
    void testSearchByName() {
        String input = "ADAM\n"; // Simulating user input
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        searchContext.setSearchStrategy(mockSearch);
        searchContext.executeSearch("ADAM", url, user, password);

        verify(mockSearch, times(1)).search("ADAM", url, user, password);
    }
}
