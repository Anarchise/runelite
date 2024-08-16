package net.runelite.client.plugins.ahotkeys;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.NPC;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyListener;
import net.runelite.client.plugins.Utils.Core;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.time.Duration;
import java.time.Instant;

public class AHotkeysListener extends MouseAdapter implements KeyListener
{
    private Client client;

    private Instant lastPress;

    @Inject
    private AHotkeys plugin;

    @Inject
    private AHotkeysConfig config;

    @Inject
    private ClientThread clientThread;

    @Inject
    Core core;

    @Inject
    private ConfigManager configManager;

    @Inject
    private AHotkeysListener(Client client, AHotkeysConfig config, AHotkeys plugin)
    {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
    }

    public static String getTag(ConfigManager configManager, int itemId)
    {
        String tag = configManager.getConfiguration("inventorytags", "item_" + itemId);
        if (tag == null || tag.isEmpty())
        {
            return "";
        }

        return tag;
    }



    public int param;
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        try
        {
            if (lastPress != null && Duration.between(lastPress, Instant.now()).getNano() > 1000)
            {
                lastPress = null;
            }

            if (lastPress != null)
            {
                return;
            }
            int key_code = e.getKeyCode();

            if (key_code == config.key1().getKeyCode())
            {
                if (config.type1().toLowerCase().contains("prayer")) {
                    core.clickPrayer(config.typep1());
                }
                if (config.type1().toLowerCase().contains("tb")){
                    core.clickSpell(WidgetInfo.SPELL_TELE_BLOCK, true);
                }
                if (config.type1().toLowerCase().contains("entangle")){
                    core.clickSpell(WidgetInfo.SPELL_ENTANGLE, true);
                }
                if (config.type1().toLowerCase().contains("veng")){
                    core.clickSpell(WidgetInfo.SPELL_VENGEANCE, false);
                }
                if (config.type1().toLowerCase().contains("magicimbue")){
                    core.clickSpell(WidgetInfo.SPELL_MAGIC_IMBUE, false);
                }
                if (config.type1().toLowerCase().contains("plankmake")){
                    core.clickSpell(WidgetInfo.SPELL_PLANK_MAKE, false);
                }
                if (config.type1().toLowerCase().contains("npccontact")){
                    core.clickSpell(WidgetInfo.SPELL_NPC_CONTACT, false);
                }
                if (config.type1().toLowerCase().contains("alch")){
                    core.clickSpell(WidgetInfo.SPELL_HIGH_LEVEL_ALCHEMY, true);
                }
                if (config.type1().toLowerCase().contains("firesurge")){
                    core.clickSpell(WidgetInfo.SPELL_FIRE_SURGE, true);
                }
                if (config.type1().toLowerCase().contains("item")){
                    core.useItem(core.getWidgetItem(config.itemID1()), config.type1op(), config.type1opp());
                }
                if (config.type1().toLowerCase().contains("object")){
                    GameObject obj = core.findNearestGameObject(config.type1ID());
                    core.useGameObject(obj, config.type1opp());
                }
                if (config.type1().toLowerCase().contains("walk")){
                    int[] Location = core.stringToIntArray(config.walkLoc1());
                    WorldPoint WalkLoc1 = new WorldPoint(Location[0], Location[1], Location[2]);
                    core.walk(WalkLoc1);
                }
                if (config.type1().toLowerCase().contains("npc")){
                    NPC npc = core.findNearestNpc(config.type1ID());
                    core.interactNPC(npc, config.type1opp());
                }
                if (config.type1().toLowerCase().contains("widget")) {
                    Widget widget = client.getWidget(config.type1Widget1(), config.type1Widget2());
                    core.clickWidget(widget, config.type1opp());
                }
            }

            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////










            if (key_code == config.key2().getKeyCode())
            {
                if (config.type2().toLowerCase().contains("prayer")) {
                    core.clickPrayer(config.typep2());
                }
                if (config.type2().toLowerCase().contains("tb")){
                    core.clickSpell(WidgetInfo.SPELL_TELE_BLOCK, true);
                }
                if (config.type2().toLowerCase().contains("entangle")){
                    core.clickSpell(WidgetInfo.SPELL_ENTANGLE, true);

                }
                if (config.type2().toLowerCase().contains("veng")){
                    core.clickSpell(WidgetInfo.SPELL_VENGEANCE, false);
                }
                if (config.type2().toLowerCase().contains("magicimbue")){
                    core.clickSpell(WidgetInfo.SPELL_MAGIC_IMBUE, false);
                }
                if (config.type2().toLowerCase().contains("plankmake")){
                    core.clickSpell(WidgetInfo.SPELL_PLANK_MAKE, false);
                }
                if (config.type2().toLowerCase().contains("npccontact")){
                    core.clickSpell(WidgetInfo.SPELL_NPC_CONTACT, false);
                }
                if (config.type2().toLowerCase().contains("alch")){
                    core.clickSpell(WidgetInfo.SPELL_HIGH_LEVEL_ALCHEMY, true);
                }
                if (config.type2().toLowerCase().contains("firesurge")){
                    core.clickSpell(WidgetInfo.SPELL_FIRE_SURGE, true);
                }
                if (config.type2().toLowerCase().contains("item")){
                   core.useItem(core.getWidgetItem(config.itemID2()), config.type2op(), config.type2opp());
                }
                if (config.type2().toLowerCase().contains("object")){
                    GameObject obj = core.findNearestGameObject(config.type2ID());
                    core.useGameObject(obj, config.type2opp());
                }
                if (config.type2().toLowerCase().contains("walk")){
                    int[] Location = core.stringToIntArray(config.walkLoc2());
                    WorldPoint WalkLoc1 = new WorldPoint(Location[0], Location[1], Location[2]);
                    core.walk(WalkLoc1);
                }
                if (config.type2().toLowerCase().contains("npc")){
                    NPC npc = core.findNearestNpc(config.type2ID());
                    core.interactNPC(npc, config.type2opp());
                }
                if (config.type2().toLowerCase().contains("widget")) {
                    Widget widget = client.getWidget(config.type2Widget1(), config.type2Widget2());
                    core.clickWidget(widget, config.type2opp());
                }
            }

            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////

            /* TODO: 3 to 5 macros */














            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////

        }
        catch (Throwable ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

}