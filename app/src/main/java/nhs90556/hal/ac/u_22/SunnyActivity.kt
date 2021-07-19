package nhs90556.hal.ac.u_22

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_favorite_sunny.*

class SunnyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_sunny)




        val imageView: ImageView = findViewById(R.id.imageView)

        Picasso.get()
            //AMAZONのやつの画像取得
            .load("https://akissutest.s3.us-east-2.amazonaws.com/tshirt_item.jpeg")
//            .resize(300, 300) //表示サイズ指定
//            .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
            .into(imageView) //imageViewに流し込み










//        val imageLink = arrayOf("imagelink1", "imagelink2", "imagelink3")
//        val strTitle = arrayOf("title1", "title2", "title3")
//
//        // TableLayoutの生成
//        val tableLayout = TableLayout(this).also {
//
//            var j = 0 ;
//
//            imageLink.forEach {
//                val i = j
//                var s = "tableRow ${i}";
//
//                val constraintLayout = findViewById<ConstraintLayout>(R.id.favoriteAnoter)
//
//                 s = TableRow(this).let {
//                     var imageBt = ImageButton(this)
//
//                     constraintLayout.addView(imageBt)
//                 }.toString()
//
//                j = j + 1;
//
//            }
//
//        }
//
//        // TableLayoutをレイアウトに設定
//        setContentView(tableLayout)

        //値を受け取り表示
//        val category = this.intent.getStringExtra("Category")
//        textView2.text = category

        //カテゴリに戻る
        BackButton.setOnClickListener {
            finish()
        }
    }

}