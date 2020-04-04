package transformations

import krangl.DataFrame
import models.PredictiveModel

class Classifier(private val svc: PredictiveModel) : Transformation {

    override fun apply(dataFrame: DataFrame): DataFrame {
        return svc.predict(dataFrame)
    }
}