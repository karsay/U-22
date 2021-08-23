package nhs90556.hal.ac.u_22

import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.kotlin.where
import nhs90556.hal.ac.u_22.models.CoordinateModel
import nhs90556.hal.ac.u_22.models.FavoriteModel


class SunnyActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    lateinit var user: FavoriteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_sunny)

        val mParent = ViewGroup.LayoutParams.MATCH_PARENT
        val wContent = ViewGroup.LayoutParams.WRAP_CONTENT

        val scrollView = ScrollView(this)

        scrollView.layoutParams = LinearLayout.LayoutParams(wContent, mParent)

        val windowMetrics = this.windowManager.currentWindowMetrics
        val ScreenWidthHalf = windowMetrics.bounds.width() / 2
        val reseizeWidthHald = ScreenWidthHalf * 0.885

        realm = Realm.getDefaultInstance()

        val users = realm.where<FavoriteModel>().findAll()

        val category = this.intent.getStringExtra("Category")?.toInt()

        val imageLink_el = users.size

        // TableLayoutの生成
        val iL_element = imageLink_el / 2
        val iL_element_p = ( imageLink_el % 2 ) - 1
        val tableLayout = TableLayout(this).also {

            var j = 0

            for (i in 1..iL_element) {

                val tableRow = TableRow(this).also {

                    for (k in 0..1) {

                        val coor = realm.where<CoordinateModel>()
                            .equalTo("coordinate_id", users[k + j]?.favorite_coordinate_id)
                            .findFirst()

                        if (coor != null) {
                            if (coor.coordinate_weather == category) {

                                val button1 = ImageButton(this)

                                Picasso.get()
                                    .load(coor.coordinate_url)
                                    .resize(reseizeWidthHald.toInt(), reseizeWidthHald.toInt())
                                    .centerCrop()
                                    .into(button1)

                                it.addView(button1)
                            }
                        }

                    }

                    j = j + 2

                }

                it.addView(tableRow)

            }

            val tableRow = TableRow(this).also {

                for (k in 0..iL_element_p) {


                    val coor = realm.where<CoordinateModel>()
                        .equalTo("coordinate_id", users[k + j]?.favorite_coordinate_id)
                        .findFirst()

                    if (coor != null) {
                        if (coor.coordinate_weather == category) {

                            val button1 = ImageButton(this)

                            Picasso.get()
                                .load(coor.coordinate_url)
                                .resize(reseizeWidthHald.toInt(), reseizeWidthHald.toInt())
                                .centerCrop()
                                .into(button1)

                            it.addView(button1)
                        }

                    }

                }

            }

            it.addView(tableRow)

        }

        scrollView.addView(tableLayout)

        setContentView(scrollView)

    }

}