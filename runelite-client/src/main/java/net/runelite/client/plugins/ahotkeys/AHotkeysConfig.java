package net.runelite.client.plugins.ahotkeys;

import net.runelite.api.MenuAction;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.*;

import java.awt.event.KeyEvent;

@ConfigGroup("ahotkeys")
public interface AHotkeysConfig extends Config {

    @ConfigSection(
            name = "PVP",
            description = "",
            position = 10,
            closedByDefault = true
    )
    String pvp = "pvp";

    @ConfigSection(
            name = "Debug",
            description = "",
            position = 11,
            closedByDefault = true
    )
    String debug = "debug";

    @ConfigSection(
            name = "Hotkey 1",
            description = "",
            position = 0,
            closedByDefault = true
    )
    String hotkey1 = "hotkey1";

    @ConfigSection(
            name = "Hotkey 2",
            description = "",
            position = 2,
            closedByDefault = true
    )
    String hotkey2 = "hotkey2";

    @ConfigSection(
            name = "Hotkey 3",
            description = "",
            position = 3,
            closedByDefault = true
    )
    String hotkey3 = "hotkey3";

    @ConfigSection(
            name = "Hotkey 4",
            description = "",
            position = 4,
            closedByDefault = true
    )
    String hotkey4 = "hotkey4";

    @ConfigSection(
            name = "Hotkey 5",
            description = "",
            position = 5,
            closedByDefault = true
    )
    String hotkey5 = "hotkey5";

    @ConfigItem(
            keyName = "key1",
            name = "Hotkey 1",
            description = "Hotkey to activate script 1",
            position = 2,
            section = hotkey1
    )
    default Keybind key1() {
        return new Keybind(KeyEvent.VK_F1, 0);
    }

    @ConfigItem(
            keyName = "key2",
            name = "Hotkey 2",
            description = "Hotkey to activate script 2",
            position = 2,
            section = hotkey2
    )
    default Keybind key2() {
        return new Keybind(KeyEvent.VK_F2, 0);
    }

    @ConfigItem(
            keyName = "key3",
            name = "Hotkey 3",
            description = "Hotkey to activate script 3",
            position = 2,
            section = hotkey3
    )
    default Keybind key3() {
        return new Keybind(KeyEvent.VK_F3, 0);
    }

    @ConfigItem(
            keyName = "key4",
            name = "Hotkey 4",
            description = "Hotkey to activate script 4",
            position = 2,
            section = hotkey4
    )
    default Keybind key4() {
        return new Keybind(KeyEvent.VK_F4, 0);
    }

    @ConfigItem(
            keyName = "key5",
            name = "Hotkey 5",
            description = "Hotkey to activate script 5",
            position = 2,
            section = hotkey5
    )
    default Keybind key5() {
        return new Keybind(KeyEvent.VK_F5, 0);
    }

	/*@ConfigItem(
			keyName = "key6",
			name = "Hotkey 6",
			description = "Hotkey to activate script 6",
			position = 2,
			section = "section6"
	)
	default Keybind key6() {
		return new Keybind(KeyEvent.VK_F6, 0);
	}

	@ConfigItem(
			keyName = "key7",
			name = "Hotkey 7",
			description = "Hotkey to activate script 7",
			position = 2,
			section = "section7"
	)
	default Keybind key7() {
		return new Keybind(KeyEvent.VK_F7, 0);
	}

	@ConfigItem(
			keyName = "key8",
			name = "Hotkey 8",
			description = "Hotkey to activate script 8",
			position = 2
	)
	default Keybind key8() {
		return new Keybind(KeyEvent.VK_F8, 0);
	}

	@ConfigItem(
			keyName = "key9",
			name = "Hotkey 9",
			description = "Hotkey to activate script 9",
			position = 2
	)
	default Keybind key9() {
		return new Keybind(KeyEvent.VK_F9, 0);
	}

	@ConfigItem(
			keyName = "key10",
			name = "Hotkey 10",
			description = "Hotkey to activate script 10",
			position = 2
	)
	default Keybind key10() {
		return new Keybind(KeyEvent.VK_F10, 0);
	}*/


    @ConfigItem(
            position = 1,
            keyName = "type1",
            name = "Hotkey 1 Type",
            description = "",
            section = hotkey1
    )
    default String type1() {return "widget";}

    @ConfigItem(
            position = 3,
            keyName = "itemID1",
            name = "Key 1 Item ID(s)",
            description = "Separate item ID's with a comma",
            section = hotkey1
    )
    default int itemID1() {
        return 0;
    }

