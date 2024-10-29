package com.example.simplelist
import android.os.Bundle

import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioPerfectSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind các view từ XML với các ID của bạn
        editText = findViewById(R.id.editTextMSSV)
        radioEven = findViewById(R.id.radioButton3)
        radioOdd = findViewById(R.id.radioButton4)
        radioPerfectSquare = findViewById(R.id.radioButton5)
        buttonShow = findViewById(R.id.button2)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            textViewError.text = ""  // Xóa thông báo lỗi trước đó
            val input = editText.text.toString()

            if (input.isBlank()) {
                textViewError.text = "Vui lòng nhập một số nguyên dương."
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương hợp lệ."
                return@setOnClickListener
            }

            val resultList = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioPerfectSquare.isChecked -> getPerfectSquares(n)
                else -> {
                    textViewError.text = "Vui lòng chọn một loại số."
                    return@setOnClickListener
                }
            }

            // Cập nhật ListView với các số thỏa mãn
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }

    // Hàm lấy danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in 0..n step 2) {
            result.add(i)
        }
        return result
    }

    // Hàm lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in 1..n step 2) {
            result.add(i)
        }
        return result
    }

    // Hàm lấy danh sách số chính phương từ 0 đến n
    private fun getPerfectSquares(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            result.add(i * i)
            i++
        }
        return result
    }
}