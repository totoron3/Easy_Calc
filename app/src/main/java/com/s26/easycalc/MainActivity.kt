package com.s26.easycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.s26.easycalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //ボタンが押された際のイベントハンドラ
    private val listener = View.OnClickListener {

        //マジックナンバーの記述をしてはいけないので、定数で定義
        val ZERO: Double = 0.0
        //一つ目のEditTextのプロパティ宣言
        val input1 = binding.editText1
        val value1 = binding.editText1.text.toString()

        //二つ目のEditTextのプロパティ宣言
        val input2 = binding.editText2
        val value2 = binding.editText2.text.toString()
        //合計を入れるプロパティーを初期化
        var sum: Double = 0.0

        //ラジオボタンのプロパティーを宣言
        val plus = binding.radioButton1
        val minus = binding.radioButton2
        val multipl = binding.radioButton3
        val division = binding.radioButton4

        //何も入力されない時の処理
        if (value1.isEmpty()) {
            input1.error = getString(R.string.message)
            return@OnClickListener
        }
        if(value2.isEmpty()) {
            input2.error = getString(R.string.message)
            return@OnClickListener
        }
            //ゼロで割ろうとした時の処理
        if(value2.toDouble() == ZERO && division.isChecked) {
            input2.error = "0で割ることはできません。0以外の値を入力してください"
        //}else{
            return@OnClickListener
        }

        //正常処理系
        //String型からDouble型への変換
        val num1: Double = value1.toDouble()
        val num2: Double = value2.toDouble()

        //ラジオボタンの選択による分岐
        when {
            plus.isChecked -> sum = num1 + num2
            minus.isChecked -> sum = num1 - num2
            multipl.isChecked -> sum = num1 * num2
            division.isChecked -> sum = num1 / num2
            }
            //TextViewに結果を表示
            binding.textView2.text = sum.toString()
    }

    //ClearButtonのイベントハンドラ実装。
    private val clearListener = View.OnClickListener {
        binding.editText1.setText("")
        binding.editText2.setText("")
        binding.textView2.text=""
        binding.radioButton1.isChecked = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //このプログラムではBindingを使用
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button1.setOnClickListener(listener)       //計算ボタンを押した時のリスナーを設定
        binding.button2.setOnClickListener(clearListener)   //Clearボタンを押した際のリスナーを設定
    }
}
