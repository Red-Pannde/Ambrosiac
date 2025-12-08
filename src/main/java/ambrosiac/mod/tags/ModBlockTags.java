package ambrosiac.mod.tags;

import ambrosiac.mod.Ambrosiac;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModBlockTags {
    public static final TagKey<Block> WATER = of("water");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ambrosiac.MOD_ID, id));
    }
}
