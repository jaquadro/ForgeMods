package com.jaquadro.minecraft.extrabuttons.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityButton extends TileEntity
{
    public byte metadata = 0;

    @Override
    public void writeToNBT (NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        tag.setByte("meta", this.metadata);
    }

    @Override
    public void readFromNBT (NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        this.metadata = tag.getByte("meta");
    }

    public int getDirection ()
    {
        return metadata & 0x7;
    }

    public void setDirection (int direction)
    {
        metadata = (byte) ((metadata & ~0x7) | (direction & 0x7));
    }

    public boolean isLatched ()
    {
        return (metadata & 0x8) != 0;
    }

    public void setIsLatched (boolean latched)
    {
        metadata = (byte) ((metadata & ~0x8) | (latched ? 0x8 : 0));
    }

    public boolean isDepressed ()
    {
        return (metadata & 0x10) != 0;
    }

    public void setIsDepressed (boolean depressed)
    {
        metadata = (byte) ((metadata & ~0x10) | (depressed ? 0x10 : 0));
    }

    @Override
    public Packet getDescriptionPacket ()
    {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
    }

    @Override
    public void onDataPacket (NetworkManager netManager, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
        getWorldObj().func_147479_m(xCoord, yCoord, zCoord); // markBlockForRenderUpdate
    }
}
