package com.mcxiv.app.cards;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.ui.widget.*;
import com.mcxiv.app.TileDissector;
import com.mcxiv.app.ui.StaticInstanceSelector;
import com.mcxiv.app.util.Color;
import com.mcxiv.app.util.ValueSupplier;
import com.mcxiv.app.util.functions.FloatFunction;
import com.mcxiv.util.ProcessingCard;

public class TileDissectorCard extends ProcessingCard<Color[][]> {

    public static final String side_rect = "side_rect";
    public static final String top_rect = "top_rect";
    public static final String bot_rect = "bot_rect";
    public static final String lef_rect = "lef_rect";
    public static final String rig_rect = "rig_rect";

    public static final String mid_rect = "mid_rect";
    public static final String hor_rect = "hor_rect";
    public static final String hor_rect_sym = "hor_rect_sym";
    public static final String ver_rect = "ver_rect";
    public static final String ver_rect_sym = "ver_rect_sym";
    public static final String inner_rect = "inner_rect";

    public static final String cor_rect = "cor_rect";
    public static final String top_lef = "top_lef";
    public static final String top_rig = "top_rig";
    public static final String bot_lef = "bot_lef";
    public static final String bot_rig = "bot_rig";

    public static final String __side_tri = "!!side_tri";
    public static final String top = "top";
    public static final String lef = "lef";
    public static final String bot = "bot";
    public static final String rig = "rig";

    public static final String side_tri = "side_tri";

    public static final String __cor_tri = "!!cor_tri";

    public static final String cor_tri = "cor_tri";

    public static final String side_poly = "side_poly";
    public static final String cw_top = "cw_top";
    public static final String cw_rig = "cw_rig";
    public static final String cw_bot = "cw_bot";
    public static final String cw_lef = "cw_lef";

    public static final String acw_top = "acw_top";
    public static final String acw_rig = "acw_rig";
    public static final String acw_bot = "acw_bot";
    public static final String acw_lef = "acw_lef";


    private static final String NAME = "Tile Dissector";

    private static final ObjectMap<String, ObjectMap<String, TileDissectorAction>> actionMap = new ObjectMap<>();

    private static void put(String category, String portion, TileDissectorAction action) {
        if (!actionMap.containsKey(category))
            actionMap.put(category, new ObjectMap<>());
        actionMap.get(category).put(portion, action);
    }


