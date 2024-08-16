package net.runelite.client.plugins.apker;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.InteractingChanged;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.kit.KitType;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.NPCManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.Utils.Core;
import net.runelite.client.plugins.apker.ScriptCommand.ScriptCommand;
import net.runelite.client.plugins.apker.ScriptCommand.ScriptCommandFactory;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.WeaponMap;
import net.runelite.client.util.WeaponStyle;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;


@PluginDescriptor(
		name = "APKer",
		description = "Anarchise' PKing Tools",
		tags = {"combat", "player", "enemy", "pvp", "overlay"},
		enabledByDefault = false
)
public class PkToolsPlugin extends Plugin
{
	private static final Duration WAIT = Duration.ofSeconds(5);

	public Queue<ScriptCommand> commandList = new ConcurrentLinkedQueue<>();

	@Inject
	Core utils;

	@Inject
	public Client client;

	@Inject
	public ClientThread clientThread;

	@Inject
	private PkToolsConfig configpk;

	@Inject
	private ConfigManager configManager;

	@Inject
	private OverlayManager overlayManager;


	@Inject
	private PkToolsHotkeyListener pkToolsHotkeyListener;

	@Inject
	private KeyManager keyManager;

	@Getter(AccessLevel.PACKAGE)



	@Inject
	private ItemManager itemManager;

	@Getter(AccessLevel.PACKAGE)
	public Player lastEnemy;
	private NPCManager npcManager;
	private Instant lastTime;
	private int nextRestoreVal = 0;
	Integer lastMaxHealth;
	Player target;
	public boolean MaultoAgs = false;
	public boolean AgsToMaul = false;
	public boolean SingleMaulAgs = false;
	int timeout = 0;
	private Random r = new Random();
	@Provides
	PkToolsConfig provideConfig(final ConfigManager configManager)
	{
		return configManager.getConfig(PkToolsConfig.class);
	}

	@Override
	protected void startUp() throws IOException {
		keyManager.registerKeyListener(pkToolsHotkeyListener);

	}

	@Override
	protected void shutDown()
	{
		lastTime = null;
		keyManager.unregisterKeyListener(pkToolsHotkeyListener);
	}

