package com.example.utils

import com.example.idictionary.model.storage.HistoryEntity


const val MEANINGS_SPLIT_KEY = ", "

fun parseSearchResult(state: com.example.model.AppState): com.example.model.AppState {
    val newSearchResult = arrayListOf<com.example.model.DataModel>()
    when (state) {
        is com.example.model.AppState.Success -> {
            val searchResults = state.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResult)
                }
            }
        }
    }
    return com.example.model.AppState.Success(newSearchResult)
}

fun parseResult(
    dataModel: com.example.model.DataModel,
    newDataModel: ArrayList<com.example.model.DataModel>
) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<com.example.model.Meanings>()
        val meaningsList = dataModel.meanings ?:return
        for (meaning in meaningsList) {
            if (meaning.translation != null && !meaning.translation!!.translation.isNullOrBlank()) {
                newMeanings.add(com.example.model.Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModel.add(com.example.model.DataModel(dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<com.example.model.Meanings>?): String {
    val stringBuilder = StringBuilder()
    val safeMeanings = meanings ?: return ""
    for ((index, meaning) in safeMeanings.withIndex()) {
        if (index + 1 != safeMeanings.size) {
            stringBuilder.append(
                String.format(
                    "%s%s",
                    meaning.translation?.translation,
                    MEANINGS_SPLIT_KEY
                )
            )
        } else {
            stringBuilder.append(meaning.translation?.translation)
        }
    }
    return stringBuilder.toString()
}

fun convertStringToMeaningsList(meaningsString: String?): List<com.example.model.Meanings>? {
    return meaningsString?.split(MEANINGS_SPLIT_KEY)?.map {
        com.example.model.Meanings(
            com.example.model.Translation(
                it
            ), null
        )
    }
}

fun fromUiToDbData(appState: com.example.model.AppState): HistoryEntity? {
    when (appState) {
        is com.example.model.AppState.Success -> {
            val safeData = appState.data ?: return null
            val safeText = safeData[0].text ?: return null
            return HistoryEntity(safeText, convertMeaningsToString(safeData[0].meanings))
        }
        else -> {
            return null
        }
    }
}

fun fromDbToUiData(historyEntity: HistoryEntity): com.example.model.DataModel {
    return com.example.model.DataModel(
        historyEntity.word,
        convertStringToMeaningsList(historyEntity.description)
    )
}
