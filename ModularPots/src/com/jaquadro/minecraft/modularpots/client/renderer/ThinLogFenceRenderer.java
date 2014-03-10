package com.jaquadro.minecraft.modularpots.client.renderer;

import com.jaquadro.minecraft.modularpots.block.ThinLogFence;
import com.jaquadro.minecraft.modularpots.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

/**
 * Created by Justin on 3/8/14.
 */
public class ThinLogFenceRenderer implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock (Block block, int metadata, int modelId, RenderBlocks renderer) {
        if (!(block instanceof ThinLogFence))
            return;

        renderInventoryBlock((ThinLogFence) block, metadata, modelId, renderer);
    }

    public void renderInventoryBlock (ThinLogFence block, int metadata, int modelId, RenderBlocks renderer) {
        block.setBlockBoundsForItemRender();
        renderer.setRenderBoundsFromBlock(block);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
        renderer.renderFaceZNeg(block, 0, 0, .5f, block.getSideIcon());
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
        renderer.renderFaceZPos(block, 0, 0, -.5f, block.getSideIcon());
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
        renderer.renderFaceXNeg(block, .5f, 0, 0, block.getSideIcon());
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
        renderer.renderFaceXPos(block, -.5f, 0, 0, block.getSideIcon());
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        renderer.renderFaceZNeg(block, 0, 0, .5f, block.getSideIcon());
        renderer.setRenderBounds(.5f, 0, 0, 1, 1, 1);
        renderer.renderFaceZNeg(block, -1, 0, .5f, block.getSideIcon());
        renderer.setRenderBounds(0, 0, 0, .5, 1, 1);
        renderer.renderFaceZNeg(block, 1, 0, .5f, block.getSideIcon());

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
        renderer.renderFaceZPos(block, 0, 0, -.5f, block.getSideIcon());
        renderer.setRenderBounds(.5f, 0, 0, 1, 1, 1);
        renderer.renderFaceZPos(block, -1, 0, -.5f, block.getSideIcon());
        renderer.setRenderBounds(0, 0, 0, .5, 1, 1);
        renderer.renderFaceZPos(block, 1, 0, -.5f, block.getSideIcon());
        tessellator.draw();

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @Override
    public boolean renderWorldBlock (IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (!(block instanceof ThinLogFence))
            return false;

        return renderWorldBlock(world, x, y, z, (ThinLogFence) block, modelId, renderer);
    }

    private boolean renderWorldBlock (IBlockAccess world, int x, int y, int z, ThinLogFence block, int modelId, RenderBlocks renderer) {
        float margin = block.getMargin();

        renderer.setRenderBounds(margin, 0, margin, 1 - margin, 1, 1 - margin);
        renderer.renderStandardBlock(block, x, y, z);

        IIcon sideIcon = block.getSideIcon();

        boolean connectedZNeg = block.canConnectFenceTo(world, x, y, z - 1);
        boolean connectedZPos = block.canConnectFenceTo(world, x, y, z + 1);
        boolean connectedXNeg = block.canConnectFenceTo(world, x - 1, y, z);
        boolean connectedXPos = block.canConnectFenceTo(world, x + 1, y, z);

        if (connectedXNeg || connectedXPos) {
            if (connectedXNeg && connectedXPos)
                renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            else if (connectedXNeg)
                renderer.setRenderBounds(0, 0, 0, .5, 1, 1);
            else if (connectedXPos)
                renderer.setRenderBounds(.5f, 0, 0, 1, 1, 1);

            renderer.renderFaceZNeg(block, x, y, z + .5f, sideIcon);
            renderer.renderFaceZPos(block, x, y, z - .5f, sideIcon);
        }

        if (connectedZNeg || connectedZPos) {
            if (connectedZNeg && connectedZPos)
                renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            else if (connectedZNeg)
                renderer.setRenderBounds(0, 0, 0, 1, 1, .5f);
            else if (connectedZPos)
                renderer.setRenderBounds(0, 0, .5f, 1, 1, 1);

            renderer.renderFaceXNeg(block, x + .5f, y, z, sideIcon);
            renderer.renderFaceXPos(block, x - .5f, y, z, sideIcon);
        }

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory (int modelId) {
        return true;
    }

    @Override
    public int getRenderId () {
        return ClientProxy.thinLogFenceRenderID;
    }
}