package com.mcxiv.app.view.subView;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTable;
import com.mcxiv.app.cards.FinalActionCard;
import com.mcxiv.app.cards.MixActionCard;
import com.mcxiv.app.cards.RotationCard;
import com.mcxiv.app.cards.TileDissectorCard;
import com.mcxiv.app.util.GdxUtil;

import static com.mcxiv.app.cards.TileDissectorCard.*;

public class TileSetView extends VisTable {

    public static final int size = 400;

    public final FinalActionCard topFlat;
    public final FinalActionCard rigFlat;
    public final FinalActionCard lefFlat;
    public final FinalActionCard botFlat;

    public final FinalActionCard topCap;
    public final FinalActionCard rigCap;
    public final FinalActionCard lefCap;
    public final FinalActionCard botCap;

    public final FinalActionCard topRigCorner;
    public final FinalActionCard topLefCorner;
    public final FinalActionCard botRigCorner;
    public final FinalActionCard botLefCorner;

    public final FinalActionCard topRigInnerCorner;
    public final FinalActionCard topLefInnerCorner;
    public final FinalActionCard botRigInnerCorner;
    public final FinalActionCard botLefInnerCorner;

    public final FinalActionCard topRigDoubleCorner;
    public final FinalActionCard topLefDoubleCorner;
    public final FinalActionCard botRigDoubleCorner;
    public final FinalActionCard botLefDoubleCorner;

    public final FinalActionCard topFlatRigInner;
    public final FinalActionCard topFlatLefInner;
    public final FinalActionCard rigFlatTopInner;
    public final FinalActionCard rigFlatBotInner;
    public final FinalActionCard botFlatRigInner;
    public final FinalActionCard botFlatLefInner;
    public final FinalActionCard lefFlatTopInner;
    public final FinalActionCard lefFlatBotInner;

    public final FinalActionCard topFlatOppoInner;
    public final FinalActionCard rigFlatOppoInner;
    public final FinalActionCard botFlatOppoInner;
    public final FinalActionCard lefFlatOppoInner;

    public final FinalActionCard topOppoInner;
    public final FinalActionCard rigOppoInner;
    public final FinalActionCard botOppoInner;
    public final FinalActionCard lefOppoInner;

    public final FinalActionCard topRigAndBotLefInner;
    public final FinalActionCard topLefAndBotRigInner;

    public final FinalActionCard exceptTopRigInnerCorner;
    public final FinalActionCard exceptTopLefInnerCorner;
    public final FinalActionCard exceptBotRigInnerCorner;
    public final FinalActionCard exceptBotLefInnerCorner;

    public final FinalActionCard center;
    public final FinalActionCard topBotFlat;
    public final FinalActionCard rigLefFlat;
    public final FinalActionCard allFlat;
    public final FinalActionCard allInner;

    //    public final Array<ProcessingCard<Color[][]>> cards = new Array<>();
    public final Array<InputListener> listeners = new Array<>();

    public HUDView hud;

    private final VisDialog card_editor;

    public TileSetView(HUDView hud) {

        this.hud = hud;

        MixActionCard mix;
        TileDissectorCard dissector;

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////// SHIT
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //<editor-fold defaultstate="collapsed" desc="SHIT">

        topFlat = new FinalActionCard("Top Flat");
        rigFlat = new FinalActionCard("Right Flat", new RotationCard(90));
        botFlat = new FinalActionCard("Bottom Flat", new RotationCard(180));
        lefFlat = new FinalActionCard("Left Flat", new RotationCard(270));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new TileDissectorCard(side_tri, top),
                new RotationCard(270, new TileDissectorCard(side_poly, cw_top)),
                new RotationCard(90, new TileDissectorCard(side_poly, acw_top))
        );

        topCap = new FinalActionCard("Top Cap", mix.clone());

        rigCap = new FinalActionCard("Right Cap", new RotationCard(90, mix.clone()));

        botCap = new FinalActionCard("Bottom Cap", new RotationCard(180, mix.clone()));

