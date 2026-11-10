package ambrosiac.mod.renderers;

import ambrosiac.mod.blocks.entities.SturdyFrameBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class SturdyFrameBlockEntityRenderer implements BlockEntityRenderer<SturdyFrameBlockEntity> {
    private final BlockRenderManager manager;

    public SturdyFrameBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        manager = context.getRenderManager();
    }

    @Override
    public void render(SturdyFrameBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity.isFramed()) {
            World world = entity.getWorld();
            BlockState state = entity.getFramedBlockState();
            BakedModel model = this.manager.getModel(state);
            BlockPos pos = entity.getPos();
            long renderingSeed = state.getRenderingSeed(pos);
            BlockModelRenderer blockModelRenderer = MinecraftClient.getInstance().getBlockRenderManager().getModelRenderer();
            RenderLayer renderLayer = RenderLayers.getMovingBlockLayer(state);
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);
            blockModelRenderer.render(world, model, state, pos, matrices, vertexConsumer, false, Random.create(), renderingSeed, overlay);

        }
    }
}
