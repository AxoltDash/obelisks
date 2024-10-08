package net.axoltdash.obelisks.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class ZenithiteItem extends Item {
    public ZenithiteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!world.isClient) {
            HitResult hitResult = player.raycast(32, 0.0F, false);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                Vec3d targetPos = blockHitResult.getPos();

                player.requestTeleport(targetPos.x, targetPos.y, targetPos.z);
                itemStack.decrement(1); // Remove one item from the stack

                // Play Enderman teleport sound
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);

                return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
            }
        }

        return new TypedActionResult<>(ActionResult.PASS, itemStack);
    }
}