package pl.a517435708.bot.scrapper.sql.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class VillageDataRecordSql implements DataRecordSql
{
    private String tableName;
    private Map<String,String> dataColumnNameByValueAsString = new LinkedHashMap<>();


    VillageDataRecordSql(String id,
                                String playerNickName,
                                String pikemanCount,
                                String swordsmanCount,
                                String axemanCount,
                                String bowmanCount,
                                String spyCount,
                                String lightCavalryCount,
                                String heavyCavalryCount,
                                String bowmanCavalryCount,
                                String ramCount,
                                String catapultCount,
                                String nobleCount)
    {
        tableName = "wioski";

        dataColumnNameByValueAsString.put("id",id);
        dataColumnNameByValueAsString.put("nick_gracza",playerNickName);

        dataColumnNameByValueAsString.put("Pikinier", pikemanCount);
        dataColumnNameByValueAsString.put("Miecznik", swordsmanCount);
        dataColumnNameByValueAsString.put("Topornik", axemanCount);
        dataColumnNameByValueAsString.put("Łucznik", bowmanCount);

        dataColumnNameByValueAsString.put("Zwiadowca", spyCount);
        dataColumnNameByValueAsString.put("`Lekki kawalerzysta`", lightCavalryCount);
        dataColumnNameByValueAsString.put("`Łucznik na koniu`", bowmanCavalryCount);
        dataColumnNameByValueAsString.put("`Ciężki kawalerzysta`", heavyCavalryCount);

        dataColumnNameByValueAsString.put("Taran", ramCount);
        dataColumnNameByValueAsString.put("Katapulta", catapultCount);

        dataColumnNameByValueAsString.put("Szlachcic", nobleCount);
    }

    @Override
    public String getTableName()
    {
        return tableName;
    }

    @Override
    public Map<String, String> getDataSql()
    {
        return dataColumnNameByValueAsString;
    }
}
