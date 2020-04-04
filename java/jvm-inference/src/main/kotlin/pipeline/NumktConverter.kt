package pipeline

import krangl.DataFrame
import krangl.dataFrameOf
import krangl.toDoubleMatrix
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.ExperimentalNumkt
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.transpose

@ExperimentalNumkt
object NumktConverter {

    fun convertToNumkt(dataFrame: DataFrame): KtNDArray<Double> {
        return array<Double>(listOf(*dataFrame.toDoubleMatrix())).transpose()
    }

    fun convertFromNumkt(matrix: KtNDArray<Double>, header: List<String>): DataFrame {

        // in krangl.dataFrame data is organized into columns, however models work with KtNdArray
        // In order to preserve correct placement of values we have to transpose multidimensional data
        // but leave 1d vectors unchanged, as what we do with np.ravel to get single column vector

        return if (matrix.ndim == 1) {
            dataFrameOf(header)(matrix.toList())
        } else {
            dataFrameOf(header)(matrix.transpose().toList())
        }
    }
}