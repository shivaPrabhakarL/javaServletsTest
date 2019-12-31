package com.shivaPrabhakar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InMemoryTaskRepository implements  TaskRepository {
    Random rand = new Random();
    private List<TaskObj> task = new ArrayList<>();
    SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public boolean checkData(){
        return (task.size() > 0);
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

    @Override
    public TaskObj addTask(String name,String des,String date) throws ParseException {
        TaskObj to = new TaskObj();
        to.setName(name);
        to.setDesc(des);
        to.setId(rand.nextInt(10000));
        to.setDate(format.parse(date));
        task.add(to);
        return to;
    }

    @Override
    public TaskObj updateTask(String name, String updatedDesc){
        TaskObj obj = searchData(name);
        if(updatedDesc != null){
            obj.setDesc(updatedDesc);
            if(!(obj.getStatus().toString()).equalsIgnoreCase("done"))
                return  changeStatus(name,"inprogress");
            else
                return obj;
        }
        else{
            return null;
        }

    }

    @Override
    public TaskObj delete(String name){
        TaskObj obj = searchData(name);

        if(obj != null) {



            task.remove(obj);
            return obj;
        }

        return null;
    }

    @Override
    public TaskObj findById(Integer taskId){
        for (TaskObj obj : task) {
            int query = obj.getId();
            if (taskId == query) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public List<TaskObj> findAll(){
        if(task.size() > 0)
            return task;
        else
            return null;
    }

    @Override
    public List<TaskObj> findAllByStatus(String status){
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
    public   TaskObj searchData(String name) {
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
                for (TaskObj obj : task) {
                    int query = obj.getId();
                    if (query == Integer.parseInt(name)) {
                        return obj;
                    }
                }
            }
            else
                return null;
        }
        return null;
    }

    @Override
    public   TaskObj changeStatus(String name, String st) {
        TaskObj obj = searchData(name);
        if(obj != null) {
            if (st.equalsIgnoreCase("initial"))
                obj.setStatus(Status.INITIAL);
            if (st.equalsIgnoreCase("inprogress")) {
                obj.setStatus(Status.INPROGRESS);
                obj.setDate(new Date());
            }
            if (st.equalsIgnoreCase("done")) {
                obj.setStatus(Status.DONE);
                obj.setDate(new Date());
            }
        }
        return obj;
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
        Date n = new Date();
        for(TaskObj obj: task){
            if(obj.getDate() == n){
                todayTasks.add(obj);
            }
        }
        if(todayTasks.size()>0)
            return todayTasks;
        else
            return null;
    }
}
