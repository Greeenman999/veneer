package de.greenman999.veneer;

import de.greenman999.layr.api.gui.screen.LayrScreen;
import de.greenman999.layr.api.renderer.RenderContext;
import de.greenman999.veneer.components.UIComponent;

public class VeneerScreen extends LayrScreen {

    private UIComponent rootComponent = new UIComponent.Builder().setLayout(node -> {
        node.setWidthPercent(100);
        node.setHeightPercent(100);
    }).build(null);

    @Override
    public void renderContent(RenderContext ctx, int mouseX, int mouseY, float partialTick) {

    }

    public UIComponent getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(UIComponent rootComponent) {
        this.rootComponent = rootComponent;
    }
}
