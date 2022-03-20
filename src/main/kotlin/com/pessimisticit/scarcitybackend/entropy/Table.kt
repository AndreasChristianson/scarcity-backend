package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.formatting.NumberFormatter.formatDecimal
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Table<T>(
    val table: Sequence<ItemGenerator<out T>>
) {
    private val log: Logger = LoggerFactory.getLogger(Table::class.java)

    private val cleaned by lazy {
        table
            .distinct()
            .sortedBy { it.rarity }
    }

    private val count by lazy {
        cleaned.count()
    }
    private val totalRarity: Double by lazy {
        cleaned
            .map { it.rarity.relativeWeight }
            .sum()
    }

    fun filter(
        predicate: (ItemGenerator<out T>) -> Boolean
    ): Table<T> {
        return Table(table = this.table.filter { predicate(it) })
    }

    fun select(
        randomDoubleFunction: (Double) -> Double
    ): T? {
        val target = randomDoubleFunction.invoke(totalRarity)
        var accumulator = 0.0
        val selected = cleaned
            .dropWhile {
                accumulator += it.rarity.relativeWeight
                accumulator <= target
            }
            .firstOrNull() ?: return null
        val generated = selected
            .generator
            .invoke()
        log.debug(
            "Rolled ${formatDecimal(target)} out of $totalRarity." +
                    " Generated a ${generated!!::class.simpleName} from $count options."
        )
        return generated
    }
}

fun <T> Table<T>.filterByItemLevel(min: Double, max: Double): Table<T> =
    this.filter { it.level in min..max }

fun <T> Table<T>.filterByTag(tag: Tag?): Table<T> =
    tag?.let { this.filter { generator -> generator.tags.contains(it) } } ?: this
