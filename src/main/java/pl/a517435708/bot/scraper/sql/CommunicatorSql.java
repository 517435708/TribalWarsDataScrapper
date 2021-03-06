package pl.a517435708.bot.scraper.sql;

import com.mysql.cj.jdbc.MysqlDataSource;
import pl.a517435708.bot.scraper.sql.generators.SqlGenerator;
import pl.a517435708.bot.scraper.sql.data.DataRecordSql;
import pl.a517435708.bot.scraper.sql.generators.SelectSqlGenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommunicatorSql
{
    private Statement statement;

    public CommunicatorSql(String nick,
                          String password,
                          String dataBaseName,
                          String timeZone,
                          String databaseAddress,
                          int port) throws Exception
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(nick);
        dataSource.setPassword(password);
        dataSource.setServerName(databaseAddress);
        dataSource.setDatabaseName(dataBaseName);
        dataSource.setPort(port);
        dataSource.setServerTimezone(timeZone);


        Connection connection = dataSource.getConnection();
        statement = connection.createStatement();
    }

    public void sqlQuerySend(SqlGenerator sqlGenerator, DataRecordSql dataRecordSql)
    {
        try
        {
            statement.executeUpdate(sqlGenerator.generate(dataRecordSql));
        } catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    public boolean findRecord(DataRecordSql dataRecordSql)
    {
        ResultSet resultSet = null;
        SqlGenerator generator = new SelectSqlGenerator();
        try
        {
            resultSet = statement.executeQuery(generator.generate(dataRecordSql));
            return resultSet != null && resultSet.next();
        } catch (Exception ex)
        {
            System.out.println(ex);
        }


        return false;
    }


}
