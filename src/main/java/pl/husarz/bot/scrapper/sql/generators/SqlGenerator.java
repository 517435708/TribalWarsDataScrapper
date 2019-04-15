package pl.husarz.bot.scrapper.sql.generators;

import pl.husarz.bot.scrapper.sql.data.DataRecordSql;

public interface SqlGenerator
{
    String generate(DataRecordSql data);
}
