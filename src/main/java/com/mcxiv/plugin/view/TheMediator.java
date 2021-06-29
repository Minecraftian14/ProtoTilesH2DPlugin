package com.mcxiv.plugin.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mcxiv.app.util.GdxUtil;
import com.mcxiv.plugin.ThePlugin;
import games.rednblack.h2d.common.MsgAPI;
import games.rednblack.h2d.common.ResourcePayloadObject;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

public class TheMediator extends Mediator<ProtoTilesView> {

    public static final String NAME = TheMediator.class.getName();

    private final ThePlugin plugin;

    public TheMediator(ThePlugin plugin) {
        super(NAME, new ProtoTilesView(plugin, null));
        this.plugin = plugin;
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{ThePlugin.EVENT_POPUP_OPEN_REQUEST, MsgAPI.SCENE_LOADED};
    }

    @Override
    public void handleNotification(INotification notification) {
        if (notification.getName().equals(ThePlugin.EVENT_POPUP_OPEN_REQUEST))

            viewComponent.show(plugin.getAPI().getUIStage());
//        for (TextureAtlas.AtlasRegion region : regions) {
//            System.out.println(region.name);
//        }

        else if(notification.getName().equals(MsgAPI.SCENE_LOADED)) {
            DragAndDrop.Target target = new DragAndDrop.Target(viewComponent) {
                @Override
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true;
                }

                @Override
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    ResourcePayloadObject resourcePayloadObject = (ResourcePayloadObject) payload.getObject();

                    if (!resourcePayloadObject.className.endsWith(".ImageResource")) return;

                    String tileName = resourcePayloadObject.name;

                    TextureRegion textureRegion = plugin.getAPI().getSceneLoader().getRm().getTextureRegion(tileName);
                    viewComponent.setViewFor(textureRegion);

//                plugin.facade.sendNotification(TiledPlugin.TILE_ADDED, new Object[]{tileName, type, isAutoGridTarget});
//                plugin.facade.sendNotification(MsgAPI.IMAGE_BUNDLE_DROP, new Object[]{tileName, type, isAutoGridTarget});

                }
            };

            plugin.facade.sendNotification(MsgAPI.ADD_TARGET, target);
        }
    }
}
