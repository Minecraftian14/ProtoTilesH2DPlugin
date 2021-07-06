package com.mcxiv.plugin.view.subView;

import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.PackedTiles;
import com.mcxiv.plugin.util.GdxUtil;

public class TileSetView extends VisTable {

    public static final int size = 400;

    public TileSetView(PackedTiles tiles) {

        Texture texture = GdxUtil.getTexture(tiles.getPixels());

        add(new VisImage(texture))
                .width(texture.getWidth() * size * 1f / texture.getHeight())
                .height(size);
    }
}
