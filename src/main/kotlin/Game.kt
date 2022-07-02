import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.SwingUtilities
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Game : JPanel() {
    val WINDOW_WIDTH = 600
    val WINDOW_HEIGHT = 600

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
    }

    private var currScene : Scene? = null

    fun changeScene(scene : Scene) {
        GameWindow.addKeyListener(scene.keyListener)
        GameWindow.addMouseListener(scene.mouseListener)
        currScene = scene
    }

    private var accumulatedTime = 0.0
    private val tickTime = 0.5 // in seconds
    fun update(timePassed : Double) {
        accumulatedTime += timePassed
        if (accumulatedTime < tickTime) return
        accumulatedTime -= tickTime

        if (isRunning) {
            currScene?.update()
        }
    }

    fun gameOver() {
        isRunning = false
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        currScene?.draw(g)
    }

    private var isRunning = true
    private var lastFrameLength = 0.0

    @OptIn(ExperimentalTime::class)
    fun loop() {
        val duration = measureTime {
            SwingUtilities.invokeLater {
                update(lastFrameLength)
                repaint()
            }
            Thread.sleep(1L)
        }
        lastFrameLength = duration.toDouble(DurationUnit.SECONDS)
    }
}