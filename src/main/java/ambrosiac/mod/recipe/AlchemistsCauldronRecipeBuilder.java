package ambrosiac.mod.recipe;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AlchemistsCauldronRecipeBuilder implements CraftingRecipeJsonBuilder {


    private final RecipeCategory category = RecipeCategory.MISC;
    private final ItemStack output;
    private final int outputCount;
    private final DefaultedList<Ingredient> inputs = DefaultedList.of();
    private final List<String> elements;
    private final Map<String, AdvancementCriterion<?>> advancementBuilder = new LinkedHashMap();
    @Nullable
    private String group;

    public AlchemistsCauldronRecipeBuilder(ItemConvertible output, int outputCount, List<String> elements) {
        this.output = output.asItem().getDefaultStack();
        this.outputCount = outputCount;
        this.elements = elements;
    }

    public static AlchemistsCauldronRecipeBuilder create( ItemConvertible output, int count, List<String> elements) {
        return new AlchemistsCauldronRecipeBuilder(output, count, elements);
    }

    public AlchemistsCauldronRecipeBuilder input(TagKey<Item> tag) {
        return this.input(Ingredient.fromTag(tag));
    }

    public AlchemistsCauldronRecipeBuilder input(ItemConvertible itemProvider) {
        return this.input(itemProvider , 1);
    }
    public AlchemistsCauldronRecipeBuilder input(ItemConvertible itemProvider, int size) {
        for(int i = 0; i < size; ++i) {
            this.input(Ingredient.ofItems(itemProvider));
        }

        return this;
    }
    public AlchemistsCauldronRecipeBuilder listInput(List<ItemConvertible> itemProviders) {
        this.input(Ingredient.ofStacks(itemProviders.stream().map(ItemStack::new)));
        return this;
    }

    public AlchemistsCauldronRecipeBuilder input(Ingredient ingredient) {
        return this.input(ingredient, 1);
    }

    public AlchemistsCauldronRecipeBuilder input(Ingredient ingredient, int size) {
        for(int i = 0; i < size; ++i) {
            this.inputs.add(ingredient);
        }

        return this;
    }


    @Override
    public AlchemistsCauldronRecipeBuilder criterion(String name, AdvancementCriterion<?> criterion) {
        this.advancementBuilder.put(name, criterion);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return this.output.getItem();
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.getAdvancementBuilder().criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        AlchemistsCauldronRecipe alchemistsCauldronRecipe = new AlchemistsCauldronRecipe(this.inputs, this.output, this.elements);
        exporter.accept(recipeId, alchemistsCauldronRecipe, builder.build(recipeId.withPrefixedPath("recipes/" + this.category.getName() + "/")));
    }




    void validate(Identifier recipeId) {
        if (this.advancementBuilder.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(recipeId));
        }

    }



}