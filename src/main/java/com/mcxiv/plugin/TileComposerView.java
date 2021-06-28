package com.mcxiv.plugin;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.CachedTiles;
import com.mcxiv.app.ProtoTiles;
import com.mcxiv.app.util.GdxUtil;

public class TileComposerView extends VisTable {

    Color[][] Invis;

    public TileComposerView(Texture texture) {

        addNewTable(texture);

    }

    private void addNewTable(Texture texture) {
        CachedTiles tiles = new CachedTiles(new ProtoTiles(texture));

        Invis = new Color[tiles.topFlat.length][tiles.topFlat[0].length];
        for (int i = 0; i < Invis.length; i++) for (int j = 0; j < Invis[0].length; j++) Invis[i][j] = Color.WHITE;

        // ROW 1

        addImage(tiles.topLefCorner);
        addImage(tiles.topFlat);
        addImage(tiles.topRigCorner);

        addImage(tiles.topCap);

        addImage(tiles.topLefDoubleCorner);
        addImage(tiles.topFlatRigInner);
        addImage(tiles.topFlatLefInner);
        addImage(tiles.topRigDoubleCorner);

        addImage(tiles.topFlatOppoInner);
        addImage(tiles.topRigAndBotLefOppoInner);
        addImage(Invis);

        row(); // ROW 2

        addImage(tiles.lefFlat);
        addImage(tiles.center);
        addImage(tiles.rigFlat);

        addImage(tiles.rigLefFlat);

        addImage(tiles.lefFlatBotInner);
        addImage(tiles.botRigInnerCorner);
        addImage(tiles.botLefInnerCorner);
        addImage(tiles.rigFlatBotInner);

        addImage(tiles.topOppoInner);
        addImage(tiles.topLefAndBotRigOppoInner);
        addImage(Invis);

        row(); // ROW 3

        addImage(tiles.botLefCorner);
        addImage(tiles.botFlat);
        addImage(tiles.botRigCorner);

        addImage(tiles.botCap);

        addImage(tiles.lefFlatTopInner);
        addImage(tiles.topRigInnerCorner);
        addImage(tiles.topLefInnerCorner);
        addImage(tiles.rigFlatTopInner);

        addImage(tiles.botOppoInner);
        addImage(tiles.exceptBotRigInnerCorner);
        addImage(tiles.exceptBotLefInnerCorner);

        row(); // ROW 4

        addImage(tiles.lefCap);
        addImage(tiles.topBotFlat);
        addImage(tiles.rigCap);

        addImage(tiles.allFlat);

        addImage(tiles.botLefDoubleCorner);
        addImage(tiles.botFlatRigInner);
        addImage(tiles.botFlatLefInner);
        addImage(tiles.botRigDoubleCorner);

        addImage(tiles.botFlatOppoInner);
        addImage(tiles.exceptTopRigInnerCorner);
        addImage(tiles.exceptTopLefInnerCorner);

        row(); // ROW 5

        addImage(Invis);
        addImage(Invis);
        addImage(Invis);

        addImage(Invis);

        addImage(tiles.lefFlatOppoInner);
        addImage(tiles.lefOppoInner);
        addImage(tiles.rigOppoInner);
        addImage(tiles.rigFlatOppoInner);

        addImage(tiles.allInner);
        addImage(Invis);
        addImage(Invis);
    }

    private void addImage(Color[][] pixels) {
        add(new VisImage(GdxUtil.getTexture(pixels))).space(5);
    }

    private void addOldTable(Texture texture) {
        CachedTiles tiles = new CachedTiles(new ProtoTiles(texture));

        VisTable table = new VisTable();

        table.add(new VisImage(GdxUtil.getTexture(tiles.topLefCorner))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.topFlat))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.topRigCorner))).space(5).row();

        table.add(new VisImage(GdxUtil.getTexture(tiles.lefFlat))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.rigFlat))).space(5).row();

        table.add(new VisImage(GdxUtil.getTexture(tiles.botLefCorner))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.botFlat))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.botRigCorner))).space(5);

        add(table).padRight(20).padLeft(20);

        table = new VisTable();

        table.add(new VisImage(GdxUtil.getTexture(tiles.botRigInnerCorner))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.botFlat))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.botLefInnerCorner))).space(5).row();

        table.add(new VisImage(GdxUtil.getTexture(tiles.rigFlat))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.lefFlat))).space(5).row();

        table.add(new VisImage(GdxUtil.getTexture(tiles.topRigInnerCorner))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.topFlat))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.topLefInnerCorner))).space(5).row();

        add(table).padRight(20);

        table = new VisTable();

        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.topCap))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5).row();

        table.add(new VisImage(GdxUtil.getTexture(tiles.lefCap))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.rigCap))).space(5).row();

        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.botCap))).space(5);
        table.add(new VisImage(GdxUtil.getTexture(tiles.center))).space(5).row();

        add(table).padRight(20);
    }
}
