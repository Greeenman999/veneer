package de.greenman999;

import de.greenman999.layr.api.LayrProvider;
import de.greenman999.layr.api.gui.screen.LayrScreen;
import de.greenman999.layr.api.renderer.RenderContext;
import org.appliedenergistics.yoga.*;
import org.appliedenergistics.yoga.config.MutableYogaConfig;
import org.appliedenergistics.yoga.config.YogaConfig;
import org.appliedenergistics.yoga.config.YogaLogger;

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

/*        YogaNode secondBackground = new YogaNode(config);
        secondBackground.setWidthPercent(1.0f);
        secondBackground.setHeightPercent(1.0f);
        second.addChildAt(secondBackground,0);*/

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
                traverse(root, child -> {
                    if (child == root) return;
                    int x = (int) (child.getLayoutX() + child.getOwner().getLayoutX());
                    int y = (int) child.getLayoutY() + (int) child.getOwner().getLayoutY();
                    int width = (int) child.getLayoutWidth();
                    int height = (int) child.getLayoutHeight();
                    if (width == 10) {
                        ctx.fillRect(x, y, width, height, Color.BLUE);
                    } else {
                        ctx.fillRect(x, y, width, height, Color.GREEN);
                    }
                });
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