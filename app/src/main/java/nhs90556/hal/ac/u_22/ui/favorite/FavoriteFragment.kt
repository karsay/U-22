package nhs90556.hal.ac.u_22.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_favorite.*
import nhs90556.hal.ac.u_22.R
import nhs90556.hal.ac.u_22.models.CoordinateModel
import nhs90556.hal.ac.u_22.models.FavoriteModel


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mParent = ViewGroup.LayoutParams.MATCH_PARENT
        val wContent = ViewGroup.LayoutParams.WRAP_CONTENT

        val constraintLayout = context?.let { ConstraintLayout(it) }
        val scrollView = ScrollView(context)

        scrollView.layoutParams = LinearLayout.LayoutParams(wContent, mParent)

        val windowMetrics = activity?.windowManager?.currentWindowMetrics
        val ScreenWidthHalf = windowMetrics?.bounds!!.width() / 2
        val reseizeWidthHald = ScreenWidthHalf// * 0.885

        realm = Realm.getDefaultInstance()

        val users = realm.where<FavoriteModel>().findAll()

        val imageLink_el = users.size


        // TableLayoutの生成
        val iL_element = imageLink_el / 2
        val iL_element_p = ( imageLink_el % 2 ) - 1
        val tableLayout = TableLayout(context).also {

            var j = 0

            for (i in 1..iL_element) {

                val tableRow = TableRow(context).also {

                    for (k in 0..1) {

                        val coor = realm.where<CoordinateModel>()
                            .equalTo("coordinate_id", users[k + j]?.favorite_coordinate_id)
                            .findFirst()

                        if (coor != null) {

                            val constraintLayout = context?.let { it1 ->
                                ConstraintLayout(it1).also {

                                    val button1 = ImageView(context)

                                    Picasso.get()
                                        .load(coor.coordinate_url)
                                        .resize(
                                            reseizeWidthHald.toInt(),
                                            reseizeWidthHald.toInt()
                                        )
                                        .centerCrop()
                                        .into(button1)

                                    it.addView(button1)

                                    val ibutton = ImageButton(context)
                                    ibutton.setImageResource(R.drawable.codbutton2)

                                    ibutton.setTag(users[k + j]?.favorite_coordinate_id)
                                    ibutton.setOnClickListener {

                                        var setCoordinateId = ibutton.getTag().toString().toInt()

                                        val coor = realm.where<CoordinateModel>()
                                            .equalTo("coordinate_id", setCoordinateId)
                                            .findFirst()

                                        if (coor?.coordinate_id != null) {

                                            val fvrt = realm.where<FavoriteModel>()
                                                .equalTo("favorite_coordinate_id", coor.coordinate_id)
                                                .findFirst()

                                            if (fvrt == null) {
                                                realm.executeTransaction { db: Realm ->
                                                    val maxId = db.where<FavoriteModel>().max("favorite_id")
                                                    val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                                                    val favorite = db.createObject<FavoriteModel>(nextId)
                                                    favorite.favorite_coordinate_id = coor.coordinate_id
                                                }
                                                ibutton.setImageResource(R.drawable.codbutton2)
                                            } else {
                                                realm.executeTransaction { db: Realm ->
                                                    fvrt.deleteFromRealm()
                                                }
                                                ibutton.setImageResource(R.drawable.codbutton)
                                            }
                                        }

                                    }

                                    it.addView(ibutton)

                                    val constraintSet = ConstraintSet()
                                    constraintSet.clone(constraintLayout)

                                    constraintSet.connect(ibutton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
                                    constraintSet.connect(ibutton.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

                                    constraintSet.applyTo(constraintLayout)
                                }
                            }
                            it.addView(constraintLayout)
//                                }
                        }
                    }
                    j = j + 2

                }

                it.addView(tableRow)

            }

            val tableRow = TableRow(context).also {

                for (k in 0..iL_element_p) {

                    val coor = realm.where<CoordinateModel>()
                        .equalTo("coordinate_id", users[k + j]?.favorite_coordinate_id)
                        .findFirst()

                    if (coor != null) {

                            val constraintLayout = context?.let { it1 ->
                                ConstraintLayout(it1).also {

                                    val button1 = ImageView(context)

                                    Picasso.get()
                                        .load(coor.coordinate_url)
                                        .resize(reseizeWidthHald.toInt(), reseizeWidthHald.toInt())
                                        .centerCrop()
                                        .into(button1)

                                    it.addView(button1)

                                    val ibutton = ImageButton(context)
                                    ibutton.setImageResource(R.drawable.codbutton2)

                                    ibutton.setTag(users[k + j]?.favorite_coordinate_id)
                                    ibutton.setOnClickListener {


                                        val setCoordinateId = ibutton.getTag().toString().toInt()

                                        val coor = realm.where<CoordinateModel>()
                                            .equalTo("coordinate_id", setCoordinateId)
                                            .findFirst()

                                        if (coor?.coordinate_id != null) {

                                            val fvrt = realm.where<FavoriteModel>()
                                                .equalTo("favorite_coordinate_id", coor.coordinate_id)
                                                .findFirst()

                                            if (fvrt == null) {
                                                realm.executeTransaction { db: Realm ->
                                                    val maxId = db.where<FavoriteModel>().max("favorite_id")
                                                    val nextId = ((maxId?.toLong() ?: 0L) + 1).toInt()
                                                    val favorite = db.createObject<FavoriteModel>(nextId)
                                                    favorite.favorite_coordinate_id = coor.coordinate_id
                                                }
                                                ibutton.setImageResource(R.drawable.codbutton2)
                                            } else {
                                                realm.executeTransaction { db: Realm ->
                                                    fvrt.deleteFromRealm()
                                                }
                                                ibutton.setImageResource(R.drawable.codbutton)
                                            }
                                        }

                                    }

                                    it.addView(ibutton)
                                    val constraintSet = ConstraintSet()
                                    constraintSet.clone(constraintLayout)

                                    constraintSet.connect(ibutton.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
                                    constraintSet.connect(ibutton.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

                                    constraintSet.applyTo(constraintLayout)
                                }
                            }
                            it.addView(constraintLayout)

                    }

                }

            }

            it.addView(tableRow)

        }

        scrollView.addView(tableLayout)
        constraitLayout.addView(scrollView)
    }

}