	/*
	TESTING ENTRIES
	 */
	@Subscribe
	private void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (event.getMenuAction() == MenuAction.CC_OP && (event.getWidgetId() == WidgetInfo.WORLD_SWITCHER_LIST.getId() ||
				event.getWidgetId() == 11927560 || event.getWidgetId() == 4522007 || event.getWidgetId() == 24772686))
		{
			//Either logging out or world-hopping which is handled by 3rd party plugins so let them have priority
			utils.targetMenu = null;
			return;
		}
		utils.sendGameMessage("O: " + event.getMenuOption() + " T: " + event.getMenuTarget() + " A: " + event.getMenuAction() + " P0: " + event.getParam0() + " P1: " + event.getParam1() + " ID: " + event.getId() + " IID: " + event.getItemId());
		//core.sendGameMessage(event.toString());	//Debugging
	}


	@Subscribe
	public void onInteractingChanged(final InteractingChanged event)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		if (event.getSource() != client.getLocalPlayer())
		{
			return;
		}

		final Actor opponent = event.getTarget();

		if (opponent == null)
		{
			lastTime = Instant.now();
			return;
		}

		Player localPlayer = client.getLocalPlayer();
		final List<Player> players = client.getPlayers();

		for (final Player player : players)
		{
			if (localPlayer != null && player == localPlayer.getInteracting())
			{
				lastEnemy = player;
			}
		}
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		lastEnemyTimer();
		processCommands();
	}
	boolean First = true;
	@Subscribe
	public void onGameTick(GameTick event) throws IOException {
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}
		doAutoSwapPrayers();
		doSwapGear();
	}

	private void processCommands()
	{
		while (commandList.peek() != null)
		{
			commandList.poll().execute(client, configpk, this, configManager);
		}
	}
	int lll = 999990;
	public void lastEnemyTimer()
	{
		Player localPlayer = client.getLocalPlayer();

		if (localPlayer == null)
		{
			return;
		}

		if (lastEnemy == null)
		{
			return;
		}

		if (localPlayer.getInteracting() == null)
		{
			if (Duration.between(lastTime, Instant.now()).compareTo(PkToolsPlugin.WAIT) > 0)
			{
				lastEnemy = null;
			}
		}
	}

	public void activatePrayer(Prayer prayer)
	{
		if (prayer == null)
		{
			return;
		}

		//check if prayer is already active this tick
		if (client.isPrayerActive(prayer))
		{
			return;
		}

		WidgetInfo widgetInfo = prayer.getWidgetInfo();

		if (widgetInfo == null)
		{
			return;
		}

		Widget prayer_widget = client.getWidget(widgetInfo);

		if (prayer_widget == null)
		{
			return;
		}

		if (client.getBoostedSkillLevel(Skill.PRAYER) <= 0)
		{
			return;
		}

		//entryList.add(new MenuEntry("Activate", prayer_widget.getName(), 1, MenuAction.CC_OP.getId(), prayer_widget.getItemId(), prayer_widget.getId(), false));
		//clientThread.invoke(() -> client.invokeMenuAction("Activate", prayer_widget.getName(), 1, MenuAction.CC_OP.getId(), prayer_widget.getItemId(), prayer_widget.getId()));
		client.menuAction(prayer_widget.getItemId(), prayer_widget.getId(),  MenuAction.CC_OP, 1, 0, "Activate", prayer_widget.getName());
		//click();
	}

	private void addCommands(String command)
	{
		for (String c : command.split("\\s*\n\\s*"))
		{
			commandList.add(ScriptCommandFactory.builder(c));
		}
	}

	public void doSwapGear()
	{
		if (!configpk.autoGearSwap())
		{
			return;
		}

		try
		{
			if (lastEnemy == null)
			{
				return;
			}

			/*if (client.getLocalPlayer().getInteracting() != lastEnemy){
				return;
			}*/

			PlayerComposition lastEnemyAppearance = lastEnemy.getPlayerComposition();

			if (lastEnemyAppearance == null)
			{
				return;
			}
			if (!configpk.swapFromPray()){
				WeaponStyle weaponStyle = WeaponMap.StyleMap.getOrDefault(lastEnemyAppearance.getEquipmentId(KitType.WEAPON), null);
				if (weaponStyle == null)
				{
					return;
				}

				switch (weaponStyle)
				{
					case MELEE:
						WeaponStyle localWeaponStyle = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
						if (localWeaponStyle == WeaponStyle.MELEE){
							break;
						}
						addCommands("group1");	//group1 = melee
						addCommands("clickenemy");
						break;
					case RANGE:
						WeaponStyle localWeaponStyle2 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
						if (localWeaponStyle2 == WeaponStyle.MAGIC){
							break;
						}
						addCommands("group3");	//group3 = magic
						addCommands("freeze");
						addCommands("clickenemy");
						break;
					case MAGIC:
						WeaponStyle localWeaponStyle3 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
						if (localWeaponStyle3 == WeaponStyle.RANGE){
							break;
						}
						addCommands("group2");	//group2 = ranged
						addCommands("clickenemy");
						break;
					default:
						break;
				}
			}

			if (configpk.swapFromPray()) {
				HeadIcon currentIcon = lastEnemy.getOverheadIcon();
				if (currentIcon == null) {
					return;
				}
				switch (currentIcon) {
					case MELEE:
						WeaponStyle localWeaponStyle2 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
						if (localWeaponStyle2 == WeaponStyle.MAGIC){
							break;
						}
						addCommands("group3");	//group3 = magic
						addCommands("freeze");
						addCommands("clickenemy");
						break;
					case RANGED:
						WeaponStyle localWeaponStyle1 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
						if (localWeaponStyle1 == WeaponStyle.MAGIC){
							break;
						}
						/*addCommands("group1");	//group1 = melee
						addCommands("clickenemy");*/
						addCommands("group3");	//group3 = magic
						addCommands("freeze");
						addCommands("clickenemy");
						break;
					case MAGIC:
						WeaponStyle localWeaponStyle3 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
						if (localWeaponStyle3 == WeaponStyle.RANGE){
							break;
						}
						addCommands("group2");	//group2 = ranged
						addCommands("clickenemy");
						break;
					default:
						break;
				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void doAutoSwapPrayers()
	{
		if (!configpk.autoPrayerSwitcher())
		{
			return;
		}

		if (!configpk.autoPrayerSwitcher())
		{
			return;
		}

		try
		{
			if (lastEnemy == null)
			{
				return;
			}

			PlayerComposition lastEnemyAppearance = lastEnemy.getPlayerComposition();

			if (lastEnemyAppearance == null)
			{
				return;
			}

			WeaponStyle weaponStyle = WeaponMap.StyleMap.getOrDefault(lastEnemyAppearance.getEquipmentId(KitType.WEAPON), null);

			if (weaponStyle == null)
			{
				return;
			}

			switch (weaponStyle)
			{
				case MELEE:
					if (configpk.enableMeleePrayer()) {
						activatePrayer(Prayer.PROTECT_FROM_MELEE);
					}
					break;
				case RANGE:
					if (configpk.enableRangedPrayer()) {
					activatePrayer(Prayer.PROTECT_FROM_MISSILES);
					}
					break;
				case MAGIC:
					if (configpk.enableMagicPrayer()) {
						activatePrayer(Prayer.PROTECT_FROM_MAGIC);
					}
					break;
				default:
					break;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("apker"))
		{
			return;
		}
	}
}
