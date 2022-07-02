import java.awt.Graphics
import java.awt.event.KeyAdapter
import java.awt.event.KeyListener
import java.awt.event.MouseAdapter

//
interface Scene {
    fun update()
    fun draw(g : Graphics)

    val keyListener : KeyListener?
    val mouseListener : MouseAdapter?
}