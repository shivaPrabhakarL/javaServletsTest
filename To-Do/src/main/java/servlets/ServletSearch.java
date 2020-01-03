package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shivaPrabhakar.Task;
import com.shivaPrabhakar.TaskManager1;
import com.shivaPrabhakar.TaskObj;

//import javax.servlet.ServletException;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

public class ServletSearch extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskManager1 tm = new TaskManager1();
        PrintWriter p = resp.getWriter();
        String s = req.getParameter("id");
        //Task to1 =  objectMapper.readValue(req.getInputStream(),Task.class);
        try {
            TaskObj to = tm.searchData(s);
            p.println(to);
        } catch (SQLException e) {
            String response = "Sql query not correct or no such data.";
            p.println(response);
        } catch (ParseException e) {
            String response = "Incorrect date format";
            p.println(response);

        }
        //   String response = to.toString();


        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskManager1 tm = new TaskManager1();
        PrintWriter p = resp.getWriter();
        try {
            // String s =  req.getParameter("id");
            Task to1 = objectMapper.readValue(req.getInputStream(), Task.class);
            TaskObj to;
            String s = to1.getName();
            if (s == null || s.equals(""))
                to = tm.delete(to1.getId());
            else
                to = tm.delete(to1.getName());
            //  String response = to.toString();
            p.println(to.toString());


        } catch (SQLException e) {
            String response = "Sql query not correct or no such data.";
            p.println(response);
        } catch (ParseException e) {
            String response = "Incorrect date format";
            p.println(response);

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskManager1 tm = new TaskManager1();
        PrintWriter p = resp.getWriter();
        try {
            Task to1 = objectMapper.readValue(req.getInputStream(), Task.class);
            //String s =  req.getParameter("id");
            // String d =  req.getParameter("Description");
            TaskObj to = tm.updateTask(to1.getId(), to1.getDesc());
            p.println(to.toString());

        } catch (SQLException e) {
            String response = "Sql query not correct or no such data.";
            p.println(response);
        } catch (ParseException e) {
            String response = "Incorrect date format";
            p.println(response);

        }
    }
}
