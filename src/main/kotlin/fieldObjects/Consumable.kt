package fieldObjects

import FieldScene
import Game
import IntVector
import java.awt.Graphics

abstract class Consumable(val position : IntVector, val action: (scene : FieldScene) -> Unit) {
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

