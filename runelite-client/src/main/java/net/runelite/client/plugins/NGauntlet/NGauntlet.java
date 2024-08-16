/*
 * Copyright (c) 2020, dutta64 <https://github.com/dutta64>
 * Copyright (c) 2019, kThisIsCvpv <https://github.com/kThisIsCvpv>
 * Copyright (c) 2019, ganom <https://github.com/Ganom>
 * Copyright (c) 2019, kyle <https://github.com/xKylee>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.client.plugins.NGauntlet;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.Utils.Core;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
//import java.util.*;

@Slf4j
@PluginDescriptor(
		name = "NGauntlet",
		description = "Automatic Gauntlet.",
		tags = {"ztd","numb","gauntlet", "hunllef"},
		enabledByDefault = false
)

public class NGauntlet extends Plugin
{

	private final Set<Integer> HALBERDS = Set.of(ItemID.CRYSTAL_HALBERD_BASIC, ItemID.CRYSTAL_HALBERD_ATTUNED, ItemID.CRYSTAL_HALBERD_PERFECTED, ItemID.CORRUPTED_HALBERD_BASIC, ItemID.CORRUPTED_HALBERD_ATTUNED, ItemID.CORRUPTED_HALBERD_PERFECTED);
	private final Set<Integer> BOWS = Set.of(ItemID.CRYSTAL_BOW_BASIC, ItemID.CRYSTAL_BOW_ATTUNED, ItemID.CRYSTAL_BOW_PERFECTED, ItemID.CORRUPTED_BOW_BASIC, ItemID.CORRUPTED_BOW_ATTUNED, ItemID.CORRUPTED_BOW_PERFECTED);
	private final Set<Integer> STAVES = Set.of(ItemID.CRYSTAL_STAFF_BASIC, ItemID.CRYSTAL_STAFF_ATTUNED, ItemID.CRYSTAL_STAFF_PERFECTED, ItemID.CORRUPTED_STAFF_BASIC, ItemID.CORRUPTED_STAFF_ATTUNED, ItemID.CORRUPTED_STAFF_PERFECTED);


	NGauntletState state = null;
	int timeout=0;


	private static final Set<Integer> HUNLLEF_IDS = Set.of(
		NpcID.CRYSTALLINE_HUNLLEF, NpcID.CRYSTALLINE_HUNLLEF_9022,
		NpcID.CRYSTALLINE_HUNLLEF_9023, NpcID.CRYSTALLINE_HUNLLEF_9024,
		NpcID.CORRUPTED_HUNLLEF, NpcID.CORRUPTED_HUNLLEF_9036,
		NpcID.CORRUPTED_HUNLLEF_9037, NpcID.CORRUPTED_HUNLLEF_9038
	);

	private static final Set<Integer> UTILITY_IDS = Set.of(
		ObjectID.SINGING_BOWL_35966, ObjectID.SINGING_BOWL_36063,
		ObjectID.RANGE_35980, ObjectID.RANGE_36077,
		ObjectID.WATER_PUMP_35981, ObjectID.WATER_PUMP_36078
	);

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private NGauntletConfig config;

	@Inject
	private SkillIconManager skillIconManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private Core utils;

	private NPC hunllef;

	private boolean inHunllef;

	@Provides
	NGauntletConfig getConfig(final ConfigManager configManager)
	{
		return configManager.getConfig(NGauntletConfig.class);
	}

	@Override
	protected void startUp() throws IOException {
		reset();
	}

	@Override
	protected void shutDown() throws IOException {
		reset();
	}
	private boolean started = false;
	String[] values;
	String lootnames = "shards,frame,ore,tirinum,bark,leaf,bowstring,orb,spike,teleport";

	private void reset() throws IOException {
		start = false;
		inHunllef = false;
		loot.clear();
		values = lootnames.toLowerCase().split("\\s*,\\s*");
		if (!lootnames.isBlank()) {
			lootableItems.clear();
			lootableItems.addAll(Arrays.asList(values));
		}
		inGauntlet = false;
		hunllef = null;
	}

	private boolean inGauntlet = false;
	private boolean start = false;


	@Subscribe
	private void onVarbitChanged(final VarbitChanged event) throws IOException {
		if (isHunllefVarbitSet())
		{
			if (!inHunllef)
			{
				initHunllef();
			}
		}
		else if (isGauntletVarbitSet())
		{
			if (!inGauntlet)
			{
				initGauntlet();
			}
		}
		else
		{
			if (inHunllef)
			{
				shutDown();
			}
		}
	}

	Prayer swap = Prayer.PROTECT_FROM_MISSILES;
	int attacks = 0;


	@Subscribe
	private void onClientTick(ClientTick event) {
		if (!inHunllef) {
			hunllef = null;
		}
		if (inHunllef) {
			if (hunllef == null) {
				hunllef = utils.findNearestNpc(NpcID.CRYSTALLINE_HUNLLEF, NpcID.CRYSTALLINE_HUNLLEF_9022,
						NpcID.CRYSTALLINE_HUNLLEF_9023, NpcID.CRYSTALLINE_HUNLLEF_9024,
						NpcID.CORRUPTED_HUNLLEF, NpcID.CORRUPTED_HUNLLEF_9036,
						NpcID.CORRUPTED_HUNLLEF_9037, NpcID.CORRUPTED_HUNLLEF_9038);
			}
			if (config.autoPray()) {
				if (client.getVarbitValue(swap.getVarbit()) == 0) {
					activatePrayer(swap);
				}
			}
			//^^^^overheads
			if (config.Piety() && utils.isItemEquipped(HALBERDS) && !client.isPrayerActive(Prayer.PIETY)) {
				activatePrayer(Prayer.PIETY);
			}
			if (!config.Piety() && utils.isItemEquipped(HALBERDS) && !client.isPrayerActive(Prayer.ULTIMATE_STRENGTH)) {
				activatePrayer(Prayer.ULTIMATE_STRENGTH);
			}
			if (!config.Piety() && utils.isItemEquipped(HALBERDS) && !client.isPrayerActive(Prayer.INCREDIBLE_REFLEXES)) {
				activatePrayer(Prayer.INCREDIBLE_REFLEXES);
			}
			if (config.Rigour() && utils.isItemEquipped(BOWS) && !client.isPrayerActive(Prayer.RIGOUR)) {
				activatePrayer(Prayer.RIGOUR);
			}
			if (!config.Rigour() && utils.isItemEquipped(BOWS) && !client.isPrayerActive(Prayer.EAGLE_EYE)) {
				activatePrayer(Prayer.EAGLE_EYE);
			}
			if (config.Augury() && utils.isItemEquipped(STAVES) && !client.isPrayerActive(Prayer.AUGURY)) {
				activatePrayer(Prayer.AUGURY);
			}
			if (!config.Augury() && utils.isItemEquipped(STAVES) && !client.isPrayerActive(Prayer.MYSTIC_MIGHT)) {
				activatePrayer(Prayer.MYSTIC_MIGHT);
			}
			//^^^^^offensives
			//PrayerSwap();
		}
	}

	private boolean changed = false;

	@Subscribe
	private void onProjectileMoved(ProjectileMoved event) {
		if (event.getProjectile().getId() == 1712) {
			if (swap != Prayer.PROTECT_FROM_MISSILES) {
				swap = Prayer.PROTECT_FROM_MISSILES;
			}
			//utils.sendGameMessage("Ranged Attack: ");
			//return;
		}
		if (event.getProjectile().getId() == 1708) {
			if (swap != Prayer.PROTECT_FROM_MAGIC) {
				swap = Prayer.PROTECT_FROM_MAGIC;
			}
			//utils.sendGameMessage("Mage Attack: ");
			//return;
		}
	}

	@Subscribe
	private void onAnimationChanged(AnimationChanged event) {
		if (event.getActor().equals(hunllef)) {
			if (event.getActor().getAnimation() == 8754) {
				if (swap == Prayer.PROTECT_FROM_MISSILES) {
					swap = Prayer.PROTECT_FROM_MAGIC;
					//utils.sendGameMessage("Swapped from animation");
					return;
				}
				if (swap == Prayer.PROTECT_FROM_MAGIC) {
					swap = Prayer.PROTECT_FROM_MISSILES;
					//utils.sendGameMessage("Swapped from animation 2");
					return;
				}
			}
		}
	}

	@Subscribe
	private void onGameTick(GameTick event) throws IOException {
		if (inHunllef) {
			/*if (attacks >= 11) { // each attack lasts 3 game ticks, if no ticks are missed
				// first attack sometimes only lasts 2 ticks for some reason
				// hunllef always starts off with ranged attack
				utils.sendGameMessage("Attacks Resetting: " + attacks);
				attacks = 0;
				if (swap == Prayer.PROTECT_FROM_MISSILES) {
					swap = Prayer.PROTECT_FROM_MAGIC;
				}
				if (swap == Prayer.PROTECT_FROM_MAGIC) {
					swap = Prayer.PROTECT_FROM_MISSILES;
				}
			}*/

			/*for (Projectile projectile : client.getProjectiles()) {
				if (projectile.getId() == ProjectileID.HUNLLEF_CORRUPTED_RANGE_ATTACK) {
					if (swap != Prayer.PROTECT_FROM_MISSILES) {
						swap = Prayer.PROTECT_FROM_MISSILES;
					}
					attacks++;
					//utils.sendGameMessage("Ranged Attack: " + attacks);
					//return;
				}
				if (projectile.getId() == ProjectileID.HUNLLEF_CORRUPTED_MAGE_ATTACK) {
					if (swap != Prayer.PROTECT_FROM_MAGIC) {
						swap = Prayer.PROTECT_FROM_MAGIC;
					}
					attacks++;
					//utils.sendGameMessage("Mage Attack: " + attacks);
					//return;
				}
			}*/
		}
		if (!inHunllef && client != null && client.getLocalPlayer() != null) {
			state = getState();
			switch (state) {
				case TIMEOUT:
					//utils.handleRun(20, 10);
					timeout--;
					break;
				case IDLE:
					//utils.handleRun(20, 10);
					//timeout = 1;
					break;
				case IDLE_2:
					timeout = 3;
					break;
			}
		}

		/*if (inHunllef && client != null && client.getLocalPlayer() != null) {
			state = getStateBoss();
			switch (state) {
				case TIMEOUT:
					//utils.handleRun(30, 20);
					timeout--;
					break;
				case IDLE:
					timeout = 1;
					break;
				case IDLE_2:
					timeout = 3;
					break;
			}
		}*/

	}

	private NGauntletState getState() {

		if (client.getBoostedSkillLevel(Skill.HITPOINTS) <= config.healthMin()) {
			WidgetItem AllWeapons = utils.getItemFromInventory(ItemID.PADDLEFISH, ItemID.CORRUPTED_PADDLEFISH, ItemID.CRYSTAL_PADDLEFISH);
			if (AllWeapons != null) {
				utils.useItem(AllWeapons, 2, MenuAction.CC_OP);
				//clientThread.invoke(() -> client.invokeMenuAction("", "", AllWeapons.getId(), MenuAction.ITEM_FIRST_OPTION.getId(), AllWeapons.getIndex(), WidgetInfo.INVENTORY.getId()));
			}
		}
		if (client.getBoostedSkillLevel(Skill.PRAYER) <= config.prayMin()) {
			WidgetItem AllWeapons = utils.getItemFromInventory(ItemID.EGNIOL_POTION_1, ItemID.EGNIOL_POTION_2, ItemID.EGNIOL_POTION_3, ItemID.EGNIOL_POTION_4);
			if (AllWeapons != null) {
				utils.useItem(AllWeapons, 2, MenuAction.CC_OP);
				//clientThread.invoke(() -> client.invokeMenuAction("", "", AllWeapons.getId(), MenuAction.ITEM_FIRST_OPTION.getId(), AllWeapons.getIndex(), WidgetInfo.INVENTORY.getId()));
			}
		}
		if (client.getEnergy() <= config.runMin()) {
			WidgetItem AllWeapons = utils.getItemFromInventory(ItemID.EGNIOL_POTION_1, ItemID.EGNIOL_POTION_2, ItemID.EGNIOL_POTION_3, ItemID.EGNIOL_POTION_4);
			if (AllWeapons != null) {
				utils.useItem(AllWeapons, 2, MenuAction.CC_OP);
				//clientThread.invoke(() -> client.invokeMenuAction("", "", AllWeapons.getId(), MenuAction.ITEM_FIRST_OPTION.getId(), AllWeapons.getIndex(), WidgetInfo.INVENTORY.getId()));
			}
		}

		if (client.getLocalPlayer().getAnimation() != -1) {
			return NGauntletState.ANIMATING;
		}

		return NGauntletState.UNHANDLED_STATE;
	}
	List<String> lootableItems = new ArrayList<>();
	List<TileItem> loot = new ArrayList<>();


	@Subscribe
	private void onGameStateChanged(final GameStateChanged event) throws IOException {
		switch (event.getGameState())
		{
			case LOADING:
				//utilities.clear();
				break;
			case LOGIN_SCREEN:
			case HOPPING:
				shutDown();
				break;
		}
	}

	@Subscribe
	private void onNpcSpawned(final NpcSpawned event)
	{
		final NPC npc = event.getNpc();

		final int id = npc.getId();

		if (HUNLLEF_IDS.contains(id))
		{
			hunllef = event.getNpc();
		}
	}

	@Subscribe
	private void onNpcDespawned(final NpcDespawned event)
	{
		final NPC npc = event.getNpc();

		final int id = npc.getId();

		if (HUNLLEF_IDS.contains(id))
		{
			hunllef = null;
		}
	}



	public void activatePrayer(Prayer prayer) {
		if (prayer == null) {
			return;
		}

		if (client.isPrayerActive(prayer)) {
			return;
		}

		WidgetInfo widgetInfo = prayer.getWidgetInfo();

		if (widgetInfo == null) {
			return;
		}

		Widget prayer_widget = client.getWidget(widgetInfo);

		if (prayer_widget == null) {
			return;
		}

		if (client.getBoostedSkillLevel(Skill.PRAYER) <= 0) {
			return;
		}

		client.menuAction(prayer_widget.getItemId(), prayer_widget.getId(), MenuAction.CC_OP,1, 0, "", "");
	}

	public void deactivatePrayer(Prayer prayer)
	{
		if (prayer == null)
		{
			return;
		}

		if (!client.isPrayerActive(prayer))
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
		client.menuAction(prayer_widget.getItemId(), prayer_widget.getId(), MenuAction.CC_OP,1, 0, "", "");
		//clientThread.invoke(() -> client.invokeMenuAction("", prayer_widget.getName(), 1, MenuAction.CC_OP.getId(), prayer_widget.getItemId(), prayer_widget.getId()));
	}


	private void initHunllef()
	{
		inHunllef = true;
	}
	private void initGauntlet() { inGauntlet = true; }

	private boolean isGauntletVarbitSet()
	{
		return client.getVarbitValue(9178) == 1;
	}

	private boolean isHunllefVarbitSet()
	{
		return client.getVarbitValue(9177) == 1;
	}

}
