package servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivaPrabhakar.Task;
import com.shivaPrabhakar.TaskManager1;
import com.shivaPrabhakar.TaskObj;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


public class Servlets extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskManager1 tm = new TaskManager1();

        PrintWriter p = resp.getWriter();
        try {
            List<TaskObj> l = tm.findAll();
            String list = objectMapper.writeValueAsString(l);
            // JSONArray ar = new JSONArray(l);
            p.println(list);


        } catch (SQLException e) {
            String response = "Sql query not correct or no such data.";
            p.println(response);
        } catch (ParseException e) {
            String response = "Incorrect date format";
            p.println(response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter p = resp.getWriter();

//        String Name = req.getParameter("Name");
//        String Desc = req.getParameter("Desc");
//        String Date = req.getParameter("Date");
        //p.println(Name);
        //   System.out.println(Name);
        try {
            TaskManager1 tm = new TaskManager1();
            Task to1 = objectMapper.readValue(req.getInputStream(), Task.class);
            tm.addTask(to1.getName(), to1.getDesc(), String.valueOf(to1.getDate()));
            p.println(tm.searchData(to1.getName()));

        } catch (SQLException e) {
            String response = "Sql query not correct or no such data.";
            p.println(response);
        } catch (ParseException e) {
            String response = "Incorrect date format";
            p.println(response);

        }
        resp.setStatus(201);
    }

}
