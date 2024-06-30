import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                + "gameName TEXT NOT NULL, "
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

    public static void saveGameState(String gameName, int playerScore,  int dealerScore, String playerHand, String dealerHand, String gameState, int wins, int losses, int ties) {
        String sql = "INSERT INTO GameStates(gameName, playerScore, dealerScore, playerHand, dealerHand, gameState, wins, losses, ties) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, gameName);
            pstmt.setInt(2, playerScore);
            pstmt.setInt(3, dealerScore);
            pstmt.setString(4, playerHand);
            pstmt.setString(5, dealerHand);
            pstmt.setString(6, gameState);
            pstmt.setInt(7, wins);
            pstmt.setInt(8, losses);
            pstmt.setInt(9, ties);
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

/*    public static String[] getGameNames(){
        String[] names = {"Paul", "Jake", "Eric", "Nachum"};
        return names;
    }*/

    public static String[] getGameNames() {
        List<String> gameNames = new ArrayList<>();
        String sql = "SELECT gameName FROM GameStates";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                gameNames.add(rs.getString("gameName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gameNames.toArray(new String[0]);
    }

    public static Map<String, Object> getGameStateByGameName(String gameName) {
        Map<String, Object> gameState = new HashMap<>();
        String sql = "SELECT gameName, playerScore, dealerScore, playerHand, dealerHand, gameState, wins, losses, ties FROM GameStates WHERE gameName = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, gameName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    gameState.put("gameName", rs.getString("gameName"));
                    gameState.put("playerScore", rs.getInt("playerScore"));
                    gameState.put("dealerScore", rs.getInt("dealerScore"));
                    gameState.put("playerHand", rs.getString("playerHand"));
                    gameState.put("dealerHand", rs.getString("dealerHand"));
                    gameState.put("gameState", rs.getString("gameState"));
                    gameState.put("wins", rs.getInt("wins"));
                    gameState.put("losses", rs.getInt("losses"));
                    gameState.put("ties", rs.getInt("ties"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gameState;
    }
 }
