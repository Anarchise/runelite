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

package net.runelite.client.plugins.apvm;

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
//import java.util.*;

@Slf4j
@PluginDescriptor(
		name = "APvm",
		description = "Pvm assistance.",
		tags = {"ztd","anarchise","gauntlet", "vardorvis"},
		enabledByDefault = false
)

public class APvm extends Plugin
{

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private APvmConfig config;

	@Inject
	private SkillIconManager skillIconManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private Core utils;

	private Prayer currentPrayer;

	@Provides
	APvmConfig getConfig(final ConfigManager configManager)
	{
		return configManager.getConfig(APvmConfig.class);
	}

	@Override
	protected void startUp() throws IOException {
		reset();
	}

	@Override
	protected void shutDown() throws IOException {
		reset();
	}


	private void reset() throws IOException {
	}


	@Subscribe
	private void onClientTick(ClientTick event) {

	}

	@Subscribe
	private void onGameTick(GameTick event) throws IOException {
		if (config.Vardorvis()) {
			if (utils.findNearestNpc("Vardorvis") != null) {
				for (Projectile projectiles : client.getProjectiles())
				{
					if (projectiles.getId() == 2521) { // Ranged
						if (!client.isPrayerActive(Prayer.PROTECT_FROM_MISSILES)) {
							activatePrayer(Prayer.PROTECT_FROM_MISSILES);
						}
						return;
					}
				}
				if (!client.isPrayerActive(Prayer.PROTECT_FROM_MELEE)) {
					activatePrayer(Prayer.PROTECT_FROM_MELEE);
				}
			}
		}
		if (config.eat()) {
			if (client.getBoostedSkillLevel(Skill.HITPOINTS) <= config.hp()) {
				if (utils.inventoryContains(config.eatID())) {
					utils.useItem(utils.getItemFromInventory(config.eatID()),2, MenuAction.CC_OP);
				}
			}
		}
		if (config.pray()) {
			if (client.getBoostedSkillLevel(Skill.PRAYER) <= config.prayer()) {
				if (utils.inventoryContains(ItemID.SUPER_RESTORE1, ItemID.SUPER_RESTORE2, ItemID.SUPER_RESTORE3, ItemID.SUPER_RESTORE4, ItemID.SUPER_RESTORE1_23573, ItemID.SUPER_RESTORE2_23571, ItemID.SUPER_RESTORE3_23569, ItemID.SUPER_RESTORE4_23567, ItemID.PRAYER_POTION1, ItemID.PRAYER_POTION2, ItemID.PRAYER_POTION3, ItemID.PRAYER_POTION4, ItemID.PRAYER_POTION1_20396, ItemID.PRAYER_POTION2_20395, ItemID.PRAYER_POTION3_20394, ItemID.PRAYER_POTION4_20393)) {
					WidgetItem PrayerPot = utils.getItemFromInventory(ItemID.SUPER_RESTORE1, ItemID.SUPER_RESTORE2, ItemID.SUPER_RESTORE3, ItemID.SUPER_RESTORE4, ItemID.SUPER_RESTORE1_23573, ItemID.SUPER_RESTORE2_23571, ItemID.SUPER_RESTORE3_23569, ItemID.SUPER_RESTORE4_23567, ItemID.PRAYER_POTION1, ItemID.PRAYER_POTION2, ItemID.PRAYER_POTION3, ItemID.PRAYER_POTION4, ItemID.PRAYER_POTION1_20396, ItemID.PRAYER_POTION2_20395, ItemID.PRAYER_POTION3_20394, ItemID.PRAYER_POTION4_20393);
					utils.useItem(PrayerPot, 2, MenuAction.CC_OP);
				}
			}
		}
	}


	@Subscribe
	private void onAnimationChanged(AnimationChanged event) {

	}

	@Subscribe
	private void onVarbitChanged(final VarbitChanged event) throws IOException {

	}

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
	}

	@Subscribe
	private void onNpcDespawned(final NpcDespawned event) {
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
}
