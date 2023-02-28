package dao.plant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import obj.plant.Plant;
import util.DBUtils;
import util.SQLBuilder;

public class PlantDAO {
    
    private static final String PLANT_DB = "Plants";
    private static final int MAX_SIZE_PER_REQUEST = 10;

    public static final Plant getPlant(int plantID) {
        
        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM " + PLANT_DB);
        builder.addLine("WHERE PID = ?");

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        builder.toString(),
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
        ) {

            statement.setInt(1, plantID);
            ResultSet results = statement.executeQuery();

            if (results.next() && results.isLast()) {
                return new Plant(
                        results.getInt("PID"),
                        results.getString("PName"),
                        results.getInt("price"),
                        results.getString("description"),
                        results.getInt("status"),
                        results.getInt("cateID")
                );
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static final List<Plant> getPlants() {
        
        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM " + PLANT_DB);

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        builder.toString(),
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
        ) {

            ResultSet results = statement.executeQuery();
            List<Plant> plants = new ArrayList<>();

            while (results.next()) {
                plants.add(new Plant(
                        results.getInt("PID"),
                        results.getString("PName"),
                        results.getInt("price"),
                        results.getString("description"),
                        results.getInt("status"),
                        results.getInt("cateID")
                ));
            }

            return plants;
        } catch (Exception e) {
            return new ArrayList<Plant>();
        }
    }

    public static final List<Plant> getPlants(int beginIndex) {
        List<Plant> plants = getPlants();
        return plants.subList(beginIndex * MAX_SIZE_PER_REQUEST,  beginIndex * MAX_SIZE_PER_REQUEST + MAX_SIZE_PER_REQUEST);
    }
}
