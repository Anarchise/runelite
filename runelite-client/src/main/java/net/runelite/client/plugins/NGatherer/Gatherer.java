package net.runelite.client.plugins.NGatherer;

import com.google.inject.Provides;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemDespawned;
import net.runelite.api.events.ItemSpawned;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.Utils.Core;
import net.runelite.client.plugins.Utils.Walking;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@PluginDescriptor(
	name = "Gatherer (Auto)",
	description = "Gathers from various nodes and banks or drops.",
	tags = {"anarchise","skiller","thieving","woodcut","mining","hunter"},
	enabledByDefault = false
)

public class Gatherer extends Plugin
{
	@Inject
	private Client client;
	@Provides
	NGathererConfig getConfig(final ConfigManager configManager)
	{
		return configManager.getConfig(NGathererConfig.class);
	}
	@Inject
	private NGathererConfig config;
	@Inject
	private ClientThread clientThread;
	@Inject
	private ItemManager itemManager;
	@Inject
	private Core core;
	@Inject
	private Walking walking;
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ConfigManager configManager;

	private Random r = new Random();
	private int timeout;
	Instant botTimer;
	WorldPoint ResetLocation = new WorldPoint(0, 0, 0);
	private final Set<Integer> itemIds = new HashSet<>();
	private GameObject bs2;
	public boolean started = false;
	@Override
	protected void startUp() throws Exception
	{
		reset();
	}
	private final Set<WidgetItem> DIAMOND_SET = Set.of();
	private void reset() throws IOException, ClassNotFoundException {
		values = config.loot().toLowerCase().split("\\s*,\\s*");
		if (!config.loot().isBlank()) {
			lootableItems.clear();
			lootableItems.addAll(Arrays.asList(values));
		}
		//FISHIES.clear();
		//FISHIES.add(ItemID.BLUEGILL, ItemID.COMMON_TENCH);
		//FISHIES.add(ItemID.MOTTLED_EEL, ItemID.GREATER_SIREN);
		banked = true;
		botTimer = null;
	}

	/*@Subscribe
	private void onMenuEntryAdded(MenuEntryAdded event)
	{
		if (event.getType() == MenuAction.CC_OP.getId() && (event.getActionParam1() == WidgetInfo.WORLD_SWITCHER_LIST.getId() ||
				event.getActionParam1() == 11927560 || event.getActionParam1() == 4522007 || event.getActionParam1() == 24772686))
		{
			return;
		}
	}*/


	public WidgetItem getFood() {
		WidgetItem item;
		item = core.getInventoryWidgetItem(Collections.singletonList(config.foodID()));
		if (item != null)
		{
			return item;
		}
		return item;
	}
	@Override
	protected void shutDown() throws Exception
	{
		reset();
	}