    @ConfigItem(
            position = 2,
            keyName = "type1ID",
            name = "Key 1 ID",
            description = "",
            section = hotkey1
    )
    default int type1ID() {
        return 0;
    }

    @ConfigItem(
            position = 21,
            keyName = "type1op",
            name = "Key 1 Index (Item)",
            description = "",
            section = hotkey1
    )
    default int type1op() {
        return 0;
    }

    @ConfigItem(
            keyName = "walkLoc1",
            name = "Tile Location",
            description = "",
            position = 43,
            section = hotkey1
    )
    default String walkLoc1() {
        return "0,0,0";
    }

    @ConfigItem(
            position = 21,
            keyName = "type1opp",
            name = "Key 1 MenuAction",
            description = "",
            section = hotkey1
    )
    default MenuAction type1opp() {
        return MenuAction.CC_OP;
    }

    @ConfigItem(
            position = 21,
            keyName = "type1Widget1",
            name = "Key 1 Param 1",
            description = "",
            section = hotkey1
    )
    default int type1Widget1() {
        return 0;
    }

    @ConfigItem(
            position = 22,
            keyName = "type1Widget2",
            name = "Key 1 Param 2",
            description = "",
            section = hotkey1
    )
    default int type1Widget2() {
        return 0;
    }

    @ConfigItem(
            position = 23,
            keyName = "prayer1",
            name = "Hotkey 1 Prayer",
            description = "",
            section = hotkey1
    )
    default WidgetInfo typep1() {return WidgetInfo.PRAYER_EAGLE_EYE;}

    @ConfigItem(
            position = 1,
            keyName = "type2",
            name = "Hotkey 2 Type",
            description = "",
            section = hotkey2
    )
    default String type2() {return "widget";}

    @ConfigItem(
            position = 3,
            keyName = "itemID2",
            name = "Key 2 Item ID(s)",
            description = "Separate item ID's with a comma",
            section = hotkey2
    )
    default int itemID2() {
        return 0;
    }

    @ConfigItem(
            position = 2,
            keyName = "type2ID",
            name = "Key 2 ID",
            description = "",
            section = hotkey2
    )
    default int type2ID() {
        return 0;
    }

    @ConfigItem(
            position = 21,
            keyName = "type2op",
            name = "Key 2 Opcode",
            description = "",
            section = hotkey2
    )
    default int type2op() {
        return 0;
    }

    @ConfigItem(
            keyName = "walkLoc2",
            name = "Tile Location",
            description = "",
            position = 43
            ,
            section = hotkey2
    )
    default String walkLoc2() {
        return "0,0,0";
    }

    @ConfigItem(
            position = 21,
            keyName = "type2opp",
            name = "Key 2 Opcode",
            description = ""
            ,
            section = hotkey2
    )
    default MenuAction type2opp() {
        return MenuAction.CC_OP;
    }

    @ConfigItem(
            position = 26,
            keyName = "type2Widget1",
            name = "Key 2 Param 1",
            description = ""
            ,
            section = hotkey2
    )
    default int type2Widget1() {
        return 0;
    }

    @ConfigItem(
            position = 27,
            keyName = "type2Widget2",
            name = "Key 2 Param 2",
            description = ""
            ,
            section = hotkey2
    )
    default int type2Widget2() {
        return 0;
    }

    @ConfigItem(
            position = 23,
            keyName = "prayer2",
            name = "Hotkey 2 Prayer",
            description = "",
            section = hotkey2
    )
    default WidgetInfo typep2() {return WidgetInfo.PRAYER_EAGLE_EYE;}


    @ConfigItem(
            position = 1,
            keyName = "type3",
            name = "Hotkey 3 Type",
            description = "",
            section = hotkey3
    )
    default String type3() {return "widget";}

    @ConfigItem(
            position = 3,
            keyName = "itemID3",
            name = "Key 1 Item ID(s)",
            description = "Separate item ID's with a comma",
            section = hotkey3
    )
    default int itemID3() {
        return 0;
    }

    @ConfigItem(
            position = 2,
            keyName = "type3ID",
            name = "Key 3 ID",
            description = "",
            section = hotkey3
    )
    default int type3ID() {
        return 0;
    }

    @ConfigItem(
            position = 21,
            keyName = "type3op",
            name = "Key 3 Opcode",
            description = "",
            section = hotkey3
    )
    default int type3op() {
        return 0;
    }

