package pl.husarz.bot.scrapper.sql.generators;

import pl.husarz.bot.scrapper.sql.data.DataRecordSql;

import java.util.Map;

public class InsertSqlGenerator implements SqlGenerator
{
    @Override
    public String generate(DataRecordSql dataRecordSql)
    {
        StringBuilder sqlStatement = new StringBuilder("INSERT INTO ");

        Map<String,String> data = dataRecordSql.getDataSql();

        String values = data.values().toString();
        String keys = data.keySet().toString();

        keys = keys.replace("[","(");
        keys = keys.replace("]",")");
        keys = keys.replaceAll(", ",",");

        values = values.replace("[","(\"");
        values = values.replace("]","\")");
        values = values.replaceAll(", ","\",\"");



        sqlStatement.append(dataRecordSql.getTableName())
                    .append(" ")
                    .append(keys)
                    .append(" VALUES ")
                    .append(values)
                    .append(";");

        return sqlStatement.toString();
    }


}
