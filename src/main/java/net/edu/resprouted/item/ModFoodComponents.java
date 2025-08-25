package net.edu.resprouted.item;


import net.edu.resprouted.effect.ModEffects;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent OLIVES = new FoodComponent.Builder().nutrition(1).saturationModifier(0.4f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,200,1,true, false, false),0.95f).build();
    public static final FoodComponent CHILLI_PEPPER = new FoodComponent.Builder().nutrition(3).saturationModifier(0.4f).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.SPEED,400,0,true, false, true),0.95f).build();
    public static final FoodComponent GHOST_PEPPER = new FoodComponent.Builder().alwaysEdible().nutrition(3).saturationModifier(0.4f).statusEffect(new StatusEffectInstance(ModEffects.FIRE_POWER,400,0),0.95f).build();
    public static final FoodComponent TOMATO = new FoodComponent.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final FoodComponent GRAPES = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3f).build();
    public static final FoodComponent IRON_BERRIES = new FoodComponent.Builder().alwaysEdible().nutrition(2).saturationModifier(0.4f).statusEffect(new StatusEffectInstance(ModEffects.FULL_METAL,280,0,true, false, false),1f).build();
    public static final FoodComponent BLUE_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.4f).build();
    public static final FoodComponent ROOT = new FoodComponent.Builder().nutrition(1).saturationModifier(0.4f).build();
    public static final FoodComponent CLOUDSBLUFF = new FoodComponent.Builder().nutrition(1).saturationModifier(0.4f).statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION,100,0),0.95f).build();
    public static final FoodComponent JUICE = new FoodComponent.Builder().nutrition(0).saturationModifier(0f).alwaysEdible().build();
    public static final FoodComponent GLOW_BERRY_JUICE = new FoodComponent.Builder().nutrition(0).saturationModifier(0f).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.GLOWING,100,0,true, false, false),1f).build();
}
