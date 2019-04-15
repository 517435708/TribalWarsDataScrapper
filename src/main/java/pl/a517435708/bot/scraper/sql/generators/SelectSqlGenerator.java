package pl.a517435708.bot.scraper.sql.generators;


import pl.a517435708.bot.scraper.sql.data.DataRecordSql;

import java.util.Collection;
import java.util.Set;

public class SelectSqlGenerator implements SqlGenerator
{
    @Override
    public String generate(DataRecordSql data)
    {
        Set<String> keys = data.getDataSql().keySet();
        Collection<String> values = data.getDataSql().values();

        return "SELECT * FROM " +
                data.getTableName() +
                " WHERE " +
                keys.iterator().next() +
                "=" +
                values.iterator().next() +
                ";";
    }

}
