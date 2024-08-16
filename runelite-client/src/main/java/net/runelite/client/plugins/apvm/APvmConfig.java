/*
 * Copyright (c) 2020, dutta64 <https://github.com/dutta64>
 * Copyright (c) 2019, kThisIsCvpv <https://github.com/kThisIsCvpv>
 * Copyright (c) 2019, ganom <https://github.com/Ganom>
 * Copyright (c) 2019, kyle <https://github.com/Kyleeld>
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


import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("APvmConfig")
public interface APvmConfig extends Config
{

	@ConfigItem(
			keyName = "eat",
			name = "Auto Eat",
			description = "",
			position = 0
	)
	default boolean eat() { return false; }

	@ConfigItem(
			keyName = "eatID",
			name = "Food Item ID",
			description = "",
			position = 1
	)
	default int eatID() { return 0; }

	@ConfigItem(
			keyName = "hp",
			name = "HP to Eat",
			description = "",
			position = 2
	)
	default int hp() { return 70; }

	@ConfigItem(
			keyName = "pray",
			name = "Auto Restore",
			description = "",
			position = 3
	)
	default boolean pray() { return false; }

	@ConfigItem(
			keyName = "prayer",
			name = "Prayer to Restore",
			description = "",
			position = 4
	)
	default int prayer() { return 40; }

	@ConfigItem(
			keyName = "Vardorvis",
			name = "Auto Vardorvis Prayers",
			description = "",
			position = 10
	)
	default boolean Vardorvis() { return false; }

}
