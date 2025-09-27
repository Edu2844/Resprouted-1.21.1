package net.edu.resprouted.mixin.boat;

import net.edu.resprouted.registry.ResproutedBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatDropsMixin {

    // Credit: nyuppo - https://github.com/nyuppo/fabric-boat-example

    @Inject(method = "asItem", at = @At("RETURN"), cancellable = true)
    public void getResproutedBoats(CallbackInfoReturnable<Item> info) {
        ResproutedBoatTypes.getBoatItem(BoatEntity.class.cast(this).getVariant()).ifPresent(info::setReturnValue);
    }
}