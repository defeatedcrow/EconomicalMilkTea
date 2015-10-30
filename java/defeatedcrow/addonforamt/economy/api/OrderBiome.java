package defeatedcrow.addonforamt.economy.api;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public enum OrderBiome {

	PLANE, COLD, ARID, DAMP, HELL;

	public static OrderBiome getType(BiomeGenBase biome) {
		OrderBiome ret = OrderBiome.PLANE;

		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD)) {
			ret = OrderBiome.COLD;
		} else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DRY)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DEAD)) {
			ret = OrderBiome.ARID;
		} else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.WET)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SWAMP)) {
			ret = OrderBiome.DAMP;
		} else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.END)) {
			ret = OrderBiome.HELL;
		}

		return ret;

	}

}
