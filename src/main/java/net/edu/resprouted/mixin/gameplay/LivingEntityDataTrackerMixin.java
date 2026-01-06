package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.effect.ModEffects;
import net.edu.resprouted.entity.interfaces.ResproutedEntityExtension;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//I tried to replicate how glowing effect check works
@Mixin(LivingEntity.class)
public abstract class LivingEntityDataTrackerMixin implements ResproutedEntityExtension {

    @Unique
    private static final TrackedData<Byte> RESPROUTED_EFFECT_FLAGS;

    static {
        RESPROUTED_EFFECT_FLAGS = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BYTE);
    }

    @Unique
    private static final int IRON_SKIN_FLAG_INDEX = 0;
    @Unique
    private static final int FULL_METAL_FLAG_INDEX = 1;

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void initResproutedEffectFlags(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(RESPROUTED_EFFECT_FLAGS, (byte) 0);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void updateResproutedEffectFlags(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!self.getWorld().isClient()) {
            this.setResproutedFlag(IRON_SKIN_FLAG_INDEX, self.hasStatusEffect(ModEffects.IRON_SKIN));
            this.setResproutedFlag(FULL_METAL_FLAG_INDEX, self.hasStatusEffect(ModEffects.FULL_METAL));
        }
    }

    @Unique
    private void setResproutedFlag(int index, boolean value) {
        LivingEntity self = (LivingEntity) (Object) this;
        byte flags = self.getDataTracker().get(RESPROUTED_EFFECT_FLAGS);

        if (value) {
            flags |= (byte)(1 << index);
        } else {
            flags &= (byte)~(1 << index);
        }

        self.getDataTracker().set(RESPROUTED_EFFECT_FLAGS, flags);
    }

    @Unique
    private boolean getResproutedFlag(int index) {
        LivingEntity self = (LivingEntity) (Object) this;
        byte flags = self.getDataTracker().get(RESPROUTED_EFFECT_FLAGS);
        return (flags & (1 << index)) != 0;
    }

    @Override
    public boolean resprouted$hasIronSkin() {
        return this.getResproutedFlag(IRON_SKIN_FLAG_INDEX);
    }

    @Override
    public boolean resprouted$hasFullMetal() {
        return this.getResproutedFlag(FULL_METAL_FLAG_INDEX);
    }
}