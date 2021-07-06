package com.mcxiv.plugin;

import com.mcxiv.plugin.view.MainViewMediator;
import games.rednblack.h2d.common.MenuAPI;
import games.rednblack.h2d.common.plugins.H2DPluginAdapter;
import net.mountainblade.modular.annotations.Implementation;

@Implementation(authors = "Minecraftian14", version = "0.0.1")
public class ProtoTilesPlugin extends H2DPluginAdapter {

    public static final String NAME = "Proto Tiles Plugin";

    public static final String EVENT_POPUP_OPEN_REQUEST = "EVENT_POPUP_OPEN_REQUEST";
    public static final String EVENT_ADD_IMAGE_REQUEST = "EVENT_ADD_IMAGE_REQUEST";

    public ProtoTilesPlugin() {
        super(NAME);
    }

    @Override
    public void initPlugin() {
        facade.registerMediator(new MainViewMediator(this));
        pluginAPI.addMenuItem(MenuAPI.WINDOW_MENU, "Proto Tiles", EVENT_POPUP_OPEN_REQUEST);



    }
}
