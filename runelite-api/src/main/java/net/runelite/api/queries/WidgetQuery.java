/*
 * Copyright (c) 2017, Devin French <https://github.com/devinfrench>
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
package net.runelite.api.queries;

import net.runelite.api.Client;
import net.runelite.api.Query;
import net.runelite.api.QueryResults;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class WidgetQuery extends Query<Widget, WidgetQuery, QueryResults<Widget>>
{
	private static final int ITEM_EMPTY = 6512;

	@Override
	public QueryResults<Widget> result(Client client)
	{
		Collection<Widget> widgetItems = getWidgets(client);
		return new QueryResults<>(widgetItems.stream()
			.filter(Objects::nonNull)
			.filter(predicate)
			.collect(Collectors.toList()));
	}

	private Collection<Widget> getWidgets(Client client)
	{
		Collection<Widget> widgetItems = new ArrayList<>();
		Widget bank = client.getWidget(WidgetInfo.LOGIN_CLICK_TO_PLAY_SCREEN);
		if (bank != null && !bank.isHidden())
		{
			Widget[] children = bank.getChildren();
			for (int i = 0; i < children.length; i++)
			{
				Widget child = children[i];
				if (child.getText().contains("Login") || child.getText().equals("Login")) {
					// set bounds to same size as default inventory
					//Rectangle bounds = child.getBounds();
					//bounds.setBounds(bounds.x - 1, bounds.y - 1, 32, 32);
					// Index is set to 0 because the widget's index does not correlate to the order in the bank
					widgetItems.add(child);
				}
			}
		}
		return widgetItems;
	}
}
