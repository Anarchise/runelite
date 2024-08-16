package net.runelite.client.plugins.ahotkeys;

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
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.NPCManager;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.Utils.Core;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.WeaponMap;
import net.runelite.client.util.WeaponStyle;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;


@PluginDescriptor(
        name = "AHotkeys",
        description = "Anarchise' Hotkeys",
        tags = {"Hotkeys","Anarchise","pvm"},
        enabledByDefault = false
)
public class AHotkeys extends Plugin
{
    @Inject
    Core core;
    @Inject
    public Client client;

    @Inject
    public ClientThread clientThread;

    @Inject
    private AHotkeysConfig aHotkeysConfig;

    @Inject
    private ConfigManager configManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private AHotkeysListener AHotkeysListener;

    @Inject
    private KeyManager keyManager;

    @Getter(AccessLevel.PACKAGE)

    @Inject
    private ItemManager itemManager;

    @Getter(AccessLevel.PACKAGE)
    public Player lastEnemy;
    private NPCManager npcManager;
    private Instant lastTime;

    @Provides
    AHotkeysConfig provideConfig(final ConfigManager configManager)
    {
        return configManager.getConfig(AHotkeysConfig.class);
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

   /* @Subscribe
    private void onItemContainerChanged(ItemContainerChanged event) {
       if (event.getItemContainer() == client.getItemContainer(InventoryID.INVENTORY)) {

           List<Item> itemz = List.of(event.getItemContainer().getItems());
           List<Integer> itemIDz = null;

           for (Item tileItem : itemz) {
               itemIDz.add(tileItem.getId());
           }

           for (int ids : value2) {

           }
       }
    }*/


    @Override
    protected void startUp() throws IOException {
        keyManager.registerKeyListener(AHotkeysListener);
    }

    @Override
    protected void shutDown()
    {
        keyManager.unregisterKeyListener(AHotkeysListener);
        lastTime = null;
    }

    @Subscribe
    public void onClientTick(ClientTick event)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        lastEnemyTimer();
        //processCommands();
    }


