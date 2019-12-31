package com.shivaPrabhakar;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TaskManager  {

    TaskRepository tr;
    public TaskManager() {
        System.out.println("Initializing TaskFileRepo");
       this.tr = new TaskFileRepostory();
    }

    public boolean checkData(){
        return tr.checkData();
    }


    public TaskObj addTask(String name, String des, String date) throws ParseException, SQLException {
        return tr.addTask(name, des, date);

    }


    public  List<TaskObj>  findAll() throws SQLException, ParseException {
        return tr.findAll();
    }


    public  TaskObj searchData(String name) throws SQLException, ParseException {
        return tr.searchData(name);
    }


    public  TaskObj delete(String name) throws SQLException, ParseException {
           return tr.delete(name);
    }


    public  List<TaskObj> findAllByStatus(String qq) throws SQLException, ParseException {
        return tr.findAllByStatus(qq);
    }


    public  TaskObj changeStatus(String name, String st) throws SQLException, ParseException {
        return tr.changeStatus(name, st);
    }


    public  TaskObj updateTask(String name, String updatedDesc) throws SQLException, ParseException {
        //System.out.println(updatedDesc);
        return tr.updateTask(name, updatedDesc);
    }

    public List<TaskObj> sortByDate() throws SQLException, ParseException {
        return tr.sortByDate();
    }

    public List<TaskObj> getPendingTasks() throws SQLException, ParseException {
        return tr.getPendingTasks();
    }

    public List<TaskObj> getTodayTask() throws ParseException, SQLException {
        return tr.getTodayTasks();
    }
}
