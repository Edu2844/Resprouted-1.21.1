package net.edu.resprouted.entity.custom;

import net.edu.resprouted.entity.ModEntities;
import net.edu.resprouted.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ThrownTomatoEntity extends ThrownItemEntity {
    public ThrownTomatoEntity(EntityType<? extends ThrownTomatoEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownTomatoEntity(World world, LivingEntity owner) {
        super(ModEntities.THROWN_TOMATO, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TOMATO;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0.2F);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_SLIME_HURT, SoundCategory.NEUTRAL, 0.7F, 0.4F);
            ((ServerWorld)this.getWorld()).spawnParticles(
                    new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(ModItems.TOMATO)),
                    this.getX(), this.getY(), this.getZ(), 12, 0.3, 0.3, 0.3, 0.05);
            this.discard();
        }
    }
}