	Player player;
	WorldPoint walkzone = new WorldPoint(0, 0, 0);
	Instant veilTimer;
	NPC beast;
	long timeRan;
	int timeRun;
	int resetTime = 61;
	int timeRuns;
	boolean isVeiled = false;
	NPC bs;
	List<TileItem> loot = new ArrayList<>();
	String[] values;
	String[] names;
	LocalPoint currentLoc;
	private boolean startedDropping = false;
	int timeout2 = 0;
	private void getStates() {

		if (veilTimer != null) {
			Duration duration = Duration.between(veilTimer, Instant.now());
			timeRan = duration.getSeconds();
			timeRun = (int) timeRan;
			timeRuns = (resetTime) - timeRun;
			if (timeRun > resetTime) {
				isVeiled = false;
				timeRan = 0;
				timeRun = 0;
				timeRuns = 0;
			}
		}
		if (client.getLocalPlayer().getAnimation() > 1 && !config.thieving()) {
			return;
		}
		if (client.getLocalPlayer().getInteracting() != null){
			return;
		}
		if (!startedDropping && core.inventoryFull() && !config.bank()) {
			startedDropping = true;
		}
		if (!core.inventoryContainsExcept(stringToIntList(config.item1())) && startedDropping) {
			startedDropping = false;
		}
		if (!config.bank() && startedDropping) {
			core.dropInventory(true, 100, 300);
			return;
		}
		if (core.isBankOpen()) {
			if (!banked) {
				core.depositAll();
				banked = true;
				return;
			}
			/*if (config.shadowVeil() && !core.inventoryContains(564)) {
				core.withdrawAllItem(564);
				return;
			}*/
			if (config.dodgynecks() && !core.inventoryContains(21143)) {
				core.withdrawItemAmount(21143, config.dodgyNecks());
				return;
			}
			if (!core.inventoryContains(config.foodID()) && config.foodID() != 0) {
				core.withdrawItemAmount(config.foodID(), config.foodAmount());
				return;
			}
			if (banked) {
				walking.walkTileOnScreen(ResetLocation);
				return;
			}
		}


		if (!core.isBankOpen()) {
			if (!loot.isEmpty()) {
				lootItem(spawnedItems, spawnedItemIDs);
				return;
			}
			if (config.bank() && core.inventoryFull() && !config.aerial()) {
				banked = false;
				GameObject bank = core.findNearestBankNoDepositBoxes();
				useGameObject(bank);
				return;
			}
			if (!config.bank() && core.inventoryFull() && !config.aerial()) {
				core.dropInventory(true, 0, 0);
				//core.dropItem(core.getWidgetItem(1511));
				return;
			}
			/*if (config.shadowVeil() && !isVeiled && client.getVarbitValue(12414) == 0 && client.getVarbitValue(12291) == 0 && client.getGameState() == GameState.LOGGED_IN) {
				veilTimer = Instant.now();
				//TODO
				//clientThread.invoke(() -> client.invokeMenuAction("", "", 1, MenuAction.CC_OP.getId(), -1, 14287025));
				isVeiled = true;
				return;//^veil
			}*/
			if (config.bank() && config.dodgynecks() && !core.inventoryContains(21143)) {
				banked = false;
				GameObject bank = core.findNearestBankNoDepositBoxes();
				useGameObject(bank);
				return;//&bank
			}
			/*if (config.bank() && !core.inventoryContains(config.foodID())) {
				banked = false;
				GameObject bank = core.findNearestBankNoDepositBoxes();
				core.useGameObjectDirect(bank);
				return;//bank
			}*/ //TODO REMOVED TO PREVENT BANK LOOP
			if (config.bank() && core.inventoryFull()) {
				banked = false;
				GameObject bank = core.findNearestBankNoDepositBoxes();
				useGameObject(bank);
				return;
			}

			if (config.dodgynecks() && core.inventoryContains(ItemID.DODGY_NECKLACE) && !core.isItemEquipped(Collections.singleton(ItemID.DODGY_NECKLACE))) {
				//core.useItem(ItemID.DODGY_NECKLACE, MenuAction.ITEM_FIRST_OPTION);
				return;
			}
			if (client.getBoostedSkillLevel(Skill.HITPOINTS) <= config.minHealth() && core.inventoryContains(config.foodID())) {
				WidgetItem food = getFood();
				if (food != null) {
					//core.useItem(food.getId(), MenuAction.ITEM_FIRST_OPTION);
				}
				return;
			}
			if (core.inventoryFull() && config.aerial()) {
				startCutting = true;
			}
			if (config.aerial() && startCutting && !core.inventoryContains(FISH)) {
				startCutting = false;
			}
			if (config.aerial() && startCutting && core.inventoryContains(FISH)) {
				//core.useItem(ItemID.KNIFE, MenuAction.ITEM_USE);
				//core.useItem(getFish().getId(), MenuAction.ITEM_USE_ON_ITEM);
				return;
			}
			if (!core.inventoryFull() && bs != null && config.typethief()) {
				if (core.getRandomIntBetweenRange(0, 10) >= 8) {
					timeout2 = (int) core.randomDelay(false, 0, 5, 4, 2);
					return;
				}
 				if (config.typethief()) {//npc
					client.menuAction(core.getRandomIntBetweenRange(0, 100), core.getRandomIntBetweenRange(0, 100), config.menuAction(), bs.getId(), 0, "", "");
					//clientThread.invoke(() -> client.invokeMenuAction("", "", bs.getIndex(), config.type().action.getId(), 0, 0));
				}
				if (!config.thieving()) {
					return;
				}
			}
			if (!core.inventoryFull() && bs2 != null && !config.typethief()) {
				if (core.getRandomIntBetweenRange(0, 10) >= 8) {
					timeout2 = (int) core.randomDelay(false, 0, 5, 4, 2);
					return;
				}
				if (!config.typethief()) {//object
					useGameObject(bs2);
					//clientThread.invoke(() -> client.invokeMenuAction("", "", bs2.getId(), config.type().action.getId(), bs2.getSceneMinLocation().getX(), bs2.getSceneMinLocation().getY()));
				}
				return;
			}
		}
	}
	private final Set<Integer> FISH = Set.of(ItemID.BLUEGILL, ItemID.COMMON_TENCH, ItemID.MOTTLED_EEL, ItemID.GREATER_SIREN);
	private boolean banked = true;
	private boolean startCutting = false;


