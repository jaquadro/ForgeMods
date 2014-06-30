package com.jaquadro.minecraft.gardencore.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public final class RenderUtil
{
    private static float[] colorScratch = new float[3];

    private RenderUtil () { }

    public static void calculateBaseColor (float[] target, int color) {
        float r = (float)(color >> 16 & 255) / 255f;
        float g = (float)(color >> 8 & 255) / 255f;
        float b = (float)(color & 255) / 255f;

        if (EntityRenderer.anaglyphEnable) {
            float gray = (r * 30f + g * 59f + b * 11f) / 100f;
            float rg = (r * 30f + g * 70f) / 100f;
            float rb = (r * 30f + b * 70f) / 100f;

            r = gray;
            g = rg;
            b = rb;
        }

        target[0] = r;
        target[1] = g;
        target[2] = b;
    }

    public static void scaleColor (float[] target, float[] source, float scale) {
        target[0] = source[0] * scale;
        target[1] = source[1] * scale;
        target[2] = source[2] * scale;
    }

    public static void setTessellatorColor (Tessellator tessellator, float[] color) {
        tessellator.setColorOpaque_F(color[0], color[1], color[2]);
    }

    public static void renderEmptyPlane (Block block, int x, int y, int z, RenderBlocks renderer) {
        renderer.setRenderBounds(0, 0, 0, 0, 0, 0);
        renderer.renderFaceYNeg(block, x, y, z, Blocks.dirt.getIcon(0, 0));
    }

    public static void renderFaceYNeg (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon) {
        calculateBaseColor(colorScratch, block.colorMultiplier(renderer.blockAccess, x, y, z));
        renderFaceYNeg(renderer, block, x, y, z, icon, colorScratch[0], colorScratch[1], colorScratch[2]);
    }

    public static void renderFaceYPos (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon) {
        calculateBaseColor(colorScratch, block.colorMultiplier(renderer.blockAccess, x, y, z));
        renderFaceYPos(renderer, block, x, y, z, icon, colorScratch[0], colorScratch[1], colorScratch[2]);
    }

    public static void renderFaceZNeg (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon) {
        calculateBaseColor(colorScratch, block.colorMultiplier(renderer.blockAccess, x, y, z));
        renderFaceZNeg(renderer, block, x, y, z, icon, colorScratch[0], colorScratch[1], colorScratch[2]);
    }

    public static void renderFaceZPos (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon) {
        calculateBaseColor(colorScratch, block.colorMultiplier(renderer.blockAccess, x, y, z));
        renderFaceZPos(renderer, block, x, y, z, icon, colorScratch[0], colorScratch[1], colorScratch[2]);
    }

    public static void renderFaceXNeg (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon) {
        calculateBaseColor(colorScratch, block.colorMultiplier(renderer.blockAccess, x, y, z));
        renderFaceXNeg(renderer, block, x, y, z, icon, colorScratch[0], colorScratch[1], colorScratch[2]);
    }

    public static void renderFaceXPos (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon) {
        calculateBaseColor(colorScratch, block.colorMultiplier(renderer.blockAccess, x, y, z));
        renderFaceXPos(renderer, block, x, y, z, icon, colorScratch[0], colorScratch[1], colorScratch[2]);
    }

    public static void renderFaceYNeg (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        if (Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderer.blockAccess, x, y, z) == 0)
            renderFaceYNegAOPartial(renderer, block, x, y, z, icon, r, g, b);
        else
            renderFaceYNegColorMult(renderer, block, x, y, z, icon, r, g, b);
    }

    public static void renderFaceYPos (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        if (Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderer.blockAccess, x, y, z) == 0)
            renderFaceYPosAOPartial(renderer, block, x, y, z, icon, r, g, b);
        else
            renderFaceYPosColorMult(renderer, block, x, y, z, icon, r, g, b);
    }

    public static void renderFaceZNeg (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        if (Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderer.blockAccess, x, y, z) == 0)
            renderFaceZNegAOPartial(renderer, block, x, y, z, icon, r, g, b);
        else
            renderFaceZNegColorMult(renderer, block, x, y, z, icon, r, g, b);
    }

    public static void renderFaceZPos (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        if (Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderer.blockAccess, x, y, z) == 0)
            renderFaceZPosAOPartial(renderer, block, x, y, z, icon, r, g, b);
        else
            renderFaceZPosColorMult(renderer, block, x, y, z, icon, r, g, b);
    }

    public static void renderFaceXNeg (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        if (Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderer.blockAccess, x, y, z) == 0)
            renderFaceXNegAOPartial(renderer, block, x, y, z, icon, r, g, b);
        else
            renderFaceXNegColorMult(renderer, block, x, y, z, icon, r, g, b);
    }

    public static void renderFaceXPos (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        if (Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderer.blockAccess, x, y, z) == 0)
            renderFaceXPosAOPartial(renderer, block, x, y, z, icon, r, g, b);
        else
            renderFaceXPosColorMult(renderer, block, x, y, z, icon, r, g, b);
    }

    public static void renderFaceYNegColorMult (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, (renderer.renderMinY > 0) ? y : y - 1, z));
        tessellator.setColorOpaque_F(.5f * r, .5f * g, .5f * b);

        renderer.enableAO = false;
        renderer.renderFaceYNeg(block, (double) x, (double) y, (double) z, icon);
    }

    public static void renderFaceYPosColorMult (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, (renderer.renderMaxY < 1) ? y : y + 1, z));
        tessellator.setColorOpaque_F(1f * r, 1f * g, 1f * b);

        renderer.enableAO = false;
        renderer.renderFaceYPos(block, (double) x, (double) y, (double) z, icon);
    }

    public static void renderFaceZNegColorMult (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, (renderer.renderMinZ > 0) ? z : z - 1));
        tessellator.setColorOpaque_F(.8f * r, .8f * g, .8f * b);

        renderer.enableAO = false;
        renderer.renderFaceZNeg(block, (double) x, (double) y, (double) z, icon);
    }

    public static void renderFaceZPosColorMult (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, (renderer.renderMaxZ < 1) ? z : z + 1));
        tessellator.setColorOpaque_F(.8f * r, .8f * g, .8f * b);

        renderer.enableAO = false;
        renderer.renderFaceZPos(block, (double) x, (double) y, (double) z, icon);
    }

    public static void renderFaceXNegColorMult (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, (renderer.renderMinX > 0) ? x : x - 1, y, z));
        tessellator.setColorOpaque_F(.6f * r, .6f * g, .6f * b);

        renderer.enableAO = false;
        renderer.renderFaceXNeg(block, (double) x, (double) y, (double) z, icon);
    }

    public static void renderFaceXPosColorMult (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, (renderer.renderMaxX < 1) ? x : x + 1, y, z));
        tessellator.setColorOpaque_F(.6f * r, .6f * g, .6f * b);

        renderer.enableAO = false;
        renderer.renderFaceXPos(block, (double) x, (double) y, (double) z, icon);
    }

    private static int aoBrightnessXYNN;
    private static int aoBrightnessYZNN;
    private static int aoBrightnessYZNP;
    private static int aoBrightnessXYPN;
    private static int aoBrightnessXYNP;
    private static int aoBrightnessXYPP;
    private static int aoBrightnessYZPN;
    private static int aoBrightnessYZPP;
    private static int aoBrightnessXZNN;
    private static int aoBrightnessXZPN;
    private static int aoBrightnessXZNP;
    private static int aoBrightnessXZPP;
    private static int aoBrightnessXYZNNN;
    private static int aoBrightnessXYZNNP;
    private static int aoBrightnessXYZPNN;
    private static int aoBrightnessXYZPNP;
    private static int aoBrightnessXYZNPN;
    private static int aoBrightnessXYZPPN;
    private static int aoBrightnessXYZNPP;
    private static int aoBrightnessXYZPPP;

    private static float aoLightValueScratchXYNN;
    private static float aoLightValueScratchYZNN;
    private static float aoLightValueScratchYZNP;
    private static float aoLightValueScratchXYPN;
    private static float aoLightValueScratchXYNP;
    private static float aoLightValueScratchXYPP;
    private static float aoLightValueScratchYZPN;
    private static float aoLightValueScratchYZPP;
    private static float aoLightValueScratchXZNN;
    private static float aoLightValueScratchXZPN;
    private static float aoLightValueScratchXZNP;
    private static float aoLightValueScratchXZPP;
    private static float aoLightValueScratchXYZNNN;
    private static float aoLightValueScratchXYZNNP;
    private static float aoLightValueScratchXYZPNN;
    private static float aoLightValueScratchXYZPNP;
    private static float aoLightValueScratchXYZNPN;
    private static float aoLightValueScratchXYZPPN;
    private static float aoLightValueScratchXYZNPP;
    private static float aoLightValueScratchXYZPPP;

    public static void renderFaceYNegAOPartial (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.renderMinY <= 0.0D)
            --y;

        aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
        aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
        aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
        aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
        aoBrightnessXYZNNN = aoBrightnessXYNN;
        aoBrightnessXYZNNP = aoBrightnessXYNN;
        aoBrightnessXYZPNN = aoBrightnessXYPN;
        aoBrightnessXYZPNP = aoBrightnessXYPN;

        aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZNNN = aoLightValueScratchXYNN;
        aoLightValueScratchXYZNNP = aoLightValueScratchXYNN;
        aoLightValueScratchXYZPNN = aoLightValueScratchXYPN;
        aoLightValueScratchXYZPNP = aoLightValueScratchXYPN;

        boolean blocksGrassXYPN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
        boolean blocksGrassXYNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
        boolean blocksGrassYZNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
        boolean blocksGrassYZNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();

        if (blocksGrassYZNN || blocksGrassXYNN) {
            aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
        }

        if (blocksGrassYZNP || blocksGrassXYNN) {
            aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
        }

        if (blocksGrassYZNN || blocksGrassXYPN) {
            aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
        }

        if (blocksGrassYZNP || blocksGrassXYPN) {
            aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
        }

        if (renderer.renderMinY <= 0.0D)
            ++y;

        int blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderMinY <= 0.0D || !renderer.blockAccess.getBlock(x, y - 1, z).isOpaqueCube())
            blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);

        float aoOpposingBlock = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
        float aoXYZNNP = (aoLightValueScratchXYZNNP + aoLightValueScratchXYNN + aoLightValueScratchYZNP + aoOpposingBlock) / 4.0F;  // TL
        float aoXYZPNP = (aoLightValueScratchYZNP + aoOpposingBlock + aoLightValueScratchXYZPNP + aoLightValueScratchXYPN) / 4.0F;  // TR
        float aoXYZPNN = (aoOpposingBlock + aoLightValueScratchYZNN + aoLightValueScratchXYPN + aoLightValueScratchXYZPNN) / 4.0F;  // BR
        float aoXYZNNN = (aoLightValueScratchXYNN + aoLightValueScratchXYZNNN + aoOpposingBlock + aoLightValueScratchYZNN) / 4.0F;  // BL

        float aoTL = (float)((double)aoXYZNNP * renderer.renderMinX * (1.0D - renderer.renderMaxZ) + (double)aoXYZPNP * renderer.renderMinX * renderer.renderMaxZ + (double)aoXYZPNN * (1.0D - renderer.renderMinX) * renderer.renderMaxZ + (double)aoXYZNNN * (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMaxZ));
        float aoBL = (float)((double)aoXYZNNP * renderer.renderMinX * (1.0D - renderer.renderMinZ) + (double)aoXYZPNP * renderer.renderMinX * renderer.renderMinZ + (double)aoXYZPNN * (1.0D - renderer.renderMinX) * renderer.renderMinZ + (double)aoXYZNNN * (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMinZ));
        float aoBR = (float)((double)aoXYZNNP * renderer.renderMaxX * (1.0D - renderer.renderMinZ) + (double)aoXYZPNP * renderer.renderMaxX * renderer.renderMinZ + (double)aoXYZPNN * (1.0D - renderer.renderMaxX) * renderer.renderMinZ + (double)aoXYZNNN * (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMinZ));
        float aoTR = (float)((double)aoXYZNNP * renderer.renderMaxX * (1.0D - renderer.renderMaxZ) + (double)aoXYZPNP * renderer.renderMaxX * renderer.renderMaxZ + (double)aoXYZPNN * (1.0D - renderer.renderMaxX) * renderer.renderMaxZ + (double)aoXYZNNN * (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMaxZ));

        int brXYZNNP = renderer.getAoBrightness(aoBrightnessXYZNNP, aoBrightnessXYNN, aoBrightnessYZNP, blockBrightness);
        int brXYZPNP = renderer.getAoBrightness(aoBrightnessYZNP, aoBrightnessXYZPNP, aoBrightnessXYPN, blockBrightness);
        int brXYZPNN = renderer.getAoBrightness(aoBrightnessYZNN, aoBrightnessXYPN, aoBrightnessXYZPNN, blockBrightness);
        int brXYZNNN = renderer.getAoBrightness(aoBrightnessXYNN, aoBrightnessXYZNNN, aoBrightnessYZNN, blockBrightness);

        renderer.brightnessTopLeft = renderer.mixAoBrightness(brXYZNNP, brXYZNNN, brXYZPNN, brXYZPNP, renderer.renderMaxX * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMaxX) * renderer.renderMaxZ, renderer.renderMaxX * renderer.renderMaxZ);
        renderer.brightnessBottomLeft = renderer.mixAoBrightness(brXYZNNP, brXYZNNN, brXYZPNN, brXYZPNP, renderer.renderMaxX * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMaxX) * renderer.renderMinZ, renderer.renderMaxX * renderer.renderMinZ);
        renderer.brightnessBottomRight = renderer.mixAoBrightness(brXYZNNP, brXYZNNN, brXYZPNN, brXYZPNP, renderer.renderMinX * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMinX) * renderer.renderMinZ, renderer.renderMinX * renderer.renderMinZ);
        renderer.brightnessTopRight = renderer.mixAoBrightness(brXYZNNP, brXYZNNN, brXYZPNN, brXYZPNP, renderer.renderMinX * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMinX) * renderer.renderMaxZ, renderer.renderMinX * renderer.renderMaxZ);

        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.5F;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.5F;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.5F;

        renderer.colorRedTopLeft *= aoTL;
        renderer.colorGreenTopLeft *= aoTL;
        renderer.colorBlueTopLeft *= aoTL;
        renderer.colorRedBottomLeft *= aoBL;
        renderer.colorGreenBottomLeft *= aoBL;
        renderer.colorBlueBottomLeft *= aoBL;
        renderer.colorRedBottomRight *= aoBR;
        renderer.colorGreenBottomRight *= aoBR;
        renderer.colorBlueBottomRight *= aoBR;
        renderer.colorRedTopRight *= aoTR;
        renderer.colorGreenTopRight *= aoTR;
        renderer.colorBlueTopRight *= aoTR;

        renderer.enableAO = true;
        renderer.renderFaceYNeg(block, (double)x, (double)y, (double)z, icon);
        renderer.enableAO = false;
    }

    public static void renderFaceYPosAOPartial (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.renderMaxY >= 1.0D)
            ++y;

        aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
        aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
        aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
        aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
        aoBrightnessXYZNPN = aoBrightnessXYNP;
        aoBrightnessXYZPPN = aoBrightnessXYPP;
        aoBrightnessXYZNPP = aoBrightnessXYNP;
        aoBrightnessXYZPPP = aoBrightnessXYPP;

        aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZNPN = aoLightValueScratchXYNP;
        aoLightValueScratchXYZPPN = aoLightValueScratchXYPP;
        aoLightValueScratchXYZNPP = aoLightValueScratchXYNP;
        aoLightValueScratchXYZPPP = aoLightValueScratchXYPP;

        boolean blocksGrassXYPP = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
        boolean blocksGrassXYNP = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
        boolean blocksGrassYZPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
        boolean blocksGrassYZPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();

        if (blocksGrassYZPN || blocksGrassXYNP) {
            aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
        }

        if (blocksGrassYZPN || blocksGrassXYPP) {
            aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
        }

        if (blocksGrassYZPP || blocksGrassXYNP) {
            aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
        }

        if (blocksGrassYZPP || blocksGrassXYPP) {
            aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
        }

        if (renderer.renderMaxY >= 1.0D)
            --y;

        int blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderMaxY >= 1.0D || !renderer.blockAccess.getBlock(x, y + 1, z).isOpaqueCube())
            blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);

        float aoOpposingBlock = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        float aoXYZNPP = (aoLightValueScratchXYZNPP + aoLightValueScratchXYNP + aoLightValueScratchYZPP + aoOpposingBlock) / 4.0F;  // TR
        float aoXYZPPP = (aoLightValueScratchYZPP + aoOpposingBlock + aoLightValueScratchXYZPPP + aoLightValueScratchXYPP) / 4.0F;  // TL
        float aoXYZPPN = (aoOpposingBlock + aoLightValueScratchYZPN + aoLightValueScratchXYPP + aoLightValueScratchXYZPPN) / 4.0F;  // BL
        float aoXYZNPN = (aoLightValueScratchXYNP + aoLightValueScratchXYZNPN + aoOpposingBlock + aoLightValueScratchYZPN) / 4.0F;  // BR

        float aoTL = (float)((double)aoXYZPPP * renderer.renderMaxX * (1.0D - renderer.renderMaxZ) + (double)aoXYZNPP * renderer.renderMaxX * renderer.renderMaxZ + (double)aoXYZNPN * (1.0D - renderer.renderMaxX) * renderer.renderMaxZ + (double)aoXYZPPN * (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMaxZ));
        float aoBL = (float)((double)aoXYZPPP * renderer.renderMaxX * (1.0D - renderer.renderMinZ) + (double)aoXYZNPP * renderer.renderMaxX * renderer.renderMinZ + (double)aoXYZNPN * (1.0D - renderer.renderMaxX) * renderer.renderMinZ + (double)aoXYZPPN * (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMinZ));
        float aoBR = (float)((double)aoXYZPPP * renderer.renderMinX * (1.0D - renderer.renderMinZ) + (double)aoXYZNPP * renderer.renderMinX * renderer.renderMinZ + (double)aoXYZNPN * (1.0D - renderer.renderMinX) * renderer.renderMinZ + (double)aoXYZPPN * (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMinZ));
        float aoTR = (float)((double)aoXYZPPP * renderer.renderMinX * (1.0D - renderer.renderMaxZ) + (double)aoXYZNPP * renderer.renderMinX * renderer.renderMaxZ + (double)aoXYZNPN * (1.0D - renderer.renderMinX) * renderer.renderMaxZ + (double)aoXYZPPN * (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMaxZ));

        int brXYZNPP = renderer.getAoBrightness(aoBrightnessXYNP, aoBrightnessXYZNPP, aoBrightnessYZPP, blockBrightness);
        int brXYZPPP = renderer.getAoBrightness(aoBrightnessYZPP, aoBrightnessXYPP, aoBrightnessXYZPPP, blockBrightness);
        int brXYZPPN = renderer.getAoBrightness(aoBrightnessYZPN, aoBrightnessXYZPPN, aoBrightnessXYPP, blockBrightness);
        int brXYZNPN = renderer.getAoBrightness(aoBrightnessXYZNPN, aoBrightnessXYNP, aoBrightnessYZPN, blockBrightness);

        renderer.brightnessTopLeft = renderer.mixAoBrightness(brXYZPPP, brXYZPPN, brXYZNPN, brXYZNPP, renderer.renderMaxX * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMaxX) * renderer.renderMaxZ, renderer.renderMaxX * renderer.renderMaxZ);
        renderer.brightnessBottomLeft = renderer.mixAoBrightness(brXYZPPP, brXYZPPN, brXYZNPN, brXYZNPP, renderer.renderMaxX * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMaxX) * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMaxX) * renderer.renderMinZ, renderer.renderMaxX * renderer.renderMinZ);
        renderer.brightnessBottomRight = renderer.mixAoBrightness(brXYZPPP, brXYZPPN, brXYZNPN, brXYZNPP, renderer.renderMinX * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMinX) * renderer.renderMinZ, renderer.renderMinX * renderer.renderMinZ);
        renderer.brightnessTopRight = renderer.mixAoBrightness(brXYZPPP, brXYZPPN, brXYZNPN, brXYZNPP, renderer.renderMinX * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMinX) * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMinX) * renderer.renderMaxZ, renderer.renderMinX * renderer.renderMaxZ);

        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b;

        renderer.colorRedTopLeft *= aoTL;
        renderer.colorGreenTopLeft *= aoTL;
        renderer.colorBlueTopLeft *= aoTL;
        renderer.colorRedBottomLeft *= aoBL;
        renderer.colorGreenBottomLeft *= aoBL;
        renderer.colorBlueBottomLeft *= aoBL;
        renderer.colorRedBottomRight *= aoBR;
        renderer.colorGreenBottomRight *= aoBR;
        renderer.colorBlueBottomRight *= aoBR;
        renderer.colorRedTopRight *= aoTR;
        renderer.colorGreenTopRight *= aoTR;
        renderer.colorBlueTopRight *= aoTR;

        renderer.enableAO = true;
        renderer.renderFaceYPos(block, (double) x, (double) y, (double) z, icon);
        renderer.enableAO = false;
    }

    public static void renderFaceZNegAOPartial (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.renderMinZ <= 0.0D)
            --z;

        aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZNNN = aoLightValueScratchXZNN;
        aoLightValueScratchXYZNPN = aoLightValueScratchXZNN;
        aoLightValueScratchXYZPNN = aoLightValueScratchXZPN;
        aoLightValueScratchXYZPPN = aoLightValueScratchXZPN;

        aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
        aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
        aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
        aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
        aoBrightnessXYZNNN = aoBrightnessXZNN;
        aoBrightnessXYZNPN = aoBrightnessXZNN;
        aoBrightnessXYZPNN = aoBrightnessXZPN;
        aoBrightnessXYZPPN = aoBrightnessXZPN;

        boolean blocksGrassXZPN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
        boolean blocksGrassXZNN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
        boolean blocksGrassYZPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
        boolean blocksGrassYZNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();

        if (blocksGrassXZNN || blocksGrassYZNN) {
            aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
        }

        if (blocksGrassXZNN || blocksGrassYZPN) {
            aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
        }

        if (blocksGrassXZPN || blocksGrassYZNN) {
            aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
        }

        if (blocksGrassXZPN || blocksGrassYZPN) {
            aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
        }

        if (renderer.renderMinZ <= 0.0D)
            ++z;

        int blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderMinZ <= 0.0D || !renderer.blockAccess.getBlock(x, y, z - 1).isOpaqueCube())
            blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);

        float aoOpposingBlock = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        float aoXYZNPN = (aoLightValueScratchXZNN + aoLightValueScratchXYZNPN + aoOpposingBlock + aoLightValueScratchYZPN) / 4.0F;
        float aoXYZPPN = (aoOpposingBlock + aoLightValueScratchYZPN + aoLightValueScratchXZPN + aoLightValueScratchXYZPPN) / 4.0F;
        float aoXYZPNN = (aoLightValueScratchYZNN + aoOpposingBlock + aoLightValueScratchXYZPNN + aoLightValueScratchXZPN) / 4.0F;
        float aoXYZNNN = (aoLightValueScratchXYZNNN + aoLightValueScratchXZNN + aoLightValueScratchYZNN + aoOpposingBlock) / 4.0F;

        float aoTL = (float)((double)aoXYZNPN * renderer.renderMaxY * (1.0D - renderer.renderMinX) + (double)aoXYZPPN * renderer.renderMaxY * renderer.renderMinX + (double)aoXYZPNN * (1.0D - renderer.renderMaxY) * renderer.renderMinX + (double)aoXYZNNN * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinX));
        float aoBL = (float)((double)aoXYZNPN * renderer.renderMaxY * (1.0D - renderer.renderMaxX) + (double)aoXYZPPN * renderer.renderMaxY * renderer.renderMaxX + (double)aoXYZPNN * (1.0D - renderer.renderMaxY) * renderer.renderMaxX + (double)aoXYZNNN * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxX));
        float aoBR = (float)((double)aoXYZNPN * renderer.renderMinY * (1.0D - renderer.renderMaxX) + (double)aoXYZPPN * renderer.renderMinY * renderer.renderMaxX + (double)aoXYZPNN * (1.0D - renderer.renderMinY) * renderer.renderMaxX + (double)aoXYZNNN * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxX));
        float aoTR = (float)((double)aoXYZNPN * renderer.renderMinY * (1.0D - renderer.renderMinX) + (double)aoXYZPPN * renderer.renderMinY * renderer.renderMinX + (double)aoXYZPNN * (1.0D - renderer.renderMinY) * renderer.renderMinX + (double)aoXYZNNN * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinX));

        int brXYZNPN = renderer.getAoBrightness(aoBrightnessXZNN, aoBrightnessXYZNPN, aoBrightnessYZPN, blockBrightness);
        int brXYZPPN = renderer.getAoBrightness(aoBrightnessYZPN, aoBrightnessXZPN, aoBrightnessXYZPPN, blockBrightness);
        int brXYZPNN = renderer.getAoBrightness(aoBrightnessYZNN, aoBrightnessXYZPNN, aoBrightnessXZPN, blockBrightness);
        int brXYZNNN = renderer.getAoBrightness(aoBrightnessXYZNNN, aoBrightnessXZNN, aoBrightnessYZNN, blockBrightness);

        renderer.brightnessTopLeft = renderer.mixAoBrightness(brXYZNPN, brXYZPPN, brXYZPNN, brXYZNNN, renderer.renderMaxY * (1.0D - renderer.renderMinX), renderer.renderMaxY * renderer.renderMinX, (1.0D - renderer.renderMaxY) * renderer.renderMinX, (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinX));
        renderer.brightnessBottomLeft = renderer.mixAoBrightness(brXYZNPN, brXYZPPN, brXYZPNN, brXYZNNN, renderer.renderMaxY * (1.0D - renderer.renderMaxX), renderer.renderMaxY * renderer.renderMaxX, (1.0D - renderer.renderMaxY) * renderer.renderMaxX, (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxX));
        renderer.brightnessBottomRight = renderer.mixAoBrightness(brXYZNPN, brXYZPPN, brXYZPNN, brXYZNNN, renderer.renderMinY * (1.0D - renderer.renderMaxX), renderer.renderMinY * renderer.renderMaxX, (1.0D - renderer.renderMinY) * renderer.renderMaxX, (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxX));
        renderer.brightnessTopRight = renderer.mixAoBrightness(brXYZNPN, brXYZPPN, brXYZPNN, brXYZNNN, renderer.renderMinY * (1.0D - renderer.renderMinX), renderer.renderMinY * renderer.renderMinX, (1.0D - renderer.renderMinY) * renderer.renderMinX, (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinX));

        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;

        renderer.colorRedTopLeft *= aoTL;
        renderer.colorGreenTopLeft *= aoTL;
        renderer.colorBlueTopLeft *= aoTL;
        renderer.colorRedBottomLeft *= aoBL;
        renderer.colorGreenBottomLeft *= aoBL;
        renderer.colorBlueBottomLeft *= aoBL;
        renderer.colorRedBottomRight *= aoBR;
        renderer.colorGreenBottomRight *= aoBR;
        renderer.colorBlueBottomRight *= aoBR;
        renderer.colorRedTopRight *= aoTR;
        renderer.colorGreenTopRight *= aoTR;
        renderer.colorBlueTopRight *= aoTR;

        renderer.enableAO = true;
        renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, icon);
        renderer.enableAO = false;
    }

    public static void renderFaceZPosAOPartial (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.renderMinZ >= 1.0D)
            ++z;

        aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZNNP = aoLightValueScratchXZNP;
        aoLightValueScratchXYZNPP = aoLightValueScratchXZNP;
        aoLightValueScratchXYZPNP = aoLightValueScratchXZPP;
        aoLightValueScratchXYZPPP = aoLightValueScratchXZPP;

        aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
        aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
        aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
        aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
        aoBrightnessXYZNNP = aoBrightnessXZNP;
        aoBrightnessXYZNPP = aoBrightnessXZNP;
        aoBrightnessXYZPNP = aoBrightnessXZPP;
        aoBrightnessXYZPPP = aoBrightnessXZPP;

        boolean blocksGrassXZPP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
        boolean blocksGrassXZNP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
        boolean blocksGrassYZPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
        boolean blocksGrassYZNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();

        if (blocksGrassXZNP || blocksGrassYZNP) {
            aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
        }

        if (blocksGrassXZNP || blocksGrassYZPP) {
            aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
        }

        if (blocksGrassXZPP || blocksGrassYZNP) {
            aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
        }

        if (blocksGrassXZPP || blocksGrassYZPP) {
            aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
            aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
        }

        if (renderer.renderMinZ >= 1.0D)
            --z;

        int blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderMinZ >= 1.0D || !renderer.blockAccess.getBlock(x, y, z + 1).isOpaqueCube())
            blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);

        float aoOpposingBlock = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        float aoXYZNPP = (aoLightValueScratchXZNP + aoLightValueScratchXYZNPP + aoOpposingBlock + aoLightValueScratchYZPP) / 4.0F;
        float aoXYZPPP = (aoOpposingBlock + aoLightValueScratchYZPP + aoLightValueScratchXZPP + aoLightValueScratchXYZPPP) / 4.0F;
        float aoXYZPNP = (aoLightValueScratchYZNP + aoOpposingBlock + aoLightValueScratchXYZPNP + aoLightValueScratchXZPP) / 4.0F;
        float aoXYZNNP = (aoLightValueScratchXYZNNP + aoLightValueScratchXZNP + aoLightValueScratchYZNP + aoOpposingBlock) / 4.0F;

        float aoTL = (float)((double)aoXYZNPP * renderer.renderMaxY * (1.0D - renderer.renderMinX) + (double)aoXYZPPP * renderer.renderMaxY * renderer.renderMinX + (double)aoXYZPNP * (1.0D - renderer.renderMaxY) * renderer.renderMinX + (double)aoXYZNNP * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinX));
        float aoBL = (float)((double)aoXYZNPP * renderer.renderMinY * (1.0D - renderer.renderMinX) + (double)aoXYZPPP * renderer.renderMinY * renderer.renderMinX + (double)aoXYZPNP * (1.0D - renderer.renderMinY) * renderer.renderMinX + (double)aoXYZNNP * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinX));
        float aoBR = (float)((double)aoXYZNPP * renderer.renderMinY * (1.0D - renderer.renderMaxX) + (double)aoXYZPPP * renderer.renderMinY * renderer.renderMaxX + (double)aoXYZPNP * (1.0D - renderer.renderMinY) * renderer.renderMaxX + (double)aoXYZNNP * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxX));
        float aoTR = (float)((double)aoXYZNPP * renderer.renderMaxY * (1.0D - renderer.renderMaxX) + (double)aoXYZPPP * renderer.renderMaxY * renderer.renderMaxX + (double)aoXYZPNP * (1.0D - renderer.renderMaxY) * renderer.renderMaxX + (double)aoXYZNNP * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxX));

        int brXYZNPP = renderer.getAoBrightness(aoBrightnessXZNP, aoBrightnessXYZNPP, aoBrightnessYZPP, blockBrightness);
        int brXYZPPP = renderer.getAoBrightness(aoBrightnessYZPP, aoBrightnessXZPP, aoBrightnessXYZPPP, blockBrightness);
        int brXYZPNP = renderer.getAoBrightness(aoBrightnessYZNP, aoBrightnessXYZPNP, aoBrightnessXZPP, blockBrightness);
        int brXYZNNP = renderer.getAoBrightness(aoBrightnessXYZNNP, aoBrightnessXZNP, aoBrightnessYZNP, blockBrightness);

        renderer.brightnessTopLeft = renderer.mixAoBrightness(brXYZNPP, brXYZNNP, brXYZPNP, brXYZPPP, renderer.renderMaxY * (1.0D - renderer.renderMinX), (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinX), (1.0D - renderer.renderMaxY) * renderer.renderMinX, renderer.renderMaxY * renderer.renderMinX);
        renderer.brightnessBottomLeft = renderer.mixAoBrightness(brXYZNPP, brXYZNNP, brXYZPNP, brXYZPPP, renderer.renderMinY * (1.0D - renderer.renderMinX), (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinX), (1.0D - renderer.renderMinY) * renderer.renderMinX, renderer.renderMinY * renderer.renderMinX);
        renderer.brightnessBottomRight = renderer.mixAoBrightness(brXYZNPP, brXYZNNP, brXYZPNP, brXYZPPP, renderer.renderMinY * (1.0D - renderer.renderMaxX), (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxX), (1.0D - renderer.renderMinY) * renderer.renderMaxX, renderer.renderMinY * renderer.renderMaxX);
        renderer.brightnessTopRight = renderer.mixAoBrightness(brXYZNPP, brXYZNNP, brXYZPNP, brXYZPPP, renderer.renderMaxY * (1.0D - renderer.renderMaxX), (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxX), (1.0D - renderer.renderMaxY) * renderer.renderMaxX, renderer.renderMaxY * renderer.renderMaxX);

        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.8F;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.8F;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.8F;

        renderer.colorRedTopLeft *= aoTL;
        renderer.colorGreenTopLeft *= aoTL;
        renderer.colorBlueTopLeft *= aoTL;
        renderer.colorRedBottomLeft *= aoBL;
        renderer.colorGreenBottomLeft *= aoBL;
        renderer.colorBlueBottomLeft *= aoBL;
        renderer.colorRedBottomRight *= aoBR;
        renderer.colorGreenBottomRight *= aoBR;
        renderer.colorBlueBottomRight *= aoBR;
        renderer.colorRedTopRight *= aoTR;
        renderer.colorGreenTopRight *= aoTR;
        renderer.colorBlueTopRight *= aoTR;

        renderer.enableAO = true;
        renderer.renderFaceZPos(block, (double) x, (double) y, (double) z, icon);
        renderer.enableAO = false;
    }

    public static void renderFaceXNegAOPartial (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.renderMinX <= 0.0D)
            --x;

        aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZNNN = aoLightValueScratchXZNN;
        aoLightValueScratchXYZNNP = aoLightValueScratchXZNP;
        aoLightValueScratchXYZNPN = aoLightValueScratchXZNN;
        aoLightValueScratchXYZNPP = aoLightValueScratchXZNP;

        aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
        aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
        aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
        aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
        aoBrightnessXYZNNN = aoBrightnessXZNN;
        aoBrightnessXYZNNP = aoBrightnessXZNP;
        aoBrightnessXYZNPN = aoBrightnessXZNN;
        aoBrightnessXYZNPP = aoBrightnessXZNP;

        boolean blocksGrassXYNP = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
        boolean blocksGrassXYNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
        boolean blocksGrassXZNN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
        boolean blocksGrassXZNP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();

        if (blocksGrassXZNN || blocksGrassXYNN) {
            aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
        }

        if (blocksGrassXZNP || blocksGrassXYNN) {
            aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
        }

        if (blocksGrassXZNN || blocksGrassXYNP) {
            aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
        }

        if (blocksGrassXZNP || blocksGrassXYNP) {
            aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
        }

        if (renderer.renderMinX <= 0.0D)
            ++x;

        int blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderMinX <= 0.0D || !renderer.blockAccess.getBlock(x - 1, y, z).isOpaqueCube())
            blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);

        float aoOpposingBlock = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
        float aoXYZNNP = (aoLightValueScratchXYNN + aoLightValueScratchXYZNNP + aoOpposingBlock + aoLightValueScratchXZNP) / 4.0F;
        float aoXYZNPP = (aoOpposingBlock + aoLightValueScratchXZNP + aoLightValueScratchXYNP + aoLightValueScratchXYZNPP) / 4.0F;
        float aoXYZNPN = (aoLightValueScratchXZNN + aoOpposingBlock + aoLightValueScratchXYZNPN + aoLightValueScratchXYNP) / 4.0F;
        float aoXYZNNN = (aoLightValueScratchXYZNNN + aoLightValueScratchXYNN + aoLightValueScratchXZNN + aoOpposingBlock) / 4.0F;

        float aoTL = (float)((double)aoXYZNPP * renderer.renderMaxY * renderer.renderMaxZ + (double)aoXYZNPN * renderer.renderMaxY * (1.0D - renderer.renderMaxZ) + (double)aoXYZNNN * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxZ) + (double)aoXYZNNP * (1.0D - renderer.renderMaxY) * renderer.renderMaxZ);
        float aoBL = (float)((double)aoXYZNPP * renderer.renderMaxY * renderer.renderMinZ + (double)aoXYZNPN * renderer.renderMaxY * (1.0D - renderer.renderMinZ) + (double)aoXYZNNN * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinZ) + (double)aoXYZNNP * (1.0D - renderer.renderMaxY) * renderer.renderMinZ);
        float aoBR = (float)((double)aoXYZNPP * renderer.renderMinY * renderer.renderMinZ + (double)aoXYZNPN * renderer.renderMinY * (1.0D - renderer.renderMinZ) + (double)aoXYZNNN * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinZ) + (double)aoXYZNNP * (1.0D - renderer.renderMinY) * renderer.renderMinZ);
        float aoTR = (float)((double)aoXYZNPP * renderer.renderMinY * renderer.renderMaxZ + (double)aoXYZNPN * renderer.renderMinY * (1.0D - renderer.renderMaxZ) + (double)aoXYZNNN * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxZ) + (double)aoXYZNNP * (1.0D - renderer.renderMinY) * renderer.renderMaxZ);

        int brXYZNNP = renderer.getAoBrightness(aoBrightnessXYNN, aoBrightnessXYZNNP, aoBrightnessXZNP, blockBrightness);
        int brXYZNPP = renderer.getAoBrightness(aoBrightnessXZNP, aoBrightnessXYNP, aoBrightnessXYZNPP, blockBrightness);
        int brXYZNPN = renderer.getAoBrightness(aoBrightnessXZNN, aoBrightnessXYZNPN, aoBrightnessXYNP, blockBrightness);
        int brXYZNNN = renderer.getAoBrightness(aoBrightnessXYZNNN, aoBrightnessXYNN, aoBrightnessXZNN, blockBrightness);

        renderer.brightnessTopLeft = renderer.mixAoBrightness(brXYZNPP, brXYZNPN, brXYZNNN, brXYZNNP, renderer.renderMaxY * renderer.renderMaxZ, renderer.renderMaxY * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMaxY) * renderer.renderMaxZ);
        renderer.brightnessBottomLeft = renderer.mixAoBrightness(brXYZNPP, brXYZNPN, brXYZNNN, brXYZNNP, renderer.renderMaxY * renderer.renderMinZ, renderer.renderMaxY * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMaxY) * renderer.renderMinZ);
        renderer.brightnessBottomRight = renderer.mixAoBrightness(brXYZNPP, brXYZNPN, brXYZNNN, brXYZNNP, renderer.renderMinY * renderer.renderMinZ, renderer.renderMinY * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinZ), (1.0D - renderer.renderMinY) * renderer.renderMinZ);
        renderer.brightnessTopRight = renderer.mixAoBrightness(brXYZNPP, brXYZNPN, brXYZNNN, brXYZNNP, renderer.renderMinY * renderer.renderMaxZ, renderer.renderMinY * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxZ), (1.0D - renderer.renderMinY) * renderer.renderMaxZ);

        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;

        renderer.colorRedTopLeft *= aoTL;
        renderer.colorGreenTopLeft *= aoTL;
        renderer.colorBlueTopLeft *= aoTL;
        renderer.colorRedBottomLeft *= aoBL;
        renderer.colorGreenBottomLeft *= aoBL;
        renderer.colorBlueBottomLeft *= aoBL;
        renderer.colorRedBottomRight *= aoBR;
        renderer.colorGreenBottomRight *= aoBR;
        renderer.colorBlueBottomRight *= aoBR;
        renderer.colorRedTopRight *= aoTR;
        renderer.colorGreenTopRight *= aoTR;
        renderer.colorBlueTopRight *= aoTR;

        renderer.enableAO = true;
        renderer.renderFaceXNeg(block, (double) x, (double) y, (double) z, icon);
        renderer.enableAO = false;
    }

    public static void renderFaceXPosAOPartial (RenderBlocks renderer, Block block, int x, int y, int z, IIcon icon, float r, float g, float b) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.renderMinX >= 1.0D)
            ++x;

        aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZPNN = aoLightValueScratchXZPN;
        aoLightValueScratchXYZPNP = aoLightValueScratchXZPP;
        aoLightValueScratchXYZPPN = aoLightValueScratchXZPN;
        aoLightValueScratchXYZPPP = aoLightValueScratchXZPP;

        aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
        aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
        aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
        aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
        aoBrightnessXYZPNN = aoBrightnessXZPN;
        aoBrightnessXYZPNP = aoBrightnessXZPP;
        aoBrightnessXYZPPN = aoBrightnessXZPN;
        aoBrightnessXYZPPP = aoBrightnessXZPP;

        boolean blocksGrassXYPP = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
        boolean blocksGrassXYPN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
        boolean blocksGrassXZPP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
        boolean blocksGrassXZPN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();

        if (blocksGrassXYPN || blocksGrassXZPN) {
            aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
        }

        if (blocksGrassXYPN || blocksGrassXZPP) {
            aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
        }

        if (blocksGrassXYPP || blocksGrassXZPN) {
            aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
        }

        if (blocksGrassXYPP || blocksGrassXZPP) {
            aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
            aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
        }

        if (renderer.renderMinX >= 1.0D)
            --x;

        int blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderMinX >= 1.0D || !renderer.blockAccess.getBlock(x + 1, y, z).isOpaqueCube())
            blockBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);

        float aoOpposingBlock = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
        float aoXYZPNP = (aoLightValueScratchXYPN + aoLightValueScratchXYZPNP + aoOpposingBlock + aoLightValueScratchXZPP) / 4.0F;
        float aoXYZPNN = (aoLightValueScratchXYZPNN + aoLightValueScratchXYPN + aoLightValueScratchXZPN + aoOpposingBlock) / 4.0F;
        float aoXYZPPN = (aoLightValueScratchXZPN + aoOpposingBlock + aoLightValueScratchXYZPPN + aoLightValueScratchXYPP) / 4.0F;
        float aoXYZPPP = (aoOpposingBlock + aoLightValueScratchXZPP + aoLightValueScratchXYPP + aoLightValueScratchXYZPPP) / 4.0F;

        float aoTL = (float)((double)aoXYZPNP * (1.0D - renderer.renderMinY) * renderer.renderMaxZ + (double)aoXYZPNN * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxZ) + (double)aoXYZPPN * renderer.renderMinY * (1.0D - renderer.renderMaxZ) + (double)aoXYZPPP * renderer.renderMinY * renderer.renderMaxZ);
        float aoBL = (float)((double)aoXYZPNP * (1.0D - renderer.renderMinY) * renderer.renderMinZ + (double)aoXYZPNN * (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinZ) + (double)aoXYZPPN * renderer.renderMinY * (1.0D - renderer.renderMinZ) + (double)aoXYZPPP * renderer.renderMinY * renderer.renderMinZ);
        float aoBR = (float)((double)aoXYZPNP * (1.0D - renderer.renderMaxY) * renderer.renderMinZ + (double)aoXYZPNN * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinZ) + (double)aoXYZPPN * renderer.renderMaxY * (1.0D - renderer.renderMinZ) + (double)aoXYZPPP * renderer.renderMaxY * renderer.renderMinZ);
        float aoTR = (float)((double)aoXYZPNP * (1.0D - renderer.renderMaxY) * renderer.renderMaxZ + (double)aoXYZPNN * (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxZ) + (double)aoXYZPPN * renderer.renderMaxY * (1.0D - renderer.renderMaxZ) + (double)aoXYZPPP * renderer.renderMaxY * renderer.renderMaxZ);

        int brXYZPNP = renderer.getAoBrightness(aoBrightnessXYPN, aoBrightnessXYZPNP, aoBrightnessXZPP, blockBrightness);
        int brXYZPNN = renderer.getAoBrightness(aoBrightnessXZPP, aoBrightnessXYPP, aoBrightnessXYZPPP, blockBrightness);
        int brXYZPPN = renderer.getAoBrightness(aoBrightnessXZPN, aoBrightnessXYZPPN, aoBrightnessXYPP, blockBrightness);
        int brXYZPPP = renderer.getAoBrightness(aoBrightnessXYZPNN, aoBrightnessXYPN, aoBrightnessXZPN, blockBrightness);

        renderer.brightnessTopLeft = renderer.mixAoBrightness(brXYZPNP, brXYZPPP, brXYZPPN, brXYZPNN, (1.0D - renderer.renderMinY) * renderer.renderMaxZ, (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMaxZ), renderer.renderMinY * (1.0D - renderer.renderMaxZ), renderer.renderMinY * renderer.renderMaxZ);
        renderer.brightnessBottomLeft = renderer.mixAoBrightness(brXYZPNP, brXYZPPP, brXYZPPN, brXYZPNN, (1.0D - renderer.renderMinY) * renderer.renderMinZ, (1.0D - renderer.renderMinY) * (1.0D - renderer.renderMinZ), renderer.renderMinY * (1.0D - renderer.renderMinZ), renderer.renderMinY * renderer.renderMinZ);
        renderer.brightnessBottomRight = renderer.mixAoBrightness(brXYZPNP, brXYZPPP, brXYZPPN, brXYZPNN, (1.0D - renderer.renderMaxY) * renderer.renderMinZ, (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMinZ), renderer.renderMaxY * (1.0D - renderer.renderMinZ), renderer.renderMaxY * renderer.renderMinZ);
        renderer.brightnessTopRight = renderer.mixAoBrightness(brXYZPNP, brXYZPPP, brXYZPPN, brXYZPNN, (1.0D - renderer.renderMaxY) * renderer.renderMaxZ, (1.0D - renderer.renderMaxY) * (1.0D - renderer.renderMaxZ), renderer.renderMaxY * (1.0D - renderer.renderMaxZ), renderer.renderMaxY * renderer.renderMaxZ);

        renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = r * 0.6F;
        renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = g * 0.6F;
        renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = b * 0.6F;

        renderer.colorRedTopLeft *= aoTL;
        renderer.colorGreenTopLeft *= aoTL;
        renderer.colorBlueTopLeft *= aoTL;
        renderer.colorRedBottomLeft *= aoBL;
        renderer.colorGreenBottomLeft *= aoBL;
        renderer.colorBlueBottomLeft *= aoBL;
        renderer.colorRedBottomRight *= aoBR;
        renderer.colorGreenBottomRight *= aoBR;
        renderer.colorBlueBottomRight *= aoBR;
        renderer.colorRedTopRight *= aoTR;
        renderer.colorGreenTopRight *= aoTR;
        renderer.colorBlueTopRight *= aoTR;

        renderer.enableAO = true;
        renderer.renderFaceXPos(block, (double) x, (double) y, (double) z, icon);
        renderer.enableAO = false;
    }
}