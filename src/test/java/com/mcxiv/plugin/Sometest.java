package com.mcxiv.plugin;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.PluginTester;
import com.mcxiv.app.Test;

public class Sometest extends VisTable {

    public Sometest() {
        Gdx.app.postRunnable(() -> add(new VisImage(new Test().texture)));
    }

    public static void main(String[] args) throws InterruptedException {

        PluginTester.launchTest(null, Sometest::run);

    }

    private static void run() {
        PluginTester.setTable(new Sometest());
    }


}
