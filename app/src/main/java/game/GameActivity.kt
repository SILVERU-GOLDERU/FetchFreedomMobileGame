package com.innoveworkshop.gametest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.innoveworkshop.gametest.assets.DroppingRectangle
import com.innoveworkshop.gametest.assets.Dogs
import com.innoveworkshop.gametest.assets.Husky
import com.innoveworkshop.gametest.engine.GameObject
import com.innoveworkshop.gametest.engine.GameSurface
import com.innoveworkshop.gametest.engine.Rectangle
import com.innoveworkshop.gametest.engine.Vector
import pt.iade.games.fetchfreedom.BookActivity
import pt.iade.games.fetchfreedom.R

class GameActivity : AppCompatActivity() {
    protected var gameSurface: GameSurface? = null
    protected var dropButton: Button? = null
    protected var leftButton: Button? = null
    protected var rightButton: Button? = null
    protected var pauseButton: Button? = null
    protected var startButton: Button? = null

    var isPaused = true
    private var isGameStarted = false
    private var isGameOver = false

    private var dropCount = 0

    protected var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameSurface = findViewById<View>(R.id.gameSurface) as GameSurface
        gameSurface?.initializeWithMainActivity(this)
        game = Game()
        gameSurface!!.setRootGameObject(game)

        setupControls()

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            navigateToBookActivity() // Call the navigation method
        }

        gameSurface?.viewTreeObserver?.addOnWindowFocusChangeListener { hasFocus ->
            if (!hasFocus) {
                // App has lost focus (e.g., multitasking mode)
                if (!isPaused) { // Only pause if not already paused
                    isPaused = true
//                    pauseButton?.text = if (isGameStarted){
//                        "Resume"
//                    } else{
//                        "Start"
//                    }
                    gameSurface?.pauseStopwatch()
                }
            }
        }
    }
    private fun navigateToBookActivity() {
        startActivity(Intent(this, BookActivity::class.java))
        finish()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupControls() {

        pauseButton = findViewById<View>(R.id.pause_button) as Button
        startButton = findViewById<View>(R.id.start_button) as Button
        dropButton = findViewById<View>(R.id.drop_button) as Button
        leftButton = findViewById<View>(R.id.left_button) as Button
        rightButton = findViewById<View>(R.id.right_button) as Button


        //pauseButton!!.text = "Start"
        pauseButton!!.setOnClickListener {
            if (isGameStarted) {
                // Subsequent clicks toggle pause/resume
                isPaused = !isPaused
                //val buttonText = if (isPaused) "Resume" else "Pause"
                //pauseButton!!.text = buttonText
                if (isPaused) {
                    gameSurface?.pauseStopwatch()
                } else {
                    gameSurface?.startStopwatch()
                }
            }
        }

        //startButton!!.text = "Start"
        startButton!!.setOnClickListener {

            if (!isGameStarted) {

                isGameStarted = true
                isPaused = false
                isGameOver = false
                dropCount = 0
                game!!.startGame()
                gameSurface?.startStopwatch()

            } else if (isGameOver) {
                resetGame()
            }

        }

        dropButton!!.setOnClickListener {
            if (!isPaused && !isGameOver) {
                dropCount++
                println("Drop button pressed $dropCount times")
                game!!.spawnNewFallingItem()

                if (dropCount >= 50000000) {
                    game!!.endGame()
                }
            }
        }


        leftButton!!.setOnTouchListener { _, event ->
            if (!isGameOver) {
                when (event.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        game!!.moveLeft = true
                        println("Move Left: true")
                    }

                    android.view.MotionEvent.ACTION_UP -> {
                        game!!.moveLeft = false
                        println("Move Left: false")
                    }
                }
            }
            true
        }

        rightButton!!.setOnTouchListener { _, event ->
            if (!isGameOver) {
                when (event.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        game!!.moveRight = true
                        println("Move Right: true")
                    }

                    android.view.MotionEvent.ACTION_UP -> {
                        game!!.moveRight = false
                        println("Move Right: false")
                    }

                }
            }
            true
        }

    }


    private fun resetGame() {
        isPaused = true
        isGameStarted = false
        isGameOver = false
        dropCount = 0

        // Clear the current game
        gameSurface?.pauseStopwatch()
        gameSurface?.resetStopwatch()
        game = Game()
        gameSurface?.setRootGameObject(game)
        gameSurface?.invalidate()

        println("Game reset. Ready to start again.")
    }

    inner class Game : GameObject() {
        private var surface: GameSurface? = null
        var rectangle: Rectangle? = null
        var floor: Rectangle? = null
        private val fallingItems = mutableListOf<DroppingRectangle>()
        private val walkingDogs = mutableListOf<Dogs>()
        private val walkingHusky = mutableListOf<Husky>()

        var moveLeft = false
        var moveRight = false
        private var velocity = 0f // Current velocity
        private val acceleration = 2f // Acceleration
        private val maxSpeed = 15f // Maximum speed
        private val deceleration = 1.5f // Deceleration


        private var dogselapsedTime = 0f // Tracks elapsed time for spawning dogs
        private val dogsspawnInterval = 0.4f // Spawn dogs every 1 second

        private var huskyelapsedTime = 0f // Tracks elapsed time for spawning Husky
        private val huskyspawnInterval = 2f // Spawn Husky every 1 second

        override fun onStart(surface: GameSurface?) {
            super.onStart(surface)
            this.surface = surface

            if (rectangle == null){
                rectangle = Rectangle(
                    Vector((surface!!.width / 2).toFloat(), (surface.height / 7).toFloat()),
                    25f,70f,
                    Color.BLACK
                )
                surface.addGameObject(rectangle!!)
            }

            if (floor == null){
                floor = Rectangle(
                    Vector((surface!!.width / 2).toFloat(), (surface.height / 7).toFloat()+30f),
                    6000f,20f,
                    Color.BLACK
                )
                surface.addGameObject(floor!!)
            }


        }
        
        fun spawnNewFallingItem() {
            if (rectangle == null || surface == null) return

            // Create a new falling rectangle at the rectangle's position
            val item = DroppingRectangle(
                Vector(rectangle!!.position.x, rectangle!!.position.y+30f), // Use the existing rectangle instance
                70f,
                30f,
                5f,
                Color.BLACK,
                this@GameActivity
            )

            fallingItems.add(item)
            surface!!.addGameObject(item) // Ensure surface is non-null
        }

        fun spawnNewDogs() {

            val screenWidth = surface!!.width
            val screenHeight = surface!!.height
            val spawnX = if (Math.random() < 0.5) 0f else (screenWidth - 70f) //adjust Dogs width fatness

            // Create a new Dogs
            val item = Dogs(
                Vector(spawnX, (screenHeight - 25f)), //adjust Dogs height fatness
                25f,
                70f,
                2f,
                Color.BLACK,
                this@GameActivity
            )

            walkingDogs.add(item)
            surface!!.addGameObject(item) // Ensure surface is non-null
        }


        fun spawnNewHusky() {

            val screenWidth = surface!!.width
            val screenHeight = surface!!.height
            val spawnX = if (Math.random() < 0.5) 0f else (screenWidth - 70f)


            val item = Husky(
                Vector(spawnX, (screenHeight - 25f)),
                25f,
                70f,
                2f,
                Color.parseColor("#CCE5FF"),
                this@GameActivity
            )

            walkingHusky.add(item)
            surface!!.addGameObject(item)
        }


        fun startGame() {
            isPaused = false
            dogselapsedTime = 0f
            huskyelapsedTime = 0f

        }
        fun endGame() {
            isPaused = true
            isGameStarted = false
            isGameOver = true
            println("Game Over! You pressed the drop button 5 times.")
        }

        override fun onFixedUpdate() {

            if (isPaused || isGameOver) return

            super.onFixedUpdate()

            //println("Velocity: $velocity, rectangle Position X: ${rectangle!!.position.x}")

            if (moveLeft && !moveRight) {

                velocity -= acceleration

                if (velocity < -maxSpeed) {
                    velocity = -maxSpeed
                }

            } else if (moveRight && !moveLeft) {

                velocity += acceleration

                if (velocity > maxSpeed) {

                    velocity = maxSpeed
                }

            } else {
                // Deceleration when moving right
                if (velocity > 0) {

                    velocity -= deceleration

                    if (velocity < 0){

                        velocity = 0f
                    }
                // Deceleration moving left with negative velocity
                } else if (velocity < 0) {

                    velocity += deceleration

                    if (velocity > 0) {

                        velocity = 0f
                    }
                }
            }

            // Update rectangle position
            val newPositionX = (rectangle!!.position.x + velocity).coerceIn(0f, surface!!.width - rectangle!!.width)

            // Check if the rectangle is at a boundary and adjust velocity
            if (newPositionX == 0f && velocity < 0) {
                velocity = 0f // Stop movement to the left
            } else if (newPositionX == surface!!.width - rectangle!!.width && velocity > 0) {
                velocity = 0f // Stop movement to the right
            }

            // Update the rectangle's position
            rectangle!!.position.x = newPositionX

            //brick
            val brickiterator = fallingItems.iterator()
            while (brickiterator.hasNext()) {
                val item = brickiterator.next()

                // Let DroppingRectangle handle its own physics
                item.onFixedUpdate()

                // Check for ground to destroy
                if (item.isOutOfBounds(surface!!.height.toFloat())) {
                    item.destroy()
                    brickiterator.remove()
                }
            }

            dogselapsedTime += 0.016f // 60 FPS update rate
            if (dogselapsedTime >= dogsspawnInterval) {
                spawnNewDogs()
                dogselapsedTime = 0f
            }

            huskyelapsedTime += 0.016f // 60 FPS update rate
            if (huskyelapsedTime >= huskyspawnInterval) {
                spawnNewHusky()
                huskyelapsedTime = 0f
            }

            //dogs spawn
            val dogsIterator = walkingDogs.iterator()
            while (dogsIterator.hasNext()) {
                val dogs = dogsIterator.next()

                // dogs file handles their own physics
                dogs.onFixedUpdate()

                // dogs removal out of bounds
                if (dogs.isOutOfBounds(surface!!.height.toFloat())) {
                    dogs.destroy()
                    dogsIterator.remove()
                }
            }

            //Husky spawn
            val huskyIterator = walkingHusky.iterator()
            while (huskyIterator.hasNext()) {
                val husky = huskyIterator.next()


                husky.onFixedUpdate()

                if (husky.isOutOfBounds(surface!!.height.toFloat())) {
                    husky.destroy()
                    huskyIterator.remove()
                }
            }

        }
    }
}
