package de.greenman999.veneerexamplemod.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import de.greenman999.layr.impl.gui.screen.LayrScreenWrapper;
import de.greenman999.veneerexamplemod.TestScreen;

public class ModMenuApiImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> new LayrScreenWrapper(new TestScreen(), parent);
    }
}
