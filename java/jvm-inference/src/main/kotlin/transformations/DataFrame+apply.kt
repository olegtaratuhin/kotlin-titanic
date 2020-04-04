package transformations

import krangl.DataFrame

fun DataFrame.apply(transformation: Transformation): DataFrame {
    return transformation.apply(this)
}