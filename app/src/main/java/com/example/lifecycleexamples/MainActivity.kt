package com.example.lifecycleexamples

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lifecycleexamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // var count = 0

    val viewModel: MyViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Log.v("TAG", "onCreate")

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.count.observe(this) {
            Log.v("TAG", "COUNT changed");
        }

        viewModel.text1.observe(this) {
            Log.v("TAG", "TEXT changed: ${viewModel.text1.value}");
        }

        viewModel.text1.postValue("Hello World")

        lifecycle.addObserver(MyObserver())

        binding.buttonOpen.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        // count = savedInstanceState?.getInt("COUNT", 0)?:0
        // val textCount = findViewById<TextView>(R.id.text_count)
        // textCount.text = "$count"
        // binding.textCount.text = viewModel.count.toString()

        binding.buttonCount.setOnClickListener {
            // textCount.text = "${count++}"
            viewModel.doCount()
            // binding.textCount.text = viewModel.count.toString()
        }

        val callback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Quit App")
                    .setMessage("Are you sure you want to quit?")
                    .setPositiveButton("Yes", { _, _ -> finish()})
                    .setNegativeButton("No", null)
                    .show()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onStart() {
        super.onStart()
        Log.v("TAG", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("TAG", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("TAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("TAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("TAG", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("TAG", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // outState.putInt("COUNT", count)
        super.onSaveInstanceState(outState)
        Log.v("TAG", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.v("TAG", "onRestoreInstanceState")
        // count = savedInstanceState.getInt("COUNT", 0)
    }
}