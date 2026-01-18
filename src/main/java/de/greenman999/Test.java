package de.greenman999;

import de.greenman999.layr.api.LayrProvider;
import de.greenman999.layr.api.gui.screen.LayrScreen;
import de.greenman999.layr.api.renderer.RenderContext;
import org.appliedenergistics.yoga.*;
import org.appliedenergistics.yoga.config.MutableYogaConfig;
import org.appliedenergistics.yoga.config.YogaConfig;
import org.appliedenergistics.yoga.config.YogaLogger;
import org.joml.Vector4f;

import java.awt.*;
import java.util.function.Consumer;

public class Test {
    public static void test() {
        MutableYogaConfig config = YogaConfig.create(new YogaLogger() {
            @Override
            public void log(YogaConfig config, YogaNode node, LogLevel level, String format, Object... args) {
                System.out.printf((format) + "%n", args);
            }
        });


        YogaNode root = new YogaNode(config);
        root.setContext("root");

        YogaNode first = new YogaNode(config);
        first.setContext("first");
        first.setWidth(100);
        first.setHeight(100);
        first.setMargin(YogaEdge.ALL, 10);
        root.addChildAt(first, 0);

        YogaNode child1 = new YogaNode(config);
        child1.setContext("child");
        child1.setWidthAuto();
        child1.setHeightAuto();
        child1.setMargin(YogaEdge.ALL, 5);
        first.addChildAt(child1, 0);

        YogaNode second = new YogaNode(config);
        second.setContext("second");
        //second.setDisplay(YogaDisplay.FLEX);
        second.setWidth(100);
        second.setHeight(50);
        second.setFlexDirection(YogaFlexDirection.ROW);
        second.setMargin(YogaEdge.ALL, 5);
        second.setWrap(YogaWrap.WRAP);
        second.setJustifyContent(YogaJustify.SPACE_BETWEEN);
        second.setAlignItems(YogaAlign.CENTER);
        root.addChildAt(second, 1);

        for (int i = 0; i < 5; i++) {
            YogaNode blue = new YogaNode(config);
            blue.setContext("blue" + i);
            blue.setWidth(10);
            blue.setHeight(10);
            blue.setMargin(YogaEdge.ALL, 2);
            //blue.setDisplay(YogaDisplay.CONTENTS);
            second.addChildAt(blue, i);
        }


        root.calculateLayout(300, 300);

        traverse(root, child -> {
            System.out.println("Node: " + child + " Context: " + child.getContext() + " Layout: x=" + child.getLayoutX() + " y=" + child.getLayoutY() + " w=" + child.getLayoutWidth() + " h=" + child.getLayoutHeight());
        });

        LayrProvider.get().getScreenManager().open(new LayrScreen() {
            @Override
            public void renderContent(RenderContext ctx, int mouseX, int mouseY, float delta) {
                /*traverse(root, child -> {
                    if (child == root) return;
                    int x = (int) (child.getLayoutX() + child.getOwner().getLayoutX());
                    int y = (int) child.getLayoutY() + (int) child.getOwner().getLayoutY();
                    int width = (int) child.getLayoutWidth();
                    int height = (int) child.getLayoutHeight();
                    if (width == 90) {
                        ctx.fillRoundedRect(x, y, width, height, 10, Color.RED);
                    } else if (width == 10){
                        ctx.fillRoundedRect(x, y, width, height, 2, Color.BLUE);
                    } else {
                        ctx.fillRoundedRect(x, y, width, height, 5, Color.GREEN);
                    }
                });*/

                ctx.fillRoundedRect(10, 10, 100, 100, new Vector4f(5, 7, 9, 11), Color.GREEN);
                ctx.fillRoundedRect(12, 12, 96, 96, new Vector4f(3, 5, 7, 9), Color.BLUE);
                //ctx.fillRect(100, 100, 200, 200, Color.RED);
                //ctx.fillRoundedRect(10, 10, 100, 100, 50, new Color(255, 0, 0, 128));
                /*for (YogaNode child : root.getChildren()) {
                    int x = (int) child.getLayoutX();
                    int y = (int) child.getLayoutY();
                    int width = (int) child.getLayoutWidth();
                    int height = (int) child.getLayoutHeight();
                    if (child.getLayoutWidth() == 10) {
                        ctx.fillRect(x, y, width, height, Color.BLUE);
                    } else {
                        ctx.fillRect(x, y, width, height, Color.GREEN);
                    }
                    //ctx.fillRect(x, y, width, height, Color.RED);
                }*/

                //ctx.fillRect(10, 10, 100, 100, Color.WHITE);
            }
        });
    }

    public static void traverse(YogaNode node, Consumer<YogaNode> visitor) {
        if (node == null) return;
        visitor.accept(node);
        for (YogaNode child : node.getLayoutChildren()) {
            traverse(child, visitor);
        }
        /*for (int i = 0; i < node.getChildCount(); i++) {
            traverse(node.getChild(i), visitor);
        }*/
    }
}