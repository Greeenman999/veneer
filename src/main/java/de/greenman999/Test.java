package de.greenman999;

import de.greenman999.layr.api.LayrProvider;
import de.greenman999.layr.api.gui.screen.LayrScreen;
import de.greenman999.layr.api.renderer.RenderContext;

import java.awt.*;

public class Test {
    public static void test() {
        LayrProvider.get().getScreenManager().open(new LayrScreen() {
            @Override
            public void renderContent(RenderContext ctx, int mouseX, int mouseY, float delta) {
                ctx.fillRect(10, 10, 100, 100, Color.WHITE);
            }
        });
    }
}