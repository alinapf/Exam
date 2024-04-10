package com.example.pasechnikovapr33exam

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.support.design.widget.Snackbar
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var fact: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        fact = findViewById(R.id.factId)
        result("23")
    }
    private fun result(number :String) {
        val url= "http://numbersapi.com/${number}/year?json"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)
                fact.text = obj.getJSONObject("year").getString("text")
            },
            {
                val sn = Snackbar.make(fact,
                    "Error!",
                    Snackbar.LENGTH_LONG)
                sn.setActionTextColor(Color.RED)
                sn.show()
            }
        )
        queue.add(stringRequest)
    }

    fun click(view: View) {
        result(editText.text.toString())
    }
}