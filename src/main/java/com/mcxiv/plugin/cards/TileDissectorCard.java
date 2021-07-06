package com.mcxiv.plugin.cards;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kotcrab.vis.ui.widget.*;
import com.mcxiv.app.TileDissector;
import com.mcxiv.app.util.AppUtil;
import com.mcxiv.app.util.Color;
import com.mcxiv.app.util.functions.FloatFunction;
import com.mcxiv.plugin.util.InterpolationSelectBox;
import com.mcxiv.util.ProcessingCard;

import java.util.function.IntSupplier;

public class TileDissectorCard extends ProcessingCard<Color[][]> {

    private static final String NAME = "Tile Dissector";

    private static final ObjectMap<String, ObjectMap<String, TileDissectorAction>> actionMap = new ObjectMap<>();

    private static void put(String category, String portion, TileDissectorAction action) {
        if (!actionMap.containsKey(category))
            actionMap.put(category, new ObjectMap<>());
        actionMap.get(category).put(portion, action);
    }

    static {
        put("side_rect", "top_rect", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getTopRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put("side_rect", "bot_rect", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getBotRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put("side_rect", "lef_rect", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getLefRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put("side_rect", "rig_rect", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getRigRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));

        put("mid_rect", "hor_rect", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getHorRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Hei. UP", "Hei. DWN"));
        put("mid_rect", "hor_rect_sym", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getHorRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));
        put("mid_rect", "ver_rect", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getVerRect(parameters[0], parameters[1], fade, fadeLength, raw, interpolator, clone, deNull), "Hei. UP", "Hei. DWN"));
        put("mid_rect", "ver_rect_sym", new TileDissectorAction((parameters, fade, fadeLength, raw, interpolator, clone, deNull) ->
                TileDissector.getVerRect(parameters[0], fade, fadeLength, raw, interpolator, clone, deNull), "Height"));


    }

    int width;

    VisTable dissector;

    VisSelectBox<String> fie_categoryBox;
    VisSelectBox<String> fie_portionsBox;
    VisCheckBox fie_fade;
    VisSlider fie_fadeLength;
    InterpolationSelectBox fie_interpolator;

    public TileDissectorCard(int width, ProcessingCard<Color[][]> parent) {
        super(NAME, parent);
        this.width = width;

        VisTable settings = getSettings();
        dissector = new VisTable();

        fie_categoryBox = new VisSelectBox<>();
        fie_categoryBox.setItems(actionMap.keys().toArray());

        fie_portionsBox = new VisSelectBox<>();
        updatePortions();

        fie_categoryBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updatePortions();
            }
        });
        createFields();

        fie_portionsBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                createFields();
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
        settings.add(fie_fadeLength = new VisSlider(0, width, 1, false)).growX().row();
        fie_fadeLength.setValue(width / 2f);

        settings.add(new VisLabel("Interpolation")).padRight(internal_pad);
        settings.add(fie_interpolator = new InterpolationSelectBox()).growX().row();

        settings.addSeparator().colspan(2);

        settings.add(dissector).colspan(2).growX();


    }

    private void updatePortions() {
        fie_portionsBox.setItems(actionMap.get(fie_categoryBox.getSelected()).keys().toArray());
    }

    private void createFields() {
        dissector.clear();
        actionMap.get(fie_categoryBox.getSelected()).get(fie_portionsBox.getSelected()).createFields(dissector, width);
    }

    @Override
    public Color[][] process(Color[][] colors) {
        TileDissectorAction action = actionMap.get(fie_categoryBox.getSelected()).get(fie_portionsBox.getSelected());
        int[] parameters = action.getParameters();
        return action.something.act(parameters, fie_fade.isChecked(), (int) fie_fadeLength.getValue(), colors, fie_interpolator, true, true);
    }

    @Override
    public ProcessingCard<Color[][]> clone() {
        TileDissectorCard card = new TileDissectorCard(width,getParentCard());
        card.fie_categoryBox.setSelected(fie_categoryBox.getSelected());
        card.fie_portionsBox.setSelected(fie_portionsBox.getSelected());
        card.fie_fade.setChecked(fie_fade.isChecked());
        card.fie_fadeLength.setValue(fie_fadeLength.getValue());
        card.fie_interpolator.setSelected(fie_categoryBox.getSelected());
        Array<ProcessingCard<Color[][]>> children = getCards();
        for (int i = 0; i < children.size; i++) card.addCard(children.get(i).clone());
        return card;
    }

    private static class TileDissectorAction {
        private final String[] parameterNames;
        private final something something;
        private final IntSupplier[] parameters;

        private TileDissectorAction(TileDissectorCard.something something, String... parameterNames) {
            this.parameterNames = parameterNames;
            this.something = something;
            parameters = new IntSupplier[parameterNames.length];
        }

        public void createFields(VisTable table, int width) {
            System.out.println(parameterNames.length);
            for (int i = 0; i < parameterNames.length; i++) {

                table.add(new VisLabel(parameterNames[i])).padRight(5).padBottom(5);

                VisSlider slider = new VisSlider(0, width, 1, false);
                slider.setValue(width/2f);
                table.add(slider).padRight(5).padBottom(5).growX().row();

                parameters[i] = () -> (int) slider.getValue();
            }
        }

        public int[] getParameters() {
            int[] p = new int[parameters.length];
            for (int i = 0; i < parameters.length; i++) p[i] = parameters[i].getAsInt();
            return p;
        }
    }

    private interface something {
        Color[][] act(int[] parameters, boolean fade, int fadeLength, Color[][] raw, FloatFunction interpolator, boolean clone, boolean deNull);
    }


}
