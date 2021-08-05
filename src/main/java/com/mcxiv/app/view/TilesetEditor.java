package com.mcxiv.app.view;

import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.view.subView.HUDView;
import com.mcxiv.app.view.subView.TileSetView;

public class TilesetEditor extends VisTable {

    public final HUDView view_HUD;
    public final TileSetView view_TileSet;

    public TilesetEditor() {
        pad(10);
        defaults().space(5);

        view_HUD = new HUDView(this);
        add(view_HUD);

        view_TileSet = new TileSetView(view_HUD);
        add(view_TileSet);

    }

}
