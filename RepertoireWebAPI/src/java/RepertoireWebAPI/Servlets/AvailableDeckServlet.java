/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepertoireWebAPI.Servlets;

import Repertoire.Shared.EntityLists.AvailableDeckList;
import Repertoire.Shared.Mapping.Sql.AvailableDeckSqlMapper;
import Repertoire.Shared.Mapping.Xml.AvailableDeckXmlMapper;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rndmorris
 */
@WebServlet(name = "AvailableDeck", urlPatterns = {"/AvailableDeck"})
public class AvailableDeckServlet extends HttpServlet {

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
        
        try
        {
            ResultSet rs = SqlHelper.CallStoredProcedure("rprtr_AvailableDeck_Select", sqlParams);
            AvailableDeckList list = new AvailableDeckSqlMapper().MapEntityListFromResultSet(rs);
            String xml = new AvailableDeckXmlMapper().stringFromEntityList(list);
            try (PrintWriter out = response.getWriter()) {
                out.print(xml);
            }
            response.setContentType("text/xml;charset=UTF-8");
        }
        catch (UnsupportedOperationException | SQLException | JAXBException ex)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Logger.getLogger(AvailableDeckServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private List<SqlParameter> BuildSqlParameters(HttpServletRequest request)
    {
        List<SqlParameter> returnList = new ArrayList<>();
        String paramValue;
        
        paramValue = request.getParameter("PageNumber");
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
                ,(paramValue != null ? Integer.parseInt(paramValue) : 25)
                ,2
        ));
        paramValue = request.getParameter("NameSearchTerm");
        returnList.add(new SqlParameter
        (
                 SqlType.VARCHAR
                ,(paramValue != null ? paramValue : "")
                ,3
        ));
        paramValue = request.getParameter("CreatorSearchTerm");
        returnList.add(new SqlParameter
        (
                 SqlType.VARCHAR
                ,(paramValue != null ? paramValue : "")
                ,4
        ));
        
        return returnList;
    }
}
