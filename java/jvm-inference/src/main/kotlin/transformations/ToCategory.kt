package transformations

import krangl.DataFrame
import krangl.map

class ToCategory(private val column: String, private val map: Map<Double, Int>) : Transformation {
    override fun apply(dataFrame: DataFrame): DataFrame {
        return dataFrame.addColumn(column) { it[column].map<Double> { map[it] } }
    }
}