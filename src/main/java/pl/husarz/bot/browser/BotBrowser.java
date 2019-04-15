package pl.husarz.bot.browser;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import javafx.scene.layout.Region;

import pl.husarz.bot.scrapper.sql.*;
import pl.husarz.bot.scrapper.*;
import pl.husarz.bot.scrapper.sql.data.DataRecordSql;
import pl.husarz.bot.scrapper.sql.generators.InsertSqlGenerator;
import pl.husarz.bot.scrapper.sql.generators.UpadteSqlGenerator;

import java.util.ArrayList;


public class BotBrowser extends Region
{

    private ToolBar toolBar = new ToolBar();
    private final WebView browser = new WebView();
    private Label infoLabel = new Label("Kliknij guzik.");


    private CommunicatorSql communicatorSql;
    private ScriptLauncher scriptLauncher;
    private ScriptOutputAnalyzer analyzer;

    public BotBrowser(String world)
    {

        try
        {
            communicatorSql = new CommunicatorSql("plemiona","Care2019!", "plemiona", "CET","194.28.33.123",3306);
        }catch (Exception ex)
        {
            System.out.println(ex);
        }

        WebEngine webEngine = browser.getEngine();
        scriptLauncher = new ScriptLauncher(webEngine,world);



        getStyleClass().add("browser");
        webEngine.load("https://www.plemiona.pl");

        toolBar.getStyleClass().add("browser-toolbar");

        toolBar.setOrientation(Orientation.VERTICAL);

        Button startSelectionButton = new Button("Kliknij Mnie!");
        startSelectionButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                scriptLauncher.execute();
                infoLabel.setText("Czekaj.");
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        waitUntilExecutionIsDone();
                        sendQueryToDatabaseServers();
                    }
                }).start();
            }
        });




        toolBar.getItems().addAll(startSelectionButton,infoLabel);

        getChildren().add(toolBar);
        getChildren().add(browser);


    }

    private void sendQueryToDatabaseServers()
    {
        analyzer = new ScriptOutputAnalyzer(scriptLauncher.getJsOutput());
        ArrayList<DataRecordSql> recordSqls =  analyzer.generateRecords();

        for (DataRecordSql recordSql : recordSqls)
        {
            if (communicatorSql.findRecord(recordSql))
            {
                communicatorSql.sqlQuerySend(new UpadteSqlGenerator(), recordSql);
            } else
            {
                communicatorSql.sqlQuerySend(new InsertSqlGenerator(), recordSql);
            }
        }

        infoLabel.setText("Done!");
    }

    private void waitUntilExecutionIsDone()
    {
        while (!scriptLauncher.isExecutionCompleted())
        {
            try
            {
                Thread.sleep(100);
            }catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
    }

    private Node createSpacer()
    {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        return spacer;
    }


    @Override
    protected void layoutChildren()
    {
        double width = getWidth();
        double height = getHeight();

        double toolbarHeight = toolBar.prefHeight(width);
        double toolbarWidth = toolBar.prefWidth(height);

        layoutInArea(browser,0,0,width - toolbarWidth,height,0, HPos.CENTER, VPos.CENTER);
        layoutInArea(toolBar,width - toolbarWidth,0, toolbarWidth, height, 0, HPos.CENTER, VPos.CENTER);

    }



    @Override
    protected double computePrefWidth(double height)
    {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width)
    {
        return  500;
    }

}
