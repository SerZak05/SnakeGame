package fieldObjects

import CELL_SIZE
import IntVector
import java.awt.Color
import java.awt.Graphics

class Wall(position: IntVector) : Consumable(position,
    {GameWindow.game.gameOver()}
) {
    override fun draw(g: Graphics) {
        val indent = 1
        g.color = Color.DARK_GRAY
        g.fillRect(position.x * CELL_SIZE + indent, position.y * CELL_SIZE + indent,
            CELL_SIZE - 2 * indent, CELL_SIZE - 2 * indent)
    }
}