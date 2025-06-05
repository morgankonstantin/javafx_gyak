package hu.alkfejl.dao;

import hu.alkfejl.model.Gepjarmu;
import hu.alkfejl.utils.ConfigManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GepjarmuSQLite implements GepjarmuDAO {

    // DB elérés
    private static final String dbURL = ConfigManager.getValue("db.url");

    @Override
    public boolean add(Gepjarmu jarmu) {
        boolean result = false;

        try (Connection c = DriverManager.getConnection(dbURL);
             PreparedStatement pst = c.prepareStatement(Query.INSERT.command)) {

            pst.setString(1, jarmu.getRendszam());
            pst.setString(2, jarmu.getTipus());
            pst.setInt(3, jarmu.getKobcenti());

            int rows = pst.executeUpdate();

            if (rows == 1) {
                result = true;
            } else {
                throw new RuntimeException("Tobb mint egy sort erintett a beszuras");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Gepjarmu> findAll() {
        var result = new ArrayList<Gepjarmu>();


        try (Connection c = DriverManager.getConnection(dbURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(Query.SELECT.command)) {

            while (rs.next()) {
                Gepjarmu jarmu = new Gepjarmu();
                jarmu.setRendszam(rs.getString("rendszam"));
                jarmu.setTipus(rs.getString("tipus"));
                jarmu.setKobcenti(rs.getInt("kobcenti"));

                result.add(jarmu);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Nemtudom");
        }

        return result;
    }

    private enum Query {
        INSERT("INSERT INTO gepjarmuvek VALUES (?,?,?);"),
        SELECT("SELECT * FROM gepjarmuvek;");

        private String command;

        Query(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }

    }
}
