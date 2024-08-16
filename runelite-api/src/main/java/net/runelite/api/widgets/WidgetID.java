/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
 *
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
package net.runelite.api.widgets;

/**
 * Utility class mapping widget IDs to global constants.
 * <p>
 * The constants defined directly under the {@link WidgetID} class are
 * Widget group IDs. All child IDs are defined in sub-classes relating
 * to their group.
 * <p>
 * For a more direct group-child widget mapping, use the
 * {@link WidgetInfo} enum class.
 */
@Deprecated
public final class WidgetID
{
	public static final int FAIRY_RING_PANEL_GROUP_ID = InterfaceID.FAIRY_RING_PANEL;
	public static final int FAIRY_RING_GROUP_ID = InterfaceID.FAIRY_RING;
	public static final int LOGOUT_PANEL_ID = InterfaceID.LOGOUT_PANEL;
	public static final int BANK_GROUP_ID = InterfaceID.BANK;
	public static final int BANK_INVENTORY_GROUP_ID = InterfaceID.BANK_INVENTORY;
	public static final int GRAND_EXCHANGE_INVENTORY_GROUP_ID = InterfaceID.GRAND_EXCHANGE_INVENTORY;
	public static final int GRAND_EXCHANGE_GROUP_ID = InterfaceID.GRAND_EXCHANGE;
	public static final int DEPOSIT_BOX_GROUP_ID = InterfaceID.DEPOSIT_BOX;
	public static final int INVENTORY_GROUP_ID = InterfaceID.INVENTORY;
	public static final int PLAYER_TRADE_SCREEN_GROUP_ID = InterfaceID.TRADE;
	public static final int PLAYER_TRADE_INVENTORY_GROUP_ID = InterfaceID.TRADE_INVENTORY;
	public static final int FRIENDS_LIST_GROUP_ID = InterfaceID.FRIEND_LIST;
	public static final int IGNORE_LIST_GROUP_ID = InterfaceID.IGNORE_LIST;
	public static final int RAIDING_PARTY_GROUP_ID = InterfaceID.RAIDING_PARTY;
	public static final int EQUIPMENT_GROUP_ID = InterfaceID.EQUIPMENT;
	public static final int EQUIPMENT_INVENTORY_GROUP_ID = InterfaceID.EQUIPMENT_INVENTORY;
	public static final int EMOTES_GROUP_ID = InterfaceID.EMOTES;
	public static final int RUNE_POUCH_GROUP_ID = InterfaceID.RUNE_POUCH;
	public static final int ACHIEVEMENT_DIARY_GROUP_ID = InterfaceID.ACHIEVEMENT_DIARY;
	public static final int PEST_CONTROL_BOAT_GROUP_ID = InterfaceID.PEST_CONTROL_BOAT;
	public static final int PEST_CONTROL_GROUP_ID = InterfaceID.PEST_CONTROL;
	public static final int FRIENDS_CHAT_GROUP_ID = InterfaceID.FRIENDS_CHAT;
	public static final int MINIMAP_GROUP_ID = InterfaceID.MINIMAP;
	public static final int LOGIN_CLICK_TO_PLAY_GROUP_ID = InterfaceID.LOGIN_CLICK_TO_PLAY_SCREEN;
	public static final int CLUE_SCROLL_GROUP_ID = InterfaceID.CLUESCROLL;
	public static final int FIXED_VIEWPORT_GROUP_ID = InterfaceID.FIXED_VIEWPORT;
	public static final int RESIZABLE_VIEWPORT_OLD_SCHOOL_BOX_GROUP_ID = InterfaceID.RESIZABLE_VIEWPORT;
	public static final int RESIZABLE_VIEWPORT_BOTTOM_LINE_GROUP_ID = InterfaceID.RESIZABLE_VIEWPORT_BOTTOM_LINE;
	public static final int SHOP_GROUP_ID = InterfaceID.SHOP;
	public static final int SHOP_INVENTORY_GROUP_ID = InterfaceID.SHOP_INVENTORY;
	public static final int SMITHING_GROUP_ID = InterfaceID.SMITHING;
	public static final int GUIDE_PRICES_GROUP_ID = InterfaceID.GUIDE_PRICES;
	public static final int GUIDE_PRICES_INVENTORY_GROUP_ID = InterfaceID.GUIDE_PRICES_INVENTORY;
	public static final int COMBAT_GROUP_ID = InterfaceID.COMBAT;
	public static final int DIALOG_NPC_GROUP_ID = InterfaceID.DIALOG_NPC;
	public static final int SLAYER_REWARDS_GROUP_ID = InterfaceID.SLAYER_REWARDS;
	public static final int PRIVATE_CHAT = InterfaceID.PRIVATE_CHAT;
	public static final int CHATBOX_GROUP_ID = InterfaceID.CHATBOX;
	public static final int VOLCANIC_MINE_GROUP_ID = InterfaceID.VOLCANIC_MINE;
	public static final int BA_ATTACKER_GROUP_ID = InterfaceID.BA_ATTACKER;
	public static final int BA_COLLECTOR_GROUP_ID = InterfaceID.BA_COLLECTOR;
	public static final int BA_DEFENDER_GROUP_ID = InterfaceID.BA_DEFENDER;
	public static final int BA_HEALER_GROUP_ID = InterfaceID.BA_HEALER;
	public static final int BA_REWARD_GROUP_ID = InterfaceID.BA_REWARD;
	public static final int BA_TEAM_GROUP_ID = InterfaceID.BA_TEAM;
	public static final int LEVEL_UP_GROUP_ID = InterfaceID.LEVEL_UP;
	public static final int DIALOG_SPRITE_GROUP_ID = InterfaceID.DIALOG_SPRITE;
	public static final int DIALOG_DOUBLE_SPRITE_GROUP_ID = InterfaceID.DIALOG_DOUBLE_SPRITE;
	public static final int QUEST_COMPLETED_GROUP_ID = InterfaceID.QUEST_COMPLETED;
	public static final int CLUE_SCROLL_REWARD_GROUP_ID = InterfaceID.CLUESCROLL_REWARD;
	public static final int BARROWS_REWARD_GROUP_ID = InterfaceID.BARROWS_REWARD;
	public static final int RAIDS_GROUP_ID = InterfaceID.RAIDS;
	public static final int TOB_GROUP_ID = InterfaceID.TOB;
	public static final int MOTHERLODE_MINE_GROUP_ID = InterfaceID.MLM;
	public static final int EXPERIENCE_DROP_GROUP_ID = InterfaceID.EXPERIENCE_TRACKER;
	public static final int PUZZLE_BOX_GROUP_ID = InterfaceID.PUZZLE_BOX;
	public static final int LIGHT_BOX_GROUP_ID = InterfaceID.LIGHT_BOX;
	public static final int NIGHTMARE_ZONE_GROUP_ID = InterfaceID.NMZ;
	public static final int NIGHTMARE_PILLAR_HEALTH_GROUP_ID = InterfaceID.NIGHTMARE_PILLAR_HEALTH;
	public static final int BLAST_FURNACE_GROUP_ID = InterfaceID.BLAST_FURNACE;
	public static final int WORLD_MAP_GROUP_ID = InterfaceID.WORLD_MAP;
	public static final int PYRAMID_PLUNDER_GROUP_ID = InterfaceID.PYRAMID_PLUNDER;
	public static final int CHAMBERS_OF_XERIC_REWARD_GROUP_ID = InterfaceID.CHAMBERS_OF_XERIC_REWARD;
	public static final int THEATRE_OF_BLOOD_REWARD_GROUP_ID = InterfaceID.TOB_REWARD;
	public static final int EXPERIENCE_TRACKER_GROUP_ID = InterfaceID.EXPERIENCE_TRACKER;
	public static final int TITHE_FARM_GROUP_ID = InterfaceID.TITHE_FARM;
	public static final int KINGDOM_GROUP_ID = InterfaceID.KINGDOM;
	public static final int BARROWS_GROUP_ID = InterfaceID.BARROWS;
	public static final int BLAST_MINE_GROUP_ID = InterfaceID.BLAST_MINE;
	public static final int MTA_ALCHEMY_GROUP_ID = InterfaceID.MTA_ALCHEMY;
	public static final int MTA_ENCHANT_GROUP_ID = InterfaceID.MTA_ENCHANT;
	public static final int MTA_GRAVEYARD_GROUP_ID = InterfaceID.MTA_GRAVEYARD;
	public static final int MTA_TELEKINETIC_GROUP_ID = InterfaceID.MTA_TELEKINETIC;
	public static final int CORP_DAMAGE = InterfaceID.CORP_DAMAGE;
	public static final int DESTROY_ITEM_GROUP_ID = InterfaceID.DESTROY_ITEM;
	public static final int VARROCK_MUSEUM_QUIZ_GROUP_ID = InterfaceID.VARROCK_MUSEUM;
	public static final int KILL_LOGS_GROUP_ID = InterfaceID.KILL_LOG;
	public static final int DIARY_QUEST_GROUP_ID = InterfaceID.DIARY;
	public static final int THEATRE_OF_BLOOD_GROUP_ID = InterfaceID.TOB;
	public static final int WORLD_SWITCHER_GROUP_ID = InterfaceID.WORLD_SWITCHER;
	public static final int DIALOG_OPTION_GROUP_ID = InterfaceID.DIALOG_OPTION;
	public static final int DIALOG_PLAYER_GROUP_ID = InterfaceID.DIALOG_PLAYER;
	public static final int DRIFT_NET_FISHING_REWARD_GROUP_ID = InterfaceID.DRIFT_NET_FISHING_REWARD;
	public static final int FOSSIL_ISLAND_OXYGENBAR_ID = InterfaceID.FOSSIL_ISLAND_OXYGEN_BAR;
	//public static final int MINIGAME_TAB_ID = InterfaceID.MINIGAMES;
	public static final int SPELLBOOK_GROUP_ID = InterfaceID.SPELLBOOK;
	public static final int PVP_GROUP_ID = InterfaceID.PVP;
	public static final int FISHING_TRAWLER_GROUP_ID = InterfaceID.TRAWLER;
	public static final int FISHING_TRAWLER_REWARD_GROUP_ID = InterfaceID.TRAWLER_REWARD;
	public static final int ZEAH_MESS_HALL_GROUP_ID = InterfaceID.ZEAH_MESS_HALL;
	public static final int LOOTING_BAG_GROUP_ID = InterfaceID.LOOTING_BAG;
	public static final int SKOTIZO_GROUP_ID = InterfaceID.SKOTIZO;
	public static final int FULLSCREEN_CONTAINER_TLI = InterfaceID.FULLSCREEN_CONTAINER_TLI;
	public static final int CHARACTER_SUMMARY_GROUP_ID = InterfaceID.CHARACTER_SUMMARY;
	public static final int QUESTLIST_GROUP_ID = InterfaceID.QUEST_LIST;
	public static final int SKILLS_GROUP_ID = InterfaceID.SKILLS;
	public static final int MUSIC_GROUP_ID = InterfaceID.MUSIC;
	public static final int BARROWS_PUZZLE_GROUP_ID = InterfaceID.BARROWS_PUZZLE;
	public static final int KEPT_ON_DEATH_GROUP_ID = InterfaceID.KEPT_ON_DEATH;
	public static final int GUIDE_PRICE_GROUP_ID = InterfaceID.GUIDE_PRICES;
	public static final int SEED_VAULT_INVENTORY_GROUP_ID = InterfaceID.SEED_VAULT_INVENTORY;
	public static final int BEGINNER_CLUE_MAP_CHAMPIONS_GUILD = InterfaceID.CLUE_BEGINNER_MAP_CHAMPIONS_GUILD;
	public static final int BEGINNER_CLUE_MAP_VARROCK_EAST_MINE = InterfaceID.CLUE_BEGINNER_MAP_VARROCK_EAST_MINE;
	public static final int BEGINNER_CLUE_MAP_DRAYNOR = InterfaceID.CLUE_BEGINNER_MAP_DYANOR;
	public static final int BEGINNER_CLUE_MAP_NORTH_OF_FALADOR = InterfaceID.CLUE_BEGINNER_MAP_NORTH_OF_FALADOR;
	public static final int BEGINNER_CLUE_MAP_WIZARDS_TOWER = InterfaceID.CLUE_BEGINNER_MAP_WIZARDS_TOWER;
	public static final int SEED_BOX_GROUP_ID = InterfaceID.SEED_BOX;
	public static final int SEED_VAULT_GROUP_ID = InterfaceID.SEED_VAULT;
	public static final int EXPLORERS_RING_ALCH_GROUP_ID = InterfaceID.EXPLORERS_RING;
	public static final int SETTINGS_SIDE_GROUP_ID = InterfaceID.SETTINGS_SIDE;
	public static final int SETTINGS_GROUP_ID = InterfaceID.SETTINGS;
	public static final int GWD_KC_GROUP_ID = InterfaceID.GWD_KC;
	public static final int LMS_GROUP_ID = InterfaceID.LMS;
	public static final int LMS_INGAME_GROUP_ID = InterfaceID.LMS_INGAME;
	public static final int ADVENTURE_LOG_ID = InterfaceID.ADVENTURE_LOG;
	public static final int COLLECTION_LOG_ID = InterfaceID.COLLECTION_LOG;
	public static final int GENERIC_SCROLL_GROUP_ID = InterfaceID.GENERIC_SCROLL;
	public static final int GAUNTLET_TIMER_GROUP_ID = InterfaceID.GAUNTLET_TIMER;
	public static final int HALLOWED_SEPULCHRE_TIMER_GROUP_ID = InterfaceID.HALLOWED_SEPULCHRE_TIMER;
	public static final int BANK_PIN_GROUP_ID = InterfaceID.BANK_PIN;
	public static final int HEALTH_OVERLAY_BAR_GROUP_ID = InterfaceID.HEALTH;
	public static final int CHAMBERS_OF_XERIC_STORAGE_UNIT_PRIVATE_GROUP_ID = InterfaceID.CHAMBERS_OF_XERIC_STORAGE_UNIT_PRIVATE;
	public static final int CHAMBERS_OF_XERIC_STORAGE_UNIT_SHARED_GROUP_ID = InterfaceID.CHAMBERS_OF_XERIC_STORAGE_UNIT_SHARED;
	public static final int CHAMBERS_OF_XERIC_STORAGE_UNIT_INVENTORY_GROUP_ID = InterfaceID.CHAMBERS_OF_XERIC_INVENTORY;
	public static final int DUEL_INVENTORY_GROUP_ID = InterfaceID.DUEL_INVENTORY;
	//public static final int DUEL_INVENTORY_OTHER_GROUP_ID = InterfaceID.DUEL_INVENTORY_OTHER;
	public static final int TRAILBLAZER_AREAS_GROUP_ID = InterfaceID.TRAILBLAZER_AREAS;
	public static final int TEMPOROSS_GROUP_ID = InterfaceID.TEMPOROSS;
	public static final int TEMPOROSS_LOBBY_GROUP_ID = InterfaceID.TEMPOROSS_LOBBY;
	public static final int CLAN_GROUP_ID = InterfaceID.CLAN;
	public static final int CLAN_GUEST_GROUP_ID = InterfaceID.CLAN_GUEST;
	public static final int GRAVESTONE_GROUP_ID = InterfaceID.GRAVESTONE;
	public static final int POH_TREASURE_CHEST_INVENTORY_GROUP_ID = InterfaceID.POH_TREASURE_CHEST_INV;
	public static final int GROUP_IRON_GROUP_ID = InterfaceID.GROUP_IRON;
	public static final int GROUP_STORAGE_INVENTORY_GROUP_ID = InterfaceID.GROUP_STORAGE_INVENTORY;
	public static final int GROUP_STORAGE_GROUP_ID = InterfaceID.GROUP_STORAGE;
	public static final int WILDERNESS_LOOT_CHEST = InterfaceID.WILDERNESS_LOOT_CHEST;
	public static final int TRADE_WINDOW_GROUP_ID = InterfaceID.TRADE;
	public static final int TOA_REWARD_GROUP_ID = InterfaceID.TOA_REWARD;
	/**
	 * toa party interface in the raid lobby
	 */
	public static final int TOA_PARTY_GROUP_ID = InterfaceID.TOA_PARTY;
	/**
	 * toa raid interface in the raid
	 */
	public static final int TOA_RAID_GROUP_ID = InterfaceID.TOA_RAID;
	public static final int ACHIEVEMENT_DIARY_SCROLL_GROUP_ID = InterfaceID.ACHIEVEMENT_DIARY_SCROLL;
	public static final int PRAYER_GROUP_ID = InterfaceID.PRAYER;
	public static final int QUICK_PRAYERS_GROUP_ID = InterfaceID.QUICK_PRAYER;
	public static final int GOTR_GROUP_ID = InterfaceID.GOTR;
	public static final int TROUBLE_BREWING_GROUP_ID = InterfaceID.TROUBLE_BREWING;
	public static final int TROUBLE_BREWING_LOBBY_GROUP_ID = InterfaceID.TROUBLE_BREWING_LOBBY;
	public static final int MORTTON_TEMPLE_GROUP_ID = InterfaceID.MORTTON_TEMPLE;
	public static final int BGR_RANK_DRAUGHTS_GROUP_ID = InterfaceID.BGR_RANK_DRAUGHTS;
	public static final int BGR_RANK_RUNELINK_GROUP_ID = InterfaceID.BGR_RANK_RUNELINK;
	public static final int BGR_RANK_RUNESQUARES_GROUP_ID = InterfaceID.BGR_RANK_RUNESQUARES;
	public static final int BGR_RANK_RUNEVERSI_GROUP_ID = InterfaceID.BGR_RANK_RUNEVERSI;
	public static final int AGILITY_ARENA_HUD_GROUP_ID = InterfaceID.AGILITY_ARENA;
	public static final int GNOMEBALL_SCORE_GROUP_ID = InterfaceID.GNOMEBALL;
	public static final int CLANRANK_POPUP = InterfaceID.CLANRANK_POPUP;
	public static final int SANITY_GROUP_ID = InterfaceID.SANITY;
	public static final int THE_STRANGLER_INFECTION_GROUP_ID = InterfaceID.STRANGLER;


