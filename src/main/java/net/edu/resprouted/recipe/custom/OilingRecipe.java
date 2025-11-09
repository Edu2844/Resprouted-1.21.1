package net.edu.resprouted.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.edu.resprouted.item.ModItems;
import net.edu.resprouted.recipe.ModRecipes;
import net.edu.resprouted.util.RecipeUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class OilingRecipe extends SpecialCraftingRecipe {
    private final CraftingRecipeCategory category;

    public OilingRecipe(CraftingRecipeCategory category) {
        super(category);
        this.category = category;
    }
    public CraftingRecipeCategory getCategory() {
        return category;
    }
    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (!Resprouted.CONFIG.isOliveOilingEnabled()) {
            return false;
        }
        if (world.isClient()) return false;

        int numStacks = input.getStackCount();
        if (numStacks != 2) {
            return false;
        }
        ItemStack foodStack = ItemStack.EMPTY;
        ItemStack oilStack = ItemStack.EMPTY;

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty()) continue;

            if (RecipeUtils.isValidFood(stack) && foodStack.isEmpty() ) {
                if (stack.get(ModDataComponentTypes.OLIVE_OILED) != null && Boolean.TRUE.equals(stack.get(ModDataComponentTypes.OLIVE_OILED))) {
                    return false;
                }
                foodStack = stack;
            } else if (stack.isOf(ModItems.OLIVE_OIL_BOTTLE) && oilStack.isEmpty()) {
                oilStack = stack;
            } else {
                return false;
            }
        }
        return !foodStack.isEmpty() && !oilStack.isEmpty();
    }
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack foodStack = ItemStack.EMPTY;
        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (!stack.isEmpty() && RecipeUtils.isValidFood(stack)) {
                foodStack = stack;
                break;
            }
        }
        if (foodStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack result = foodStack.copyWithCount(1);
        result.set(ModDataComponentTypes.OLIVE_OILED, true);

        return result;
    }
    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainder = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isOf(ModItems.OLIVE_OIL_BOTTLE)) {
                remainder.set(i, new ItemStack(Items.GLASS_BOTTLE));
                break;
            }
        }
        return remainder;
    }
    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.OLIVE_OIL_SERIALIZER;
    }

    public static class OilingRecipeSerializer implements RecipeSerializer<OilingRecipe> {

        public static final MapCodec<OilingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                CraftingRecipeCategory.CODEC.fieldOf("category").forGetter(OilingRecipe::getCategory)).apply(instance, OilingRecipe::new));

        public static final PacketCodec<RegistryByteBuf, OilingRecipe> PACKET_CODEC = PacketCodec.ofStatic(
                (buf, recipe) -> buf.writeEnumConstant(recipe.getCategory()),
                buf -> new OilingRecipe(buf.readEnumConstant(CraftingRecipeCategory.class)));

        @Override
        public MapCodec<OilingRecipe> codec() {
            return CODEC;
        }
        @Override
        public PacketCodec<RegistryByteBuf, OilingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}
