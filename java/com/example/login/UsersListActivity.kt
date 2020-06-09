package com.example.login



import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.activity_users_list.*
import java.util.*
import kotlin.collections.ArrayList as ArrayList1


class UsersListActivity : AppCompatActivity() {

    private val activity = this@UsersListActivity
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UsersRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var appCompatButtonCampaign: AppCompatButton
    private lateinit var appCompatButtonEmail: AppCompatButton
    private lateinit var appCompatButtonViewCampaign: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        supportActionBar!!.title = ""
        initViews()
        initObjects()

    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        textViewName = findViewById<View>(R.id.textViewName) as AppCompatTextView
        recyclerViewUsers = findViewById<View>(R.id.recyclerViewUsers) as RecyclerView
        appCompatButtonCampaign = findViewById<View>(R.id.appCompatButtonCampaign) as AppCompatButton
        appCompatButtonEmail = findViewById<View>(R.id.appCompatButtonEmail) as AppCompatButton
        appCompatButtonViewCampaign=findViewById<View>(R.id.appCompatButtonViewCampaign) as AppCompatButton

    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listUsers = ArrayList()
        usersRecyclerAdapter = UsersRecyclerAdapter(listUsers)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewUsers.layoutManager = mLayoutManager
        recyclerViewUsers.itemAnimator = DefaultItemAnimator()
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.adapter = usersRecyclerAdapter
        databaseHelper = DatabaseHelper(activity)

        val emailFromIntent = intent.getStringExtra("EMAIL")
        textViewName.text = emailFromIntent

        val getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()


    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg p0: Void?): List<User> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }

    }
    fun onClickcam(v: View) {
        when (v.id) {
            R.id.appCompatButtonCampaign -> {
                // Navigate to RegisterActivity
                val intentCampaign= Intent(applicationContext, MainActivity2::class.java)
                startActivity(intentCampaign)
            }

        }
    }
    fun onClickemail(v: View) {
        when (v.id) {
            R.id.appCompatButtonEmail -> {
                // Navigate to RegisterActivity
                val intentEmail= Intent(applicationContext, Email::class.java)
                startActivity(intentEmail)
            }

        }
    }
    fun onClickview(v: View) {
        when (v.id) {
            R.id.appCompatButtonViewCampaign -> {
                // Navigate to RegisterActivity
                val intentView= Intent(applicationContext, ViewActivity::class.java)
                startActivity(intentView)
            }

        }
    }
}
