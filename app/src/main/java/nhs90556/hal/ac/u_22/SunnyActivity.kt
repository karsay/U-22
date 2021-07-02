package nhs90556.hal.ac.u_22

import android.os.Bundle
import android.app.Activity
import android.view.View
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_favorite_sunny.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.ui.favorite.FavoriteFragment

class SunnyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_sunny)

        //値を受け取り表示
        val category = this.intent.getStringExtra("Category")
        textView2.text = category

        //カテゴリに戻る
        BackButton.setOnClickListener {
            finish()
        }
    }

}