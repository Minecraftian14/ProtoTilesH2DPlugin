package com.mcxiv.plugin.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mcxiv.app.PluginTester;

class ProtoTilesViewTest {

    public static void
    main(String[] args) throws InterruptedException {

        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title=ProtoTilesView.class.getSimpleName();
        configuration.width=1200;
        configuration.height=600;

        PluginTester.launchTest(configuration, ProtoTilesViewTest::run);

    }

    private static void run() {
        Gdx.app.postRunnable(() -> {
            Texture texture = new Texture(Gdx.files.internal("assets/grass.png"));
            TextureRegion region = new TextureRegion(texture);
//            region = null;
//            PixmapIO.writePNG(Gdx.files.absolute("D:/image.png"), GdxUtil.getPixmap(region));

            ProtoTilesView protoTilesView = new ProtoTilesView(null, region, "null");
            PluginTester.setTable(protoTilesView);

//            protoTilesView.setViewFor(region, "null");

//            texture = new Texture(Gdx.files.internal("assets/grass.png"));
//            region = new TextureRegion(texture);
//            protoTilesView.setViewFor(region);

        });
    }

}