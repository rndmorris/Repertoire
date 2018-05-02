/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepertoireWebAPI.Servlets;

import Repertoire.Shared.Sql.Queries.DictionaryVersionVisibilitySqlQuery;
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
@WebServlet(name = "DownloadServlet", urlPatterns = {"/Dictionary/Download"})
public class DictionaryDownloadServlet extends BaseServlet {

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
        String versionIdString = request.getParameter("VersionId");
        if (versionIdString == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else {
            try {
                int visibilitySetting = DictionaryVersionVisibilitySqlQuery.execute(
                        getSqlConnection(),
                        Integer.parseInt(versionIdString)
                );
                if (visibilitySetting == -1 || visibilitySetting == 2) {
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
            } catch (NumberFormatException ex) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
