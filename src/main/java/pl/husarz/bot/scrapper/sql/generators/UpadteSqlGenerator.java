package pl.husarz.bot.scrapper.sql.generators;

import pl.husarz.bot.scrapper.sql.data.DataRecordSql;

import java.util.Map;

public class UpadteSqlGenerator implements SqlGenerator
{
    @Override
    public String generate(DataRecordSql dataRecordSql)
    {
        StringBuilder sqlStatement = new StringBuilder("UPDATE ");

        Map<String,String> data = dataRecordSql.getDataSql();

        String setData = data.toString();

        setData = setData.replace("{","");
        setData = setData.replace("}","\"");

        setData = setData.replaceAll("=","=\"");
        setData = setData.replaceAll(", ", "\",");

        Map.Entry<String,String> entry = data.entrySet().iterator().next();

        String keyCondition = entry.getKey();
        String valueCondition = entry.getValue();


        sqlStatement.append(dataRecordSql.getTableName())
                    .append(" SET ")
                    .append(setData)
                    .append(" WHERE ")
                    .append(keyCondition)
                    .append("=")
                    .append(valueCondition)
                    .append(";");

        return sqlStatement.toString();
    }
}
