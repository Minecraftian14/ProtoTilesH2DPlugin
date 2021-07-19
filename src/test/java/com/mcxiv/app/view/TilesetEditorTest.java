package com.mcxiv.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mcxiv.app.PluginTester;

class TilesetEditorTest {

    public static void
    main(String[] args) throws InterruptedException {

        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title=ProtoTilesView.class.getSimpleName();
        configuration.width=1200;
        configuration.height=600;

        PluginTester.launchTest(configuration, TilesetEditorTest::run);

    }

    private static void run() {
        Gdx.app.postRunnable(() -> {
            PluginTester.setTable(new TilesetEditor());
        });
    }

}