    @ConfigItem(
            keyName = "walkLoc3",
            name = "Tile Location",
            description = "",
            position = 43,
            section = hotkey3
    )
    default String walkLoc3() {
        return "0,0,0";
    }

    @ConfigItem(
            position = 21,
            keyName = "type3opp",
            name = "Key 3 Opcode",
            description = "",
            section = hotkey3
    )
    default MenuAction type3opp() {
        return MenuAction.CC_OP;
    }

    @ConfigItem(
            position = 31,
            keyName = "type3Widget1",
            name = "Key 3 Param 1",
            description = "",
            section = hotkey3
    )
    default int type3Widget1() {
        return 0;
    }

    @ConfigItem(
            position = 32,
            keyName = "type3Widget2",
            name = "Key 3 Param 2",
            description = "",
            section = hotkey3
    )
    default int type3Widget2() {
        return 0;
    }

    @ConfigItem(
            position = 23,
            keyName = "prayer3",
            name = "Hotkey 3 Prayer",
            description = "",
            section = hotkey3
    )
    default WidgetInfo typep3() {return WidgetInfo.PRAYER_EAGLE_EYE;}

    @ConfigItem(
            position = 1,
            keyName = "type4",
            name = "Hotkey 4 Type",
            description = "",
            section = hotkey4
    )
    default String type4() {return "widget";}

    @ConfigItem(
            position = 3,
            keyName = "itemID4",
            name = "Key 4 Item ID(s)",
            description = "Separate item ID's with a comma",
            section = hotkey4
    )
    default int itemID4() {
        return 0;
    }

    @ConfigItem(
            position = 2,
            keyName = "type4ID",
            name = "Key 4 ID",
            description = "",
            section = hotkey4
    )
    default int type4ID() {
        return 0;
    }

    @ConfigItem(
            position = 21,
            keyName = "type4op",
            name = "Key 4 Opcode",
            description = "",
            section = hotkey4
    )
    default int type4op() {
        return 0;
    }

    @ConfigItem(
            keyName = "walkLoc4",
            name = "Tile Location",
            description = "",
            position = 43,
            section = hotkey4
    )
    default String walkLoc4() {
        return "0,0,0";
    }

    @ConfigItem(
            position = 21,
            keyName = "type4opp",
            name = "Key 4 Opcode",
            description = "",
            section = hotkey4
    )
    default MenuAction type4opp() {
        return MenuAction.CC_OP;
    }

    @ConfigItem(
            position = 36,
            keyName = "type4Widget1",
            name = "Key 4 Param 1",
            description = "",
            section = hotkey4
    )
    default int type4Widget1() {
        return 0;
    }

    @ConfigItem(
            position = 37,
            keyName = "type1Widget2",
            name = "Key 4 Param 2",
            description = "",
            section = hotkey4
    )
    default int type4Widget2() {
        return 0;
    }

    @ConfigItem(
            position = 23,
            keyName = "prayer4",
            name = "Hotkey 4 Prayer",
            description = "",
            section = hotkey4
    )
    default WidgetInfo typep4() {return WidgetInfo.PRAYER_EAGLE_EYE;}


///////////////////////////////////////////////////////////////////////////////////////////////////


    ///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

    @ConfigItem(
            position = 1,
            keyName = "type5",
            name = "Hotkey 5 Type",
            description = "",
            section = hotkey5
    )
    default String type5() {return "widget";}

    @ConfigItem(
            position = 3,
            keyName = "itemID5",
            name = "Key 5 Item ID(s)",
            description = "Separate item ID's with a comma",
            section = hotkey5
    )
    default int itemID5() {
        return 0;
    }

    @ConfigItem(
            position = 2,
            keyName = "type5ID",
            name = "Key 5 ID",
            description = "",
            section = hotkey5
    )
    default int type5ID() {
        return 0;
    }

    @ConfigItem(
            position = 21,
            keyName = "type5op",
            name = "Key 5 Opcode",
            description = "",
            section = hotkey5
    )
    default int type5op() {
        return 0;
    }

    @ConfigItem(
            keyName = "walkLoc5",
            name = "Tile Location",
            description = "",
            position = 43,
            section = hotkey5
    )
    default String walkLoc5() {
        return "0,0,0";
    }

    @ConfigItem(
            position = 21,
            keyName = "type5opp",
            name = "Key 5 Opcode",
            description = "",
            section = hotkey5
    )
    default MenuAction type5opp() {
        return MenuAction.CC_OP;
    }

