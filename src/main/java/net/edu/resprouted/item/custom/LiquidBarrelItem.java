package net.edu.resprouted.item.custom;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class LiquidBarrelItem extends BlockItem {
    private static final RegistryOps<NbtElement> TOOLTIP_OPS = RegistryOps.of(NbtOps.INSTANCE, BuiltinRegistries.createWrapperLookup());
    public LiquidBarrelItem(Block block, Settings settings) {
        super(block, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        var nbt = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);

        if (nbt == null) return;

        var tag = nbt.copyNbt().getCompound("FluidStorage");
        if (!tag.contains("variant") || !tag.contains("amount")) return;
        try {
            var variant = FluidVariant.CODEC.parse(TOOLTIP_OPS, tag.get("variant")).result().orElse(FluidVariant.blank());

            if (!variant.isBlank()) {
                var name = FluidVariantAttributes.getName(variant);
                long amount = tag.getLong("amount");

                tooltip.add(Text.translatable("tooltip.resprouted.fluid", name).formatted(Formatting.DARK_PURPLE));
                tooltip.add(Text.translatable("tooltip.resprouted.amount", amount).formatted(Formatting.DARK_PURPLE));
            }
        } catch (Exception e) {
            tooltip.add(Text.translatable("tooltip.resprouted.fluid_error").formatted(Formatting.RED));
        }
    }
}
