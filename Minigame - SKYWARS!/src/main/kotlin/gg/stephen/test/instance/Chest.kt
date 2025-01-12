package gg.stephen.test.instance

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

data class Chest(private val tier: ChestTier, val location: Location) {

    enum class ChestTier(val items: List<Pair<ItemStack, Double>>) {
        TIER_1(
            listOf(
                ItemStack(Material.IRON_SWORD) to 0.3,
                ItemStack(Material.IRON_CHESTPLATE) to 0.7,
                ItemStack(Material.ENDER_PEARL) to 0.8
            )
        ),
        TIER_2(
            listOf(
                ItemStack(Material.STONE_SWORD) to 0.3,
                ItemStack(Material.ARROW, 16) to 0.7,
                ItemStack(Material.GOLDEN_APPLE, 2) to 0.6
            )
        ),
        TIER_3(
            listOf(
                ItemStack(Material.DIRT, 32) to 0.9,
                ItemStack(Material.ARROW, 16) to 0.4,
                ItemStack(Material.BOW) to 0.8
            )
        )
    }

    fun populate() {
        val block = location.block
        if (block.type != Material.CHEST) {
            block.type = Material.CHEST
        }

        val chest = block.state as org.bukkit.block.Chest
        val inventory = chest.inventory
        inventory.clear()

        tier.items.forEach { (item, chance) ->
            if (Random.nextDouble() <= chance) {
                val slot = Random.nextInt(inventory.size)
                if (inventory.getItem(slot) == null) {
                    inventory.setItem(slot, item)
                }
            }
        }
    }

}
