/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepertoireWebAPI.Servlets;

import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.Mapping.Sql.AvailableDictionarySqlMapper;
import Repertoire.Shared.Mapping.Xml.AvailableDictionaryXmlMapper;
import Repertoire.Shared.Sql.RepertoireDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repertoire.Shared.Sql.SqlParameter;
import Repertoire.Shared.Sql.SqlHelper;
import Repertoire.Shared.Sql.SqlType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rndmorris
 */
@WebServlet(name = "AvailableDictionary", urlPatterns = {"/AvailableDictionary"})
public class AvailableDictionaryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<SqlParameter> sqlParams = BuildSqlParameters(request);
        ServletContext context = getServletContext();
        String dbUrl = context.getInitParameter("repertoireDBUrl");
        String dbUser = context.getInitParameter("repertoireDBUsername");
        String dbPass = context.getInitParameter("repertoireDBPassword");

        try
        {
            RepertoireDB db = new RepertoireDB(dbUrl);
            Connection connection = db.getConnection(dbUser, dbPass);
            
            ResultSet rs = SqlHelper.CallStoredProcedure(connection,"rprtr_AvailableDictionary_Select", sqlParams);
            
            AvailableDictionaryList list = new AvailableDictionarySqlMapper().MapEntityListFromResultSet(rs);
            String xml = new AvailableDictionaryXmlMapper().stringFromEntityList(list);
            try (PrintWriter out = response.getWriter()) {
                out.print(xml);
            }
            response.setContentType("text/xml;charset=UTF-8");
        }
        catch (UnsupportedOperationException | SQLException | JAXBException ex)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(AvailableDictionaryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private List<SqlParameter> BuildSqlParameters(HttpServletRequest request) throws UnsupportedEncodingException
    {
        List<SqlParameter> returnList = new ArrayList<>();
        String paramValue;
        
        // 0 indexed
        paramValue = request.getParameter("PageOffset");
        returnList.add(new SqlParameter
        (
                 SqlType.INTEGER
                ,(paramValue != null ? Integer.parseInt(paramValue) : 0)
                ,1
        ));
        paramValue = request.getParameter("PageSize");
        returnList.add(new SqlParameter
        (
                 SqlType.INTEGER
                ,(paramValue != null ? (
                        Integer.parseInt(paramValue) <= 101 ? Integer.parseInt(paramValue) : 100
                        ) : 25)
                ,2
        ));
        paramValue = request.getParameter("NameSearchTerm");
        returnList.add(new SqlParameter
        (
                 SqlType.VARCHAR
                ,(paramValue != null ? URLDecoder.decode(paramValue,"UTF-8") : "")
                ,3
        ));
        paramValue = request.getParameter("CreatorSearchTerm");
        returnList.add(new SqlParameter
        (
                 SqlType.VARCHAR
                ,(paramValue != null ? URLDecoder.decode(paramValue,"UTF-8") : "")
                ,4
        ));
        
        return returnList;
    }
}
