package models

import krangl.DataFrame
import krangl.dataFrameOf
import krangl.toDoubleMatrix
import models.generated.RandomForest

class RandomForestClassifier(private val header: String) : PredictiveModel {

    private fun extract(matrix: Array<DoubleArray>, row: Int): DoubleArray {
        return DoubleArray(matrix.size) { index ->
            matrix[index][row]
        }
    }

    override fun predict(dataFrame: DataFrame): DataFrame {
        val matrix = dataFrame.toDoubleMatrix()

        return dataFrameOf(header)(List<Int>(dataFrame.nrow) { index ->
            RandomForest.predict(extract(matrix, index))
        })
    }
}