    @Subscribe
    public void onGameTick(GameTick event) throws IOException {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }
        doAutoSwapPrayers();
       // doSwapGear();
    }
    private static final Duration WAIT = Duration.ofSeconds(5);
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
            if (Duration.between(lastTime, Instant.now()).compareTo(WAIT) > 0)
            {
                lastEnemy = null;
            }
        }
    }

    public void doAutoSwapPrayers()
    {
        if (!aHotkeysConfig.autoPrayerSwitcher())
        {
            return;
        }

        if (!aHotkeysConfig.autoPrayerSwitcher())
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
                    if (aHotkeysConfig.enableMeleePrayer()) {
                        activatePrayer(Prayer.PROTECT_FROM_MELEE);
                    }
                    break;
                case RANGE:
                    if (aHotkeysConfig.enableRangedPrayer()) {
                        activatePrayer(Prayer.PROTECT_FROM_MISSILES);
                    }
                    break;
                case MAGIC:
                    if (aHotkeysConfig.enableMagicPrayer()) {
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

    public void doSwapGear()
    {
        if (!aHotkeysConfig.autoGearSwap())
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
            if (!aHotkeysConfig.swapFromPray()){
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
                        int[] maGroup = core.stringToIntArray(aHotkeysConfig.maGroup());
                        for (int mgroup : maGroup) {
                            core.useItem(core.getWidgetItem(mgroup), 3, MenuAction.CC_OP);
                        }
                        if (lastEnemy != null) {
                            int boosted_level = client.getBoostedSkillLevel(Skill.MAGIC);

                            if (boosted_level >= 82 && boosted_level < 94) {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BLITZ, true);
                            } else {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BARRAGE, true);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) >= 77 && !client.isPrayerActive(Prayer.AUGURY)) {
                                core.clickPrayer(WidgetInfo.PRAYER_AUGURY);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) >= 45 && client.getRealSkillLevel(Skill.PRAYER) < 77 && !client.isPrayerActive(Prayer.MYSTIC_MIGHT)) {
                                core.clickPrayer(WidgetInfo.PRAYER_MYSTIC_MIGHT);
                            }
                            client.menuAction(0, 0, client.isWidgetSelected() ? MenuAction.WIDGET_TARGET_ON_PLAYER : MenuAction.PLAYER_SECOND_OPTION, lastEnemy.getId(),0, "","");
                        }
                        break;
                    case RANGE:
                        WeaponStyle localWeaponStyle2 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
                        if (localWeaponStyle2 == WeaponStyle.MAGIC){
                            break;
                        }
                        int[] maaGroup = core.stringToIntArray(aHotkeysConfig.maGroup());
                        for (int mgroup : maaGroup) {
                            core.useItem(core.getWidgetItem(mgroup), 3, MenuAction.CC_OP);
                        }
                        if (lastEnemy != null) {
                            int boosted_level = client.getBoostedSkillLevel(Skill.MAGIC);

                            if (boosted_level >= 82 && boosted_level < 94) {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BLITZ, true);
                            } else {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BARRAGE, true);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) >= 77 && !client.isPrayerActive(Prayer.AUGURY)) {
                                core.clickPrayer(WidgetInfo.PRAYER_AUGURY);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) >= 45 && client.getRealSkillLevel(Skill.PRAYER) < 77 && !client.isPrayerActive(Prayer.MYSTIC_MIGHT)) {
                                core.clickPrayer(WidgetInfo.PRAYER_MYSTIC_MIGHT);
                            }
                            client.menuAction(0, 0, client.isWidgetSelected() ? MenuAction.WIDGET_TARGET_ON_PLAYER : MenuAction.PLAYER_SECOND_OPTION, lastEnemy.getId(),0, "","");
                        }
                        break;
                    case MAGIC:
                        WeaponStyle localWeaponStyle3 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
                        if (localWeaponStyle3 == WeaponStyle.RANGE){
                            break;
                        }
                        int[] rGroup = core.stringToIntArray(aHotkeysConfig.rGroup());
                        for (int mgroup : rGroup) {
                            core.useItem(core.getWidgetItem(mgroup), 3, MenuAction.CC_OP);
                        }
                        if (client.getRealSkillLevel(Skill.PRAYER) >= 74) {
                            core.clickPrayer(WidgetInfo.PRAYER_RIGOUR);
                        }
                        if (client.getRealSkillLevel(Skill.PRAYER) < 74 && client.getRealSkillLevel(Skill.PRAYER) >= 44) {
                            core.clickPrayer(WidgetInfo.PRAYER_EAGLE_EYE);
                        }
                        if (lastEnemy != null) {
                            client.menuAction(0, 0, client.isWidgetSelected() ? MenuAction.WIDGET_TARGET_ON_PLAYER : MenuAction.PLAYER_SECOND_OPTION, lastEnemy.getId(),0, "","");
                        }
                        break;
                    default:
                        break;
                }
            }

            if (aHotkeysConfig.swapFromPray()) {
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
                        int[] maGroup = core.stringToIntArray(aHotkeysConfig.maGroup());
                        for (int mgroup : maGroup) {
                            core.useItem(core.getWidgetItem(mgroup), 3, MenuAction.CC_OP);
                        }
                        if (lastEnemy != null) {
                            int boosted_level = client.getBoostedSkillLevel(Skill.MAGIC);

                            if (boosted_level >= 82 && boosted_level < 94) {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BLITZ, true);
                            } else {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BARRAGE, true);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) > 77 && !client.isPrayerActive(Prayer.AUGURY)) {
                                core.clickPrayer(WidgetInfo.PRAYER_AUGURY);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) > 45 && client.getRealSkillLevel(Skill.PRAYER) < 77 && !client.isPrayerActive(Prayer.MYSTIC_MIGHT)) {
                                core.clickPrayer(WidgetInfo.PRAYER_MYSTIC_MIGHT);
                            }
                            client.menuAction(0, 0, client.isWidgetSelected() ? MenuAction.WIDGET_TARGET_ON_PLAYER : MenuAction.PLAYER_SECOND_OPTION, lastEnemy.getId(),0, "","");
                        }
                        break;
                    case RANGED:
                        WeaponStyle localWeaponStyle1 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
                        if (localWeaponStyle1 == WeaponStyle.MAGIC){
                            break;
                        }
                        int[] maaGroup = core.stringToIntArray(aHotkeysConfig.maGroup());
                        for (int mgroup : maaGroup) {
                            core.useItem(core.getWidgetItem(mgroup), 3, MenuAction.CC_OP);
                        }
                        if (lastEnemy != null) {
                            int boosted_level = client.getBoostedSkillLevel(Skill.MAGIC);

                            if (boosted_level >= 82 && boosted_level < 94) {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BLITZ, true);
                            } else {
                                core.clickSpell(WidgetInfo.SPELL_ICE_BARRAGE, true);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) > 77 && !client.isPrayerActive(Prayer.AUGURY)) {
                                core.clickPrayer(WidgetInfo.PRAYER_AUGURY);
                            }
                            if (client.getRealSkillLevel(Skill.PRAYER) > 45 && client.getRealSkillLevel(Skill.PRAYER) < 77 && !client.isPrayerActive(Prayer.MYSTIC_MIGHT)) {
                                core.clickPrayer(WidgetInfo.PRAYER_MYSTIC_MIGHT);
                            }
                            client.menuAction(0, 0, client.isWidgetSelected() ? MenuAction.WIDGET_TARGET_ON_PLAYER : MenuAction.PLAYER_SECOND_OPTION, lastEnemy.getId(),0, "","");
                        }
                        break;
                    case MAGIC:
                        WeaponStyle localWeaponStyle3 = WeaponMap.StyleMap.getOrDefault(client.getLocalPlayer().getPlayerComposition().getEquipmentId(KitType.WEAPON), null);
                        if (localWeaponStyle3 == WeaponStyle.RANGE){
                            break;
                        }
                        int[] rGroup = core.stringToIntArray(aHotkeysConfig.rGroup());
                        for (int mgroup : rGroup) {
                            core.useItem(core.getWidgetItem(mgroup), 3, MenuAction.CC_OP);
                        }
                        if (lastEnemy != null) {
                            client.menuAction(0, 0, client.isWidgetSelected() ? MenuAction.WIDGET_TARGET_ON_PLAYER : MenuAction.PLAYER_SECOND_OPTION, lastEnemy.getId(),0, "","");
                        }
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

    @Subscribe
    private void onMenuOptionClicked(MenuOptionClicked event)
    {
        if (event.getMenuAction() == MenuAction.CC_OP && (event.getWidgetId() == WidgetInfo.WORLD_SWITCHER_LIST.getId() ||
                event.getWidgetId() == 11927560 || event.getWidgetId() == 4522007 || event.getWidgetId() == 24772686))
        {
            //Either logging out or world-hopping which is handled by 3rd party plugins so let them have priority
            core.targetMenu = null;
            return;
        }
        if (aHotkeysConfig.debug()) {
            core.sendGameMessage("Option: " + event.getMenuOption() + " Target: " + event.getMenuTarget() + " MenuAction: " + event.getMenuAction() + " Param0: " + event.getParam0() + " Param1: " + event.getParam1() + " ID: " + event.getId() + " ItemID: " + event.getItemId());
        }
        //core.sendGameMessage(event.toString());	//Debugging
    }
}



