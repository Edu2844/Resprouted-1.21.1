package net.edu.resprouted.mixin.boat;

import net.edu.resprouted.registry.ResproutedBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoatEntity.class)
public class ChestBoatDropsMixin {
    @Inject(method = "asItem", at = @At("RETURN"), cancellable = true)
    public void getResproutedBoats(CallbackInfoReturnable<Item> info) {
        ResproutedBoatTypes.getChestBoatItem(BoatEntity.class.cast(this).getVariant()).ifPresent(info::setReturnValue);
    }
}