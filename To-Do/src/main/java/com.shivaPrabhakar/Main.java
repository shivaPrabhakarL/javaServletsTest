package com.shivaPrabhakar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    protected static String menu ()throws IOException{
        System.out.println("\nenter function");
        System.out.println("Add,  List,  Search,  Delete,  Quit, ListByStatus, ChangeStatus, updateDescription, sortbydate, getPendingTasks, getTodayTasks");
        return br.readLine();
    }

    public static void main(String[] args) throws IOException, ParseException, SQLException {

        TaskManager1 tm = new TaskManager1();
        while (true) {
            String s = menu();

            if (s.equalsIgnoreCase("Add")) {

                System.out.println("enter task name");
                String name = br.readLine();
                if(name.equals("")){
                    while(name.equals("")) {
                        System.out.println("enter task name");
                        name = br.readLine();
                    }
                }

                System.out.println("enter description");
                String des = br.readLine();

                System.out.println("enter task date in yyyy-MM-dd format");
                String date = br.readLine();
                if(date.equals("")){
                    while(date.equals("")) {
                        System.out.println("enter task date in yyyy-MM-dd format");
                        date = br.readLine();
                    }
                }
                System.out.println(tm.addTask(name,des,date));
            }

            if (s.equalsIgnoreCase("list") ) {
               // tm.findAll();
                if(tm.checkData()) {
                    List<TaskObj> list = tm.findAll();
                    if (list != null)
                        System.out.println(tm.findAll());
                    else
                        System.out.println("No data to show.");
                }
                else
                    System.out.println("No data to show.");
            }

            if (s.equalsIgnoreCase("search") ) {
                if(tm.checkData()) {
                    System.out.println("enter task name or id");
                    String nam = br.readLine();
                    TaskObj taskobject = tm.searchData(nam);
                    if (taskobject != null)
                        System.out.println(taskobject);
                    else
                        System.out.println("Task not found");
                }
                else
                    System.out.println("No data to show");
            }

            if (s.equalsIgnoreCase("delete")) {
                if(tm.checkData()) {
                    System.out.println("enter task name or number");
                    String nam = br.readLine();
                    TaskObj obj = tm.delete(nam);
                    if(null == obj){
                        System.out.println("Task is not present to delete");
                    }
                }
                else
                    System.out.println("No data to show");
            }

            if(s.equalsIgnoreCase("ListByStatus")) {
                if(tm.checkData()) {
                    System.out.println("\nenter a status code to filter");
                    String qq = br.readLine();
                    List<TaskObj> taskList = tm.findAllByStatus(qq);
                    if (taskList != null)
                        System.out.println(taskList);
                    else
                        System.out.println("No tasks with status" + qq + " to show.");
                }
                else
                    System.out.println("No data to show");
            }

            if(s.equalsIgnoreCase("changestatus")) {
                if(tm.checkData()) {
                    System.out.println("enter task name or number");
                    String nam = br.readLine();
                    System.out.println("enter status (INITIAL | INPROGRESS | DONE)");
                    String st = br.readLine();
                    TaskObj taskobject = tm.changeStatus(nam, st);
                    if (taskobject != null)
                        System.out.println(taskobject);
                    else
                        System.out.println("Task is not present to change the status.");
                }
                else
                    System.out.println("No data to show");
            }

            if(s.equalsIgnoreCase("updateDescription")){
                if(tm.checkData()) {
                    System.out.println("enter task name or number");
                    String nam = br.readLine();
                    System.out.println("enter description");
                    String desc = br.readLine();
                    TaskObj taskObject = tm.updateTask(nam, desc);
                    if (taskObject != null)
                        System.out.println(taskObject);
                    else
                        System.out.println("Task is not present to change the description");
                }
                else
                    System.out.println("No data to show");
            }

            if(s.equalsIgnoreCase("sortbydate")){
                List<TaskObj> tsk =  tm.sortByDate();
                System.out.println(tsk);
            }

            if(s.equalsIgnoreCase("getPendingTasks")){
                System.out.println(tm.getPendingTasks());
            }

            if(s.equalsIgnoreCase("getTodayTasks")){
                System.out.println(tm.getTodayTask());
            }

            if (s.equalsIgnoreCase("Quit"))
                System.exit(0);
        }
    }
}
