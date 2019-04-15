package pl.husarz.bot.scrapper;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScriptLauncher
{

    private WebEngine webEngine;
    private final String world;

    private StringBuilder script;

    private JSObject jsOutput;
    private AtomicBoolean executionCompleted = new AtomicBoolean(false);


    public ScriptLauncher(WebEngine webEngine, String world)
    {
        this.webEngine = webEngine;
        this.world = world;

        try
        {
            loadScript();
        }catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public JSObject getJsOutput()
    {
        return jsOutput;
    }

    private void loadScript() throws Exception
    {

        FileReader fileReader = new FileReader("./resources/script.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            line = line.replace("%#%WORLD%#%","'" +world + "'");
            script.append(line).append('\n');
        }

    }


    public void execute()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                jsOutput = (JSObject)webEngine.executeScript(script.toString());
                executionCompleted.set(true);
            }
        });
    }


    public boolean isExecutionCompleted()
    {
        return executionCompleted.get();
    }



}
