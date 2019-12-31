package com.shivaPrabhakar;
import java.util.Date;
import java.util.List;

public class TaskObj{


    TaskObj(){
        this.setStatus(Status.INITIAL);
    }
    private String name,desc;
    Status status;
    private int id;
    private Date date;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  ("id : "+id+
                " name : " + name +
                " desc : " + desc +
                " status : " + status +
                " date : " + date )
                ;
    }




}
