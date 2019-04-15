package pl.husarz.bot.scrapper.sql.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerDataRecordSql implements DataRecordSql
{
    private String tableName;
    private Map<String,String> dataColumnNameByValueAsString = new LinkedHashMap<>();


    PlayerDataRecordSql(String id,
                        String playerNickName,
                        String averageFarm,
                        String armySent,
                        String armyGained,
                        String armyLost)
    {
        tableName = "gracze";

        dataColumnNameByValueAsString.put("id",id);
        dataColumnNameByValueAsString.put("nick",playerNickName);
        dataColumnNameByValueAsString.put("srednia_farmy",averageFarm);
        dataColumnNameByValueAsString.put("wyslane_wojsko",armySent);
        dataColumnNameByValueAsString.put("wojska_zyskane",armyGained);
        dataColumnNameByValueAsString.put("wojska_stracone",armyLost);

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
