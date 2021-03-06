package com.mushroom.midnight.common.world.generator;

import com.mushroom.midnight.common.registry.ModBlocks;
import com.mushroom.midnight.common.registry.ModDimensions;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class MidnightOreGenerator implements IWorldGenerator {
    private static final long SEED = 21052088057241959L;

    private static final WorldGenerator DARK_PEARL_GENERATOR = new WorldGenMinable(ModBlocks.DARK_PEARL_ORE.getDefaultState(), 4, BlockMatcher.forBlock(ModBlocks.NIGHTSTONE));

    private static final int DARK_PEARL_PER_CHUNK = 12;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator generator, IChunkProvider provider) {
        if (world.provider.getDimensionType() != ModDimensions.MIDNIGHT) {
            return;
        }

        random.setSeed(random.nextLong() ^ SEED);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int globalX = chunkX << 4;
        int globalZ = chunkZ << 4;

        for (int i = 0; i < DARK_PEARL_PER_CHUNK; i++) {
            int offsetX = random.nextInt(16);
            int offsetY = random.nextInt(40);
            int offsetZ = random.nextInt(16);

            pos.setPos(globalX + offsetX, offsetY, globalZ + offsetZ);
            DARK_PEARL_GENERATOR.generate(world, random, pos);
        }
    }
}
