using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Substrate.Core;
using Substrate.Nbt;

namespace ModularpotsIDFix
{
    class Program
    {
        static void Main (string[] args)
        {
            if (!File.Exists("level.dat")) {
                Console.WriteLine("Could not find level.dat");
                return;
            }

            NBTFile nf = new NBTFile("level.dat");
            NbtTree tree;

            using (Stream nbtstr = nf.GetDataInputStream()) {
                if (nbtstr == null) {
                    Console.WriteLine("Could not open level.dat");
                    return;
                }

                tree = new NbtTree(nbtstr);
            }

            TagNodeList list = tree.Root["FML"].ToTagCompound()["ItemData"].ToTagList();
            foreach (TagNodeCompound tag in list) {
                TagNodeString modid = tag["K"].ToTagString();
                if (modid.Data.Contains("modularpots:modularpots:")) {
                    modid.Data = modid.Data.Replace("modularpots:modularpots:", "modularpots:");
                    Console.WriteLine("Updating entry " + tag["V"].ToTagInt().Data + ": " + modid.Data);
                }
            }

            using (Stream zipstr = nf.GetDataOutputStream()) {
                if (zipstr == null) {
                    Console.WriteLine("Could not write back to level.dat");
                    return;
                }

                tree.WriteTo(zipstr);
            }

            Console.WriteLine("Update complete");
        }
    }
}
