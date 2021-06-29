package com.mcxiv.plugin;

import com.mcxiv.plugin.view.TheMediator;
import games.rednblack.h2d.common.MenuAPI;
import games.rednblack.h2d.common.plugins.H2DPluginAdapter;
import net.mountainblade.modular.annotations.Implementation;

@Implementation(authors = "Minecraftian14", version = "0.0.1")
public class ThePlugin extends H2DPluginAdapter {

    public static final String NAME = "Proto Tiles Plugin";

    public static final String EVENT_POPUP_OPEN_REQUEST = "EVENT_POPUP_OPEN_REQUEST";

    public ThePlugin() {
        super(NAME);
    }

    @Override
    public void initPlugin() {
        facade.registerMediator(new TheMediator(this));
        pluginAPI.addMenuItem(MenuAPI.WINDOW_MENU, "Proto Tiles", EVENT_POPUP_OPEN_REQUEST);
    }
}
