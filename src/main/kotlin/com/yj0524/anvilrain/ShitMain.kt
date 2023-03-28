package com.yj0524.anvilrain

import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.GameMode
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.plugin.java.JavaPlugin

class ShitMain : JavaPlugin() {

    companion object {
        lateinit var instance: ShitMain
            private set
    }

    override fun onEnable() {
        saveDefaultConfig()
        val dustOptions = DustOptions(Color.AQUA, 1.0F)
        instance = this
        server.pluginManager.registerEvents(ShitEvent(), this)
        server.scheduler.scheduleSyncRepeatingTask(this, {
           Bukkit.getOnlinePlayers().forEach {
               if (it.gameMode == GameMode.SPECTATOR) {
                   it.world.spawnParticle(Particle.REDSTONE, it.location.add(0.0, 2.0, 0.0), 2, dustOptions)
               }
           }
        }, 0, 0)
        ShitKommand.shitKommand()
    }
}
