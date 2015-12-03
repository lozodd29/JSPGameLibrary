package model;

import java.io.Serializable;

/**
 *
 * @author Dylan Lozo
 */
public class Game implements Serializable {

    private String gameName;
    private String gameGenre;
    private String gameDescription;
    private String esrbRating;
    private int fiveStarRating;
    private double pricePaid;

    public Game() {
    }

    public Game(String gameName, String gameGenre, String gameDescription, 
            String esrbRating, int fiveStarRating, double pricePaid) {
        this.gameName = gameName;
        this.gameGenre = gameGenre;
        this.gameDescription = gameDescription;
        this.esrbRating = esrbRating;
        this.fiveStarRating = fiveStarRating;
        this.pricePaid = pricePaid;
    }

    public String inHTMLRowFormat() {
        return "<tr><td>" + gameName + "</td>"
                + "<td>" + gameGenre + "</td>"
                + "<td>" + gameDescription + "</td>"
                + "<td>" + esrbRating + "</td>"
                + "<td>" + fiveStarRating + "</td>"
                + "<td>" + pricePaid + "</td></tr>\n";
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public int getFiveStarRating() {
        return fiveStarRating;
    }

    public void setFiveStarRating(int fiveStarRating) {
        this.fiveStarRating = fiveStarRating;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    @Override
    public String toString() {
        return "Game{" + "gameName=" + gameName + ", gameGenre=" + gameGenre 
                + ", gameDescription=" + gameDescription + ", esrbRating=" 
                + esrbRating + ", fiveStarRating=" + fiveStarRating 
                + ", pricePaid=" + pricePaid + '}';
    }

    
}
