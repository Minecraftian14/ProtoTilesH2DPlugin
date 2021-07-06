package com.mcxiv.util;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mcxiv.app.PluginTester;

import static org.junit.jupiter.api.Assertions.*;

class ProcessingCardTest {

    public static void main(String[] args) throws InterruptedException {
        var config = new LwjglApplicationConfiguration();
        config.title = "Hyperlap2D Plugin Tester";
        config.width = 400;
        config.height = 800;
        PluginTester.launchTest(null, ProcessingCardTest::run);
    }

    private static void run() {
//        PluginTester.setTable(new ProcessingCard());
    }

}