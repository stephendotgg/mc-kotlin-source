package gg.stephen.test

import org.bukkit.inventory.ItemStack

object PageUtil {

    fun getItems(pageIndex: Int, items: MutableList<ItemStack>, spaces: Int): MutableList<ItemStack>  {
        val startIndex = (pageIndex - 1) * spaces
        val endIndex = startIndex + spaces
        return items.subList(startIndex, endIndex.coerceAtMost(items.size))
    }

    fun isPageValid(pageIndex: Int, items: MutableList<ItemStack>, spaces: Int): Boolean {
        if (pageIndex <= 0) return false
        return items.size > (pageIndex - 1) * spaces
    }

}