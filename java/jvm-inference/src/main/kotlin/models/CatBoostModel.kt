package models

import ai.catboost.CatBoostModel.loadModel
import krangl.DataFrame
import krangl.DataFrameRow
import krangl.dataFrameOf
import kotlin.math.E
import kotlin.math.pow

class CatBoostModel(
    path: String,
    private val numerical: List<String>,
    private val categorical: List<String>,
    private val header: String
) : PredictiveModel {

    private val catboost: ai.catboost.CatBoostModel = loadModel(path)

    private fun extractCategorical(row: DataFrameRow): Array<String> {
        return Array<String>(categorical.size) {
            row[categorical[it]].toString()
        }
    }

    private fun sigmoid(x: Double): Double {
        return 1 / (1 + E.pow(-x))
    }

    private fun extractNumerical(row: DataFrameRow): FloatArray {
        return FloatArray(numerical.size) {
            if (row[numerical[it]] == null) {
                -999.9f
            } else {
                (row[numerical[it]] as Double).toFloat()
            }
        }
    }

    override fun predict(dataFrame: DataFrame): DataFrame {
        val result = ArrayList<Int>()
        val threshold = 0.5

        // todo: extract batches from dataFrame in feed them as matrix
        for (row in dataFrame.rows) {
            val catFeat = extractCategorical(row)
            val numFeat = extractNumerical(row)

            val prediction = catboost.predict(numFeat, catFeat).get(0, 0)

            result.add(if (prediction > threshold) 1 else 0)
        }

        return dataFrameOf(header)(result)
    }
}