package com.jaquadro.minecraft.modularpots.addon;

import com.jaquadro.minecraft.modularpots.ModularPots;
import com.jaquadro.minecraft.modularpots.block.LargePotPlantProxy;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.lang.reflect.Method;
import java.util.logging.Level;

public class ModPlantMegaPackLegacyHandler implements IPlantHandler
{
    private static final String MOD_ID = "plantmegapack";
    private static final String PLANT_BLOCK_CLASS = "plantmegapack.bin.PMPBlockPlant";

    private static Class<?> plantBlockClass;
    private static Method plantGrowAction;

    @Override
    public boolean init () {
        if (!Loader.isModLoaded(MOD_ID))
            return false;

        try {
            plantBlockClass = Class.forName(PLANT_BLOCK_CLASS);
            if (plantBlockClass == null)
                return false;

            plantGrowAction = plantBlockClass.getDeclaredMethod("growPlant", World.class, int.class, int.class, int.class);
            if (plantGrowAction == null)
                return false;

            FMLLog.log(ModularPots.MOD_ID, Level.INFO, "Initialized Plant Mega Pack legacy handler.");

            return true;
        }
        catch (Exception e) {
            FMLLog.log(ModularPots.MOD_ID, Level.SEVERE, "Could not initialize the Plant Mega Pack legacy handler");
            FMLLog.log(ModularPots.MOD_ID, Level.SEVERE, "Encountered load exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean applyBonemeal (World world, int x, int y, int z) {
        LargePotPlantProxy proxy = (LargePotPlantProxy) Block.blocksList[world.getBlockId(x, y, z)];
        if (proxy == null)
            return false;

        Block block = proxy.getItemBlock(world, x, y, z);
        if (block == null)
            return false;

        if (!plantBlockClass.isAssignableFrom(block.getClass()))
            return false;

        // Convert proxy to native block
        if (world.getBlockId(x, y + 1, z) == ModularPots.largePotPlantProxyId)
            world.setBlock(x, y + 1, z, block.blockID, world.getBlockMetadata(x, y + 1, z), 4);
        world.setBlock(x, y, z, block.blockID, world.getBlockMetadata(x, y, z), 4);
        if (world.getBlockId(x, y - 1, z) == ModularPots.largePotPlantProxyId)
            world.setBlock(x, y - 1, z, block.blockID, world.getBlockMetadata(x, y - 1, z), 4);

        Boolean result = false;
        try {
            result = (Boolean) plantGrowAction.invoke(block, world, x, y, z);
        }
        catch (Exception e) {
            System.out.println("[MFP] Error encountered in Plant Mega Pack Legacy handler.");
            result = false;
        }

        // Convert native block to proxy
        if (world.getBlockId(x, y + 1, z) == block.blockID)
            world.setBlock(x, y + 1, z, ModularPots.largePotPlantProxyId, world.getBlockMetadata(x, y + 1, z), 2);
        world.setBlock(x, y, z, ModularPots.largePotPlantProxyId, world.getBlockMetadata(x, y, z), 2);
        if (world.getBlockId(x, y - 1, z) == block.blockID)
            world.setBlock(x, y - 1, z, ModularPots.largePotPlantProxyId, world.getBlockMetadata(x, y - 1, z), 2);

        return result;
    }
}