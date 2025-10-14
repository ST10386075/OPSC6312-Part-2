package com.example.doodlenote.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("noteTitle") ?: "Doodle Note"
        val message = intent.getStringExtra("noteContent") ?: "You have a note reminder!"
        Toast.makeText(context, "$title: $message", Toast.LENGTH_LONG).show()
    }
}
