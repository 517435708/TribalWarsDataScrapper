package pl.husarz.bot.scrapper;

import netscape.javascript.JSObject;
import pl.husarz.bot.scrapper.sql.data.DataRecordSql;
import pl.husarz.bot.scrapper.sql.data.SqlRecordFactory;
import pl.husarz.bot.scrapper.sql.data.VillageDataRecordSql;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptOutputAnalyzer
{
    private JSObject executionResult;
    private String playerNick;
    private String playerId;
    ArrayList<DataRecordSql> dataRecordSqlArray = new ArrayList<>();


    public ScriptOutputAnalyzer(JSObject executionResult)
    {
        this.executionResult = executionResult;

        playerId = (String)executionResult.call("pop");
        playerNick = (String)executionResult.call("pop");
    }

    public ArrayList<DataRecordSql> generateRecords()
    {

        ArrayList<DataRecordSql> dataRecordSqls = new ArrayList<>();

        ArrayList<String> unitByNumberBuffer;
        ArrayList<String> numbersBuffer;


        Pattern pattern = Pattern.compile("\\d+");
        String armySent = (String)executionResult.call("pop");
        Matcher matcher = pattern.matcher(armySent);

        armySent = matcher.group();

        Object element;


        while((element = executionResult.call("pop")) instanceof String)
        {
            if(!(unitByNumberBuffer = getPairOfUnitNameAndItsNumber(element.toString())).isEmpty())
            {
                VillageDataBuffer.add(unitByNumberBuffer.get(0),unitByNumberBuffer.get(1));
            }

            if(VillageDataBuffer.isFull())
            {
                numbersBuffer = VillageDataBuffer.getBuffer();
                dataRecordSqls.add(SqlRecordFactory.createVillageRecord(playerId,playerNick,numbersBuffer));
            }

        }

        JSObject table = (JSObject)element;

        String armyRowData = (String)table.call("pop");
        String farmRowData = (String)table.call("pop");

        dataRecordSqls.add(SqlRecordFactory.createPlayerRecord(playerId,playerNick,getRawNumbersFromJSScript(farmRowData),armySent,getRawNumbersFromJSScript(armyRowData)));


        return dataRecordSqls;
    }

    private ArrayList<String> getPairOfUnitNameAndItsNumber(String row)
    {
        ArrayList<String> unitNameByItsNumber = new ArrayList<>();


        row = row.replace("\n","");
        row = row.replace("\t","");
        row = row.replace(" : ","");

        Pattern numberPattern = Pattern.compile("\\d+\\/(\\d+)");

        Matcher numberMatcher = numberPattern.matcher(row);

        if(numberMatcher.find())
        {
            int begin = numberMatcher.start();
            unitNameByItsNumber.add(row.substring(0,begin));
            unitNameByItsNumber.add(numberMatcher.group(0));
        }


        return unitNameByItsNumber;
    }

    private ArrayList<String> getRawNumbersFromJSScript(String script)
    {
        ArrayList<String> rawNumbers = new ArrayList<>();

        Pattern squareBracketPattern = Pattern.compile("\\[[0-9\",\\\\]{10,}\\]");
        Pattern numberPattern = Pattern.compile("-?\\d+");

        Matcher squareMatcher = squareBracketPattern.matcher(script);

        StringBuilder squareBracketMatcherResult = new StringBuilder();
        while (squareMatcher.find())
        {
            squareBracketMatcherResult.append(squareMatcher.group());
        }

        Matcher numberMatcher = numberPattern.matcher(squareBracketMatcherResult.toString());

        int counter = 0;


        while (numberMatcher.find())
        {
            String number = numberMatcher.group();
            if(counter%2 == 1)
                rawNumbers.add(number);

            counter++;
        }


        return rawNumbers;
    }


}