	static class SpellBook
	{
		static final int FILTERED_SPELLS_BOUNDS = 3;
		static final int TOOLTIP = 190;

		// NORMAL SPELLS
		static final int LUMBRIDGE_HOME_TELEPORT = 7;
		static final int WIND_STRIKE = 8;
		static final int CONFUSE = 9;
		static final int ENCHANT_CROSSBOW_BOLT = 10;
		static final int WATER_STRIKE = 11;
		static final int LVL_1_ENCHANT = 12;
		static final int EARTH_STRIKE = 14;
		static final int WEAKEN = 15;
		static final int FIRE_STRIKE = 16;
		static final int BONES_TO_BANANAS = 17;
		static final int WIND_BOLT = 18;
		static final int CURSE = 19;
		static final int BIND = 20;
		static final int LOW_LEVEL_ALCHEMY = 21;
		static final int WATER_BOLT = 22;
		static final int VARROCK_TELEPORT = 23;

		static final int EARTH_BOLT = 25;
		static final int LUMBRIDGE_TELEPORT = 26;
		static final int TELEKINETIC_GRAB = 27;
		static final int FIRE_BOLT = 28;
		static final int FALADOR_TELEPORT = 29;
		static final int CRUMBLE_UNDEAD = 30;
		static final int TELEPORT_TO_HOUSE = 31;
		static final int WIND_BLAST = 32;
		static final int SUPERHEAT_ITEM = 33;
		static final int CAMELOT_TELEPORT = 34;
		static final int WATER_BLAST = 35;
		static final int TELEPORT_TO_KOUREND = 36;

