package de.greenman999.veneerexamplemod;

import de.greenman999.Test;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VeneerExampleMod implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("veneer-example-mod");

    @Override
    public void onInitializeClient() {
        System.out.println("Veneer Example Mod initialized!");
    }

}
