/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepertoireWebAPI.Servlets;

import Repertoire.Shared.EntityLists.AvailableDictionaryList;
import Repertoire.Shared.Mapping.Xml.AvailableDictionaryListXmlMapper;
import Repertoire.Shared.Sql.Queries.AvailableDictionarySQLQuery;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rndmorris
 */
@WebServlet(name = "AvailableDictionary", urlPatterns = {"/Dictionary/Available"})
public class AvailableDictionaryServlet extends BaseServlet {
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
        try
        {
            Connection connection = getSqlConnection();
            
            String pageOffset = request.getParameter("PageOffset");
            String pageSize = request.getParameter("PageSize");
            String searchTerms = request.getParameter("SearchTerms");
            
            AvailableDictionaryList list = AvailableDictionarySQLQuery.execute(
                    connection,
                    (pageOffset != null ? Integer.parseInt(pageOffset) : 0),
                    (pageSize != null ? Integer.parseInt(pageSize) : 10),
                    (searchTerms != null ? searchTerms.split(" ") : new String[0])
            );
            
            String xml = new AvailableDictionaryListXmlMapper().stringFromEntityList(list);
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
}
