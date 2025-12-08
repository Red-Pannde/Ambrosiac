package ambrosiac.mod.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ambrosiac.mod.blocks.ModBlocks;
import ambrosiac.mod.items.ModItems;
import ambrosiac.mod.recipe.AlchemistsCauldronRecipeBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import org.jetbrains.annotations.Nullable;

public class AmbrosiacRecipeProvider extends FabricRecipeProvider {

    public AmbrosiacRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    public void generate(RecipeExporter exporter) {
        buildPlantRecipe(exporter, ModItems.ACTIVATED_SWISS_CHARD_LEAF, 1, List.of("ice"), List.of(Items.KELP), List.of(Items.AMETHYST_SHARD), List.of(Items.BONE_MEAL), ModItems.SWISS_CHARD_LEAF, "activating_swiss_chard_leaf");
        buildPlantRecipe(exporter, ModItems.ACTIVATED_PEACE_LILY, 1, List.of("water"), List.of(Items.JUNGLE_SAPLING, Items.MANGROVE_PROPAGULE), List.of(Items.AMETHYST_SHARD), List.of(Items.SUGAR), ModBlocks.PEACE_LILY, "activating_peace_lily");
        buildPlantRecipe(exporter, ModItems.ACTIVATED_DAHLIA, 1, List.of(), List.of(Items.POPPY), List.of(Items.POPPY), List.of(Items.SPIDER_EYE, Items.GUNPOWDER, Items.BONE), ModBlocks.DAHLIA, "activating_dahlia");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NAUTILUS_SHELL, 8)
                .input(ModBlocks.PEACE_LILY)
                .input(Items.CALCITE)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PEACE_LILY), FabricRecipeProvider.conditionsFromItem(ModBlocks.PEACE_LILY))
                .criterion(FabricRecipeProvider.hasItem(Items.CALCITE), FabricRecipeProvider.conditionsFromItem(Items.CALCITE))
                .offerTo(exporter);
        AlchemistsCauldronRecipeBuilder.create(Items.MUD, 1, List.of("water"))
                .input(Items.DIRT)
                .criterion(FabricRecipeProvider.hasItem(Items.DIRT),
                        FabricRecipeProvider.conditionsFromItem(Items.DIRT))
                .offerTo(exporter);

        List<ItemConvertible> unoxidizedCopper = List.of(Items.COPPER_BLOCK, Items.COPPER_GRATE,
                Items.COPPER_BULB, Items.COPPER_DOOR, Items.COPPER_TRAPDOOR, Items.CHISELED_COPPER,
                Items.CUT_COPPER, Items.CUT_COPPER_SLAB, Items.CUT_COPPER_STAIRS);

        List<ItemConvertible> exposedCopper = List.of(Items.EXPOSED_COPPER, Items.EXPOSED_COPPER_GRATE,
                Items.EXPOSED_COPPER_BULB, Items.EXPOSED_COPPER_DOOR, Items.EXPOSED_COPPER_TRAPDOOR, Items.EXPOSED_CHISELED_COPPER,
                Items.EXPOSED_CUT_COPPER, Items.EXPOSED_CUT_COPPER_SLAB, Items.EXPOSED_CUT_COPPER_STAIRS);

        List<ItemConvertible> weatheredCopper = List.of(Items.WEATHERED_COPPER, Items.WEATHERED_COPPER_GRATE,
                Items.WEATHERED_COPPER_BULB, Items.WEATHERED_COPPER_DOOR, Items.WEATHERED_COPPER_TRAPDOOR, Items.WEATHERED_CHISELED_COPPER,
                Items.WEATHERED_CUT_COPPER, Items.WEATHERED_CUT_COPPER_SLAB, Items.WEATHERED_CUT_COPPER_STAIRS);

        List<ItemConvertible> oxidizedCopper = List.of(Items.OXIDIZED_COPPER, Items.OXIDIZED_COPPER_GRATE,
                Items.OXIDIZED_COPPER_BULB, Items.OXIDIZED_COPPER_DOOR, Items.OXIDIZED_COPPER_TRAPDOOR, Items.OXIDIZED_CHISELED_COPPER,
                Items.OXIDIZED_CUT_COPPER, Items.OXIDIZED_CUT_COPPER_SLAB, Items.OXIDIZED_CUT_COPPER_STAIRS);

        buildRecipesFromList(exporter, exposedCopper, 1, List.of("water"), unoxidizedCopper, null, null);
        buildRecipesFromList(exporter, weatheredCopper, 1, List.of("water"), exposedCopper, null, null);
        buildRecipesFromList(exporter, oxidizedCopper, 1, List.of("water"), weatheredCopper, null, null);
    }

    public static void buildRecipesFromList(RecipeExporter exporter, List<ItemConvertible> outputList, int outputCount, List<String> elements, List<ItemConvertible> inputList1, @Nullable List<ItemConvertible> inputList2, @Nullable List<ItemConvertible> inputList3) {
        for (int i = 0; i < inputList1.size(); i++) {
            ItemConvertible input1 = inputList1.get(i);
            ItemConvertible output = outputList.get(i);
            AlchemistsCauldronRecipeBuilder alchemistsCauldronRecipeBuilder = AlchemistsCauldronRecipeBuilder.create(output, outputCount, elements);
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

    public static void buildPlantRecipe(RecipeExporter exporter, ItemConvertible activatedPlant, int outputCount, List<String> elements, List<ItemConvertible> basePlant, List<ItemConvertible> mysticalIngredient, List<ItemConvertible> nutrient, ItemConvertible unactivatedPlant, String infusionRecipepath) {

        AlchemistsCauldronRecipeBuilder fullRecipe = AlchemistsCauldronRecipeBuilder.create(activatedPlant, outputCount, elements)
                .listInput(basePlant)
                .listInput(mysticalIngredient)
                .listInput(nutrient);
        AlchemistsCauldronRecipeBuilder infusionRecipe = AlchemistsCauldronRecipeBuilder.create(activatedPlant, outputCount, elements)
                .input(unactivatedPlant)
                .listInput(mysticalIngredient)
                .criterion(FabricRecipeProvider.hasItem(unactivatedPlant), FabricRecipeProvider.conditionsFromItem(unactivatedPlant));

        AlchemistsCauldronRecipeBuilder transmutationRecipe = AlchemistsCauldronRecipeBuilder.create(unactivatedPlant, outputCount, elements)
                .listInput(basePlant)
                .listInput(nutrient);
        for (ItemConvertible baseEntry : basePlant) {
            fullRecipe.criterion(FabricRecipeProvider.hasItem(baseEntry), FabricRecipeProvider.conditionsFromItem(baseEntry));
            transmutationRecipe.criterion(FabricRecipeProvider.hasItem(baseEntry), FabricRecipeProvider.conditionsFromItem(baseEntry));
        }
        for (ItemConvertible mysticalEntry : mysticalIngredient) {
            fullRecipe.criterion(FabricRecipeProvider.hasItem(mysticalEntry), FabricRecipeProvider.conditionsFromItem(mysticalEntry));
            infusionRecipe.criterion(FabricRecipeProvider.hasItem(mysticalEntry), FabricRecipeProvider.conditionsFromItem(mysticalEntry));
        }
        for (ItemConvertible nutrientEntry : nutrient) {
            fullRecipe.criterion(FabricRecipeProvider.hasItem(nutrientEntry), FabricRecipeProvider.conditionsFromItem(nutrientEntry));
            transmutationRecipe.criterion(FabricRecipeProvider.hasItem(nutrientEntry), FabricRecipeProvider.conditionsFromItem(nutrientEntry));
        }
        fullRecipe.offerTo(exporter);
        infusionRecipe.offerTo(exporter, infusionRecipepath);
        transmutationRecipe.offerTo(exporter);



    }
}
