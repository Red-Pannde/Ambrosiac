package ambrosiac.mod.datagen;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import ambrosiac.mod.recipe.AlchemistsCauldronRecipeBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.util.Identifier;

public class AmbrosiacRecipeProvider extends FabricRecipeProvider {

    public AmbrosiacRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        AlchemistsCauldronRecipeBuilder.create(RecipeCategory.MISC, Items.MUD, 1)
                .input(Items.DIRT)
                .criterion(FabricRecipeProvider.hasItem(Items.DIRT),
                        FabricRecipeProvider.conditionsFromItem(Items.DIRT))
                .offerTo(recipeExporter);
    }
}
