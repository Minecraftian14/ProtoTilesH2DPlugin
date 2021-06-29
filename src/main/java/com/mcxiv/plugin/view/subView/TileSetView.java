package com.mcxiv.plugin.view.subView;

import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.CachedTiles;
import com.mcxiv.app.PackedTiles;

public class TileSetView extends VisTable {

    public TileSetView(CachedTiles tiles) {
        PackedTiles pack = new PackedTiles(tiles);

        Texture texture = pack.getTexture();

        add(new VisImage(texture))
                .width(texture.getWidth() * 200f / texture.getHeight())
                .height(200);
    }
}
