/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bas
 */
public class DataTable {

    /**
     * Attributes
     */
    private ResultSet resultSet;

    /**
     * Constructors
     *
     */
    /**
     *
     * @param data
     */
    public DataTable(ResultSet data) {
        this.resultSet = data;
    }

    /**
     * Methods
     */
    /**
     * Gets the specified row from the table
     *
     * @param row The row of the row to fetch
     * @return The data in the row
     */
    public Object[] getRow(int row) {
        List<Object> data = new ArrayList();

        try {
            resultSet.absolute(row + 1);

            for (int i = 0; i < this.resultSet.getMetaData().getColumnCount(); i++) {
                data.add(this.resultSet.getObject(i + 1));
            }

        } catch (SQLException ex) {

        }

        return data.toArray();
    }

    public Object[] getRow(int row, String columns) {
        return getRow(row, columns.split(","));
    }

    public Object[] getRow(int row, String[] columns) {
        List<Object> data = new ArrayList();

        try {
            resultSet.absolute(row + 1);

            for (int i = 0; i < columns.length; i++) {
                data.add(resultSet.getObject(columns[i]));
            }

        } catch (SQLException ex) {

        }

        return data.toArray();
    }
    
    public Object[] getNextRow()
    {
        
        List<Object> data = new ArrayList();

        try {
            this.resultSet.next();
            for (int i = 0; i < this.resultSet.getMetaData().getColumnCount(); i++) {
                data.add(this.resultSet.getObject(i + 1));
            }

        } catch (SQLException ex) {

        }

        return data.toArray();
    }

    public Object getDataFromRow(int row, String column) {
        try {
            resultSet.absolute(row + 1);

            return resultSet.getObject(column);
        } catch (SQLException ex) {

        }

        return null;
    }
    
    public int getRowCount()
    {
        int amount = 0;
        try {
            int oldIndex = resultSet.getRow();
            
            resultSet.absolute(1);
            while(resultSet.next())
                amount++;
            
            resultSet.absolute(oldIndex);
        } catch (SQLException ex) {
            
        }
        
        return amount + 1;
    }
}
