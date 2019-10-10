package trivera.security.web;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Kim Morello, Worldwide
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2018 Kim Morello
 * </p>
 * @author Kim Morello.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trivera.security.data.ConnectionFactory;
import trivera.security.data.User;

public class UserQueryController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    String username = (String) request.getParameter("username");

    Connection conn = ConnectionFactory.getConnection();
    if (conn == null) {
      response.sendRedirect("index.html");
      return;
    }

    ResultSet rs = null;
    Statement sqlStatement = null;
    try {
      sqlStatement = conn.createStatement();
      String sql = "select * from MEMBER WHERE USERNAME = '" + username + "'";
      System.out.println(sql);
      rs = sqlStatement.executeQuery(sql);
      while (rs.next()) {
        // Setup the empty bean
        User user = new User();

        // Populate the bean with the request parameters
        user.setUserName(rs.getString("USERNAME"));
        user.setFullName(rs.getString("FULLNAME"));
        user.setStreet(rs.getString("STREET"));
        user.setCity(rs.getString("CITY"));
        user.setZipcode(rs.getString("ZIPCODE"));
        user.setCcn(rs.getString("CCN"));

        // Add the bean to the request
        request.setAttribute("user", user);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      response.sendRedirect("index.html");
      return;
    } finally {
      Statement[] statements = new Statement[1];
      statements[0] = sqlStatement;
      ConnectionFactory.closeConnection(conn, statements);
    }

    request.getRequestDispatcher("JSPs/UserResults.jsp").forward(request,
        response);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    doPost(request, response);
  }

    /** ***************************************************************** */
    /** ***********************Utilities********************************* */
    /** ***************************************************************** */

    private void genBadIdPage(String message, HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
      request.setAttribute("message", message);
      request.getRequestDispatcher("ErrorHandler").forward(request, response);
    }

}
