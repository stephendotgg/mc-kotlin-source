package gg.stephen.test

import io.papermc.paper.enchantments.EnchantmentRarity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.entity.EntityCategory
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class AutoSmeltingEnchantment : Enchantment(KEY), Listener {

    companion object {
        val KEY = NamespacedKey.minecraft("auto_smelting")
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!e.isDropItems) return
        if (!e.player.inventory.itemInMainHand.itemMeta.hasEnchant(this)) return

        val material = when (e.block.type) {
            Material.GOLD_ORE -> Material.GOLD_INGOT
            Material.IRON_ORE -> Material.IRON_INGOT
            else -> return
        }

        e.isDropItems = false
        e.block.world.dropItemNaturally(e.block.location, ItemStack(material))
    }

    override fun translationKey(): String {
        return "auto_smelting"
    }

    override fun getName(): String {
        return "Auto Smelting"
    }

    override fun getMaxLevel(): Int {
        return 10
    }

    override fun getStartLevel(): Int {
        return 1
    }

    override fun getItemTarget(): EnchantmentTarget {
        return EnchantmentTarget.TOOL
    }

    override fun isTreasure(): Boolean {
        return false
    }

    override fun isCursed(): Boolean {
        return false
    }

    override fun conflictsWith(other: Enchantment): Boolean {
        return false
    }

    override fun canEnchantItem(item: ItemStack): Boolean {
        return false
    }

    override fun displayName(level: Int): Component {
        return Component.text("Auto Smelting $level").color(NamedTextColor.GRAY)
    }

    override fun isTradeable(): Boolean {
        return false
    }

    override fun isDiscoverable(): Boolean {
        return true
    }

    override fun getRarity(): EnchantmentRarity {
        return EnchantmentRarity.COMMON
    }

    override fun getDamageIncrease(level: Int, entityCategory: EntityCategory): Float {
        return 0F
    }

    override fun getActiveSlots(): MutableSet<EquipmentSlot> {
        return mutableSetOf(EquipmentSlot.HAND, EquipmentSlot.OFF_HAND)
    }

}