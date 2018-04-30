/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepertoireWebAPI.Servlets;

import Repertoire.Shared.Sql.RepertoireDB;
import Repertoire.Shared.Sql.SqlHelper;
import Repertoire.Shared.Sql.SqlParameter;
import Repertoire.Shared.Sql.SqlType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rndmorris
 */
@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadDictionary"})
public class DictionaryDownloadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
        if (request.getParameter("versionId") != null) {
            try {
                List<SqlParameter> params = new ArrayList<>();
                params.add(
                        new SqlParameter(
                                SqlType.INTEGER,
                                Integer.parseInt(request.getParameter("versionId"))
                                , 1));
                
                StringBuilder query = new StringBuilder();
                query
                        .append("SELECT VisibilitySettingId FROM DictionaryDefinition JOIN DictionaryVersion ON  DictionaryDefinition.Id = DictionaryVersion.DictionaryDefinitionId WHERE  DictionaryVersion.Id = ? LIMIT 1;");
                
                ServletContext context = getServletContext();
                String dbUrl = context.getInitParameter("repertoireDBUrl");
                String dbUser = context.getInitParameter("repertoireDBUsername");
                String dbPass = context.getInitParameter("repertoireDBPassword");
                
                RepertoireDB db = new RepertoireDB(dbUrl);
                Connection conn = db.getConnection(dbUser, dbPass);
                
                ResultSet rs = SqlHelper.ExecuteQuery(conn,query.toString(),params);
                int visibilitySetting = -1;
                while (rs.next())
                {
                    visibilitySetting = rs.getInt("VisibilitySettingId");
                }
                if (visibilitySetting == -1) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                else {
                    ServletOutputStream writer;
                    try (InputStream file = getClass().getResourceAsStream("/deckVersions/deck.json")) {
                        writer = response.getOutputStream();
                        response.setContentType("application/octet-stream;charset=UTF-8");
                        response.setHeader("Content-Disposition", "attachment; filename=\"dictionary.json\"");
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = file.read(buffer)) != -1)
                        {
                            writer.write(buffer, 0, bytesRead);
                        }
                    }
                    writer.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DictionaryDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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

}
