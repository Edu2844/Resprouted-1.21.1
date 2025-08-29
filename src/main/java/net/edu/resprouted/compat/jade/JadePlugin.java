package net.edu.resprouted.compat.jade;

import net.edu.resprouted.block.custom.agriculture.FruitingLeavesBlock;
import net.edu.resprouted.block.custom.agriculture.GrapeLeavesBlock;
import net.edu.resprouted.compat.jade.component.FruitingLeavesComponentProvider;
import net.edu.resprouted.compat.jade.component.GrapeLeavesComponentProvider;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;

public class JadePlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {

    }
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(FruitingLeavesComponentProvider.INSTANCE, FruitingLeavesBlock.class);
        registration.registerBlockComponent(GrapeLeavesComponentProvider.INSTANCE, GrapeLeavesBlock.class);
    }
}
