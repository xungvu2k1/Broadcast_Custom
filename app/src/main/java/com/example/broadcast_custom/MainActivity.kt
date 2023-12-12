package com.example.broadcast_custom

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
// xây dựng broadcast receiver lắng nghe sự kiện gửi từ app BroadcastReceiver_2
class MainActivity : AppCompatActivity() {

    // sự kiện sẽ lắng nghe từ các app khác : cụ thể là app BroadcastReceiver2
    private var MY_ACTION : String = "com.broadcast.ACTION"
    private var MY_TEXT : String = "com.broadcast.TEXT"

    private var tvReceived : TextView? = null

    private var mBroadcastReceiver : BroadcastReceiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (MY_ACTION.equals(p1?.action)){
                val text = p1?.getStringExtra(MY_TEXT)
                tvReceived?.setText(text)
            }
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvReceived = findViewById(R.id.tv_received)
    }

    override fun onStart() {
        super.onStart()
        val intentFilter : IntentFilter = IntentFilter(MY_ACTION)
        // đăng kí 1 broadcast receiver
        registerReceiver(mBroadcastReceiver, intentFilter)//
        // sau khi đăng kí xong thì phải xử lý khi nào hủy broadcast receiver này đi
        // => hủy trong hàm onS top()
 }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
    }
}