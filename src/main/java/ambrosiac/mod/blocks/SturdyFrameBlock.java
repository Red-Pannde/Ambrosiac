package ambrosiac.mod.blocks;

import ambrosiac.mod.blocks.entities.SturdyFrameBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickPriority;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SturdyFrameBlock extends BlockWithEntity {
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final MapCodec<SturdyFrameBlock> CODEC = createCodec(SturdyFrameBlock::new);

    public SturdyFrameBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(POWERED, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player.canModifyBlocks()) {
            return super.onUse(state, world, pos, player, hit);
        }
        return ActionResult.PASS;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.canModifyBlocks()) {
            SturdyFrameBlockEntity blockEntity = (SturdyFrameBlockEntity) world.getBlockEntity(pos);
            assert blockEntity != null;
            if (!blockEntity.isFramed() || (blockEntity.isFramed() && stack.getItem().equals(Items.AIR))) {
                return blockEntity.onUseWithItem(stack, world, player, hand, hit);
            }
            return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        return ItemActionResult.FAIL;
    }
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        boolean bl = (Boolean) state.get(POWERED);
        boolean bl2 = this.hasPower(world, pos, state);
        if (bl != bl2 && !world.getBlockTickScheduler().isTicking(pos, this)) {
            TickPriority tickPriority = TickPriority.HIGH;
            if (bl) {
                tickPriority = TickPriority.VERY_HIGH;
            }

            world.scheduleBlockTick(pos, this, 0, tickPriority);
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean bl = (Boolean) state.get(POWERED);
        boolean bl2 = this.hasPower(world, pos, state);
        if (bl && !bl2) {
            world.setBlockState(pos, state.with(POWERED, false), Block.NOTIFY_LISTENERS);
        } else if (!bl) {
            world.setBlockState(pos, state.with(POWERED, true), Block.NOTIFY_LISTENERS);
            if (!bl2) {
                world.scheduleBlockTick(pos, this, 0, TickPriority.VERY_HIGH);
            }
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.canPlaceAt(world, pos)) {
            this.updatePowered(world, pos, state);
        } else {
            BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
            dropStacks(state, world, pos, blockEntity);
            world.removeBlock(pos, false);

            for (Direction direction : Direction.values()) {
                world.updateNeighborsAlways(pos.offset(direction), this);
            }
        }
    }

    protected int getPower(World world, BlockPos pos, BlockState state) {
        int i = 0;
        for (Direction direction : Direction.values()) {


            BlockPos blockPos = pos.offset(direction);
            if (i < world.getEmittedRedstonePower(blockPos, direction)) {
                i = world.getEmittedRedstonePower(blockPos, direction);
            }
        }
        return i;
    }

    protected boolean hasPower(World world, BlockPos pos, BlockState state) {
        return this.getPower(world, pos, state) > 0;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SturdyFrameBlockEntity(pos, state);
    }
}
