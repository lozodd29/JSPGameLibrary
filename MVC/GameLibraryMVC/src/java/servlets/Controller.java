package servlets;

import datastore.DAOSQLite;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Game;

/**
 * All of this application's web pages send their requests to this controller
 * which then updates the model / database as needed and transfers control with
 * data to one the the HTML/JSP view-oriented programs for display.
 *
 * @author Dylan Lozo
 */
public class Controller extends HttpServlet {

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

        // get real path to the sqlite db
        ServletContext sc = this.getServletContext();
        String dbPath = sc.getRealPath("/WEB-INF/superstar.db");

        // set default url
        String url = "/home.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            System.out.println("controller:home");
            url = "/home.html";

        } else if (action.equalsIgnoreCase("createRecord")) {
            System.out.println("controller:createRecord");
            int fiveStarRating = 0;
            double pricePaid = 0;

            // get parameters passed in from the request
            String gameName = request.getParameter("name");
            String gameGenre = request.getParameter("genre");
            String gameDescription = request.getParameter("description");
            String esrbRating = request.getParameter("esrbRating");
            String fiveStarString = request.getParameter("fiveStarRating");
            String priceString = request.getParameter("price");

            // validate and convert rating string into an integer
            if (fiveStarString == null || fiveStarString.isEmpty()) {
                fiveStarRating = 0;
            } else {
                fiveStarRating = Integer.parseInt(fiveStarString);
            }
            
            // validate and convert price string into a double
            if (priceString == null || priceString.isEmpty()) {
                pricePaid = 0;
            } else {
                pricePaid = Double.parseDouble(priceString);
            }

            // store data in an Game object
            Game game = new Game(gameName, gameGenre, gameDescription, esrbRating, fiveStarRating, pricePaid);
            System.out.println("Controller:createRecord:game=" + game);

            // validate the parameters
            if (gameName == null || gameGenre == null || gameDescription == null
                    || gameName.isEmpty() || gameGenre.isEmpty() || gameDescription.isEmpty() 
                    ) {
                url = "/createRecord.jsp";
            } else {
                // insert this data record into the database
                DAOSQLite.createRecord(game, dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("report")) {
            System.out.println("controller:report");
            String gameName = request.getParameter("name");
            if (gameName == null || gameName.isEmpty()) {
                gameName = "%";
            }
            String gameGenre = request.getParameter("genre");
            String gameDescription = request.getParameter("description");
            String esrbRating = request.getParameter("esrbRating");
            String fiveStarString = request.getParameter("fiveStarRating");
            String priceString = request.getParameter("price");
            List<Game> mydata = DAOSQLite.retrieveAllRecords(dbPath);
            request.setAttribute("gameName", gameName);
            request.setAttribute("gameGenre", gameGenre);
            request.setAttribute("gameDescription", gameDescription);
            request.setAttribute("esrbRating", esrbRating);
            request.setAttribute("fiveStarRating", fiveStarString);
            request.setAttribute("pricePaid", priceString);
            request.setAttribute("mydata", mydata);
            url = "/showRecords.jsp";

        } else if (action.equalsIgnoreCase("deleteRecord")) {
            System.out.println("controller:deleteRecord");
            String nameString = request.getParameter("name");
            if (nameString == null || nameString.isEmpty()) {
                url = "/deleteRecord.jsp";
            } else {
                DAOSQLite.deleteRecord(nameString, dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("makeDB")) {
            System.out.println("controller:makeDB");
            DAOSQLite.dropTable(dbPath);
            DAOSQLite.createTable(dbPath);
            DAOSQLite.populateTable(dbPath);
            url = "/home.html";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
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
        return "Controller for Employee App";
    }// </editor-fold>

}
