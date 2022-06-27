package fieldObjects

import CELL_SIZE
import Game
import IntVector
import java.awt.Color
import java.awt.Graphics

abstract class Consumable(val position : IntVector, private val action : (g : Game) -> Unit) {
    fun consume(g : Game) = action(g)
    abstract fun draw(g : Graphics)
    override operator fun equals(other: Any?): Boolean =
        if (other is Consumable)
            position == other.position
        else false
    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + action.hashCode()
        return result
    }
}

class Food(position: IntVector) : Consumable(position,
    {g : Game -> g.snake.body.add(g.snake.body.last()) }
) {
    override fun draw(g: Graphics) {
        val indent = 1
        g.color = Color.GREEN
        g.fillRect(position.x * CELL_SIZE + indent, position.y * CELL_SIZE + indent,
            CELL_SIZE - 2 * indent, CELL_SIZE - 2 * indent)
    }
}

class Wall(position: IntVector) : Consumable(position,
    {g -> g.gameOver()}
) {
    override fun draw(g: Graphics) {
        val indent = 1
        g.color = Color.DARK_GRAY
        g.fillRect(position.x * CELL_SIZE + indent, position.y * CELL_SIZE + indent,
            CELL_SIZE - 2 * indent, CELL_SIZE - 2 * indent)
    }
}