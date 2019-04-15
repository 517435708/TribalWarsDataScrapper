package pl.a517435708.bot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import pl.a517435708.bot.browser.BotBrowser;
import pl.husarz.bot.scrapper.*;
import pl.husarz.bot.scrapper.sql.*;


public class PlemionaBot extends Application
{
    private static String world;

    @Override
    public void start(Stage stage)
    {
        System.out.println("BotV1.0");
        stage.setTitle("Selection Tool");

        BotBrowser root = new BotBrowser(world);
        Scene scene = new Scene(root, 1250, 1000, Color.web("#666970"));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            args = new String[1];
            args[0] = "pl138";
        }

        world = args[0];

        launch(args);
    }
}
