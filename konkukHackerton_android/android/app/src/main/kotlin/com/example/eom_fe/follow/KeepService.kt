package com.example.eom_fe.follow

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import com.example.eom_fe.activities.BackgroundActivity

class KeepService: Service() {
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): KeepService = this@KeepService
    }


    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.d("eyesonme-KS", "KeepService - onTaskRemoved called")
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)

        val restartServicePendingIntent = PendingIntent.getService(
            applicationContext,
            1,
            restartServiceIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmService =
            applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()] =
            restartServicePendingIntent

        super.onTaskRemoved(rootIntent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent != null) {
            val alarmCode = intent.getIntExtra("alarm_code", 0)
            if (alarmCode != 0) {
                // alarmCode를 사용하여 필요한 작업 수행
                val i = Intent(applicationContext, BackgroundActivity::class.java)
                i.putExtra("alarm_code", alarmCode)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                applicationContext.startActivity(i)
                Log.d("eyesonme-KS", "KeepService - onStartCommand called")
            }
        }

        return super.onStartCommand(intent, flags, startId)

    }

    fun stopService() {
        stopSelf()
    }

}