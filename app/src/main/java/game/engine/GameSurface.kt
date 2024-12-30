package com.innoveworkshop.gametest.engine

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.innoveworkshop.gametest.GameActivity
import com.innoveworkshop.gametest.assets.Stopwatch
import com.innoveworkshop.gametest.assets.DroppingRectangle
import com.innoveworkshop.gametest.assets.Dogs
import com.innoveworkshop.gametest.assets.Husky
import java.util.Timer
import java.util.TimerTask

class GameSurface @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    private val holder: SurfaceHolder
    private var timer: Timer? = null
    private var root: GameObject? = null

    // Create the GameObject list.
    private val gameObjects = ArrayList<GameObject>()

    private val stopwatch = Stopwatch()
    private val paint = Paint().apply {
        textSize = 50f
        isAntiAlias = true
        color = Color.WHITE
    }
    private var gameActivity: GameActivity? = null

    private var destroyedDogsCount = 0
    private var destroyedHuskyCount = 0

    fun initializeWithMainActivity(activity: GameActivity) {
        this.gameActivity = activity
    }

    init {
        // Ensure we are on top of everything.
        setZOrderOnTop(true)

        // Set up the SurfaceHolder event handler.
        holder = getHolder()
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // Ensure we get the onDraw events.
                setWillNotDraw(false)

                // Start up the root object.
                root!!.onStart(this@GameSurface)


                if (gameActivity?.isPaused == false) {
                    stopwatch.start()
                }

                // Set up the fixed update timer.
                timer = Timer()
                timer!!.scheduleAtFixedRate(FixedUpdateTimer(), 0, (1000 / 30).toLong())
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                stopwatch.pause()
                timer?.cancel()
                timer = null
            }
        })
    }

    fun setRootGameObject(root: GameObject?) {
        this.root = root
    }

    fun addGameObject(gameObject: GameObject) {
        gameObjects.add(gameObject)
        gameObject.onStart(this)
    }

    fun removeGameObject(gameObject: GameObject): Boolean {
        return gameObjects.remove(gameObject)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.parseColor("#8bac0f"))

        root!!.onDraw(canvas)
        for (gameObject in gameObjects) {
            gameObject.onDraw(canvas)
        }

        // Draw stopwatch time
        val timeText = stopwatch.getFormattedTime()
        val textWidth = paint.measureText(timeText) // Measure the width of the text
        val x = (width - textWidth) / 4f
        val y = height / 15f
        canvas.drawText(timeText, x, y, paint)

        val dogsdestroyedText = "Dogs Boned: $destroyedDogsCount"
        val dogsdestroyedTextWidth = paint.measureText(dogsdestroyedText)
        canvas.drawText(dogsdestroyedText, width - dogsdestroyedTextWidth - 20f, height / 15f, paint)

        val huskydestroyedText = "Husky Boned: $destroyedHuskyCount"
        val huskydestroyedTextWidth = paint.measureText(huskydestroyedText)
        canvas.drawText(huskydestroyedText, width - huskydestroyedTextWidth - 20f, height / 10f, paint)
    }

    fun incrementDestroyedDogs() {
        destroyedDogsCount++
    }
    fun incrementDestroyedHusky() {
        destroyedHuskyCount++
    }

    fun startStopwatch() {
        stopwatch.start()
    }

    fun pauseStopwatch() {
        stopwatch.pause()
    }

    fun resetStopwatch() {
        stopwatch.reset()
    }


    internal inner class FixedUpdateTimer : TimerTask() {
        override fun run() {
            for (gameObject in gameObjects) {
                gameObject.onFixedUpdate()
            }
            checkCollisions()  ///


            root!!.onFixedUpdate()
            invalidate()
        }
    }


    private fun checkCollisions() {
        val droppingRectangles = gameObjects.filterIsInstance<DroppingRectangle>()
        val dogs = gameObjects.filterIsInstance<Dogs>()

        for (rectangle in droppingRectangles) {
            for (dogs in dogs) {
                if (rectangle.collidesWith(dogs)) {
                    rectangle.onCollision(dogs)
                    dogs.onCollision(rectangle)

                    incrementDestroyedDogs()
                }
            }
        }

        val husky = gameObjects.filterIsInstance<Husky>()

        for (rectangle in droppingRectangles) {
            for (husky in husky) {
                if (rectangle.collidesWith(husky)) {
                    rectangle.onCollision(husky)
                    husky.onCollision(rectangle)

                    incrementDestroyedHusky()
                }
            }
        }
    }
}
