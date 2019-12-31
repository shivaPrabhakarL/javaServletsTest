package com.shivaPrabhakar;

//import java.util.ArrayList;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface TaskRepository {
    TaskObj addTask(String name,String des,String date) throws ParseException, SQLException;
    TaskObj updateTask(String name, String updatedDesc) throws SQLException, ParseException;
    TaskObj delete(String name) throws SQLException, ParseException;
    TaskObj findById(Integer taskId) throws SQLException, ParseException;
    List<TaskObj> findAll() throws SQLException, ParseException;
    List<TaskObj> findAllByStatus(String status) throws SQLException, ParseException;
    TaskObj searchData(String name) throws SQLException, ParseException;
    TaskObj changeStatus(String name, String status) throws SQLException, ParseException;
    boolean checkData();
    List<TaskObj> sortByDate() throws SQLException, ParseException;
    List<TaskObj> getPendingTasks() throws SQLException, ParseException;
    List<TaskObj> getTodayTasks() throws ParseException, SQLException;

}