		static final int IBAN_BLAST = 38;
		static final int SNARE = 39;
		static final int MAGIC_DART = 40;
		static final int ARDOUGNE_TELEPORT = 41;
		static final int EARTH_BLAST = 42;
		static final int CIVITAS_TELEPORT = 43;

		static final int HIGH_LEVEL_ALCHEMY = 44;
		static final int CHARGE_WATER_ORB = 45;

		static final int WATCHTOWER_TELEPORT = 47;
		static final int FIRE_BLAST = 48;
		static final int CHARGE_EARTH_ORB = 49;
		static final int BONES_TO_PEACHES = 50;
		static final int SARADOMIN_STRIKE = 51;
		static final int CLAWS_OF_GUTHIX = 52;
		static final int FLAMES_OF_ZAMORAK = 53;
		static final int TROLLHEIM_TELEPORT = 54;
		static final int WIND_WAVE = 55;
		static final int CHARGE_FIRE_ORB = 56;
		static final int TELEPORT_TO_APE_ATOLL = 57;
		static final int WATER_WAVE = 58;
		static final int CHARGE_AIR_ORB = 59;
		static final int VULNERABILITY = 60;

		static final int EARTH_WAVE = 62;
		static final int ENFEEBLE = 63;
		static final int TELEOTHER_LUMBRIDGE = 64;
		static final int FIRE_WAVE = 65;
		static final int ENTANGLE = 66;
		static final int STUN = 67;
		static final int CHARGE = 68;
		static final int WIND_SURGE = 69;
		static final int TELEOTHER_FALADOR = 70;
		static final int WATER_SURGE = 71;
		static final int TELE_BLOCK = 72;
		static final int BOUNTY_TARGET_TELEPORT = 73;

