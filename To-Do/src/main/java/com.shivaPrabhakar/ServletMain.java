package com.shivaPrabhakar;

//import com.zetcode.util.JsonConverter;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ServletMain extends HttpServlet {

    public ServletMain(){}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager1 tm = new TaskManager1();

        PrintWriter p = resp.getWriter();
        try {
            List<TaskObj> l = tm.findAll();
            JSONArray ar = new JSONArray(l);

            p.println(ar);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
