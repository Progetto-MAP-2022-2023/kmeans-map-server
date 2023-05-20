package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import database.TableSchema.Column;


public class TableData {

    private DbAccess db;



    public TableData(DbAccess db) {
        this.db=db;
    }

    public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException{
        TreeSet<Example> tempSet = new TreeSet<>();
        TableSchema schema = new TableSchema(this.db, table);
        Statement statement = this.db.getConnection().createStatement();

        String query = "SELECT * FROM " + table + ";";
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            Example example = new Example();
            for(int i = 0; i < schema.getNumberOfAttributes(); i++){
                if(schema.getColumn(i).isNumber()){
                    example.add(resultSet.getDouble(schema.getColumn(i).getColumnName()));
                }else{
                    example.add(resultSet.getString(schema.getColumn(i).getColumnName()));
                }
            }
            tempSet.add(example);
        }
        if(tempSet.isEmpty()){
            throw new EmptySetException("Empty set resulted by query " + query);
        }
        resultSet.close();
        statement.close();

        return new ArrayList<Example>(tempSet);
    }


    public  Set<Object>getDistinctColumnValues(String table, Column column) throws SQLException{
        TreeSet<Object> distinctValues = new TreeSet<Object>();
        Statement statement = db.getConnection().createStatement();
        String query = "SELECT DISTINCT " + column.getColumnName() + " FROM " + table + " ORDER BY " + column.getColumnName() + ";";
        ResultSet resultSet = statement.executeQuery(query);

        if(column.isNumber()){
            while(resultSet.next()){
                distinctValues.add(resultSet.getDouble(column.getColumnName()));
            }
        }else{
            while(resultSet.next()){
                distinctValues.add(resultSet.getString(column.getColumnName()));
            }
        }
        resultSet.close();
        statement.close();

        return distinctValues;
    }

    public  Object getAggregateColumnValue(String table,Column column,QUERY_TYPE aggregate) throws SQLException,NoValueException{
        Statement statement = db.getConnection().createStatement();
        Object valueToReturn;

        String query = "SELECT " + aggregate.toString() + "(" + column.getColumnName() + ") AS " + column.getColumnName() + " FROM " + table +";";
        ResultSet resultSet = statement.executeQuery(query);
        if(!resultSet.next()){
            throw new NoValueException("Error: result set empty.");
        }
        if(column.isNumber()){
            valueToReturn = resultSet.getDouble(column.getColumnName());
        }else{
            valueToReturn = resultSet.getString(column.getColumnName());
        }
        if(valueToReturn.equals(null)){
            throw new NoValueException("Error: the calculated value is null.");
        }

        resultSet.close();
        statement.close();

        return valueToReturn;
    }





}
