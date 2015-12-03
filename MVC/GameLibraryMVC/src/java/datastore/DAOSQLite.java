package datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Game;

/**
 * DAOSQLite Data Access Object for an SQLite database
 *
 * @author Dylan Lozo
 * @version 0.3 on 2015-11-03 revised 2015-11-24
 */
public class DAOSQLite {

    protected final static String DRIVER = "org.sqlite.JDBC";
    protected final static String JDBC = "jdbc:sqlite";

    /**
     * Inserts an record into the database table. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param game the object to insert
     * @param dbPath the path to the SQLite database
     */
    public static void createRecord(Game game, String dbPath) {
        String q = "insert into game (gameName, gameGenre, gameDescription, esrbRating, fiveStarRating, pricePaid) "
                + "values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, game.getGameName());
            ps.setString(2, game.getGameGenre());
            ps.setString(3, game.getGameDescription());
            ps.setString(4, game.getEsrbRating());
            ps.setInt(5, game.getFiveStarRating());
            ps.setDouble(6, game.getPricePaid());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieve all of the records in the database as a list sorted by
     * name. This method was replaced by a more advanced method.
     *
     * @param dbPath the path to the SQLite database
     * @return list of objects
     */
    public static List<Game> retrieveAllRecords(String dbPath) {
        String q = "select * from game order by gameName";
        List<Game> list = null;
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            list = myQuery(conn, ps);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
//    /**
//     * This is a much more advanced retrieve method. It can get all of the
//     * records from the database or a subset based on the various parameters
//     * passed in.
//     *
//     * @param dbPath the path to the SQLite database
//     * @param gameName - the name of the video game
//     * @return list of objects
//     */
//    public static List<Game> retrieveRecords(String dbPath, String gameName) {
//        String q = "select * from game order by gameName";
//
//        List<Game> list = null;
//        try (Connection conn = getConnectionDAO(dbPath);
//                PreparedStatement ps = conn.prepareStatement(q)) {
//            // the % sign is an sql wildcard so that we can search by just a few letters of the game name
//            ps.setString(1, gameName + "%");
//            System.out.println(q);
//            list = myQuery(conn, ps);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOSQLite.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }

    /**
     * Delete a record from the database given its name. Note the use of a
     * parameterized query to prevent SQL Injection attacks.
     *
     * @param gameName the name of the record to delete
     * @param dbPath the path to the SQLite database
     */
    public static void deleteRecord(String gameName, String dbPath) {
        String q = "delete from game where gameName = ?";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.setString(1, gameName);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a new user table.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void createTable(String dbPath) {
        String q = "create table game ("
                + "gameName varchar(50), "
                + "gameGenre varchar(20) not null, "
                + "gameDescription varchar(200) not null, "
                + "esrbRating varchar(10) not null, "
                + "fiveStarRating int not null, "
                + "pricePaid double not null);";
        System.out.println("createtable " + q);
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Drops the user table erasing all of the data.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void dropTable(String dbPath) {
        final String q = "drop table if exists game";
        try (Connection conn = getConnectionDAO(dbPath);
                PreparedStatement ps = conn.prepareStatement(q)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populates the table with sample data records.
     *
     * @param dbPath the path to the SQLite database
     */
    public static void populateTable(String dbPath) {
        Game p;
        p = new Game("Destiny", "MMORPG FPS", "Destiny is a good game but it doesn't have very much story.", "M", 2, 59.99);
        DAOSQLite.createRecord(p, dbPath);
        p = new Game("MGSV: The Phantom Pain", "Action/Adventure Stealth", "You play as Venom Snake and assist Kazuhira Miller in defeating Cipher.", "M", 5, 64.99);
        DAOSQLite.createRecord(p, dbPath);
        p = new Game("COD: Black Ops II", "FPS", "You play as David Mason and fight a terrorist group controlled by Menendez", "M", 4, 59.99);
        DAOSQLite.createRecord(p, dbPath);
    }

    /**
     * A helper method that executes a prepared statement and returns the result
     * set as a list of objects.
     *
     * @param conn a connection to the database
     * @param ps a prepared statement
     * @return list of objects from the result set
     */
    protected static List<Game> myQuery(Connection conn, PreparedStatement ps) {
        List<Game> list = new ArrayList();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String gameName = rs.getString("gameName");
                String gameGenre = rs.getString("gameGenre");
                String gameDescription = rs.getString("gameDescription");
                String esrbRating = rs.getString("esrbRating");
                int fiveStarRating = rs.getInt("fiveStarRating");
                double pricePaid = rs.getDouble("pricePaid");
                Game p = new Game(gameName, gameGenre, gameDescription, esrbRating, fiveStarRating, pricePaid);
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSQLite.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Creates a connection to the SQLite database.
     *
     * @param dbPath the path to the SQLite database
     * @return connection to the database
     */
    protected static Connection getConnectionDAO(String dbPath) {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(JDBC + ":" + dbPath);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAOSQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
