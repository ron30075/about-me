package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //create a data binding object

    private val myName:MyName = MyName("Tim Hortons")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //we want to bind MainActivity.kt to activity_main.xml

        binding.myName = myName

        binding.doneButton.setOnClickListener{addNickname()}
        binding.nicknameText.setOnClickListener { updateNickname() }
        //from snake case automatically converted to camel case
        //it referring to itself
    }

    private fun addNickname() {

        binding.apply {
        myName?.nickname = nicknameEdit.text.toString()
        //since di niya raw alam yung datatype kailangan ng ?
        invalidateAll()

        binding.nicknameEdit.visibility = View.GONE
        binding.doneButton.visibility = View.GONE //view nalang since view: View refers to the button itself
                                    //since this listener is added to the view

        binding.nicknameText.visibility = View.VISIBLE

        }
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)
    }

    private fun updateNickname () {

        binding.nicknameEdit.visibility = View.VISIBLE
        binding.doneButton.visibility = View.VISIBLE
        binding.nicknameText.visibility = View.GONE

        binding.nicknameEdit.requestFocus()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameText,0)


    }


}
