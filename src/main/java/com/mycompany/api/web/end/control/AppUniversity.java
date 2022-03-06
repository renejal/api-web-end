/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.web.end.control;
import com.mycompany.api.web.end.TbEstudiante;
import com.mycompany.api.web.end.TbEstudianteCursoJpaController;
import com.mycompany.api.web.end.TbEstudianteJpaController;
import com.mycompany.api.web.end.exceptions.IllegalOrphanException;
import com.mycompany.api.web.end.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bjalvin
 */
@WebServlet("/")
public class AppUniversity extends HttpServlet {
    private TbEstudianteJpaController estudianteJpa;
    private final static String PU = "com.mycompany_api-web-end_war_1.0-SNAPSHOTPU";
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void init() throws ServletException {
        super.init();
        //creamos una instancia de la clase EntityManagerFactory
        //esta clase se encarga de gestionar la construcción de entidades y
        //permite a los controladores JPA ejecutar las operaciones CRUD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        //creamos una instancia del controldor JPA para la clase clients y le
        //pasamos el gestor de entidades
        estudianteJpa = new TbEstudianteJpaController(emf);
        //esta parte es solamente para realizar la prueba:
        //listamos todos los clientes de la base de datos y los imprimimos enconsola
        List<TbEstudiante> listStudent = estudianteJpa.findTbEstudianteEntities();
        //imprimimos los clientes en consola
        for(TbEstudiante student: listStudent){
        System.out.println("Nombre "+student.getName()+ " last Name"+student.getLastName());
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NonexistentEntityException, IllegalOrphanException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new": //Muestra el formulario para crear un nuevo cliente
                showNewForm(request, response);
                break;
                case "/insert": //ejecuta la creación de un nuevo cliente en la
                //BD
                insertClient(request, response);
                break;
                case "/delete": //Ejecuta la eliminación de un cliente de la BD
                deleteClient(request, response);
                break;
                case "/edit": //Muestra el formulario para editar un cliente
                showEditForm(request, response);
                break;
                case "/update": //Ejecuta la edición de un cliente de la BD
                String a = request.getParameter("id");
                updateClient(request, response);
                break;
                default:
                listStudent(request, response);
                break;
                }
        }catch (SQLException ex) {
                throw new ServletException(ex);
                }
                
    }
     private void listStudent(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List< TbEstudiante> listStudent = estudianteJpa.findTbEstudianteEntities();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
        dispatcher.forward(request, response);
     }
     
     //muestra el formulario para crear un nuevo usuario
    private void showNewForm(HttpServletRequest request, HttpServletResponse
        response)
         throws ServletException, IOException {
         RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
         dispatcher.forward(request, response);
    }
    
    //muestra el formulario para editar un usuario
    private void showEditForm(HttpServletRequest request, HttpServletResponse
       response)
        throws SQLException, ServletException, IOException {
        //toma el id del cliente a ser editaro
        int id = Integer.parseInt(request.getParameter("id"));
        //busca al cliente en la base de datos
        TbEstudiante existingClient = estudianteJpa.findTbEstudiante(id);
        RequestDispatcher dispatcher = null;
        if (existingClient != null) {
        //si lo encuentra lo envía al formulario
        dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingClient);
        } else {
        //si no lo encuentra regresa a la página con la lista de los student
       dispatcher = request.getRequestDispatcher("list-student.jsp");
       }
       dispatcher.forward(request, response);
       }
    //método para crear un cliente en la base de datos
    private void insertClient(HttpServletRequest request, HttpServletResponse response)
     throws SQLException, IOException, IllegalOrphanException {

     //toma los datos del formulario de clientes
     String program = request.getParameter("program");
     String code = request.getParameter("code");
     String name = request.getParameter("name");     
     String last_name = request.getParameter("lastName");

     
    //crea un objeto de tipo student vacío y lo llena con los datosobtenidos
     TbEstudiante Student = new TbEstudiante();
     Student.setProgram(program);
     Student.setCode(code);
     Student.setName(name);
     Student.setLastName(last_name);
     //Crea el cliente utilizando el objeto controlador JPA
     estudianteJpa.create(Student);
     //solicita al Servlet que muestre la página actualizada con la lista de Student
     response.sendRedirect("list");
     }
    
    //Método para editar un cliente
   private void updateClient(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    //toma los datos enviados por el formulario de clientes.
    int id_student = Integer.parseInt(request.getParameter("id"));
    String code = request.getParameter("code");
    String name = request.getParameter("name");
    String last_name = request.getParameter("lastName");
    String program = request.getParameter("program");     
    //crea un objeto vacío y lo llena con los datos del cliente
    TbEstudiante Student = new TbEstudiante();
    Student.setId(id_student);
    Student.setCode(code);
    Student.setProgram(program);
    Student.setName(name);
    Student.setLastName(last_name);
    try {
    //Edita el cliente en la BD
    estudianteJpa.edit(Student);
    
    estudianteJpa.edit(Student);
    } catch (Exception ex) {

   Logger.getLogger(AppUniversity.class.getName()).log(Level.SEVERE, null, ex);
    }
    response.sendRedirect("list");
    }

    //Elimina un cliente de la BD
  private void deleteClient(HttpServletRequest request, HttpServletResponse response)
          throws SQLException, IOException, NonexistentEntityException {
    //Recibe el ID del cliente que se espera eliminar de la BD
    int id = Integer.parseInt(request.getParameter("id"));
    try {
    //Elimina el student con el id indicado
        estudianteJpa.destroy(id);
    } catch (NonexistentEntityException ex) {

   Logger.getLogger(AppUniversity.class.getName()).log(Level.SEVERE, null, ex);
    }
    response.sendRedirect("list");
    }
  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AppUniversity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(AppUniversity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AppUniversity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(AppUniversity.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
