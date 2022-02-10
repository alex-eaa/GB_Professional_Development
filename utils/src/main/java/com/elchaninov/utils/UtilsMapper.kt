package com.elchaninov.utils

import com.elchaninov.model.usermodel.Meanings


fun convertMeaningsToString(meanings: List<Meanings>?): String {
    var meaningsSeparatedByComma = String()
    meanings?.forEachIndexed { index, meaning ->
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}