		static final int TELEOTHER_CAMELOT = 75;
		static final int EARTH_SURGE = 76;
		static final int FIRE_SURGE = 78;

		// ANCIENT SPELLS
		static final int ICE_RUSH = 79;
		static final int ICE_BLITZ = 80;
		static final int ICE_BURST = 81;
		static final int ICE_BARRAGE = 82;
		static final int BLOOD_RUSH = 83;
		static final int BLOOD_BLITZ = 84;
		static final int BLOOD_BURST = 85;
		static final int BLOOD_BARRAGE = 86;
		static final int SMOKE_RUSH = 87;
		static final int SMOKE_BLITZ = 88;
		static final int SMOKE_BURST = 89;
		static final int SMOKE_BARRAGE = 90;
		static final int SHADOW_RUSH = 91;
		static final int SHADOW_BLITZ = 92;
		static final int SHADOW_BURST = 93;
		static final int SHADOW_BARRAGE = 94;
		static final int PADDEWWA_TELEPORT = 92;
		static final int SENNTISTEN_TELEPORT = 93;
		static final int KHARYRLL_TELEPORT = 94;
		static final int LASSAR_TELEPORT = 95;
		static final int DAREEYAK_TELEPORT = 96;
		static final int CARRALLANGER_TELEPORT = 97;
		static final int ANNAKARL_TELEPORT = 98;
		static final int GHORROCK_TELEPORT = 99;
		static final int EDGEVILLE_HOME_TELEPORT = 100;

