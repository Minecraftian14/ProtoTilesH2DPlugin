package com.mcxiv.plugin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mcxiv.app.PluginTester;
import games.rednblack.editor.renderer.components.TextureRegionComponent;

import static org.junit.jupiter.api.Assertions.*;

class TileComposerViewTest {

    public static void main(String[] args) throws InterruptedException {

        PluginTester.launchTest(null,TileComposerViewTest::run);

    }

    private static void run() {
        Gdx.app.postRunnable(() ->{
            Texture texture = new Texture(Gdx.files.internal("assets/grass.png"));
            PluginTester.setTable(new TileComposerView(texture));
        });
    }

}