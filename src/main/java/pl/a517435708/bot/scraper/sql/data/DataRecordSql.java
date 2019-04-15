package pl.a517435708.bot.scraper.sql.data;

import java.util.Map;

public interface DataRecordSql
{
    String getTableName();
    Map<String,String> getDataSql();
}
