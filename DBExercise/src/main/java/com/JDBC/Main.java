package com.JDBC;


import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Created by Анна on 02.08.2016.
 */

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/employees";

    Main(){}

    public static void main (String args []) throws SQLException {

        Main main = new Main();
        DBProcessor db = new DBProcessor();
        Connection conn= db.getConnection(URL,USERNAME,PASSWORD);
        main.enterUser(conn);
        main.showDatabase(conn);
        conn.close();

    }

    private void enterUser(Connection connection) throws SQLException{
        Scanner scanner=new Scanner(System.in);
        String fName, lName;
        do{
            System.out.println("Введите имя: ");
            fName=scanner.next();
            System.out.println("Введите фамилию: ");
            lName=scanner.next();
            putUserIntoDatabase(fName,lName,connection);

        }while (isAddUser());
    }

    private void putUserIntoDatabase(String fName, String lName, Connection connection) throws SQLException{
        PreparedStatement prepInsert;
        if (isUsernameCorrect(fName,lName)) {
            prepInsert = connection.prepareStatement(queryInsert());
            prepInsert.setString(1, fName);
            prepInsert.setString(2, lName);
            prepInsert.execute();
            prepInsert.close();
        } else {
            System.out.println("имя или фамилия введены некорректно, пользователь не будет добавлен в бд!");
        }
    }

    private String queryInsert(){
        return "INSERT INTO employees.users (first_name, last_name) VALUES (?, ?)";
    }

    private boolean isUsernameCorrect(String fName,String lName){
        if (patternString().matcher(fName).matches() && patternString().matcher(lName).matches()){
            return true;
        }else return false;
    }

    private Pattern patternString(){
        return Pattern.compile("([А-ЯЁ]{1}[a-яё]{0,21})|([A-Z]{1}[a-z]{0,43})");
    }

    private boolean isAddUser(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Добавить еще пользователя? ");
        String addUser=scanner.next().toLowerCase();

        if (isAddUserYes(addUser)){
            return true;
        }else if (isAddUserNo(addUser)){
            return false;
        }else return isAddUser();
    }

    private boolean isAddUserYes(String addUser){
        if(addUser.equals("y")||addUser.equals("yes")||addUser.equals("да")) {
            return true;
        } else return false;
    }

    private boolean isAddUserNo(String addUser){
        if(addUser.equals("n")||addUser.equals("no")||addUser.equals("нет")) {
            return true;
        }else return false;
    }

    private void showDatabase(Connection connection) throws SQLException{

        PreparedStatement prepSelect = connection.prepareStatement(querySelect());
        ResultSet resultSet = prepSelect.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("last_name") + " " + resultSet.getString("first_name"));
        }
        prepSelect.close();

    }

    private String querySelect(){
        return "SELECT * FROM employees.users";
    }

}