		// LUNAR SPELLS
		static final int LUNAR_HOME_TELEPORT = 101;
		static final int BAKE_PIE = 102;
		static final int CURE_PLANT = 103;
		static final int MONSTER_EXAMINE = 104;
		static final int NPC_CONTACT = 105;
		static final int CURE_OTHER = 106;
		static final int HUMIDIFY = 107;
		static final int MOONCLAN_TELEPORT = 108;
		static final int TELE_GROUP_MOONCLAN = 109;
		static final int CURE_ME = 110;
		static final int HUNTER_KIT = 111;
		static final int WATERBIRTH_TELEPORT = 112;
		static final int TELE_GROUP_WATERBIRTH = 113;
		static final int CURE_GROUP = 114;
		static final int STAT_SPY = 115;
		static final int BARBARIAN_TELEPORT = 116;
		static final int TELE_GROUP_BARBARIAN = 117;
		static final int SUPERGLASS_MAKE = 118;
		static final int TAN_LEATHER = 119;
		static final int KHAZARD_TELEPORT = 120;
		static final int TELE_GROUP_KHAZARD = 121;
		static final int DREAM = 122;
		static final int STRING_JEWELLERY = 123;
		static final int STAT_RESTORE_POT_SHARE = 124;
		static final int MAGIC_IMBUE = 125;
		static final int FERTILE_SOIL = 126;
		static final int BOOST_POTION_SHARE = 127;
		static final int FISHING_GUILD_TELEPORT = 128;
		static final int TELE_GROUP_FISHING_GUILD = 129;
		static final int PLANK_MAKE = 130;
		static final int CATHERBY_TELEPORT = 131;
		static final int TELE_GROUP_CATHERBY = 132;
		static final int RECHARGE_DRAGONSTONE = 133;
		static final int ICE_PLATEAU_TELEPORT = 134;
		static final int TELE_GROUP_ICE_PLATEAU = 135;
		static final int ENERGY_TRANSFER = 136;
		static final int HEAL_OTHER = 137;
		static final int VENGEANCE_OTHER = 138;
		static final int VENGEANCE = 142;
		static final int HEAL_GROUP = 140;
		static final int SPELLBOOK_SWAP = 141;
		static final int GEOMANCY = 999;
		static final int SPIN_FLAX = 143;
		static final int OURANIA_TELEPORT = 144;

