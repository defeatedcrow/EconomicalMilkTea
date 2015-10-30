/**
 * Copyright (c) defeatedcrow, 2015
 * URL:http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17657
 * EconomicalMilkTea is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License(MMPL_1.0).txt included in the package file of this Mod.
 * 
 * @author defeatedcrow
 */
package defeatedcrow.addonforamt.economy;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.common.CommonProxyEMT;
import defeatedcrow.addonforamt.economy.common.recipe.FuelFluidRegister;
import defeatedcrow.addonforamt.economy.common.recipe.RegisterBasicRecipe;
import defeatedcrow.addonforamt.economy.common.recipe.RegisterMachineRecipe;
import defeatedcrow.addonforamt.economy.plugin.mce.MCEPlugin;
import defeatedcrow.addonforamt.economy.util.ChunkLoaderController;

@Mod(
		modid = "DCsEcoMT",
		name = "EconomicalMilkTea",
		version = "1.7.10_alpha2",
		dependencies = "required-after:Forge@[10.13.0.1448,);required-after:mceconomy2@[2.4.5,);required-after:DCsAppleMilk@[1.7.10_2.8i,)")
public class EcoMTCore {

	@SidedProxy(
			clientSide = "defeatedcrow.addonforamt.economy.client.ClientProxyEMT",
			serverSide = "defeatedcrow.addonforamt.economy.common.CommonProxyEMT")
	public static CommonProxyEMT proxy;

	@Instance("DCsEcoMT")
	public static EcoMTCore instance;

	public static final String PACKAGE = "economical";

	public static final CreativeTabs economy = new CreativeTabEMT("economy");

	// block
	public static Block enTank; // 出力可能なタンク
	public static Block distributor; // 配電盤
	public static Block loader; // チャンクローダー

	public static Block generator; // 発電機
	public static Block motor; // 変換モーター

	public static Block emtShop; // MPで装置をそろえる
	public static Block coldShop; // MPでおやつをかう
	public static Block questBlock; // 納品
	public static Block questKanban; // 納品クエストの確認

	public static Block exchanger; // 両替

	public static Block kariShop;

	// item
	public static Item ticket; // 月額チケット
	public static Item checker; // 受信機との接続用
	public static Item stamp; // ポイントをためよう

	public static Item gift; // ポイントでもらえる

	public static Item fuelCan; // 燃料缶も買える

	public static int dummyRB;

	public static boolean debug = false;

	public static int guiGen = 0;
	public static int guiTank = 1;
	public static int guiMotor = 2;
	public static int guiDist = 3;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MaterialEMT.init();

		ChunkLoaderController.getInstance().preInit(event);

		if (DCsAppleMilk.debugMode)
			debug = true;
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipeManagerEMT.fuelRegister = new FuelFluidRegister();
		dummyRB = proxy.getRenderID();

		proxy.registerTileEntity();
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.loadInit();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RegisterMachineRecipe.addRecipe();
		RegisterBasicRecipe.addRecipe();
		MCEPlugin.load();
	}

	public int getMajorVersion() {
		return 0;
	}

	public int getMinorVersion() {
		return 1;
	}

	public String getRivision() {
		return "b";
	}

	public String getModName() {
		return "EconomicalMilkTea";
	}

	public String getModID() {
		return "DCsEcoMT";
	}

}
