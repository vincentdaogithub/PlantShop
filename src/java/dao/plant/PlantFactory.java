package dao.plant;

import obj.plant.Plant;
import obj.plant.PlantCategories;
import obj.plant.PlantStatus;

public class PlantFactory {
    public static final Plant buildForDB(
            String plantName,
            int plantPrice,
            String plantDescription,
            PlantStatus plantStatus,
            PlantCategories plantCategories
    ) {

        return new Plant(
                0,
                plantName,
                plantPrice,
                plantDescription,
                plantStatus.getStatus(),
                plantCategories.getCategoryID()
        );
    }
}
