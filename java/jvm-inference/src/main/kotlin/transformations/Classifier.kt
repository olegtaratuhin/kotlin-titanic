package transformations

import krangl.DataFrame
import models.PredictiveModel

class Classifier(private val model: PredictiveModel) : Transformation {

    override fun apply(dataFrame: DataFrame): DataFrame {
        return model.predict(dataFrame)
    }
}