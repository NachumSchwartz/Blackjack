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

    



}
