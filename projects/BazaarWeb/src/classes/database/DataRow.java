/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.database;

import java.util.Arrays;
import java.util.List;

/**
 *
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
     * 
     * @param columns
     * @param data 
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
     * 
     * @param column
     * @return 
     */
    public Object getData(String column)
    {
        return data.get(columns.indexOf(column.toUpperCase()));
    }
    
    public Object[] getDataFromColumns(String columnNames)
    {
        return getDataFromColumns(columnNames.split(","));
    }
    
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
