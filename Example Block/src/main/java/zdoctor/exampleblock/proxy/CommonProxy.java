package zdoctor.exampleblock.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.exampleblock.init.ZBlocks;
import zdoctor.exampleblock.init.ZItems;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		ZBlocks.preInit();
		MinecraftForge.EVENT_BUS.register(new ZBlocks());
		ZItems.preInit();
		MinecraftForge.EVENT_BUS.register(new ZItems());
	}
	
	public void init(FMLInitializationEvent e) {
		
	}
	
	public void postInit(FMLPostInitializationEvent e) {
	}
	
}