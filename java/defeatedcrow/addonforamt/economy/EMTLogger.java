package defeatedcrow.addonforamt.economy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EMTLogger {

	public static Logger logger = LogManager.getLogger("EconomicalMilkTea");

	public static void loadingModInfo(String modid) {
		EMTLogger.logger.trace("Now checking other mod :" + modid);
	}

	public static void loadedModInfo(String modid) {
		EMTLogger.logger.trace("Succeeded to check other mod :" + modid);
	}

	public static void failLoadingModInfo(String modid) {
		EMTLogger.logger.error("Failed to check other mod :" + modid);
	}

	public static void trace(String msg) {
		EMTLogger.logger.trace(msg);
	}

	public static void info(String msg) {
		EMTLogger.logger.info(msg);
	}

	public static void warn(String msg) {
		EMTLogger.logger.warn(msg);
	}

	public static void debugInfo(String msg) {
		if (EcoMTCore.debug) {
			EMTLogger.logger.info("Debug: " + msg);
		}
	}

}
