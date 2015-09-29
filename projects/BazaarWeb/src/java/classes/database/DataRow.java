/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.classes.database;

import java.util.Arrays;
import java.util.List;

/**
 * Contains data from a readed row from the database
 * @author Bas
 */
public class DataRow {
    
    /**
     * Attributes
     */
    private List<String> columns;
    private List<Object> data;
    
    /**
     * Constructor
     */
    
    /**
     * Create a new DataRow object
     * @param columns The names of the columns in the DataRow
     * @param data The data in the DataRow
     */
    public DataRow(String[] columns, Object[] data)
    {
        if(columns.length != data.length)
        {
            throw new IllegalArgumentException("Column count is not equal to the data count");
        }
        
        this.columns = Arrays.asList(columns);
        this.data = Arrays.asList(data);
    }
    
    /**
     * Methods
     */
    
    /**
     * Get data from the specified column
     * @param column The name of the column of the data to get
     * @return The data found in the column
     */
    public Object getData(String column)
    {
        return data.get(columns.indexOf(column.toUpperCase()));
    }
    
    /**
     * Get data from multiple specified columns
     * @param columnNames A String object with the names of the columns to read. Format (no spaces): COLUMN1,COLUMN2,COLUMN3...
     * @return The data found in the specified rows
     */
    public Object[] getDataFromColumns(String columnNames)
    {
        return getDataFromColumns(columnNames.split(","));
    }
    
    /**
     * Get data from multiple specified columns
     * @param columnNames An array of String objects with the names of the columns to read. One column per String object
     * @return The data found in the specified rows
     */
    public Object[] getDataFromColumns(String[] columnNames)
    {
        Object[] output = new Object[columnNames.length];
        for(int i = 0; i<columnNames.length; i++)
        {
            output[i] = getData(columnNames[i].toUpperCase());
        }
        
        return output;
    }
}
