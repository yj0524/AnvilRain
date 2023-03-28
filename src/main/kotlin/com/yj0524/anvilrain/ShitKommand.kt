package com.yj0524.anvilrain

import io.github.monun.kommand.KommandSource
import io.github.monun.kommand.StringType
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.*

object ShitKommand {
    private fun getInstance(): Plugin {
        return ShitMain.instance
    }

    private val config = getInstance().config

    private val coolHashMap = HashMap<UUID, Long>()

    private var UUID.cooltime: Long
        get() {
            return coolHashMap[this] ?: 0
        }
        set(value) {
            coolHashMap[this] = value
        }

    private fun summonShit(player: Player) {
        val loc = player.location

        if (System.currentTimeMillis() - player.uniqueId.cooltime < 60000) {
            player.sendMessage(text("이 명령어는 현재 쿨타임에 있습니다.").color(TextColor.color(0xFF0000)))
        } else {
            loc.block.type = Material.ANVIL
            player.sendMessage(text("모루를 소환하셨습니다!"))
            player.uniqueId.cooltime = System.currentTimeMillis()
        }
    }

    fun shitKommand() {
        getInstance().kommand {
            register("모루") {
                requires { playerOrNull != null }
                executes {
                    summonShit(player)
                }
            }
            register("anvil") {
                requires { playerOrNull != null }
                executes {
                    summonShit(player)
                }
                then("player") {
                    then("playerName" to string(StringType.GREEDY_PHRASE)) {
                        requires { playerOrNull != null && isOp }
                        executes {
                            player(it["playerName"])
                        }
                    }
                }
            }
        }
    }

    private fun KommandSource.player(playerNames: String) {
        config.set("player-names", playerNames)
        getInstance().saveConfig()
        feedback(text("Current Player settings has been changed to: \"$playerNames\"."))
    }
}
