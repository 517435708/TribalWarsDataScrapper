package pl.husarz.bot.scrapper.sql.data;

import java.util.Map;

public interface DataRecordSql
{
    String getTableName();
    Map<String,String> getDataSql();
}
