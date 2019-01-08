package com.mateuszwiorek.budget;

import javax.swing.*;
import java.sql.*;
import java.util.List;


public class BudgetDAO {

    String data = "jdbc:mysql://localhost:3306/budget";
    ResultSet rec;

    MoneyList moneyList= new MoneyList();


    public void addToDatabase(String name, int value, Date date, String tableType){
        try(Connection conn = DriverManager.getConnection(data,"root","")){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO "+tableType+"(name,value,date,user)"+"VALUES(?,?,?,?)");
            ps.setString(1,name);
            ps.setInt(2,value);
            ps.setDate(3,date);
            ps.setString(4,Users.currentLoggedUser);
            ps.executeUpdate();
            ps.close();
            conn.close();
    }catch (SQLException sqe){
            sqe.getMessage();
        }
    }
    public void delete(String tableType, int idToDelete){
        try(Connection conn = DriverManager.getConnection(data, "root", "")){
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM "+tableType+" WHERE id="+idToDelete);
            st.close();
            conn.close();
        }catch (SQLException sqe){
            sqe.getMessage();
        }
    }

    public void editRow(Money money){
        int editedValue = money.getValue();
        String editedName = money.getName();
        int idToEdit = money.getId();
        try(
                Connection conn = DriverManager.getConnection(data,"root", "")){
                String query = "UPDATE " +money.getMoneyType()+ " set name = '"+ editedName+"' , value ="+editedValue+" WHERE id="+idToEdit;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated");
            ps.close();
            conn.close();

        }catch (SQLException sqe){
            sqe.getMessage();
        }

    }

    public final List<Money> viewAll(){
        try (
                Connection conn = DriverManager.getConnection(data, "root", "");
                Statement st = conn.createStatement() ) {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            rec = st.executeQuery("SELECT * FROM expenses WHERE expenses.USER='"+Users.currentLoggedUser+"'");
            saveMoneyResultsAsList("expenses");
            rec = st.executeQuery("SELECT * FROM incomes WHERE incomes.USER='"+Users.currentLoggedUser+"'");
            saveMoneyResultsAsList("incomes");
            st.close();
            return moneyList.moneyList;
        } catch (SQLException sqe){
            System.out.println(sqe.getMessage());
        }
        return null;
    }

    public final List<Money> viewAllOrderedByDate(Date dateStart, Date dateEnd){
        try(
                Connection conn = DriverManager.getConnection(data,"root","");
                Statement st = conn.createStatement()){
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            rec = st.executeQuery("SELECT * FROM expenses WHERE user='"+Users.currentLoggedUser+"'AND date BETWEEN '"+changer(dateStart)+"' AND '"+changer(dateEnd)+"'");
            saveMoneyResultsAsList("expenses");
            rec = st.executeQuery("SELECT * FROM incomes WHERE user='"+Users.currentLoggedUser+"'AND date BETWEEN '"+changer(dateStart)+"' AND '"+changer(dateEnd)+"'");
            saveMoneyResultsAsList("incomes");
            st.close();
            return moneyList.moneyList;
        } catch (SQLException sqe){
            System.out.println(sqe.getMessage());
        }
        return null;
    }
    public final List<Money> viewAll(String type){
        try(
                Connection conn = DriverManager.getConnection(data, "root", "");
                Statement st = conn.createStatement()){
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            rec = st.executeQuery("SELECT * FROM "+type+" WHERE USER='"+Users.currentLoggedUser+"' ORDER BY date");
            saveMoneyResultsAsList(type);
            st.close();
            return moneyList.moneyList;
        } catch (SQLException sqe){
            System.out.println(sqe.getMessage());
        }
        return null;
    }
    public final List<Money> searchByName(String type,String username){
        try (
                Connection conn = DriverManager.getConnection(data, "root", "");
                Statement st = conn.createStatement()) {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            rec = st.executeQuery("SELECT * FROM "+type+" WHERE user='"+Users.currentLoggedUser+ "' AND name LIKE'%" +username + "%' ORDER BY date" );
            saveMoneyResultsAsList(type);
            st.close();
            return moneyList.moneyList;
        } catch (SQLException sqe){
            System.out.println(sqe.getMessage());
        }
        return null;
    }
    public final List<Money> searchByValue(String type, int value) {
        try (
                Connection conn = DriverManager.getConnection(data, "root", "");
                Statement st = conn.createStatement()) {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            rec = st.executeQuery("SELECT * FROM "+type+" WHERE user='"+Users.currentLoggedUser+ "' AND value='" +value+ "'ORDER BY date" );
            saveMoneyResultsAsList(type);
            st.close();
            return moneyList.moneyList;
        } catch (SQLException sqe){
            System.out.println(sqe.getMessage());
        }
        return null;
    }

    public final List<Money> searchByDate(String type, Date date){
        try(
                Connection conn = DriverManager.getConnection(data, "root", "");
                Statement st = conn.createStatement()){
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            rec = st.executeQuery("SELECT * FROM "+type+" WHERE user='"+ Users.currentLoggedUser + "' AND DATE='"+date+"' ORDER BY date");
            saveMoneyResultsAsList(type);
            st.close();
            System.out.println(moneyList.moneyList);
            return moneyList.moneyList;
        }catch (SQLException sqe){
            System.out.println(sqe.getMessage());
        }
        return null;
    }

    private void saveMoneyResultsAsList(String type) throws SQLException {
        while(rec.next()){
            Money money = new Money();
            money.setId(Integer.parseInt(rec.getString(1)));
            money.setName(rec.getString(2));
            money.setValue(Integer.parseInt(rec.getString(3)));
            money.setDate(Date.valueOf(rec.getString(4)));
            money.setUser(rec.getString(5));
            money.setMoneyType(type);
            moneyList.addtToMoneyList(money);
        }
    }

    private String changer(Date data){
        String datatemp=data.toString();
        StringBuilder temp = new StringBuilder("");
        temp.append(datatemp.substring(0,4));
        temp.append(datatemp.substring(5,7));
        temp.append(datatemp.substring(8,10));
        datatemp=temp.toString();
        return datatemp;
    }

}
