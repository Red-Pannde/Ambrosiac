package ambrosiac.mod.registry;

import ambrosiac.mod.Ambrosiac;
import ambrosiac.mod.blocks.*;
import ambrosiac.mod.blocks.activated.ActivatedDahliaBlock;
import ambrosiac.mod.blocks.activated.ActivatedPeaceLilyBlock;
import ambrosiac.mod.blocks.activated.ActivatedSwissChardBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;


public class ModBlocks {
    private ComponentMap.Builder components;

    private static Block register( String name, Block block, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings());
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Ambrosiac.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ambrosiac.MOD_ID, name));
    }

    public static final Block DAHLIA = register(
            "dahlia",
            new DahliaBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AZALEA).noCollision().dynamicBounds().pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block ALCHEMISTS_CAULDRON = register(
            "alchemists_cauldron",
            new AlchemistsCauldronBlock(Block.Settings.create().sounds(BlockSoundGroup.METAL).dynamicBounds().strength(1.0f, 3.0f)),
            true
    );
    public static final Block SWISS_CHARD = register(
            "swiss_chard",
            new SwissChardBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AZALEA).dynamicBounds().noCollision().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)),
            false
    );
    public static final Block PEACE_LILY = register(
            "peace_lily",
            new PeaceLilyBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AZALEA).dynamicBounds().noCollision().nonOpaque().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY)),
            true
    );
    public static final Block ACTIVATED_PEACE_LILY = register(
            "activated_peace_lily",
            new ActivatedPeaceLilyBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AZALEA).dynamicBounds().noCollision().nonOpaque().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY)),
            false
    );
    public static final Block ACTIVATED_DAHLIA = register(
            "activated_dahlia",
            new ActivatedDahliaBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AZALEA).dynamicBounds().noCollision().nonOpaque().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY)),
            false
    );
    public static final Block ACTIVATED_SWISS_CHARD = register(
            "activated_swiss_chard",
            new ActivatedSwissChardBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.AZALEA).dynamicBounds().noCollision().nonOpaque().ticksRandomly().pistonBehavior(PistonBehavior.DESTROY)),
            false
    );
    public static final Block STURDY_FRAME = register(
            "sturdy_frame",
            new SturdyFrameBlock(AbstractBlock.Settings.create().nonOpaque()),
            true
    );
    public static final Block POTTED_DAHLIA = register(
            "potted_dahlia",
            Blocks.createFlowerPotBlock(DAHLIA),
            false
    );


    public static void initialize() {
    }
}
