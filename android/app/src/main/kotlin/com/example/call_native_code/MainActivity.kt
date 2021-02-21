package com.example.call_native_code

import android.media.MediaPlayer
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import android.provider.Settings

class MainActivity : FlutterActivity() {
    private val channel = "CallNativeCode/Kotlin";
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channel).setMethodCallHandler { call, result ->
            if (call.method == "playMusic") {
                val player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI) as MediaPlayer
                player.setLooping(true)
                player.start()
            } else {
                result.notImplemented()
            }
        };
        super.configureFlutterEngine(flutterEngine)
    }
}
