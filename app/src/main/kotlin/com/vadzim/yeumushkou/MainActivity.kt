package com.vadzim.yeumushkou

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.vadzim.yeumushkou.navigation.FragmentManagerHolder
import com.vadzim.yeumushkou.navigation.NavControllerManager
import javax.inject.Inject

internal class MainActivity : AppCompatActivity(R.layout.activity_main), FragmentManagerHolder {

    @Inject
    lateinit var navControllerManager: NavControllerManager

    override val fragmentManager: FragmentManager
        get() = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findAppComponent().inject(this)
        lifecycle.addObserver(navControllerManager)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}