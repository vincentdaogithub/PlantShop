package dao.plant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import obj.plant.Plant;
import obj.plant.PlantCategories;
import obj.plant.PlantStatuses;
import util.DBUtils;
import util.SQLBuilder;

public class PlantDAO {

    private static final String PLANT_DB = "Plants";
    private static final int MAX_SIZE_PER_REQUEST = 5;

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
                );) {

            statement.setInt(1, plantID);
            ResultSet results = statement.executeQuery();

            if (results.next() && results.isLast()) {
                return new Plant(
                        results.getInt("PID"),
                        results.getString("PName"),
                        results.getInt("price"),
                        results.getString("description"),
                        PlantStatuses.convertIntToStatus(results.getInt("status")),
                        PlantCategories.convertIntToCategory(results.getInt("CateID"))
                );
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static final Set<Plant> getPlants() {

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM " + PLANT_DB);

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        builder.toString(),
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );) {

            ResultSet results = statement.executeQuery();
            Set<Plant> plants = Collections.synchronizedSet(new LinkedHashSet<>());

            synchronized (plants) {
                while (results.next()) {
                    plants.add(new Plant(
                            results.getInt("PID"),
                            results.getString("PName"),
                            results.getInt("price"),
                            results.getString("description"),
                            PlantStatuses.convertIntToStatus(results.getInt("status")),
                            PlantCategories.convertIntToCategory(results.getInt("CateID"))
                    ));
                }
            }

            return plants;
        } catch (Exception e) {
            return Collections.synchronizedSet(new LinkedHashSet<>());
        }
    }
}
