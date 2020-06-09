package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ArrayAdapter

import androidx.core.widget.NestedScrollView

class ViewActivity : AppCompatActivity() {
    private val activity = this@ViewActivity
    private lateinit var listView: ListView
    private lateinit var databaseHelper: DatabaseHelper2
    private lateinit var nestedScrollView: NestedScrollView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        // initializing the listeners

        // initializing the objects
        initObjects()
        listView = findViewById<View>(R.id.listView) as ListView

    }

    private fun initObjects() {
        databaseHelper= DatabaseHelper2(activity)

    }
        fun viewRecord(view: View){
        //creating the instance of DatabaseHandler class
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<Campaign> = databaseHelper.viewUser()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayRegno = Array<String>(emp.size){"null"}
        val empArrayCgparank = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}
        val empArrayRoom = Array<String>(emp.size){"null"}
        val empArrayBlock = Array<String>(emp.size){"null"}
        val empArrayAcnonac = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.id.toString()
            empArrayRegno[index] = e.regno
            empArrayEmail[index] = e.email
            empArrayCgparank[index] = e.cgparank
            empArrayRoom[index] = e.room
            empArrayBlock[index] = e.block
            empArrayAcnonac[index] = e.acnonac

            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = MyListAdapter(this,empArrayId,empArrayRegno,empArrayEmail,empArrayCgparank,empArrayRoom,empArrayBlock,empArrayAcnonac)
        listView.adapter = myListAdapter
    }
}
