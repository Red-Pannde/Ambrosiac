package ambrosiac.mod.datagen;

import ambrosiac.mod.Ambrosiac;
import ambrosiac.mod.blocks.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;



public class AmbrosiacBlockTagGen extends FabricTagProvider<Block> {

public static final TagKey<Block> WATER_ELEMENT_BLOCK = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ambrosiac.MOD_ID, "water"));
public static final TagKey<Block> ICE_ELEMENT_BLOCK = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ambrosiac.MOD_ID, "ice"));
    public AmbrosiacBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WATER_ELEMENT_BLOCK)
                .add(ModBlocks.ACTIVATED_PEACE_LILY);
        getOrCreateTagBuilder(ICE_ELEMENT_BLOCK)
                .add(ModBlocks.ACTIVATED_SWISS_CHARD);
    }
}