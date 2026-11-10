package ambrosiac.mod.blocks.entities;

import ambrosiac.mod.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SturdyFrameBlockEntity extends BlockEntity {

    private ItemStack framedItem = ItemStack.EMPTY;
    private BlockState framedBlockState;

    public SturdyFrameBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STURDY_FRAME_BLOCK_ENTITY, pos, state);
    }

    public boolean isFramed() {
        return !framedItem.isEmpty();
    }

    public BlockState getFramedBlockState() {
        return framedBlockState;
    }

    public ItemActionResult onUseWithItem(ItemStack stack, World world, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (canSetFramedItem(stack, player)) {
            return ItemActionResult.SUCCESS;
        } else if (isFramed()) {
            return this.framedBlockState.onUseWithItem(stack, world, player, hand, hit);

        }

        return ItemActionResult.FAIL;
    }



    public boolean canSetFramedItem(ItemStack stack, PlayerEntity player) {
        List<Item> frameableBlocks = List.of(ModBlocks.ACTIVATED_DAHLIA.asItem(), ModBlocks.ACTIVATED_PEACE_LILY.asItem(), ModBlocks.ACTIVATED_SWISS_CHARD.asItem());
        if (frameableBlocks.contains(stack.getItem())) {
            if (isFramed()) {
                if (framedItem.equals(stack)) {
                    return false;
                }
                player.giveItemStack(framedItem);
            }
            this.framedItem = new ItemStack(stack.getItem());
            this.framedBlockState = Block.getBlockFromItem(framedItem.getItem()).getDefaultState();
            stack.decrementUnlessCreative(1, player);
            return true;
        } else if (stack.getItem().equals(Items.AIR)) {
            player.giveItemStack(framedItem);
            framedItem.setCount(0);
            framedBlockState = null;
            return true;
        }
        return false;
    }


}
