package com.yj0524.anvilrain

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin

class ShitEvent : Listener {
    private fun getInstance(): Plugin {
        return ShitMain.instance
    }

    private val config = getInstance().config

    private val playerNames = config.getString("player-names").toString()

    @EventHandler
    fun onPlayerJoinEvent(e: PlayerJoinEvent) {
        val p = e.player

        if (!playerNames.contains(p.name)) {
            p.gameMode = GameMode.SPECTATOR
        }
        p.gameMode = p.gameMode
    }
}
