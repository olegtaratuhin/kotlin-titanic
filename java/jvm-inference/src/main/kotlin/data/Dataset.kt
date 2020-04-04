package data

import krangl.DataFrame

interface Dataset {

    public fun loadTrain(): DataFrame?

    public fun loadTest(): DataFrame?
}