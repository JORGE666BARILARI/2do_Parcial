
package com.emergentes.controlador;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String op;
            ArrayList<Seminario> lista =new ArrayList<Seminario>();
            ConexionDB canal=new ConexionDB();
            Connection conn=canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            int id;
            op= (request.getParameter("op")!=null)?request.getParameter("op"):"list";
            
            if(op.equals("list")){
                String sql ="select *from seminarios";
                try{
                    ps=conn.prepareStatement(sql);
                    rs=ps.executeQuery();
                    while(rs.next()){
                       Seminario sem =new Seminario();
                        sem.setId(rs.getInt("id"));
                        sem.setTitulo(rs.getString("titulo"));
                        sem.setExpositor(rs.getString("expositor"));
                        sem.setFecha(rs.getString("fecha"));
                        sem.setHoras(rs.getString("horas"));
                        sem.setCupos(rs.getInt("cupos"));
                        lista.add(sem);
                    }
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    }catch (SQLException ex){
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                
            }
            if(op.equals("nuevo")){
                Seminario se=new Seminario();
                request.setAttribute("sem", se);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if(op.equals("editar")){
                id =Integer.parseInt(request.getParameter("id"));
                try {
                    Seminario sem1=new Seminario();
                    ps=conn.prepareStatement("select *from seminarios where id=?");
                    ps.setInt(1,id);
                    rs=ps.executeQuery();
                    if(rs.next()){
                        sem1.setId(rs.getInt("id"));
                        sem1.setTitulo(rs.getString("titulo"));
                        sem1.setExpositor(rs.getString("expositor"));
                        sem1.setFecha(rs.getString("fecha"));
                        sem1.setHoras(rs.getString("horas"));
                        sem1.setCupos(rs.getInt("cupos"));
                        
                    }
                    request.setAttribute("sem", sem1);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(op.equals("eliminar")){
                id =Integer.parseInt(request.getParameter("id"));
               try{
                    ps=conn.prepareStatement("delete from seminarios where id= ?");
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    response.sendRedirect("MainController");
                }catch(SQLException ex){
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo=request.getParameter("titulo");
            String expositor=request.getParameter("expositor");
            String fecha=request.getParameter("fecha");
            String horas=request.getParameter("horas");
            int cupos = Integer.parseInt(request.getParameter("cupos"));
            
            Seminario sem =new Seminario();
            sem.setId(id);
            sem.setTitulo(titulo);
            sem.setExpositor(expositor);
            sem.setFecha(fecha);
            sem.setHoras(horas);
            sem.setCupos(cupos);
            
            ConexionDB canal= new ConexionDB();
            Connection conn=canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if(id==0){
                String sql="insert into seminarios(titulo,expositor,fecha,horas,cupos) values(?,?,?,?,?)";
                try{
                    ps=conn.prepareStatement(sql);
                    ps.setString(1, sem.getTitulo());
                    ps.setString(2,sem.getExpositor());
                    ps.setString(3,sem.getFecha());
                    ps.setString(4,sem.getHoras());
                    ps.setInt(5,sem.getCupos());
                    ps.executeUpdate();
                }catch(SQLException ex){
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                String sql1="update seminarios set titulo=?, expositor=?, fecha=?,horas=?, cupos=? where id=?";
                try {
                    ps=conn.prepareStatement(sql1);
                    ps.setString(1, sem.getTitulo());
                    ps.setString(2, sem.getExpositor());
                    ps.setString(3, sem.getFecha());
                    ps.setString(4, sem.getHoras());
                    ps.setInt(5, sem.getCupos());
                    ps.setInt(6, sem.getId());
                    ps.executeUpdate();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect("MainController");
    }

}
