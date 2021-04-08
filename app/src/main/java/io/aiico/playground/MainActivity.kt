package io.aiico.playground

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: supportFragmentManager.commit {
            add(Window.ID_ANDROID_CONTENT, CalendarFragment.newInstance())
        }
    }
}