	public void useGameObject(GameObject targetObject)
	{
		if(targetObject!=null) {

			client.menuAction(targetObject.getSceneMinLocation().getX(), targetObject.getSceneMinLocation().getY(), MenuAction.GAME_OBJECT_FIRST_OPTION, targetObject.getId(), 0, "", "");

			//targetObject.getId(), opcode, targetObject.getLocalLocation().getSceneX(), targetObject.getLocalLocation().getSceneY()));
		}
	}
/*	@Subscribe
	private void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (event.getMenuAction() == MenuAction.CC_OP && (event.getWidgetId() == WidgetInfo.WORLD_SWITCHER_LIST.getId() ||
				event.getWidgetId() == 11927560 || event.getWidgetId() == 4522007 || event.getWidgetId() == 24772686))
		{
			//Either logging out or world-hopping which is handled by 3rd party plugins so let them have priority
			core.targetMenu = null;
			return;
		}
		if (event.getMenuOption().contains("Walk") || event.getMenuAction() == MenuAction.WALK) {
			event.consume();
		}
		if (core.targetMenu != null && event.getParam1() != WidgetInfo.INVENTORY.getId() && event.getParam1() != WidgetInfo.FIXED_VIEWPORT_PRAYER_TAB.getId() && event.getParam1() != WidgetInfo.RESIZABLE_VIEWPORT_PRAYER_TAB.getId()){
			if (event.getId() != core.targetMenu.getIdentifier() ||
					event.getParam0() != core.targetMenu.getParam0() ||
					event.getParam1() != core.targetMenu.getParam1()) {
				event.consume();
			}
			core.targetMenu = null;
		}
	}
 */
	public List<Integer> stringToIntList(String string)
	{
		return (string == null || string.trim().equals("")) ? List.of(0) :
				Arrays.stream(string.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
	}

	public WidgetItem getFish() {
		return core.getInventoryWidgetItem(stringToIntList("22826,22829,22832,22835"));
	}

	@Subscribe
	private void onGameTick(final GameTick event) throws IOException, ClassNotFoundException {
		if (timeout2 > 0) {
			timeout2--;
			return;
		}
		currentLoc = client.getLocalPlayer().getLocalLocation();
		bs = core.findNearestNpc(config.npcID());
		bs2 = core.findNearestGameObject(config.objID());
		beast = core.getFirstNPCWithLocalTarget();
		player = client.getLocalPlayer();
		int[] customTemp2 = core.stringToIntArray(config.returnLoc());
		ResetLocation = new WorldPoint(customTemp2[0], customTemp2[1], customTemp2[2]);
		getStates();

	}

	List<String> lootableItems = new ArrayList<>();

	@Subscribe
	private void onGameStateChanged(GameStateChanged gameStateChanged){
		spawnedItems.clear();
		spawnedItemIDs.clear();
	}

	private void lootItem(List<WorldPoint> itemList, List<Integer> itemIDList) {
		if (itemList.get(0) != null) {
			//core.walk(itemList.get(0));
			client.menuAction(itemList.get(0).getX(), itemList.get(0).getY(), MenuAction.GROUND_ITEM_THIRD_OPTION, 0, itemIDList.get(0), "", "");

			//clientThread.invoke(() -> client.invokeMenuAction("", "", lootItem.getId(), MenuAction.GROUND_ITEM_THIRD_OPTION.getId(), lootItem.getTile().getSceneLocation().getX(), lootItem.getTile().getSceneLocation().getY()));
		}
	}
	public final List<WorldPoint> spawnedItems = new ArrayList<>();
	public final List<Integer> spawnedItemIDs = new ArrayList<>();
	@Subscribe
	private void onItemSpawned(ItemSpawned event) {
		TileItem item = event.getItem();
		String itemName = client.getItemDefinition(item.getId()).getName().toLowerCase();
		if (lootableItems.stream().anyMatch(itemName.toLowerCase()::contains)) {
			//Point point = Perspective.localToCanvas(client, new LocalPoint(event.getTile().getWorldLocation().getX(), event.getTile().getWorldLocation().getY()), client.getPlane());
			spawnedItems.add(event.getTile().getWorldLocation());
			spawnedItemIDs.add(event.getItem().getId());
		}
	}
	@Subscribe
	private void onItemDespawned(ItemDespawned event) {
		spawnedItems.remove(event.getTile().getWorldLocation());
		spawnedItemIDs.remove(event.getItem().getId());
	}
	private long sleepDelay()
	{
		long sleepLength = core.randomDelay(false, 200, 300, 100, 250);
		return sleepLength;
	}
	private int tickDelay()
	{
		int tickLength = (int) core.randomDelay(false, 1, 2, 1, 1);
		return tickLength;
	}
}