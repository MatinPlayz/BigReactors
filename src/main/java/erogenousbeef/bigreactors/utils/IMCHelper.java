package erogenousbeef.bigreactors.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Helper class for sending IMC messages to other mods.
 * MUST be called on both client & server.
 * MUST be called during init(), not preInit() or postInit()
 * 
 * @author Erogenous Beef
 *
 */
public class IMCHelper {

	protected static void sendInterModMessage(String to, String type, NBTTagCompound message) {
		FMLInterModComms.sendMessage(to, type, message);
	}

	public static class ThermalExpansion {
		public static void addPulverizerRecipe(ItemStack from, ItemStack to, int energy) {
			NBTTagCompound message = new NBTTagCompound();
			message.setInteger("energy", energy);
			message.setTag("input", from.writeToNBT(new NBTTagCompound()));
			message.setTag("primaryOutput", to.writeToNBT(new NBTTagCompound()));
			sendInterModMessage("ThermalExpansion", "PulverizerRecipe", message);
		}
		
		public static void addInductionSmelterRecipe(ItemStack firstInput, ItemStack secondInput, ItemStack output, int energy) {
			NBTTagCompound message = new NBTTagCompound();
			message.setInteger("energy", energy);
			message.setTag("primaryInput", firstInput.writeToNBT(new NBTTagCompound()));
			message.setTag("secondaryInput", secondInput.writeToNBT(new NBTTagCompound()));
			message.setTag("primaryOutput", output.writeToNBT(new NBTTagCompound()));
			sendInterModMessage("ThermalExpansion", "SmelterRecipe", message);
		}
		
	}
	
	/// MineFactory Reloaded
	public static class MFR {
		public static void addOreToMiningLaserFocus(ItemStack stack, int color) {
	        NBTTagCompound laserOreMsg = new NBTTagCompound();
	        stack.writeToNBT(laserOreMsg);
	        laserOreMsg.setInteger("value", color);
	        IMCHelper.sendInterModMessage("MineFactoryReloaded", "registerLaserOre", laserOreMsg);

		}
		
		public static void setMiningLaserFocusPreferredOre(ItemStack stack, int color) {
			
		}
	}
	
	/// Applied Energistics 2
	public static class AE2 {
		public static void addGrinderRecipe(ItemStack input, ItemStack output, int turns) {
			NBTTagCompound msg = new NBTTagCompound();
			NBTTagCompound in = new NBTTagCompound();
			NBTTagCompound out = new NBTTagCompound();

			input.writeToNBT( in );
			output.writeToNBT( out );

			msg.setTag( "in", in );
			msg.setTag( "out", out );
			msg.setInteger( "turns", turns );
			
			sendInterModMessage("appliedenergistics2", "add-grindable", msg);
		}
	}		
}
