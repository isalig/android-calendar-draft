package io.aiico.playground

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class PlaygroundApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
    }
}
