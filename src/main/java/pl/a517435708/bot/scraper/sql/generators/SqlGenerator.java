package pl.a517435708.bot.scraper.sql.generators;

import pl.a517435708.bot.scraper.sql.data.DataRecordSql;

public interface SqlGenerator
{
    String generate(DataRecordSql data);
}
