import models.PreTrainedLogisiticRegressionModel
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.ExperimentalNumkt
import org.junit.Assert
import org.junit.Test

@ExperimentalNumkt
class EstimatorTest {

    @Test
    fun testLogisitcRegression() {
        val model = PreTrainedLogisiticRegressionModel(weights = array(arrayOf(1.0, 1.0)))
        val matrix = array<Double>(listOf(listOf(2, 0), listOf(0, -3)))
        val predictions = model.predict(matrix)

        Assert.assertEquals(array<Double>(listOf(1, -1)), predictions)
    }

    @Test
    fun testLogisitcRegressionThreshold0() {
        val model = PreTrainedLogisiticRegressionModel(weights = array(arrayOf(1.0, 1.0)))
        val matrix = array<Double>(listOf(listOf(2, 0), listOf(0, -3)))
        val predictions = model.predict(matrix, 0.0)

        Assert.assertEquals(array<Double>(listOf(1, 1)), predictions)
    }

    @Test
    fun testLogisitcRegressionThreshold1() {
        val model = PreTrainedLogisiticRegressionModel(weights = array(arrayOf(1.0, 1.0)))
        val matrix = array<Double>(listOf(listOf(2, 0), listOf(0, -3)))
        val predictions = model.predict(matrix, 1.0)

        Assert.assertEquals(array<Double>(listOf(-1, -1)), predictions)
    }
}