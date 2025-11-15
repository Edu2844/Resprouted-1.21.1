package net.edu.resprouted.mixin.boat;

import net.edu.resprouted.registry.ResproutedBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//Borrowed and modified from fabric-boat-example by nyuppo
//Source:
//https://github.com/nyuppo/fabric-boat-example/blob/main/src/main/java/com/examplemod/mixin/BoatDropsMixin.java

@Mixin(BoatEntity.class)
public class BoatDropsMixin {
    @Inject(method = "asItem", at = @At("RETURN"), cancellable = true)
    public void getResproutedBoats(CallbackInfoReturnable<Item> info) {
        ResproutedBoatTypes.getBoatItem(BoatEntity.class.cast(this).getVariant()).ifPresent(info::setReturnValue);
    }
}