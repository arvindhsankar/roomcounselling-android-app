package com.example.login

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val id: Array<String>, private var regno: Array<String>, private var cgparank: Array<String> ,private var email: Array<String>,private var room: Array<String>,private var block: Array<String>,private var acnonac: Array<String>)
    : ArrayAdapter<String>(context, R.layout.viewlayout, regno) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.viewlayout, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val regnoText = rowView.findViewById(R.id.textViewRegno) as TextView
        val cgparankText = rowView.findViewById(R.id.textViewCgpaRank) as TextView
        val emailText = rowView.findViewById(R.id.textViewEmail) as TextView
        val roomText = rowView.findViewById(R.id.textViewRoom) as TextView
        val blockText = rowView.findViewById(R.id.textViewBlock) as TextView

        val acnonacText = rowView.findViewById(R.id.textViewAcnonac) as TextView

        idText.text = "Id: ${id[position]}"
        regnoText.text = "Regno: ${regno[position]}"
        cgparankText.text = "Name: ${cgparank[position]}"

        emailText.text = "Email: ${email[position]}"
        roomText.text = "Room: ${room[position]}"
        blockText.text = "Block: ${block[position]}"
        acnonacText.text = "Ac/NonAc: ${acnonac[position]}"

        return rowView
    }
}