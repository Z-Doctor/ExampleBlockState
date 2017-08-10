package zdoctor.exampleblock.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zdoctor.exampleblock.blocks.ExampleBlock;

public class ZItems {
	public static Item example;
	
	public static void preInit() {
		example = new ExampleBlock.ExampleItemBlock();
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(example);
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent e) {
		NonNullList<ItemStack> subBlocks = NonNullList.create();
		ZBlocks.example.getSubBlocks(CreativeTabs.SEARCH, subBlocks);
		for (int i = 0; i < subBlocks.size(); i++) {
		ModelLoader.setCustomModelResourceLocation(example, i,
				new ModelResourceLocation(example.getRegistryName().toString(), "inventory"));
		}
	}
}
