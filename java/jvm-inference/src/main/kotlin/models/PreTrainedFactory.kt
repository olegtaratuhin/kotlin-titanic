package models

import org.jetbrains.numkt.core.ExperimentalNumkt

object PreTrainedFactory {

    @ExperimentalNumkt
    fun getPretrainedLogisitcRegression(): PreTrainedLogisiticRegressionModel {
        val weightsFile = javaClass.classLoader.getResource("model/titanic/v1/log_reg_1.txt")!!
        val interceptFile = javaClass.classLoader.getResource("model/titanic/v1/log_reg_1_intercept.txt")!!
        return PreTrainedLogisiticRegressionModel(weightsFile.file, interceptFile.file)
    }

    fun getPretrainedCatboost(header: String, numerical: List<String>, categorical: List<String>): CatBoostModel {
        val cbm = javaClass.classLoader.getResource("model/titanic/catboost/v1.cbm")!!
        return CatBoostModel(cbm.file, numerical, categorical, header)
    }
}