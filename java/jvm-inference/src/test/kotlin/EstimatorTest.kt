import models.PretrainedLogisiticRegressionModel
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.ExperimentalNumkt
import org.junit.Assert
import org.junit.Test

@ExperimentalNumkt
class EstimatorTest {

    @Test
    fun testLogisitcRegression() {
        val model = PretrainedLogisiticRegressionModel(weights = array(arrayOf(1.0, 1.0)))
        val matrix = array<Double>(listOf(listOf(2, 0), listOf(0, -3)))
        val predictions = model.predict(matrix)

        Assert.assertEquals(array<Double>(listOf(1, -1)), predictions)
    }

    @Test
    fun testLogisitcRegressionThreshold() {
        val model = PretrainedLogisiticRegressionModel(weights = array(arrayOf(1.0, 1.0)))
        val matrix = array<Double>(listOf(listOf(2, 0), listOf(0, -3)))
        val predictions = model.predict(matrix, 0.9)

        Assert.assertEquals(array<Double>(listOf(-1, -1)), predictions)
    }
}