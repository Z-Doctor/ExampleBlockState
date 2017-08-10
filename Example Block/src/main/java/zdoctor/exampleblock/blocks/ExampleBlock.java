package zdoctor.exampleblock.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import zdoctor.exampleblock.init.ZBlocks;

public class ExampleBlock extends Block {
	public static final PropertyEnum TYPE = PropertyEnum.create("type", ExampleBlock.EnumType.class);

	public ExampleBlock() {
		super(Material.ROCK);
		setRegistryName("example");
		setUnlocalizedName("example");
		setCreativeTab(CreativeTabs.MISC);
		
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumType.BRICK));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, meta == 0 ? EnumType.BRICK : EnumType.STONE);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		EnumType type = (EnumType) state.getValue(TYPE);
	    return type.getID();
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
		items.add(new ItemStack(this, 1, 1));
	}
	
	public static class ExampleItemBlock extends ItemBlock {
		public static final IItemPropertyGetter META_GETTER = new IItemPropertyGetter() {

			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				return stack.getMetadata();
			}
		};

		public ExampleItemBlock() {
			super(ZBlocks.example);
			NonNullList<ItemStack> subBlocks = NonNullList.create();
			ZBlocks.example.getSubBlocks(CreativeTabs.SEARCH, subBlocks);
			setHasSubtypes(subBlocks.size() > 1);
			setRegistryName(ZBlocks.example.getRegistryName().toString());
			setUnlocalizedName(ZBlocks.example.getRegistryName().toString());
			
			setCreativeTab(CreativeTabs.MISC);
			
			this.addPropertyOverride(new ResourceLocation("meta"), META_GETTER);
		}
		
		@Override
		public int getMetadata(int damage) {
			return damage;
		}
		
		@Override
		public String getUnlocalizedName(ItemStack stack) {
			return "tile.example_" + ((EnumType)this.block.getStateFromMeta(stack.getMetadata()).getValue(TYPE)).getName();
		}
		
	}
	
	public enum EnumType implements IStringSerializable {
	    BRICK(0, "brick"),
	    STONE(1, "stone");

	    private int ID;
	    private String name;
	    
	    private EnumType(int ID, String name) {
	        this.ID = ID;
	        this.name = name;
	    }
	    
	    @Override
	    public String getName() {
	        return name;
	    }
	    
	    @Override
	    public String toString() {
	    	return getName();
	    }

	    public int getID() {
	        return ID;
	    }
	}

}
