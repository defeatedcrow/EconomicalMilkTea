/**
 * Copyright (c) defeatedcrow, 2015
 * URL:http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17657
 * EconomicalMilkTea is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License(MMPL_1.0).txt included in the package file of this Mod.
 * 
 * @author defeatedcrow
 */
package defeatedcrow.addonforamt.economy;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import cpw.mods.fml.common.Loader;
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
import defeatedcrow.addonforamt.economy.common.quest.CustomTooltipEvent;
import defeatedcrow.addonforamt.economy.common.quest.OrderPool;
import defeatedcrow.addonforamt.economy.common.quest.OrderRegister;
import defeatedcrow.addonforamt.economy.common.recipe.FuelFluidRegister;
import defeatedcrow.addonforamt.economy.common.recipe.RegisterBasicRecipe;
import defeatedcrow.addonforamt.economy.common.recipe.RegisterMachineRecipe;
import defeatedcrow.addonforamt.economy.packet.EMTPacketHandler;
import defeatedcrow.addonforamt.economy.plugin.IntegrationLoader;
import defeatedcrow.addonforamt.economy.plugin.amt.AMTIntegration;
import defeatedcrow.addonforamt.economy.plugin.mce.MCEPlugin;
import defeatedcrow.addonforamt.economy.util.ChunkLoaderController;

@Mod(

		modid = "DCsEcoMT",
		name = "EconomicalMilkTea",
		version = "1.7.10_1.1d",
		dependencies = "required-after:Forge@[10.13.4.1448,);required-after:mceconomy2@[2.5,);after:DCsAppleMilk@[1.7.10_2.9a,)")
public class EcoMTCore {

	@SidedProxy(
			clientSide = "defeatedcrow.addonforamt.economy.client.ClientProxyEMT",
			serverSide = "defeatedcrow.addonforamt.economy.common.CommonProxyEMT")
	public static CommonProxyEMT proxy;

	@Instance("DCsEcoMT")
	public static EcoMTCore instance;

	public static final String PACKAGE = "economical";

	public static final CreativeTabs economy = new CreativeTabEMT("economy");
	public static final CreativeTabs economyBuild = new CreativeTabEMTBuild("economy:build");

	// block
	public static Block enTank; // 出力可能なタンク
	public static Block distributor; // 配電盤
	public static Block loader; // チャンクローダー

	public static Block generator; // 発電機
	public static Block motor; // 変換モーター

	public static Block questBlock; // 納品
	public static Block questKanban; // 納品クエストの確認
	public static Item dummyItem; // クエスト要求品がnullの場合用

	public static Block exchanger; // 両替
	public static Block safetyBox; // 金庫
	public static Block safetyChest; // Item金庫

	public static Block emtShop; // ショップ系備品はここ
	public static Block energyShop; // CE系機械類
	public static Block coldShop; // MPでおやつをかう

	public static Block engeneerShop; // 工業モドショップ
	public static Block cropShop; // 野菜
	public static Block mealShop; // その他食べ物
	public static Block buildShop; // BuildTicket専門店

	public static Block sfattDoor; // staff専用ドア

	// item
	public static Item yukiti; // 一万円札
	public static Item ticket; // 月額チケット
	public static Item checker; // 受信機との接続用

	public static Item stamp; // ポイントをためよう

	public static Item giftCatalog; // ポイントでもらえる

	public static Item staffCard; // ショップ操作用のレンチ的アイテム

	// building
	public static Item buildCard_b;
	public static Item buildCard_p;
	public static Item buildCard_r;
	public static Item buildCard_s;
	public static Item buildCard_c;
	public static Item villageCard;

	public static Item fuelCan; // 燃料缶も買える

	// おやつ
	public static Item EMT; // EMT

	public static int dummyRB;
	public static int boardRB;

	public static boolean debug = false;

	public static int guiGen = 0;
	public static int guiTank = 1;
	public static int guiMotor = 2;
	public static int guiDist = 3;
	public static int guiBoard = 4;
	public static int guiSafety = 5;
	public static int guiOrder = 6;
	public static int guiShop = 7;
	public static int guiCatalog = 8;
	public static int guiSChest = 9;

	// config
	public static boolean arrowClearBedrock = false;
	public static boolean useAltTex = false;
	private final String BR = System.getProperty("line.separator");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RecipeManagerEMT.fuelRegister = new FuelFluidRegister();
		RecipeManagerEMT.orderRegister = new OrderPool();

		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try {
			cfg.load();

			Property bedrock = cfg.get("Setting", "ArrowClearBedrock", arrowClearBedrock,
					"Arrow clear bedrocks by build ticket.");
			arrowClearBedrock = bedrock.getBoolean();

			Property alttex = cfg.get("Setting", "UseAnotherTexture", useAltTex,
					"Use Another texture for Meal Shop and Transaction Box.");
			useAltTex = alttex.getBoolean();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

		MaterialEMT.init();

		ChunkLoaderController.getInstance().preInit(event);

		if (Loader.isModLoaded("DCsAppleMilk")) {
			debug = AMTIntegration.getDebug();
		}

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		dummyRB = proxy.getRenderID();
		boardRB = proxy.getRenderID();

		proxy.registerTileEntity();
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.loadInit();

		EMTPacketHandler.init();

		MinecraftForge.EVENT_BUS.register(new CustomTooltipEvent());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RegisterMachineRecipe.addRecipe();
		RegisterBasicRecipe.addRecipe();
		OrderRegister.addBasicOrder();
		MCEPlugin.load();
		IntegrationLoader.load();
	}

	public int getMajorVersion() {
		return 1;
	}

	public int getMinorVersion() {
		return 1;
	}

	public String getRivision() {
		return "d";
	}

	public String getModName() {
		return "EconomicalMilkTea";
	}

	public String getModID() {
		return "DCsEcoMT";
	}
}
