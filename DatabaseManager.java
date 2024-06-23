import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:CMSC495T3BlackJackState.db";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                createTables(connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+ "Initialize Database Error");
        }
    }

    private static void createTables(Connection connection) {
        String createGameStatesTable = "CREATE TABLE IF NOT EXISTS GameStates ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "playerScore INTEGER NOT NULL, "
                + "dealerScore INTEGER NOT NULL, "
                + "playerHand TEXT NOT NULL, "
                + "dealerHand TEXT NOT NULL, "
                + "gameState TEXT NOT NULL, "
                + "wins INTEGER NOT NULL, "
                + "losses INTEGER NOT NULL, "
                + "ties INTEGER NOT NULL"
                + ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createGameStatesTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "Create Tables Error");
        }
    }

    public static void saveGameState(int playerScore, int dealerScore, String playerHand, String dealerHand, String gameState, int wins, int losses, int ties) {
        String sql = "INSERT INTO GameStates(playerScore, dealerScore, playerHand, dealerHand, gameState, wins, losses, ties) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, playerScore);
            pstmt.setInt(2, dealerScore);
            pstmt.setString(3, playerHand);
            pstmt.setString(4, dealerHand);
            pstmt.setString(5, gameState);
            pstmt.setInt(6, wins);
            pstmt.setInt(7, losses);
            pstmt.setInt(8, ties);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> getSavedGameStates() {
        List<String> gameStates = new ArrayList<>();
        String sql = "SELECT gameState FROM GameStates";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                gameStates.add(rs.getString("gameState"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return gameStates;
    }

    /*public static GameState loadGameState(String gameState) {
        String sql = "SELECT * FROM GameStates WHERE gameState = ?";
        GameState state = null;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, gameState);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    state = new GameState(
                            rs.getInt("playerScore"),
                            rs.getInt("dealerScore"),
                            rs.getString("playerHand"),
                            rs.getString("dealerHand"),
                            rs.getString("gameState"),
                            rs.getInt("wins"),
                            rs.getInt("losses"),
                            rs.getInt("ties")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + ("Load Game State Error"));
        }

        return state;
    }*/

    /*public static GameState loadGameState(String gameState) {
        String sql = "SELECT * FROM GameStates WHERE gameState = ?";
        GameState state = null;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, gameState);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    state = new GameState(
                            rs.getInt("playerScore"),
                            rs.getInt("dealerScore"),
                            rs.getString("playerHand"),
                            rs.getString("dealerHand"),
                            rs.getString("gameState"),
                            rs.getInt("wins"),
                            rs.getInt("losses"),
                            rs.getInt("ties")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return state;
    }*/

    /*public static class GameState {
        public final int playerScore;
        public final int dealerScore;
        public final Hand playerHand;
        public final Hand dealerHand;
        public final String gameState;
        public final int wins;
        public final int losses;
        public final int ties;

        public GameState(int playerScore, int dealerScore, Hand playerHand, Hand dealerHand, String gameState, int wins, int losses, int ties) {
            this.playerScore = playerScore;
            this.dealerScore = dealerScore;
            this.playerHand = playerHand;
            this.dealerHand = dealerHand;
            this.gameState = gameState;
            this.wins = wins;
            this.losses = losses;
            this.ties = ties;
        }
    }*/

    /*public class GameState {
        private int playerScore;
        private int dealerScore;
        private String playerHand;
        private String dealerHand;
        private String gameState;
        private int wins;
        private int losses;
        private int ties;

        public GameState(int playerScore, int dealerScore, String playerHand, String dealerHand, String gameState, int wins, int losses, int ties) {
            this.playerScore = playerScore;
            this.dealerScore = dealerScore;
            this.playerHand = playerHand;
            this.dealerHand = dealerHand;
            this.gameState = gameState;
            this.wins = wins;
            this.losses = losses;
            this.ties = ties;
        }

        // Getters for all fields
    }*/



}
