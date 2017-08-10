package zdoctor.exampleblock.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zdoctor.exampleblock.blocks.ExampleBlock;

public class ZBlocks {
	public static Block example;
	
	public static void preInit() {
		example = new ExampleBlock();
	}
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> e) {
		e.getRegistry().register(example);
	}
	
}
