package com.mcxiv.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mcxiv.util.ProcessingCard;

class ProcessingCardTest extends ProcessingCard {

    public ProcessingCardTest(String title) {
        super(title);
    }

    public ProcessingCardTest(String title, ProcessingCard parent) {
        super(title, parent);
    }

    public static void main(String[] args) throws InterruptedException {
        var config = new LwjglApplicationConfiguration();
        config.title = "Hyperlap2D Plugin Tester";
        config.width = 400;
        config.height = 600;
        PluginTester.launchTest(config, () -> Gdx.app.postRunnable(ProcessingCardTest::run));
    }

    private static void run() {
        ProcessingCard testing_tiles = new ProcessingCardTest("Testing Tiles");
        testing_tiles.addCard(get(1, testing_tiles));
        testing_tiles.addCard(get(2, testing_tiles));
        testing_tiles.addCard(get(3, testing_tiles));
        PluginTester.setTable(testing_tiles);
    }

    private static ProcessingCard get(int i, ProcessingCard parent) {
        if (i == 0) return null;
        ProcessingCard card = new ProcessingCardTest(getTitle(), parent);
        for (int j = 0; j < i; j++) {
            ProcessingCard hell = get(i - 1, card);
            if (hell != null) card.addCard(hell);
        }
        return card;
    }


    static String[] names = new String[]{"Processing...", "Is that a joke?", "Wonderful Lmao", "Seven lollipop", "Starlight Dinner"};

    private static String getTitle() {
        return names[(int) (names.length * Math.random())];
    }

    @Override
    public ProcessingCard clone() {
        return null;
    }
}