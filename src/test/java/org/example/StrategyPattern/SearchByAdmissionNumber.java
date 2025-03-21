package org.example.StrategyPattern;

import org.example.StrategyPattern.SearchByAdmissionNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchByAdmissionNumberTest {

    @InjectMocks
    private SearchByAdmissionNumber searchByAdmissionNumber;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testSearch_Found() throws SQLException {
        // Mock result set behavior
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("NAME")).thenReturn("John Doe");
        when(mockResultSet.getString("ADMISSION_NUMBER")).thenReturn("A12345");
        when(mockResultSet.getInt("MARKS_PHYSICS")).thenReturn(85);
        when(mockResultSet.getInt("MARKS_CHEMISTRY")).thenReturn(90);
        when(mockResultSet.getInt("MARKS_MATHS")).thenReturn(88);

        // Execute search method
        searchByAdmissionNumber.search("A12345", "jdbc:mysql://localhost:3306/marklist", "root", "123456");

        // Verify interactions
        verify(mockPreparedStatement).setString(1, "A12345");
        verify(mockPreparedStatement).executeQuery();
    }

    @Test
    void testSearch_NotFound() throws SQLException {
        // Mock result set to return no results
        when(mockResultSet.next()).thenReturn(false);

        // Execute search method
        searchByAdmissionNumber.search("A99999", "jdbc:mysql://localhost:3306/marklist", "root", "123456");

        // Verify interactions
        verify(mockPreparedStatement).setString(1, "A99999");
        verify(mockPreparedStatement).executeQuery();
    }
}
