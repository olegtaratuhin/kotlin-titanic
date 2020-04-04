package transformations

import krangl.DataFrame
import models.Model
import org.jetbrains.numkt.core.ExperimentalNumkt

@ExperimentalNumkt
abstract class Estimator(val model: Model) : Transformation {

    abstract override fun apply(dataFrame: DataFrame): DataFrame

}