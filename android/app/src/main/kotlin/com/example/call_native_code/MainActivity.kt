package com.example.call_native_code

import android.content.Intent
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
                Intent(this,MusicService:: class.java).also { intent ->  
                    startService(intent)
                }
            } else if (call.method == "stopMusic") {
            Intent(this,MusicService:: class.java).also { intent ->
                stopService(intent)
            }
        }else {
                result.notImplemented()
            }
        };
        super.configureFlutterEngine(flutterEngine)
    }
}
