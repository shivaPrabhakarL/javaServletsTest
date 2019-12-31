package com.shivaPrabhakar;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskFileRepostory  implements TaskRepository {
    private static final String task_JSON_FILE = "/home/shivap/Desktop/task.json";
    Random rand = new Random();

    SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy");

    private ObjectMapper objectMapper = new ObjectMapper();
    final File file = new File(task_JSON_FILE);


    List<TaskObj> task;
    public TaskFileRepostory() {
        task = readFromFile();
    }


    // We can use FileWriter instead of FileOutputStream.
    public void writeToFile2(List<TaskObj> task) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new FileWriter(task_JSON_FILE), task);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<TaskObj> readFromFile() {

        if (file.exists()) {
            try {
                return objectMapper.readValue(file, TaskList.class);
            } catch (IOException e) {
                //System.out.println("No data to show.");
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public TaskObj addTask(String name, String des, String date) throws ParseException {
        TaskObj to = new TaskObj();
        to.setName(name);
        to.setDesc(des);
        to.setId(rand.nextInt(10000));
        to.setDate(format.parse(date));
        task.add(to);
        System.out.println();
        writeToFile2(task);
        return to;
    }

    @Override
    public TaskObj updateTask(String name, String updatedDesc) {
        TaskObj obj = searchData(name);
        System.out.println(obj);
        if(updatedDesc != null){
            obj.setDesc(updatedDesc);
            if(!(obj.getStatus().toString()).equalsIgnoreCase("done")) {
                writeToFile2(task);
                return changeStatus(name, "inprogress");
            }
            else
                return obj;
        }
        else{
            return null;
        }
    }

    @Override
    public TaskObj delete(String name) {

        TaskObj obj = searchData(name);
        if(obj != null) {

            task.remove(obj);
            writeToFile2(task);
            return obj;
        }

        return null;
    }

    @Override
    public TaskObj findById(Integer taskId) {
        for (TaskObj obj : task) {
            int query = obj.getId();
            if (taskId == query) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public List<TaskObj> findAll() {
        if(task.size() > 0)
            return task;
        else
            return null;
    }

    @Override
    public List<TaskObj> findAllByStatus(String status) {
        ArrayList<TaskObj> arr = new ArrayList<>();

        for (TaskObj obj : task) {
            String stat = obj.getStatus().toString();
            if (stat.equalsIgnoreCase(status)) {
                arr.add(obj);
            }
        }
        return arr;
    }

    @Override
    public TaskObj searchData(String name) {
        if(task.size()>0){
            if(!isNumeric(name)) {
                for (TaskObj obj : task) {
                    String query = obj.getName();
                    if (query.equalsIgnoreCase(name)) {
                        return obj;
                    }
                }
            }
            else if(isNumeric(name)){
                return findById(Integer.parseInt(name));
            }
            else
                return null;
        }
        return null;
    }

    @Override
    public TaskObj changeStatus(String name, String status) {
        TaskObj obj = searchData(name);
        if(obj != null) {
            if (status.equalsIgnoreCase("initial"))
                obj.setStatus(Status.INITIAL);
            if (status.equalsIgnoreCase("inprogress")) {
                obj.setStatus(Status.INPROGRESS);
                obj.setDate(new Date());
            }
            if (status.equalsIgnoreCase("done")) {
                obj.setStatus(Status.DONE);
                obj.setDate(new Date());
            }
            writeToFile2(task);
        }
        return obj;
    }

    @Override
    public boolean checkData() {
        return (task.size() > 0);
    }

    @Override
    public List<TaskObj> sortByDate() {
        task.sort(Comparator.comparing(TaskObj::getDate));
        return task;
    }

    @Override
    public List<TaskObj> getPendingTasks() {
        List<TaskObj> taskList = new ArrayList<>();
        for(TaskObj obj: task){
            if(!obj.getStatus().toString().equalsIgnoreCase("done"))
                taskList.add(obj);
        }
        return taskList;
    }

    @Override
    public List<TaskObj> getTodayTasks()throws ParseException {
        ArrayList<TaskObj> todayTasks = new ArrayList<>();
        String n =  format.format(new Date());
      //  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       // String ns = n.format(myFormatObj);
        System.out.println(n);
        for(TaskObj obj: task){
            Date od = obj.getDate();
            if(od.equals(format.parse(n))){
                todayTasks.add(obj);
            }
        }
        if(todayTasks.size()>0)
            return todayTasks;
        else
            return null;
    }

    private  boolean isNumeric(String q){
        try{
            Integer.parseInt(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}


