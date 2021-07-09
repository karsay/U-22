package nhs90556.hal.ac.u_22.ui.register


import android.os.Bundle
//import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_register.*
import nhs90556.hal.ac.u_22.R


class RegisterFragment : Fragment() {
    private var checkedRadioButtontext :String =""
    private var prefecturestext :String =""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val radioGroup = findViewById<RadioGroup>(R.id.radioGender)

        radioGender.check(R.id.radioMale)

        // 選択項目変更のイベント追加
        radioGender.setOnCheckedChangeListener { group, checkedId ->
            // IDをもとに、選択状態のRadioButtonを取得し、ボタンのテキストを出力
            val checkedRadioButton = view.findViewById<RadioButton>(checkedId)
            checkedRadioButtontext = checkedRadioButton.text.toString()

        }

        //ここからspinner

        //xmlファイルからアイテムの配列を取得
        val items = resources.getStringArray(R.array.txt_area_arr)

        //アダプターにアイテム配列を設定
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)

        //スピナーにアダプターを設定
        spinner.adapter = adapter

        //スピナーのセレクトイベント設定
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                //選択されたアイテムをテキストビューに設定
                 prefecturestext = parent.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            debug.text = checkedRadioButtontext
            textView7.text = prefecturestext
        }
    }
}
