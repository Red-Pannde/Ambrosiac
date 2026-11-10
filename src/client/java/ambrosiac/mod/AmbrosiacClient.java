package ambrosiac.mod;

import ambrosiac.mod.registry.ModBlocks;
import ambrosiac.mod.blocks.entities.ModBlockEntities;
import ambrosiac.mod.renderers.SturdyFrameBlockEntityRenderer;
import ambrosiac.mod.screens.AlchemistsCauldronScreen;
import ambrosiac.mod.screens.ModScreenHandler;
import net.fabricmc.api.ClientModInitializer;
public class AmbrosiacClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAHLIA, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DAHLIA, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ACTIVATED_DAHLIA, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWISS_CHARD, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ACTIVATED_SWISS_CHARD, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEACE_LILY, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ACTIVATED_PEACE_LILY, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALCHEMISTS_CAULDRON, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALCHEMISTS_CAULDRON, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STURDY_FRAME, RenderLayer.getCutoutMipped());

		HandledScreens.register(ModScreenHandler.ALCHEMISTS_CAULDRON_SCREEN_HANDLER, AlchemistsCauldronScreen::new);
		BlockEntityRendererFactories.register(ModBlockEntities.STURDY_FRAME_BLOCK_ENTITY, SturdyFrameBlockEntityRenderer::new);


	}

}