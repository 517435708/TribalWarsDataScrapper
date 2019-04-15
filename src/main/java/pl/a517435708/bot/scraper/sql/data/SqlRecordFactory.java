package pl.a517435708.bot.scraper.sql.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SqlRecordFactory
{

    public static PlayerDataRecordSql createPlayerRecord(String id,
                                                         String playerNickName,
                                                         ArrayList<String> farmData,
                                                         String armySent,
                                                         ArrayList<String> armyLostGain)
    {
        Integer armyLost = 0;
        Integer armyGain = 0;

        Integer farmDataInt = 0;

        int buffer;

        for (String anArmyLostGain : armyLostGain)
        {
            if ((buffer = Integer.parseInt(anArmyLostGain)) > 0)
            {
                armyGain += buffer;
            } else
            {
                armyLost += buffer;
            }
        }


        for (String aFarmData : farmData)
        {
            farmDataInt += Integer.parseInt(aFarmData);
        }

        farmDataInt /= 7;

        return new PlayerDataRecordSql(id,playerNickName,
                                        farmDataInt.toString(),
                                        armySent,
                                        armyGain.toString(),
                                        armyLost.toString());
    }

    public static FarmDataRecordSql createFarmRecord(String id,
                                                     String playerNickName,
                                                     String farm,
                                                     int daysToRemove)
    {
        return new FarmDataRecordSql(id,playerNickName,getDateRemovedByNDaysInFormatYYYYMMDD(daysToRemove),farm);
    }

    public static FarmDataRecordSql createFarmRecord(String playerNickName,
                                                     String farm,
                                                     int daysToRemove)
    {
        return new FarmDataRecordSql(playerNickName,getDateRemovedByNDaysInFormatYYYYMMDD(daysToRemove),farm);
    }

    public static VillageDataRecordSql createVillageRecord(String id, String nick, ArrayList<String> numberOfTroops)
    {
        return new VillageDataRecordSql(id,nick,
                numberOfTroops.get(10),
                numberOfTroops.get(9),
                numberOfTroops.get(8),
                numberOfTroops.get(7),
                numberOfTroops.get(6),
                numberOfTroops.get(5),
                numberOfTroops.get(4),
                numberOfTroops.get(3),
                numberOfTroops.get(2),
                numberOfTroops.get(1),
                numberOfTroops.get(0));
    }

    private static String getDateRemovedByNDaysInFormatYYYYMMDD(int daysToRemove)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        return dateFormat.format(getDateRemovedByNDays(daysToRemove));
    }

    private static Date getDateRemovedByNDays(int daysToRemove)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-daysToRemove);
        return cal.getTime();
    }
}
