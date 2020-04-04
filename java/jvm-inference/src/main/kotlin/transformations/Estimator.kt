package transformations

import krangl.DataFrame
import models.CustomModel
import org.jetbrains.numkt.core.ExperimentalNumkt

@ExperimentalNumkt
abstract class Estimator(val model: CustomModel) : Transformation {

    abstract override fun apply(dataFrame: DataFrame): DataFrame

}