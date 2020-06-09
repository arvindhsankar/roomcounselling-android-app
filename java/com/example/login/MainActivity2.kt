package com.example.login

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() ,View.OnClickListener{
    private val activity = this@MainActivity2
    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var textInputLayoutRegno: TextInputLayout
    private lateinit var textInputLayoutCgparank: TextInputLayout
    private lateinit var textInputLayoutRoom: TextInputLayout
    private lateinit var textInputLayoutBlock: TextInputLayout
    private lateinit var textInputLayoutAcnonac: TextInputLayout
    private lateinit var textInputLayoutEmail : TextInputLayout


    private lateinit var textInputEditTextRegno: TextInputEditText
    private lateinit var textInputEditTextCgparank: TextInputEditText
    private lateinit var textInputEditTextRoom: TextInputEditText
    private lateinit var textInputEditTextBlock: TextInputEditText
    private lateinit var textInputEditTextAcnonac: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText

    private lateinit var appCompatButtonRegister: AppCompatButton


    private lateinit var databaseHelper: DatabaseHelper2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar!!.hide()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()
    }
    private fun initViews() {
        nestedScrollView = findViewById<View>(R.id.nestedScrollView) as NestedScrollView

        textInputLayoutRegno = findViewById<View>(R.id.textInputLayoutRegno) as TextInputLayout
        textInputLayoutCgparank = findViewById<View>(R.id.textInputLayoutCgparank) as TextInputLayout
        textInputLayoutRoom = findViewById<View>(R.id.textInputLayoutRoom) as TextInputLayout
        textInputLayoutBlock = findViewById<View>(R.id.textInputLayoutBlock) as TextInputLayout
        textInputLayoutAcnonac = findViewById<View>(R.id.textInputLayoutAcnonac) as TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout

        textInputEditTextRegno = findViewById<View>(R.id.textInputEditTextRegno) as TextInputEditText
        textInputEditTextCgparank = findViewById<View>(R.id.textInputEditTextCgparank) as TextInputEditText
        textInputEditTextRoom = findViewById<View>(R.id.textInputEditTextRoom) as TextInputEditText
        textInputEditTextBlock = findViewById<View>(R.id.textInputEditTextBlock) as TextInputEditText
        textInputEditTextAcnonac = findViewById<View>(R.id.textInputEditTextAcnonac) as TextInputEditText
        textInputEditTextEmail = findViewById<View>(R.id.textInputEditTextEmail) as TextInputEditText

        appCompatButtonRegister = findViewById<View>(R.id.appCompatButtonRegister) as AppCompatButton


    }
    private fun initListeners() {
        appCompatButtonRegister!!.setOnClickListener(this)

    }
    private fun initObjects() {
        databaseHelper= DatabaseHelper2(activity)

    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.appCompatButtonRegister -> postDataToSQLite()

        }
    }
    private fun postDataToSQLite() {

            var campaign = Campaign(regno = textInputEditTextRegno!!.text.toString().trim(),
                cgparank = textInputEditTextCgparank!!.text.toString().trim(),
                email = textInputEditTextEmail!!.text.toString().trim(),
                room = textInputEditTextRoom!!.text.toString().trim(),
                block = textInputEditTextBlock!!.text.toString().trim(),
                acnonac = textInputEditTextAcnonac!!.text.toString().trim())

            databaseHelper!!.addUser(campaign)

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView!!, getString(R.string.sucesscampaign), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()





    }
    private fun emptyInputEditText() {
        textInputEditTextRegno!!.text = null
        textInputEditTextRoom!!.text = null
        textInputEditTextBlock!!.text = null
        textInputEditTextEmail!!.text = null
        textInputEditTextAcnonac!!.text = null
        textInputEditTextCgparank!!.text = null

    }
}
