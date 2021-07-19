package nhs90556.hal.ac.u_22.models

import io.realm.RealmObject
import io.realm.annotations.*

open class FavoriteModel: RealmObject() {
    @PrimaryKey
    var favorite_id: Int = 0
    // favorite_coordinate_idに@Requiredをつけるとエラーになってしまう
    var favorite_coordinate_id: Int = 0
}