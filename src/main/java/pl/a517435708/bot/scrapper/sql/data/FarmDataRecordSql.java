package pl.a517435708.bot.scrapper.sql.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class FarmDataRecordSql implements DataRecordSql
{

    private String tableName;
    private Map<String,String> dataColumnNameByValueAsString = new LinkedHashMap<>();


    FarmDataRecordSql(String id,
                      String playerNickName,
                      String date,
                      String farm)
    {
        tableName = "farma";

        dataColumnNameByValueAsString.put("id",id);
        dataColumnNameByValueAsString.put("nick_gracza",playerNickName);
        dataColumnNameByValueAsString.put("data",date);
        dataColumnNameByValueAsString.put("lacznie",farm);

    }

    FarmDataRecordSql(String playerNickName,
                      String date,
                      String farm)
    {
        tableName = "farma";

        dataColumnNameByValueAsString.put("nick_gracza",playerNickName);
        dataColumnNameByValueAsString.put("data",date);
        dataColumnNameByValueAsString.put("lacznie",farm);
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
