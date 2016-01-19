package defeatedcrow.addonforamt.economy.api.order;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * オーダーの納品直前に呼ばれるイベント。 <br>
 * 報酬の増減、納品ボックスのTileEntityやインベントリ操作ができる<br>
 * Result.ARROWでなければ報酬変更の結果は反映しない。<br>
 * <br>
 * TileEntityは納品ボックスのTileEntity(QuestExchanger)で、IMPStorageBox, ISidedInventoryを実装している。
 */
@Event.HasResult
public class OnOrderExchangeEvent extends Event {

	public final World world;
	public final ItemStack require;
	public final int requireNum;
	public final int rewaedMP;
	public final List<ItemStack> rewards;
	public final TileEntity thisTile;
	public final float grade;
	public final String ownerName;

	public OnOrderExchangeEvent(World thisWorld, TileEntity tile, String owner, ItemStack reqItem, int reqNum,
			int reward, List<ItemStack> rewardItems, float orderGrade) {
		this.world = thisWorld;
		this.ownerName = owner;
		this.require = reqItem;
		this.requireNum = reqNum;
		this.rewaedMP = reward;
		this.rewards = rewardItems;
		this.thisTile = tile;
		this.grade = orderGrade;
	}

}