    static {

        // RECTANGLES WHICH ARE ALIGNED BY THE SIDES TOUCHING THREE SIDES

        put(side_rect, top_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put(side_rect, bot_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put(side_rect, lef_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getLefRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));
        put(side_rect, rig_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRigRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));


        // RECTANGLES WHICH LYE IN BETWEEN THE IMAGE BUT TOUCHES TWO OPPOSITE SIDES

        put(mid_rect, hor_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getHorRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Hei. UP", "Hei. DWN"));
        put(mid_rect, hor_rect_sym, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getHorRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put(mid_rect, ver_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getVerRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Wid. LEF", "Wid. RIG"));
        put(mid_rect, ver_rect_sym, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getVerRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));


        // RECTANGLES WHICH LYE INSIDE THE IMAGE AND TOUCHES NO SIDES

        put(mid_rect, inner_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRect(parameters[0], parameters[1], parameters[2], parameters[3], fade, fadeLength, raw, interpolator, clone, deNull),
                "Hei. UP", "Hei. DWN", "Wid. LEF", "Wid. RIG"));

        put(mid_rect, inner_rect, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getCenRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Size"));


        // RECTANGLES WHICH LYE ALONG THE CORNERS TOUCHING TWO SIDES

        put(cor_rect, top_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopLefRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(cor_rect, top_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopRigRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(cor_rect, bot_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotLefRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(cor_rect, bot_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotRigRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));


        // TRIANGLES WHICH ARE ALIGNED BY THE SIDES BUT TOUCH ONLY ONE SIDE

        put(__side_tri, top, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Abscissa", "Ordinate"));

        put(__side_tri, lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getLefTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Abscissa", "Ordinate"));

        put(__side_tri, bot, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Abscissa", "Ordinate"));

        put(__side_tri, rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRigTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Abscissa", "Ordinate"));

        put(side_tri, top, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopTri(fade, fadeLength, raw, interpolator, clone, deNull)));

        put(side_tri, lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getLefTri(fade, fadeLength, raw, interpolator, clone, deNull)));

        put(side_tri, bot, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotTri(fade, fadeLength, raw, interpolator, clone, deNull)));

        put(side_tri, rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRigTri(fade, fadeLength, raw, interpolator, clone, deNull)));


        // TRIANGLES WHICH ARE ALIGNED BY THE CORNERS AND TOUCH ONLY TWO SIDES

        put(__cor_tri, top_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopLefTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(__cor_tri, top_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopRigTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(__cor_tri, bot_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotLefTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(__cor_tri, bot_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotRigTri(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Width", "Height"));

        put(cor_tri, top_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopLefTri(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));

        put(cor_tri, top_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopRigTri(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));

        put(cor_tri, bot_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotLefTri(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));

        put(cor_tri, bot_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotRigTri(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Width"));


        // THAT STRANGE POLYGON WHICH TOUCHES 2 SIDES AND HAS ONE INTERNAL ANGLE AS 135 DEGREES

        put(side_poly, cw_top, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopLefPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

        put(side_poly, cw_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRigTopPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

        put(side_poly, cw_bot, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotRigPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

        put(side_poly, cw_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getLefBotPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));


        put(side_poly, acw_top, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopRigPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

        put(side_poly, acw_rig, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRigBotPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

        put(side_poly, acw_bot, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotLefPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

        put(side_poly, acw_lef, new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getLefTopPoly(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Height", "Width"));

    }

    VisTable dissector;

    VisSelectBox<String> fie_categoryBox;
    VisSelectBox<String> fie_portionsBox;
    VisCheckBox fie_fade;
    VisSlider fie_fadeLength;
    StaticInstanceSelector<Interpolation> fie_interpolator;

    public TileDissectorCard() {
        this("side_rect", "top_rect");
    }

    public TileDissectorCard(String category, String portion, int...defaults) {
        super(NAME);

        VisTable settings = getSettings();
        dissector = new VisTable();

        fie_categoryBox = new VisSelectBox<>();
        fie_categoryBox.setItems(actionMap.keys().toArray());
        fie_categoryBox.setSelected(category);

        fie_portionsBox = new VisSelectBox<>();
        updatePortions();
        fie_portionsBox.setSelected(portion);

        fie_categoryBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updatePortions();
            }
        });
        createFields(defaults);

        fie_portionsBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                createFields(defaults);
            }
        });

        settings.defaults().padBottom(internal_pad);

        settings.add(new VisLabel("Category")).padRight(internal_pad);
        settings.add(fie_categoryBox).growX().row();

        settings.add(new VisLabel("Portion")).padRight(internal_pad);
        settings.add(fie_portionsBox).growX().row();

        settings.addSeparator().colspan(2);

        settings.add(new VisLabel("Fade?")).padRight(internal_pad);
        settings.add(fie_fade = new VisCheckBox("")).growX().row();
        fie_fade.setChecked(true);

        settings.add(new VisLabel("Fade Length")).padRight(internal_pad);
        settings.add(fie_fadeLength = new VisSlider(0, 120, 1, false)).growX().row();
        fie_fadeLength.setValue(50);

        settings.add(new VisLabel("Interpolation")).padRight(internal_pad);
        settings.add(fie_interpolator = new StaticInstanceSelector<>(Interpolation.class)).growX().row();
        fie_interpolator.setSelected("pow3In");

        settings.addSeparator().colspan(2);

        settings.add(dissector).colspan(2).growX();
    }

    private void updatePortions() {
        fie_portionsBox.setItems(actionMap.get(fie_categoryBox.getSelected()).keys().toArray());
    }

    private void createFields(int...defaults) {
        dissector.clear();
        actionMap.get(fie_categoryBox.getSelected()).get(fie_portionsBox.getSelected()).createFields(dissector, defaults);
    }

    @Override
    public Color[][] process(Color[][] colors) {
        colors = super.process(colors);
        TileDissectorAction action = actionMap.get(fie_categoryBox.getSelected()).get(fie_portionsBox.getSelected());
        int[] parameters = action.getParameters(colors.length);
        return action.tileDissectorFunction.act(parameters, fie_fade.isChecked(), (int) fie_fadeLength.getValue(), colors, fie_interpolator.getSelectedT()::apply, true, true);
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        TileDissectorCard card = ProcessingCard.clone(this, new TileDissectorCard(fie_categoryBox.getSelected(), fie_portionsBox.getSelected()));
        card.fie_fade.setChecked(fie_fade.isChecked());
        card.fie_fadeLength.setValue(fie_fadeLength.getValue());
        card.fie_interpolator.setSelected(fie_categoryBox.getSelected());
        return card;
    }

    private static class TileDissectorAction {
        private final String[] parameterNames;
        private final TileDissectorFunction tileDissectorFunction;
        private final ValueSupplier[] parameters;

        private TileDissectorAction(TileDissectorFunction tileDissectorFunction, String... parameterNames) {
            this.parameterNames = parameterNames;
            this.tileDissectorFunction = tileDissectorFunction;
            parameters = new ValueSupplier[parameterNames.length];
        }

        public void createFields(VisTable table, int... defaults) {
            for (int i = 0; i < parameterNames.length; i++) {

                table.add(new VisLabel(parameterNames[i])).padRight(5).padBottom(5);

                VisSlider slider = new VisSlider(0, 120, 1, false);
                slider.setValue(i < defaults.length ? defaults[i] : 50);
                table.add(slider).padRight(5).padBottom(5).growX().row();

                parameters[i] = slider::getValue;
            }
        }

        public int[] getParameters(int width) {
            int[] p = new int[parameters.length];
            for (int i = 0; i < parameters.length; i++) p[i] = parameters[i].getValue(width);
            return p;
        }
    }

    private interface TileDissectorFunction {
        Color[][] act(int[] parameters, boolean fade, int fadeLength, Color[][] raw, FloatFunction interpolator, boolean clone, boolean deNull);
    }

}
