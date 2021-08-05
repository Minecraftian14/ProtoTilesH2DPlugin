package com.mcxiv.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

class CommonListeners {

    public static abstract class CardParentActionListener extends ChangeListener {

        protected final ProcessingCard context;

        public CardParentActionListener(ProcessingCard context, Button button) {
            this.context = context;
            button.setDisabled(context.getParentCard() == null);
            context.addParentListeners(new ParentListener() {
                @Override
                public void parentAdded() {
                    button.setDisabled(context.getParentCard() == null);
                }
            });
        }
    }

    public static Button assembleMoveCardUp(ProcessingCard context, Button button) {
        button.addListener(new MoveCardUp(context, button));
        return button;
    }

    public static class MoveCardUp extends CardParentActionListener {

        public MoveCardUp(ProcessingCard context, Button button) {
            super(context, button);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            ProcessingCard parent = context.getParentCard();
            if (parent == null) return;
            int idx = -1;
            for (int i = 0, s = parent.getCards().size; i < s; i++) {
                if (parent.getCards().get(i) == context) {
                    idx = i;
                    break;
                }
            }
            if (idx == 0) return;
            parent.getCards().removeIndex(idx);
            parent.getCards().insert(idx - 1, context);
            parent.rebuildContent();
        }

    }

    public static Button assembleMoveCardDown(ProcessingCard context, Button button) {
        button.addListener(new MoveCardDown(context, button));
        return button;
    }

    public static class MoveCardDown extends CardParentActionListener {

        public MoveCardDown(ProcessingCard context, Button button) {
            super(context, button);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            ProcessingCard parent = context.getParentCard();
            if (parent == null) return;
            int idx = -1;
            for (int i = 0, s = parent.getCards().size; i < s; i++) {
                if (parent.getCards().get(i) == context) {
                    idx = i;
                    break;
                }
            }
            if (idx == parent.getCards().size - 1) return;
            parent.getCards().removeIndex(idx);
            parent.getCards().insert(idx + 1, context);
            parent.rebuildContent();
        }

    }

    public static Button assembleRemoveCard(ProcessingCard context, Button button) {
        button.addListener(new RemoveCard(context, button));
        return button;
    }

    public static class RemoveCard extends CardParentActionListener {

        public RemoveCard(ProcessingCard context, Button button) {
            super(context, button);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            ProcessingCard parent = context.getParentCard();
            if (parent == null) return;
            parent.removeCard(context);
        }

    }

    public static Button assembleCollapse(ProcessingCard context, Button button) {
        button.addListener(new Collapse(context, button));
        return button;
    }

    public static final class Collapse extends CardParentActionListener {

        private final TextButton button;

        public Collapse(ProcessingCard context, Button button) {
            super(context, button);
            assert button instanceof TextButton;
            this.button = (TextButton) button;
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (context.isCollapsed()) {
                button.setText("Collapse");
                context.expand();
            } else {
                button.setText("Expand");
                context.collapse();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final class CopyCard extends CardParentActionListener {
        public CopyCard(ProcessingCard context, Button button) {
            super(context, button);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            ProcessingCard parent = context.getParentCard();
            if (parent == null) return;
            // TODO: copy a clone
//            CopyPasteCommand.context = context.clone();
//            CopyPasteCommand.parent = parent;
        }
    }


    public static Button assembleCutCard(ProcessingCard context, Button button) {
        button.addListener(new CutCard(context, button));
        return button;
    }

    public static final class CutCard extends CardParentActionListener {

        public CutCard(ProcessingCard context, Button button) {
            super(context, button);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            ProcessingCard parent = context.getParentCard();
            if (parent == null) return;

            CopyPasteCommand.context = context;
            CopyPasteCommand.parent = parent;
            parent.removeCard(context);
            context.setParentCard(null);
        }
    }

    public static Button assemblePasteInCard(ProcessingCard context, Button button) {
        button.addListener(new PasteInCard(context, button));
        return button;
    }

    public static final class PasteInCard extends CardParentActionListener {
        public PasteInCard(ProcessingCard context, Button button) {
            super(context, button);
            button.setDisabled(false);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (CopyPasteCommand.context == null) return;
            CopyPasteCommand.context.setParentCard(context);
            context.addCard(CopyPasteCommand.context);
            CopyPasteCommand.context = null;
        }
    }

    public static Button assemblePasteAboveCard(ProcessingCard context, Button button) {
        button.addListener(new PasteAboveCard(context, button));
        return button;
    }

    public static final class PasteAboveCard extends CardParentActionListener {
        public PasteAboveCard(ProcessingCard context, Button button) {
            super(context, button);
        }

        @Override
        public void changed(ChangeEvent event, Actor actor) {
            if (CopyPasteCommand.context == null) return;
            ProcessingCard parent = context.getParentCard();
            Array<ProcessingCard> cards = parent.getCards();
            int idx = cards.indexOf(context, false);
            cards.insert(idx, CopyPasteCommand.context);
            CopyPasteCommand.context.setParentCard(parent);
            parent.rebuildContent();
            CopyPasteCommand.context = null;
        }
    }

    private static final class CopyPasteCommand {
        public static ProcessingCard context;
        public static ProcessingCard parent;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Actor assembleSetTransparent(Actor button) {
        button.addListener(new SetTransparent());
        return button;
    }

    public static final class SetTransparent extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            actor.getParent().getColor().a = 0;
        }
    }

}