    @ConfigItem(
            position = 41,
            keyName = "type5Widget1",
            name = "Key 5 Param 1",
            description = "",
            section = hotkey5
    )
    default int type5Widget1() {
        return 0;
    }

    @ConfigItem(
            position = 42,
            keyName = "type5Widget2",
            name = "Key 5 Param 2",
            description = "",
            section = hotkey5
    )
    default int type5Widget2() {
        return 0;
    }

    @ConfigItem(
            position = 23,
            keyName = "prayer5",
            name = "Hotkey 5 Prayer",
            description = "",
            section = hotkey5
    )
    default WidgetInfo typep5() {return WidgetInfo.PRAYER_EAGLE_EYE;}
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////



/* PVP PVP PVP PVP PVP PVP PVP PVP PVP




	@ConfigItem(
		position = 21,
		keyName = "autoPrayerSwitcher",
		name = "Prayer Switcher",
		description = "Automatically Switch Prayers based on opponent's gear."
	)
	default boolean autoPrayerSwitcher()
	{
		return false;
	}



	/*@ConfigItem(
		position = 22,
		keyName = "specOnHealthAmount",
		name = "Spec Below Health Amount",
		description = "Automatically spec enemy when their health is below amount"
	)
	default int specOnHealthAmount(){return 67;}


	@ConfigItem(
			position = 21,
			keyName = "specOnHealth",
			name = "Spec When Health Below",
			description = "Automatically spec enemy when their health is below amount"
	)
	default boolean specOnHealth()
	{
		return false;
	}*/



    @ConfigItem(
            position = 21,
            keyName = "autoPrayerSwitcher",
            name = "Prayer Switcher",
            description = "Automatically Switch Prayers based on opponent's gear.",
            section = pvp
    )
    default boolean autoPrayerSwitcher()
    {
        return false;
    }


    @ConfigItem(
            position = 22,
            keyName = "enableMeleePrayer",
            name = "Enable Melee Prayer",
            description = "If disabled, will ignore melee prayer switches",
            section = pvp

    )
    default boolean enableMeleePrayer()
    {
        return true;
    }

    @ConfigItem(
            position = 23,
            keyName = "enableMagicPrayer",
            name = "Enable Magic Prayer",
            description = "If disabled, will ignore magic prayer switches",
            section = pvp

    )
    default boolean enableMagicPrayer()
    {
        return true;
    }

    @ConfigItem(
            position = 24,
            keyName = "enableRangedPrayer",
            name = "Enable Ranged Prayer",
            description = "If disabled, will ignore ranged prayer switches",
            section = pvp

    )
    default boolean enableRangedPrayer()
    {
        return true;
    }

	/*@ConfigItem(
		position = 28,
		keyName = "prayerHelper",
		name = "Prayer Helper",
		description = "Draws icons to suggest proper prayer switches",
		hidden = true
	)
	default boolean prayerHelper()
	{
		return false;
	}*/


    @ConfigItem(
            position = 36,
            keyName = "autoGearSwap",
            name = "Automatically Swap Gear",
            description = "Swap gear based on opponent's weakness using group 1 as meelee, 2 as ranged, 3 as magic",
            section = pvp

    )
    default boolean autoGearSwap()
    {
        return true;
    }

    @ConfigItem(
            position = 37,
            keyName = "swapFromPray",
            name = "Auto Gear Swap Type",
            description = "If Enabled, will swap your gear based on opponents overhead prayer, if Disabled, uses their weapon type",
            section = pvp

    )
    default boolean swapFromPray()
    {
        return true;
    }



    @ConfigItem(
            position = 3,
            keyName = "mGroup",
            name = "Melee Item IDs",
            description = "Separate item ID's with a comma",
            section = pvp
    )
    default String mGroup() {
        return "0,1,2";
    }


    @ConfigItem(
            position = 4,
            keyName = "rGroup",
            name = "Ranged Item IDs",
            description = "Separate item ID's with a comma",
            section = pvp
    )
    default String rGroup() {
        return "0,1,2";
    }

    @ConfigItem(
            position = 3,
            keyName = "maGroup",
            name = "Magic Item IDs",
            description = "Separate item ID's with a comma",
            section = pvp
    )
    default String maGroup() {
        return "0,1,2";
    }



    @ConfigItem(
            position = 222,
            keyName = "debug",
            name = "Print Menu Entries",
            description = "",
            section = debug

    )
    default boolean debug()
    {
        return false;
    }


/*
	///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

 */
}
