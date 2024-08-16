package net.runelite.client.plugins.vorkath;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("avork")
public interface VorkathConfig extends Config {


    @ConfigItem(
            keyName = "autoQuickPrayers",
            name = "Auto Quick Prayers",
            description = "Keeps quick prayers active during the Vorkath fight.",
            position = 0
    )
    default boolean autoQuickPrayers()
    {
        return false;
    }

}