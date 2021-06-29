package com.mcxiv.plugin.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mcxiv.app.PluginTester;
import com.mcxiv.app.util.GdxUtil;

class ProtoTilesViewTest {

    public static void main(String[] args) throws InterruptedException {

        PluginTester.launchTest(null, ProtoTilesViewTest::run);

    }

    private static void run() {
        Gdx.app.postRunnable(() -> {
            Texture texture = new Texture(Gdx.files.internal("assets/image.png"));
            TextureRegion region = new TextureRegion(texture);
//            region = null;
            PixmapIO.writePNG(Gdx.files.absolute("D:/image.png"), GdxUtil.getPixmap(region));

            ProtoTilesView protoTilesView = new ProtoTilesView(null, null);
            PluginTester.setTable(protoTilesView);

            protoTilesView.setViewFor(region);

//            texture = new Texture(Gdx.files.internal("assets/grass.png"));
//            region = new TextureRegion(texture);
//            protoTilesView.setViewFor(region);

        });
    }

}