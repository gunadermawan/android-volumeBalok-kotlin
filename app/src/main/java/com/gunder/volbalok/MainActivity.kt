package com.gunder.volbalok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView


    companion object {
        private const val STATE_RESULT = "state_result"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHight = findViewById(R.id.edt_hitght)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

//        kde dibawah digunakan untuk mengatasi hasil yang hilang saat device berubah orientasi
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHight = edtHight.text.toString().trim()

            var isEmptyFields = false

            if (inputLength.isEmpty()){
                isEmptyFields = true
                edtLength.error = "kolom ini harus diisi"
            }
            if (inputWidth.isEmpty()){
                isEmptyFields = true
                edtWidth.error = "kolom ini harus diisi"
            }
            if (inputHight.isEmpty()) {
                isEmptyFields = true
                edtHight.error = "kolom ini harus diisi"
            }

            if (!isEmptyFields){
                val volume = inputLength.toDouble()*inputWidth.toDouble()*inputHight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }
}