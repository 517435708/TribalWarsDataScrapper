package pl.a517435708.bot.scrapper.sql.generators;

import pl.a517435708.bot.scrapper.sql.data.DataRecordSql;

public interface SqlGenerator
{
    String generate(DataRecordSql data);
}
