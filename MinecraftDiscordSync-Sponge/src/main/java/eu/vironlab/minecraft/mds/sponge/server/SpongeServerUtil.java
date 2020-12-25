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

package eu.vironlab.minecraft.mds.sponge.server;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import eu.vironlab.minecraft.mds.sponge.ChatColor;
import eu.vironlab.minecraft.mds.sponge.SpongeMinecraftDiscordSync;

public class SpongeServerUtil {
	
	public static int getPlayerCount() {
		return SpongeMinecraftDiscordSync.getInstance().getServer().getOnlinePlayers().size();
	}
	
	public static int getMaxPlayerCount() {
		return SpongeMinecraftDiscordSync.getInstance().getServer().getMaxPlayers();
	}
	
	public static String getPlayerList() {
		StringBuilder stringBuilder = new StringBuilder();
		for(Player player : SpongeMinecraftDiscordSync.getInstance().getServer().getOnlinePlayers()) {
			stringBuilder.append("+" + player.getName() + "\n");
		}
		return stringBuilder.length() == 0 ? "" : stringBuilder.toString();	
	}
	
	public static boolean broadcastMessage(String message) {
		for(Player player : SpongeMinecraftDiscordSync.getInstance().getServer().getOnlinePlayers()) {
			player.sendMessage(Text.of(ChatColor.translateAlternateColorCodes('&', message)));
		}
		return true;
	}

}
