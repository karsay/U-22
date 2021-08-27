package nhs90556.hal.ac.u_22.ui.register

import android.os.Bundle
import android.preference.PreferenceManager
import android.text.method.TextKeyListener.clear
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.edit
import kotlinx.android.synthetic.main.fragment_register.*
import nhs90556.hal.ac.u_22.R


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // メモリから登録データの取得
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val genderId = pref.getInt("GENDER_ID", 1)
        val predectureId = pref.getInt("PREFECTURE_ID", 22)
        var tmpGenderId = 0

        if(genderId == 1) {
            tmpGenderId = R.id.radioMale
        } else {
            tmpGenderId = R.id.radioFemale
        }

        //ラジオボタンの初期値を設定
        val radioButton = view.findViewById<RadioButton>(tmpGenderId)
        radioButton.isChecked = true

        //xmlファイルからアイテムの配列を取得
        val items = resources.getStringArray(R.array.txt_area_arr)

        //アダプターにアイテム配列を設定
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)

        //スピナーにアダプターを設定
        spinner.adapter = adapter

        // スピナーの初期値を設定
        spinner.setSelection(predectureId)

        //　登録処理
        button.setOnClickListener {
            saveData()
            Toast.makeText(context, "登録情報を変更しました", Toast.LENGTH_LONG).show()
        }
    }

    // データの保存
    private fun saveData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = pref.edit()
//        editor.putInt("GENDER_ID", radioGender.checkedRadioButtonId)
        if(radioGender.checkedRadioButtonId == R.id.radioMale){
            editor.putInt("GENDER_ID", 1)
        } else {
            editor.putInt("GENDER_ID", 2)
        }
        editor.putInt("PREFECTURE_ID", spinner.selectedItemPosition)
            .apply()
    }
}
