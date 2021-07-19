package com.mcxiv.app.view.subView;

import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.PackedTiles;
import com.mcxiv.app.TileDissector;
import com.mcxiv.app.cards.FinalActionCard;
import com.mcxiv.app.cards.MixActionCard;
import com.mcxiv.app.cards.TileDissectorCard;
import com.mcxiv.app.util.Color;
import com.mcxiv.app.util.GdxUtil;

public class TileSetView extends VisTable {

    public static final int size = 400;

    public static final FinalActionCard topRig;

    static {
        MixActionCard mix;
        TileDissectorCard dissector;

        topRig = new FinalActionCard("Top Right Corner");
        mix = new MixActionCard(topRig);
        dissector = new TileDissectorCard();
        mix.addCard(dissector);


    }

    public TileSetView(Color[][] original) {


    }

    public TileSetView(PackedTiles tiles) {

        Texture texture = GdxUtil.getTexture(tiles.getPixels());

        add(new VisImage(texture))
                .width(texture.getWidth() * size * 1f / texture.getHeight())
                .height(size);
    }
}
