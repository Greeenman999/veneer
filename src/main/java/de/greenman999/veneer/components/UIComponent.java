package de.greenman999.veneer.components;

import org.appliedenergistics.yoga.YogaNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UIComponent {

    private final List<UIComponent> children = new ArrayList<>();
    private UIComponent parent = null;

    private YogaNode yogaNode;

    public void draw() {
        for (UIComponent child : children) {
            child.draw();
        }
    }

    public void addChild(UIComponent child) {
        children.add(child);
    }

    public void removeChild(UIComponent child) {
        children.remove(child);
    }

    public void clearChildren() {
        children.clear();
    }

    public UIComponent getParent() {
        return parent;
    }

    public static class Builder {
        private final UIComponent component;

        public Builder() {
            component = new UIComponent();
        }

        public Builder setLayout(Consumer<YogaNode> layoutConfigurer) {
            layoutConfigurer.accept(component.yogaNode);
            return this;
        }

        public UIComponent build(UIComponent parent) {
            if (parent != null) {
                parent.addChild(component);
                component.parent = parent;
            }
            return component;
        }
    }
}
