package com.mcxiv.plugin.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mcxiv.plugin.ProtoTilesPlugin;
import games.rednblack.h2d.common.MsgAPI;
import games.rednblack.h2d.common.ResourcePayloadObject;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

public class MainViewMediator extends Mediator<ProtoTilesView> {

    public static final String NAME = MainViewMediator.class.getName();

    private final ProtoTilesPlugin plugin;

    public MainViewMediator(ProtoTilesPlugin plugin) {
        super(NAME, new ProtoTilesView(plugin, null, "null"));
        this.plugin = plugin;
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{ProtoTilesPlugin.EVENT_POPUP_OPEN_REQUEST, ProtoTilesPlugin.EVENT_ADD_IMAGE_REQUEST, MsgAPI.SCENE_LOADED};
    }

    @Override
    public void handleNotification(INotification notification) {
        if (notification.getName().equals(ProtoTilesPlugin.EVENT_POPUP_OPEN_REQUEST))
            viewComponent.show(plugin.getAPI().getUIStage());

        else if (notification.getName().equals(MsgAPI.SCENE_LOADED)) {
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
                    viewComponent.setViewFor(textureRegion, tileName);

//                plugin.facade.sendNotification(TiledPlugin.TILE_ADDED, new Object[]{tileName, type, isAutoGridTarget});
//                plugin.facade.sendNotification(MsgAPI.IMAGE_BUNDLE_DROP, new Object[]{tileName, type, isAutoGridTarget});

                }
            };

            plugin.facade.sendNotification(MsgAPI.ADD_TARGET, target);
        }

        else if (notification.getName().equals(ProtoTilesPlugin.EVENT_ADD_IMAGE_REQUEST)) {
            String tilesetName = notification.getBody();
            Gdx.app.postRunnable(() -> {
                plugin.getAPI().getCurrentProjectInfoVO().imagesPacks.get("main").regions.add(tilesetName);
//                    plugin.getAPI().saveProject();
                plugin.getAPI().getFacade().sendNotification(MsgAPI.ACTION_REPACK);
                plugin.getAPI().reLoadProject();
            });
        }
    }
}
