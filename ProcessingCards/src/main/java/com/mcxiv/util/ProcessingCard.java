package com.mcxiv.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.*;

public abstract class ProcessingCard<Type> extends VisTable {

    protected final int internal_pad = 5;

    private ProcessingCard<Type> parent;
    private final Array<ProcessingCard<Type>> children = new Array<>();

    private VisTable header;
    private VisTable settings;
    private VisTable content;
    private boolean isCollapsed = false;

    private Actor addButton;

    public ProcessingCard(String title) {
        this(title, null);
    }

    public ProcessingCard(String title, ProcessingCard<Type> parent) {

        this.parent = parent;
//        if (parent == null) {
//            dragAndDrop = new DragAndDrop();
//        } else {
//            dragAndDrop = parent.dragAndDrop;
//            dropZoneAbove = new DropZone();
//        }

        defaults().align(Align.top);

        initBackground();
        initHeader(title);
        initSettings();
        initContent();
    }

    int nine_top;

    private void initBackground() {
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("assets/UI.png")), 9, 9, 34, 9);
//        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("assets/UI.9.png")));
        setBackground(new NinePatchDrawable(patch));
        nine_top = (int) getPadTop();
        padTop(getPadBottom());
    }

    private void initHeader(String title) {
        header = new VisTable();
        header.add(getTitle(title)).align(Align.topLeft).growX();
        header.add(CommonListeners.assembleMoveCardUp(this, new VisTextButton("U")));
        header.add(CommonListeners.assembleMoveCardDown(this, new VisTextButton("D")));
        header.add(CommonListeners.assembleRemoveCard(this, new VisTextButton("X"))).align(Align.topRight);
        add(header).growX().row();
    }

    private Actor getTitle(String name) {
        VisLabel label = new VisLabel(name);

        VisTable optionsMenu = new VisTable();
        optionsMenu.add(CommonListeners.assembleMoveCardUp(this, new VisTextButton("Move Up"))).fillX().row();
        optionsMenu.add(CommonListeners.assembleMoveCardDown(this, new VisTextButton("Move Down"))).fillX().row();
        optionsMenu.add(CommonListeners.assembleCutCard(this, new VisTextButton("Cut"))).fillX().row();
        optionsMenu.add(CommonListeners.assemblePasteInCard(this, new VisTextButton("Paste In"))).fillX().row();
        optionsMenu.add(CommonListeners.assemblePasteAboveCard(this, new VisTextButton("Paste Above"))).fillX().row();
        optionsMenu.add(CommonListeners.assembleCollapse(this, new VisTextButton("Collapse"))).fillX().row();
        optionsMenu.add(CommonListeners.assembleRemoveCard(this, new VisTextButton("Delete"))).fillX();
        optionsMenu.getChildren().forEach(CommonListeners::assembleSetTransparent);
        optionsMenu.pack();
        optionsMenu.getColor().a = 0;
        optionsMenu.setVisible(false);

        header.addActor(optionsMenu);

        label.addListener(new ClickListener(Input.Buttons.RIGHT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                optionsMenu.setPosition(label.getX() + x, label.getY() + y - optionsMenu.getHeight());
                header.toFront();
                ProcessingCard.this.toFront();
                optionsMenu.toFront();
                optionsMenu.addAction(Actions.sequence(
                        Actions.visible(true),
                        Actions.fadeIn(.5f),
                        Actions.delay(3),
                        Actions.fadeOut(.5f),
                        Actions.visible(false)
                ));
            }
        });
        return label;
    }

    private int getContentTop() {
        header.pack();
        System.out.println(">"+header.getHeight());
        return (int) (nine_top - header.getHeight());
    }

    private void initSettings() {
        add(settings = new VisTable()).padTop(getContentTop()).growX().row();
    }

    private void initContent() {
        content = new VisTable();
        content.defaults().align(Align.top);
        addNewButtonToContent();

        VisScrollPane scrollPane = new VisScrollPane(content);
        scrollPane.setOverscroll(false, true);
        scrollPane.setScrollingDisabled(true, false);
        add(scrollPane).padTop(internal_pad).grow();
    }

    private void addNewButtonToContent() {
        if (addButton == null) addButton = new VisTextButton("Add");
        content.add(addButton).expandX().pad(2 * internal_pad).row();
    }

    private void removeNewButtonFromContent() {
        content.removeActor(addButton);
    }

    public void addCard(ProcessingCard<Type> card) {
        addCard(card, false);
    }

    void addCard(ProcessingCard<Type> card, boolean isDnDUpdate) {

        if (card.parent == null)
            throw new RuntimeException("Please initialize the given card by also giving in the parent card, ie, this card.");
        else if (card.parent != this)
            throw new RuntimeException("That's not my child!!!!!");

        // Some initialisation
//        card.dragAndDrop = dragAndDrop;

        // Personal record
        if (!isDnDUpdate)
            children.add(card);

        // Updating UI
        removeNewButtonFromContent();
        content.add(card).growX().row();
        addNewButtonToContent();

//        if (isDnDUpdate) return;

        // Updating our DnD
    }

    public void removeCard(ProcessingCard<Type> card) {
        if (!children.contains(card, false)) throw new RuntimeException("We don't have any such card!");
        children.removeValue(card, false);
        content.removeActor(card);
    }

    void rebuildContent() {
        content.clear();
        for (int i = 0, s = children.size; i < s; i++) {
//            dragAndDrop.removeSource();
//            dragAndDrop.removeTarget();
            addCard(children.get(i), true);
        }
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void collapse() {
        if (isCollapsed) return;
        removeActor(settings);
        removeActor(content.getParent());
        isCollapsed = true;
    }

    public void expand() {
        if (!isCollapsed) return;
        clear();
        add(header).growX().row();
        add(settings).padTop(nine_top / 2f).growX().row();
        add(content.getParent()).padTop(internal_pad).grow();
        isCollapsed = false;
    }

    public ProcessingCard<Type> getParentCard() {
        return parent;
    }

    public Array<ProcessingCard<Type>> getCards() {
        return children;
    }

    public VisTable getSettings() {
        return settings;
    }

    protected void setParentCard(ProcessingCard<Type> newPa) {
        parent = newPa;
    }

    public abstract ProcessingCard<Type> clone();

    public Type process(Type type) {
        for (int i = 0, s = children.size; i < s; i++) type = children.get(i).process(type);
        return type;
    }
}
