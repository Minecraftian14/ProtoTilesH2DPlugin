package com.mcxiv.plugin.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.mcxiv.app.CachedTiles;
import com.mcxiv.app.ProtoTiles;
import com.mcxiv.app.util.GdxUtil;
import com.mcxiv.plugin.ThePlugin;
import com.mcxiv.plugin.view.subView.MainTileView;
import com.mcxiv.plugin.view.subView.TileSetView;
import games.rednblack.h2d.common.MsgAPI;
import games.rednblack.h2d.common.ResourcePayloadObject;
import games.rednblack.h2d.common.UIDraggablePanel;

public class ProtoTilesView extends VisTable {

    private final ThePlugin plugin;

//    private final til;

    public ProtoTilesView(ThePlugin plugin, TextureRegion texture) {
        this.plugin = plugin;
        pad(10);
        setViewFor(texture);
    }

    public void setViewFor(TextureRegion region) {
        clear();

        if (region == null) region = GdxUtil.getBlackTextureRegion(80);

        add(new MainTileView(region)).space(20);
        add(new TileSetView(new CachedTiles(new ProtoTiles(region)))).space(20).row();
        add(getAddButton(region)).space(20).colspan(2);
    }

    private VisTextButton getAddButton(TextureRegion texture) {
        VisTextButton button = new VisTextButton("Add Tile Set");
        return button;
    }

    public void show(Stage uiStage) {
        UIDraggablePanel dialog = new UIDraggablePanel("Proto Tile Maker");
        dialog.setMovable(true);
        dialog.setModal(false);
        dialog.addCloseButton();
        dialog.add(this).fill().expand();
        dialog.pack();
        dialog.show(uiStage);
    }
}
