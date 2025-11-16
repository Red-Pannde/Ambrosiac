package ambrosiac.mod.datagen;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import ambrosiac.mod.blocks.ModBlocks;
import ambrosiac.mod.items.ModItems;
import ambrosiac.mod.recipe.AlchemistsCauldronRecipeBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class AmbrosiacRecipeProvider extends FabricRecipeProvider {

    public AmbrosiacRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    public static void buildRecipesFromList(RecipeExporter exporter, RecipeCategory category, List<ItemConvertible> outputList, int outputCount, List<ItemConvertible> inputList1, @Nullable List<ItemConvertible> inputList2, @Nullable List<ItemConvertible> inputList3) {
        for (int i = 0; i < inputList1.size(); i++) {
            ItemConvertible input1 = inputList1.get(i);
            ItemConvertible output = outputList.get(i);
            AlchemistsCauldronRecipeBuilder alchemistsCauldronRecipeBuilder = AlchemistsCauldronRecipeBuilder.create(category, output, outputCount);
            alchemistsCauldronRecipeBuilder.input(input1).criterion(FabricRecipeProvider.hasItem(input1), FabricRecipeProvider.conditionsFromItem(input1));
            if (inputList2 != null) {
                ItemConvertible input2 = inputList2.get(i);
                alchemistsCauldronRecipeBuilder.input(input2).criterion(FabricRecipeProvider.hasItem(input2), FabricRecipeProvider.conditionsFromItem(input2));
            }
            if (inputList3 != null) {
                ItemConvertible input3 = inputList3.get(i);
                alchemistsCauldronRecipeBuilder.input(input3).criterion(FabricRecipeProvider.hasItem(input3), FabricRecipeProvider.conditionsFromItem(input3));
            }
            alchemistsCauldronRecipeBuilder.offerTo(exporter);
        }

    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NAUTILUS_SHELL, 8)
                .input(ModBlocks.PEACE_LILY)
                .input(Items.CALCITE)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PEACE_LILY), FabricRecipeProvider.conditionsFromItem(ModBlocks.PEACE_LILY) )
                .criterion(FabricRecipeProvider.hasItem(Items.CALCITE), FabricRecipeProvider.conditionsFromItem(Items.CALCITE) )
                .offerTo(exporter);
        AlchemistsCauldronRecipeBuilder.create(RecipeCategory.MISC, Items.MUD, 1)
                .input(Items.DIRT)
                .criterion(FabricRecipeProvider.hasItem(Items.DIRT),
                        FabricRecipeProvider.conditionsFromItem(Items.DIRT))
                .offerTo(exporter);
        List<ItemConvertible> unoxidizedCopper = List.of(Items.COPPER_BLOCK, Items.COPPER_GRATE, Items.COPPER_BULB, Items.COPPER_DOOR);
        List<ItemConvertible> exposedCopper = List.of(Items.EXPOSED_COPPER, Items.EXPOSED_COPPER_GRATE, Items.EXPOSED_COPPER_BULB, Items.EXPOSED_COPPER_DOOR);
        buildRecipesFromList(exporter, RecipeCategory.MISC, exposedCopper, 1, unoxidizedCopper, null, null);
    }
}
