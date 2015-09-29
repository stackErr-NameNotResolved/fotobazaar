/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.classes.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Bas
 */
public class DataTable implements Iterable<DataRow> {

    /**
     * Attributes
     */
    private ResultSet resultSet;

    /**
     * Constructors
     *
     */
    /**
     * Create a new DataTable object
     * @param data The ResultSet the DataTable object should read
     */
    public DataTable(ResultSet data) {
        this.resultSet = data;
    }

    /**
     * Methods
     */
    /**
     * Gets the specified row from the table
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

    /**
     * Checks if the DataTable contains any rows with data
     * @return Returns true if there is any is data found; otherwise false
     */
    public boolean containsData() {
        boolean result = false;
        try {
            int cursorPosition = resultSet.getRow();
            result = resultSet.first();
            resultSet.absolute(cursorPosition);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    /**
     * Gets a row from the DataTable with data from the specified columns
     * @param row The number of the row you want to read. Zero-based index
     * @param columns A String object with the names of the columns to read. Format (no spaces): COLUMN1,COLUMN2,COLUMN3...
     * @return An array of Object types containing the data read from the database
     */
    public Object[] getRow(int row, String columns) {
        return getRow(row, columns.split(","));
    }

    /**
     * Gets a row from the DataTable with data from the specified columns
     * @param row The number of the row you want to read. Zero-based index
     * @param columns An array of String objects with the names of the columns to read. One column per String object
     * @return An array of Object types containing the data read from the database
     */
    public Object[] getRow(int row, String[] columns) {
        List<Object> data = new ArrayList();

        try {
            resultSet.absolute(row + 1);

            for (int i = 0; i < columns.length; i++) {
                data.add(resultSet.getObject(columns[i].toUpperCase()));
            }

        } catch (SQLException ex) {

        }

        return data.toArray();
    }

    /**
     * Fetch a single row from the DataTable
     * @param row The number of the row to return. Zero-based index
     * @return Return a DataRow object containing the data from the row
     */
    private DataRow getDataRow(int row) {
        String[] columns = null;
        try {
            columns = new String[resultSet.getMetaData().getColumnCount()];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = resultSet.getMetaData().getColumnName(i + 1).toUpperCase();
            }
        } catch (Exception ex) {
        }

        return new DataRow(columns, getRow(row, columns));
    }

    /**
     * Gets the next row from the DataTable
     * @return A array of Objects with 
     */
    public Object[] getNextRow() {

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

            return resultSet.getObject(column.toUpperCase());
        } catch (SQLException ex) {

        }

        return null;
    }

    public int getRowCount() {
        int amount = 0;
        try {
            int oldIndex = resultSet.getRow();

            resultSet.absolute(1);
            while (resultSet.next()) {
                amount++;
            }

            resultSet.absolute(oldIndex);
        } catch (SQLException ex) {

        }

        return amount + 1;
    }

    @Override
    public Iterator<DataRow> iterator() {
        Iterator<DataRow> it = new Iterator<DataRow>() {

            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex < getRowCount() - 1;
            }

            @Override
            public DataRow next() {
                currentIndex++;
                return getDataRow(currentIndex);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
