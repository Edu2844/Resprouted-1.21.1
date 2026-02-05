package net.edu.resprouted.compat.item;

import net.edu.resprouted.Resprouted;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class CompatItem extends Item {
    private final String modId;

    public CompatItem( String modId, Settings settings) {
        super(settings);
        this.modId = modId;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return Resprouted.isModLoaded(modId) || Resprouted.isDatagen();
    }
}
