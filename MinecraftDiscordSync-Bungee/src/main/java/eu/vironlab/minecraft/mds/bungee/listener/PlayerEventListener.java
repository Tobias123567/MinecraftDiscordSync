/**
 *   Copyright © 2020 | vironlab.eu | All Rights Reserved.
 * 
 *      ___    _______                        ______         ______  
 *      __ |  / /___(_)______________ _______ ___  / ______ ____  /_ 
 *      __ | / / __  / __  ___/_  __ \__  __ \__  /  _  __ `/__  __ \
 *      __ |/ /  _  /  _  /    / /_/ /_  / / /_  /___/ /_/ / _  /_/ /
 *      _____/   /_/   /_/     \____/ /_/ /_/ /_____/\__,_/  /_.___/ 
 *                                                             
 *    ____  _______     _______ _     ___  ____  __  __ _____ _   _ _____ 
 *   |  _ \| ____\ \   / / ____| |   / _ \|  _ \|  \/  | ____| \ | |_   _|
 *   | | | |  _|  \ \ / /|  _| | |  | | | | |_) | |\/| |  _| |  \| | | |  
 *   | |_| | |___  \ V / | |___| |__| |_| |  __/| |  | | |___| |\  | | |  
 *   |____/|_____|  \_/  |_____|_____\___/|_|   |_|  |_|_____|_| \_| |_|  
 * 
 *                                                         
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *   Contact:
 * 
 *     Discordserver:   https://discord.gg/wvcX92VyEH
 *     Website:         https://vironlab.eu/ 
 *     Mail:            contact@vironlab.eu
 *   
 */

package eu.vironlab.minecraft.mds.bungee.listener;

import java.awt.Color;

import eu.vironlab.minecraft.mds.bungee.BungeeMinecraftDiscordSync;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerEventListener implements Listener {
	
	@EventHandler
	public void onEvent(ServerDisconnectEvent event) {
		if(event == null) return;
		if(!BungeeMinecraftDiscordSync.getInstance().getConfig().getBoolean("plugin.enable_playerevents", false)) return;
		
		TextChannel chatChannel = null;
		try {
			chatChannel = getChannel();
		} catch(Exception e) {}
		if(chatChannel == null) return;
		
		boolean useEmbed = BungeeMinecraftDiscordSync.getInstance().getConfig().getBoolean("format.use_embeds", false);
		String messageFormat = BungeeMinecraftDiscordSync.getInstance().getConfig().getString("format.event_playerjoin", "%playername% Joined the Minecraft Server");
		String pluginPrefix = BungeeMinecraftDiscordSync.getInstance().getConfig().getString("plugin.prefix", "MinecraftDiscordSync");
		String message = messageFormat.replace("%pluginprefix%", pluginPrefix)
				.replace("%playername%", ChatColor.stripColor(event.getPlayer().getDisplayName()));
		
		if(!useEmbed) {
			chatChannel.sendMessage(message).queue();
		} else {
			EmbedBuilder embedBuilder = new EmbedBuilder();
			embedBuilder.setColor(getColor("player_join", "#00ff00"));
			embedBuilder.setDescription(message);
			chatChannel.sendMessage(embedBuilder.build()).queue();;
		}
		
		
	}
	
	@EventHandler
	public void onEvent(ServerConnectEvent event) {
		if(event == null) return;
		if(!BungeeMinecraftDiscordSync.getInstance().getConfig().getBoolean("plugin.enable_playerevents", false)) return;
		
		TextChannel chatChannel = null;
		try {
			chatChannel = getChannel();
		} catch(Exception e) {}
		if(chatChannel == null) return;
		
		boolean useEmbed = BungeeMinecraftDiscordSync.getInstance().getConfig().getBoolean("format.use_embeds", false);
		String messageFormat = BungeeMinecraftDiscordSync.getInstance().getConfig().getString("format.event_playerquit", "%playername% Left the Minecraft Server");
		String pluginPrefix = BungeeMinecraftDiscordSync.getInstance().getConfig().getString("plugin.prefix", "MinecraftDiscordSync");
		String message = messageFormat.replace("%pluginprefix%", pluginPrefix)
				.replace("%playername%", ChatColor.stripColor(event.getPlayer().getDisplayName()));
	
		if(!useEmbed) {
			chatChannel.sendMessage(message).queue();
		} else {
			EmbedBuilder embedBuilder = new EmbedBuilder();
			embedBuilder.setColor(getColor("player_left", "#ff0000"));
			embedBuilder.setDescription(message);
			chatChannel.sendMessage(embedBuilder.build()).queue();;
		}
	
	}
	
	
	private TextChannel getChannel() {
		return BungeeMinecraftDiscordSync.getInstance().getPluginAPI().getDiscordBot().getJDA()
				.getGuildById(BungeeMinecraftDiscordSync.getInstance().getConfig().getString("guild.id"))
				.getTextChannelById(BungeeMinecraftDiscordSync.getInstance().getConfig().getString("guild.chatchannel_id"));
	}
	
	private Color getColor(String colFor, String defaultColor) {
		String hexString = BungeeMinecraftDiscordSync.getInstance().getConfig().getString("embed.colors." + colFor, defaultColor);
		if(!hexString.startsWith("#")) hexString = "#" + hexString;
		return hex2Rgb(hexString);
	}
	
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
}