		// ARCEUUS SPELLS
		static final int ARCEUUS_HOME_TELEPORT = 145;
		static final int BASIC_REANIMATION = 146;
		static final int ARCEUUS_LIBRARY_TELEPORT = 147;
		static final int ADEPT_REANIMATION = 148;
		static final int EXPERT_REANIMATION = 149;
		static final int MASTER_REANIMATION = 150;
		static final int DRAYNOR_MANOR_TELEPORT = 151;
		static final int MIND_ALTAR_TELEPORT = 153;
		static final int RESPAWN_TELEPORT = 154;
		static final int SALVE_GRAVEYARD_TELEPORT = 155;
		static final int FENKENSTRAINS_CASTLE_TELEPORT = 156;
		static final int WEST_ARDOUGNE_TELEPORT = 157;
		static final int HARMONY_ISLAND_TELEPORT = 158;
		static final int CEMETERY_TELEPORT = 159;
		static final int RESURRECT_CROPS = 160;
		static final int BARROWS_TELEPORT = 161;
		static final int APE_ATOLL_TELEPORT = 162;
		static final int BATTLEFRONT_TELEPORT = 163;
		static final int INFERIOR_DEMONBANE = 164;
		static final int SUPERIOR_DEMONBANE = 165;
		static final int DARK_DEMONBANE = 166;
		static final int MARK_OF_DARKNESS = 167;
		static final int GHOSTLY_GRASP = 168;
		static final int SKELETAL_GRASP = 169;
		static final int UNDEAD_GRASP = 170;
		static final int WARD_OF_ARCEUUS = 171;
		static final int LESSER_CORRUPTION = 172;
		static final int GREATER_CORRUPTION = 173;
		static final int DEMONIC_OFFERING = 174;
		static final int SINISTER_OFFERING = 175;
		static final int DEGRIME = 176;
		static final int SHADOW_VEIL = 177;
		static final int VILE_VIGOUR = 178;
		static final int DARK_LURE = 179;
		static final int DEATH_CHARGE = 180;
		static final int RESURRECT_LESSER_GHOST = 181;
		static final int RESURRECT_LESSER_SKELETON = 182;
		static final int RESURRECT_LESSER_ZOMBIE = 183;
		static final int RESURRECT_SUPERIOR_GHOST = 184;
		static final int RESURRECT_SUPERIOR_SKELETON = 185;
		static final int RESURRECT_SUPERIOR_ZOMBIE = 186;
		static final int RESURRECT_GREATER_GHOST = 187;
		static final int RESURRECT_GREATER_SKELETON = 188;
		static final int RESURRECT_GREATER_ZOMBIE = 189;
	}



