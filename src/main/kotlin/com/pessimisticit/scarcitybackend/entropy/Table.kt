package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.formatting.NumberFormatter.formatDecimal
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.sign

class Table<T>(
    val table: Sequence<ItemGenerator<out T>>
) {
    private val log: Logger = LoggerFactory.getLogger(Table::class.java)

    private val cleaned by lazy {
        table
            .sortedWith(compareBy(
                { it.level.sign * -1 },
                { it.rarity.relativeWeight }
            ))
            .toList()
            .asReversed()
    }

    private val totalRarity: Double by lazy {
        cleaned.sumOf { it.rarity.relativeWeight }
    }

    fun filter(
        predicate: (ItemGenerator<out T>) -> Boolean
    ): Table<T> {
        return Table(table = this.table.filter { predicate(it) })
    }

    private fun selectGenerator(
        randomDoubleFunction: (Double) -> Double
    ): ItemGenerator<out T>? {
        val target = randomDoubleFunction.invoke(totalRarity)
        var accumulator = 0.0
        log.trace(
            "Rolled ${formatDecimal(target)} out of $totalRarity."
        )
        return cleaned
            .dropWhile {
                accumulator += it.rarity.relativeWeight
                accumulator <= target
            }
            .firstOrNull()
    }

    fun select(
        randomDoubleFunction: (Double) -> Double
    ): T? {
        return selectMultiple(1, randomDoubleFunction).firstOrNull()
    }

    fun selectMultiple(count: Int, entropySource: (Double) -> Double): Sequence<T> {
        return (1..count)
            .asSequence()
            .mapNotNull { selectGenerator(entropySource) }
            .distinct()
            .map { it.generator.invoke() }
    }
}

fun <T> Table<T>.filterByItemLevel(min: Double = -1000.0, max: Double = 1000.0): Table<T> =
    this.filter { it.level in min..max }

fun <T> Table<T>.filterByTag(tag: Tag?): Table<T> =
    tag?.let { this.filter { generator -> generator.tags.contains(it) } } ?: this
