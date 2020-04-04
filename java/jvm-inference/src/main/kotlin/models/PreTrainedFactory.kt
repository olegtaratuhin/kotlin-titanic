package models

import org.jetbrains.numkt.core.ExperimentalNumkt

object PreTrainedFactory {

    @ExperimentalNumkt
    fun getPreTrainedModel(): PreTrainedLogisiticRegressionModel {
        val weightsFile = javaClass.classLoader.getResource("model/titanic/v1/log_reg_1.txt")!!
        val interceptFile = javaClass.classLoader.getResource("model/titanic/v1/log_reg_1_intercept.txt")!!
        return PreTrainedLogisiticRegressionModel(weightsFile.file, interceptFile.file)
    }
}