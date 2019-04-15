package pl.husarz.bot.scrapper;

import java.util.ArrayList;

public class VillageDataBuffer
{
    private static ArrayList<String> numbers;
    private static boolean full = false;

    static void add(String key, String value)
    {
        switch(numbers.size())
        {
            case 0:
            {
                if(key.equals("Szlachcic"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 1:
            {
                if(key.equals("Katapulta"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 2:
            {
                if(key.equals("Taran"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 3:
            {
                if(key.equals("Ciężki kawalerzysta"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 4:
            {
                if(key.equals("Łucznik na koniu"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 5:
            {
                if(key.equals("Lekki kawalerzysta"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 6:
            {
                if(key.equals("Zwiadowca"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 7:
            {
                if(key.equals("Łucznik"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 8:
            {
                if(key.equals("Topornik"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 9:
            {
                if(key.equals("Miecznik"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                    add(key,value);
                }

            }break;
            case 10:
            {
                if(key.equals("Pikinier"))
                    numbers.add(value);
                else
                {
                    numbers.add("-1");
                }

                full = true;

            }break;

        }


    }


    static boolean isFull()
    {
        return full;
    }

    static ArrayList<String> getBuffer()
    {
        ArrayList<String> data = new ArrayList<>(numbers);
        numbers.clear();
        full = false;
        return data;
    }


}
