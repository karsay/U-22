package nhs90556.hal.ac.u_22.models

import io.realm.RealmObject
import io.realm.annotations.*

open class CoordinateModel: RealmObject() {
    @PrimaryKey
    var coordinate_id: Int = 0
    @Required
    var coordinate_url: String = ""
    @Required
    var coordinate_detail: String = ""
    // indexとweatherに@Requiredをつけるとエラーになってしまう
    var coordinate_index: Int = 0
    var coordinate_weather: Int = 0
}