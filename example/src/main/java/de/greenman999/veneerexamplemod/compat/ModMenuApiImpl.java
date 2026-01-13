package de.greenman999.veneerexamplemod.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import de.greenman999.Test;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ModMenuApiImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> new Screen(Component.empty()) {
            @Override
            protected void init() {
                super.init();
                Test.test();
            }
        };
    }
}