	static class Prayer
	{
		static final int THICK_SKIN = 9;
		static final int BURST_OF_STRENGTH = 10;
		static final int CLARITY_OF_THOUGHT = 11;
		static final int SHARP_EYE = 27;
		static final int MYSTIC_WILL = 30;
		static final int ROCK_SKIN = 12;
		static final int SUPERHUMAN_STRENGTH = 13;
		static final int IMPROVED_REFLEXES = 14;
		static final int RAPID_RESTORE = 15;
		static final int RAPID_HEAL = 16;
		static final int PROTECT_ITEM = 17;
		static final int HAWK_EYE = 28;
		static final int MYSTIC_LORE = 31;
		static final int STEEL_SKIN = 18;
		static final int ULTIMATE_STRENGTH = 19;
		static final int INCREDIBLE_REFLEXES = 20;
		static final int PROTECT_FROM_MAGIC = 21;
		static final int PROTECT_FROM_MISSILES = 22;
		static final int PROTECT_FROM_MELEE = 23;
		static final int EAGLE_EYE = 29;
		static final int MYSTIC_MIGHT = 32;
		static final int RETRIBUTION = 24;
		static final int REDEMPTION = 25;
		static final int SMITE = 26;
		static final int PRESERVE = 37;
		static final int CHIVALRY = 34;
		static final int PIETY = 35;
		static final int RIGOUR = 33;
		static final int AUGURY = 36;
	}


	public static class QuickPrayer
	{
		static final int PRAYERS = 4;
	}


}