        lefCap = new FinalActionCard("Left Cap", new RotationCard(270, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new RotationCard(270, new TileDissectorCard(cor_tri, top_lef)),
                new TileDissectorCard(cor_tri, top_rig)
        );

        topLefCorner = new FinalActionCard("Top Left Corner", mix.clone());

        topRigCorner = new FinalActionCard("Top Right Corner", new RotationCard(90, mix.clone()));

        botLefCorner = new FinalActionCard("Bottom Left Corner", new RotationCard(270, mix.clone()));

        botRigCorner = new FinalActionCard("Bottom Right Corner", new RotationCard(180, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new TileDissectorCard(cor_tri, bot_lef),
                new RotationCard(270, new TileDissectorCard(cor_tri, bot_rig))
        );

        topLefInnerCorner = new FinalActionCard("Top Left Inner", mix.clone());

        botLefInnerCorner = new FinalActionCard("Bottom Left Inner", new RotationCard(270, mix.clone()));

        topRigInnerCorner = new FinalActionCard("Top Right Inner", new RotationCard(90, mix.clone()));

        botRigInnerCorner = new FinalActionCard("Bottom Right Inner", new RotationCard(180, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new TileDissectorCard(side_poly, acw_top) {{
                    addCard(topLefCorner.clone());
                }},
                new TileDissectorCard(side_poly, cw_lef) {{
                    addCard(topLefCorner.clone());
                }},
                new RotationCard(180, new TileDissectorCard(cor_rect, top_lef) {{
                    addCard(topLefInnerCorner.clone());
                }})
        );

        topLefDoubleCorner = new FinalActionCard("Top Left Double Corner", mix.clone());

        topRigDoubleCorner = new FinalActionCard("Top Right Double Corner", new RotationCard(90, mix.clone()));

        botRigDoubleCorner = new FinalActionCard("Bottom Right Double Corner", new RotationCard(180, mix.clone()));

        botLefDoubleCorner = new FinalActionCard("Bottom Left Double Corner", new RotationCard(270, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new TileDissectorCard(side_rect, top_rect),
                new RotationCard(180, new TileDissectorCard(side_rect, top_rect) {{
                    addCard(topLefInnerCorner.clone());
                }})
        );

        topFlatRigInner = new FinalActionCard("Top Flat Right Inner", mix.clone());

        rigFlatBotInner = new FinalActionCard("Right Flat Bottom Inner", new RotationCard(90, mix.clone()));

        botFlatLefInner = new FinalActionCard("Bottom Flat Left Inner", new RotationCard(180, mix.clone()));

        lefFlatTopInner = new FinalActionCard("Left Flat Top Inner", new RotationCard(270, mix.clone()));

        mix = new MixActionCard(
                new TileDissectorCard(side_rect, top_rect),
                new RotationCard(270, new TileDissectorCard(side_rect, lef_rect) {{
                    addCard(topLefInnerCorner.clone());
                }})
        );

        topFlatLefInner = new FinalActionCard("Top Flat Left Inner", mix.clone());

        rigFlatTopInner = new FinalActionCard("Right Flat Top Inner", new RotationCard(90, mix.clone()));

        botFlatRigInner = new FinalActionCard("Bottom Flat Right Inner", new RotationCard(180, mix.clone()));

        lefFlatBotInner = new FinalActionCard("Left Flat Bottom Inner", new RotationCard(270, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new TileDissectorCard(side_rect, top_rect),
                new TileDissectorCard(cor_rect, bot_lef) {{
                    addCard(topFlatLefInner.clone());
                }},
                new TileDissectorCard(cor_rect, bot_rig) {{
                    addCard(topFlatRigInner.clone());
                }}
        );

        topFlatOppoInner = new FinalActionCard("Top Flat Opposite Corners", mix);
        rigFlatOppoInner = new FinalActionCard("Right Flat Opposite Corners", new RotationCard(90, mix.clone()));
        botFlatOppoInner = new FinalActionCard("Bottom Flat Opposite Corners", new RotationCard(180, mix.clone()));
        lefFlatOppoInner = new FinalActionCard("Left Flat Opposite Corners", new RotationCard(270, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        mix = new MixActionCard(
                new RotationCard(180, new TileDissectorCard(side_rect, bot_rect)),
                new TileDissectorCard(side_rect, bot_rect) {{
                    addCard(topFlatOppoInner.clone());
                }}
        );

        topOppoInner = new FinalActionCard("Top Opposite Inner Corners", mix);
        rigOppoInner = new FinalActionCard("Right Opposite Inner Corners", new RotationCard(90, mix.clone()));
        botOppoInner = new FinalActionCard("Bottom Opposite Inner Corners", new RotationCard(180, mix.clone()));
        lefOppoInner = new FinalActionCard("Left Opposite Inner Corners", new RotationCard(270, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        dissector = new TileDissectorCard(side_rect, lef_rect);
        dissector.addCard(topLefInnerCorner.clone());
        mix = new MixActionCard(
                dissector,
                new RotationCard(180, dissector.clone())
        );
        topLefAndBotRigInner = new FinalActionCard("Top Left and Bottom Right Corner", mix);

        topRigAndBotLefInner = new FinalActionCard("Top Right and Bottom Left Corner",
                new RotationCard(90, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        RotationCard rotation = new RotationCard(180, topOppoInner.clone());
        mix = new MixActionCard(
                new RotationCard(270, new TileDissectorCard(cor_tri, top_lef) {{
                    addCard(rotation.clone());
                }}),
                new TileDissectorCard(cor_tri, top_rig) {{
                    addCard(rotation.clone());
                }}
        );

        exceptBotRigInnerCorner = new FinalActionCard("Inner Corners except Bottom Right", mix.clone());
        exceptBotLefInnerCorner = new FinalActionCard("Inner Corners except Bottom Left", new RotationCard(90, mix.clone()));
        exceptTopLefInnerCorner = new FinalActionCard("Inner Corners except Top Left", new RotationCard(180, mix.clone()));
        exceptTopRigInnerCorner = new FinalActionCard("Inner Corners except Top Right", new RotationCard(270, mix.clone()));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        dissector = new TileDissectorCard(side_rect, bot_rect);
        center = new FinalActionCard("Center", new MixActionCard(
                dissector,
                new RotationCard(180, dissector.clone())
        ));

        dissector = new TileDissectorCard(side_rect, top_rect);
        topBotFlat = new FinalActionCard("Top Bottom Flat", new MixActionCard(
                dissector.clone(),
                new RotationCard(180, dissector.clone())
        ));

        rigLefFlat = new FinalActionCard("Right Left Flat", new MixActionCard(
                new RotationCard(90, dissector.clone()),
                new RotationCard(270, dissector.clone())
        ));

        dissector = new TileDissectorCard(side_tri, top);
        allFlat = new FinalActionCard("All Flat", new MixActionCard(
                dissector,
                new RotationCard(90, dissector.clone()),
                new RotationCard(180, dissector.clone()),
                new RotationCard(270, dissector.clone())
        ));

        dissector = new TileDissectorCard(side_rect, bot);
        dissector.addCard(topOppoInner.clone());
        allInner = new FinalActionCard("All Inner Corners", new MixActionCard(
                dissector,
                new RotationCard(180, dissector.clone())
        ));

        //</editor-fold>

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        for (Field field : TileSetView.class.getFields()) {
//            if (ProcessingCard.class.isAssignableFrom(field.getType())) {
//                try {
//                    cards.add(((ProcessingCard) field.get(this)));
//                } catch (IllegalAccessException e) {
//                    System.out.println("Error trying to access this field " + field);
//                    System.out.println(e.getMessage());
//                }
//            }
//        }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        add(getButton(topLefCorner));
        add(getButton(topFlat));
        add(getButton(topRigCorner));
        add(getButton(topCap));

        add(getButton(topLefDoubleCorner));
        add(getButton(topFlatRigInner));
        add(getButton(topFlatLefInner));
        add(getButton(topRigDoubleCorner));

        add(getButton(topFlatOppoInner));
        add(getButton(topRigAndBotLefInner));
        row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        add(getButton(lefFlat));
        add(getButton(center));
        add(getButton(rigFlat));
        add(getButton(rigLefFlat));

        add(getButton(lefFlatBotInner));
        add(getButton(botRigInnerCorner));
        add(getButton(botLefInnerCorner));
        add(getButton(rigFlatBotInner));

        add(getButton(topOppoInner));
        add(getButton(topLefAndBotRigInner));
        row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        add(getButton(botLefCorner));
        add(getButton(botFlat));
        add(getButton(botRigCorner));
        add(getButton(botCap));

        add(getButton(lefFlatTopInner));
        add(getButton(topRigInnerCorner));
        add(getButton(topLefInnerCorner));
        add(getButton(rigFlatTopInner));

        add(getButton(botOppoInner));
        add(getButton(exceptBotRigInnerCorner));
        add(getButton(exceptBotLefInnerCorner));
        row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        add(getButton(lefCap));
        add(getButton(topBotFlat));
        add(getButton(rigCap));
        add(getButton(allFlat));

        add(getButton(botLefDoubleCorner));
        add(getButton(botFlatRigInner));
        add(getButton(botFlatLefInner));
        add(getButton(botRigDoubleCorner));

        add(getButton(botFlatOppoInner));
        add(getButton(exceptTopRigInnerCorner));
        add(getButton(exceptTopLefInnerCorner));
        row();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        add();
        add();
        add();
        add();

        add(getButton(lefFlatOppoInner));
        add(getButton(lefOppoInner));
        add(getButton(rigOppoInner));
        add(getButton(rigFlatOppoInner));

        add(getButton(allInner));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        card_editor = new VisDialog("Card Editor");

    }

    public void update() {
        listeners.forEach(inputListener -> inputListener.keyDown(null, Input.Keys.ENTER));
    }

    private Actor getButton(FinalActionCard actionCard) {
        VisImageButton button = new VisImageButton(new TextureRegionDrawable(GdxUtil.getTexture(actionCard.process(hud.getSelected()))));
        InputListener inputListener = new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                System.out.println("YO " + event + keycode);
                if (keycode == Input.Keys.ENTER) {
                    card_editor.fadeOut();
                    button.getStyle().imageUp = new TextureRegionDrawable(GdxUtil.resizeTo(GdxUtil.getPixmap(GdxUtil.getTexture(actionCard.process(hud.getSelected()))), 80));
                    return true;
                }
                return false;
            }
        };
        listeners.add(inputListener);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                card_editor.clear();
                card_editor.add(actionCard).grow();
                card_editor.pack();
                if (card_editor.getHeight() > getHeight()) card_editor.setHeight(getHeight());
                card_editor.centerWindow();
                card_editor.addListener(inputListener);
                addActor(card_editor.fadeIn());
            }
        });
        return button;
    }

}
