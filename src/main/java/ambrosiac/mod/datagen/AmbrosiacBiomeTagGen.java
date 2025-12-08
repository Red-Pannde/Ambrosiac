package ambrosiac.mod.datagen;

import ambrosiac.mod.Ambrosiac;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class AmbrosiacBiomeTagGen extends FabricTagProvider<Biome> {

    public AmbrosiacBiomeTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    public static final TagKey<Biome> WATER_ELEMENT_BIOME = TagKey.of(RegistryKeys.BIOME, Identifier.of(Ambrosiac.MOD_ID, "water"));
    public static final TagKey<Biome> ICE_ELEMENT_BIOME = TagKey.of(RegistryKeys.BIOME, Identifier.of(Ambrosiac.MOD_ID, "ice"));


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(WATER_ELEMENT_BIOME)
                .add(RegistryKeys.BIOME.getValue().withPath("jungle"))
                .add(RegistryKeys.BIOME.getValue().withPath("mangrove_swamp"))
                .add(RegistryKeys.BIOME.getValue().withPath("lush_caves"))
                .add(RegistryKeys.BIOME.getValue().withPath("bamboo_jungle"))
                .setReplace(true);
        getOrCreateTagBuilder(ICE_ELEMENT_BIOME)
                .add(RegistryKeys.BIOME.getValue().withPath("deep_frozen_ocean"))
                .add(RegistryKeys.BIOME.getValue().withPath("frozen_ocean"))
                .add(RegistryKeys.BIOME.getValue().withPath("frozen_peaks"))
                .add(RegistryKeys.BIOME.getValue().withPath("frozen_river"))
                .add(RegistryKeys.BIOME.getValue().withPath("ice_spikes"))
                .add(RegistryKeys.BIOME.getValue().withPath("snowy_beach"))
                .add(RegistryKeys.BIOME.getValue().withPath("snowy_plains"))
                .add(RegistryKeys.BIOME.getValue().withPath("snowy_slopes"))
                .add(RegistryKeys.BIOME.getValue().withPath("snowy_taiga"))
                .setReplace(true);
    }
}
