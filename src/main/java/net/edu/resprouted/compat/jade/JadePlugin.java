package net.edu.resprouted.compat.jade;

import net.edu.resprouted.block.custom.agriculture.CustomLeavesBlock;
import net.edu.resprouted.compat.jade.component.CustomLeavesComponentProvider;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;

public class JadePlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {

    }
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(CustomLeavesComponentProvider.INSTANCE, CustomLeavesBlock.class);
    }
}
