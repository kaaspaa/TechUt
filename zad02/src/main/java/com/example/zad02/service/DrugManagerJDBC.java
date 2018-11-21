package com.example.zad02.service;

import com.example.zad02.domain.Drug;

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugManagerJDBC implements DrugManager {

    private Connection connection;

    private String url = "jdbc:hsqldb:hsql://localhost/workdb";

    private String createTableDrug = "CREATE TABLE Drug(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, prize double, yoe integer, isChildrenFriendly BOOLEAN)";

    private PreparedStatement addDrugStmt;
    private PreparedStatement deleteAllDrugsStmt;
    private PreparedStatement getAllDrugsStmt;
    private PreparedStatement deleteDrugStmt;

    private Statement statement;

    public DrugManagerJDBC() {
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()) {
                if ("Drug".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }

            if (!tableExists)
                statement.executeUpdate(createTableDrug);

            addDrugStmt = connection.prepareStatement("INSERT INTO Drug (name,prize,yoe,isChildrenFriendly) VALUES (?,?,?,?)");
            deleteAllDrugsStmt = connection.prepareStatement("DELETE FROM Drug");
            getAllDrugsStmt = connection.prepareStatement("SELECT name,prize,yoe,isChildrenFriendly FROM Drug");
            deleteDrugStmt = connection.prepareStatement("DELETE FROM Drug WHERE id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void clearDrugs() {
        try {
            deleteAllDrugsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addDrug(Drug drug) {
        int count = 0;
        try {
            addDrugStmt.setString(1, drug.getName());
            addDrugStmt.setDouble(2, drug.getPrize());
            addDrugStmt.setInt(3, drug.getYoe());
            addDrugStmt.setBoolean(4, drug.getIsChildrenFriendly() );

            count = addDrugStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void deleteDrug(Drug drug) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT id FROM Dog WHERE name=" + drug.getName());
            String deleteDrugSql = "DELETE FROM Dog WHERE id=" + resultSet.getLong("id");
            statement.executeUpdate(deleteDrugSql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Drug> getAllDrugs() {
        List<Drug> drugs = new ArrayList<Drug>();

        try {
            ResultSet rs = getAllDrugsStmt.executeQuery();

            while (rs.next()) {
                Drug d = new Drug(
                    rs.getString("name"),
                    rs.getDouble("prize"),
                    rs.getInt("yoe"),
                    rs.getBoolean("isChildrenFriendly"));
                drugs.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }

    @Override
    public void addAllDrugs(List<Drug> drugs) {

        try {
            connection.setAutoCommit(false);
            for (Drug drug : drugs) {
                addDrugStmt.setString(1, drug.getName());
                addDrugStmt.setDouble(2, drug.getPrize());
                addDrugStmt.setInt(3, drug.getYoe());
                addDrugStmt.setBoolean(4,drug.getIsChildrenFriendly());
                addDrugStmt.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                //!!!! ALARM
            }
        }
    }

    @Override
    public Drug removeDrug(Drug d) {
        try{
            deleteDrugStmt.setString(1,String.valueOf(d.getId()));
            deleteDrugStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public Drug findDrugById(long id){

        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Drug WHERE id=" + id);
            if(rs.next())
                return  getDrugById(rs);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Drug findDrugByName(String name){
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Drug WHERE name=" + name);
            if(rs.next())
                return  getDrugById(rs);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

     private Drug getDrugById(ResultSet rs) throws SQLException {
         return new Drug(
                 rs.getString(2),
                 rs.getDouble(3),
                 rs.getInt(4),
                 rs.getBoolean(5));
     }


}

