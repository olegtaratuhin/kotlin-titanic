package models

import krangl.DataFrame

interface PredictiveModel {

    public fun predict(dataFrame: DataFrame): DataFrame

}