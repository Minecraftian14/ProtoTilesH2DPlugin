package com.mcxiv.plugin.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.PluginTester;
import com.mcxiv.app.util.Color;
import com.mcxiv.plugin.util.GdxUtil;
import com.mcxiv.util.ProcessingCard;

import static org.junit.jupiter.api.Assertions.*;

class FinalActionCardTest {

    public static void main(String[] args) throws InterruptedException {
        var config = new LwjglApplicationConfiguration();
        config.title = "Hyperlap2D Plugin Tester";
        config.width = 400;
        config.height = 600;
        PluginTester.launchTest(config, () -> Gdx.app.postRunnable(FinalActionCardTest::run));
    }

    private static void run() {

        Texture texture = new Texture(Gdx.files.internal("assets/grass.png"));
        Color[][] pixels = GdxUtil.getPixels(texture);

        VisTable content = new VisTable();

        VisImage visImage = new VisImage(texture);
        content.add(visImage).space(20).row();

        FinalActionCard testing_tiles = new FinalActionCard("Testing FinalAction");

        MixActionCard mixActionCard = new MixActionCard(testing_tiles);

        mixActionCard.addCard(new TileDissectorCard(pixels.length, mixActionCard));
        mixActionCard.addCard(new TileDissectorCard(pixels.length, mixActionCard));

        testing_tiles.addCard(mixActionCard);

        content.add(testing_tiles);

        PluginTester.setTable(content);

        visImage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                visImage.setDrawable(GdxUtil.getTexture(testing_tiles.process(pixels)));
            }
        });


    }

}