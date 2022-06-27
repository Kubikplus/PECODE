package com.example.pecode

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : FragmentActivity() {
    private lateinit var adapter: NumberAdapter
    private lateinit var buttonNtf: ExtendedFloatingActionButton
    private lateinit var viewPager: ViewPager2

    private lateinit var addFragment: FloatingActionButton
    private lateinit var removeFragment: FloatingActionButton
    private lateinit var numberFragment: NumberFragment
    private lateinit var ntfManager: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        addFragment = findViewById(R.id.addFragment)
        buttonNtf = findViewById(R.id.buttonNtf)
        removeFragment = findViewById(R.id.removeFragment)
        adapter = NumberAdapter(this)
        numberFragment = NumberFragment()
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        val extras: Bundle? = intent.extras
        Log.d("Count","This is ${adapter.itemCount}")
        Log.d("Count","This is ${extras?.getInt(ARG_OBJ)}")
        addFragment()
        removeFragment()
        buttonNtf.setOnClickListener {
            showNotification()
        }

    }

    fun addFragment() {
        addFragment.setOnClickListener {
            adapter.list.add(1)
            adapter.notifyItemInserted(adapter.list.last())
            if (adapter.list.size > 1) {
                removeFragment.show()
            }
        }
    }

    fun removeFragment() {
        removeFragment.setOnClickListener {
            adapter.list.removeLast()
            adapter.notifyItemRemoved(adapter.list.last())
            if (adapter.list.size <= 1) {
                removeFragment.hide()
            }

        }
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "MyNotification"
            val description = "My notify channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel("notify", name, importance)
            notificationChannel.description = description
            val notificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun showNotification() {
        createNotificationChannel()
        val notificationBuild = NotificationCompat.Builder(applicationContext, "notify")
            .setSmallIcon(R.drawable.ic_baseline_add_24)
            .setContentTitle("You create a notification")
            .setContentText("Notification")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(1, notificationBuild.build())
    }
}


