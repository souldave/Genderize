package com.example.davegilbier.genderize

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url= "https://api.genderize.io/?name="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        genderBtn.setOnClickListener {
            progressBar.visibility= View.VISIBLE
            genderLbl.visibility= View.GONE
            val name = nameEditText.text.toString()
            doAsync {
                val resultJson = URL(url+name).readText()
                uiThread {
              //      val jsonObj = JSONObject(resultJson) // converted to class instance
             //       val gender = jsonObj.getString("gender")
               //     genderLbl.text =gender
// another way esp many data are needed
                    val genderize: Genderize= Gson().fromJson(resultJson, Genderize::class.java)
                    val gender = genderize.gender
                    genderLbl.text =gender

                    progressBar.visibility= View.GONE
                    genderLbl.visibility=View.VISIBLE
                }
            }
        }
    }
}
