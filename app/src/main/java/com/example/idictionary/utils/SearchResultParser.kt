package com.example.idictionary.utils

import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.data.Meanings
import java.lang.StringBuilder

fun parseSearchResult(state: AppState): AppState {
    val newSearchResult = arrayListOf<DataModel>()
    when (state) {
        is AppState.Success -> {
            val searchResults = state.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResult)
                }
            }
        }
    }
    return AppState.Success(newSearchResult)
}

fun parseResult(dataModel: DataModel, newDataModel: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModel.add(DataModel(dataModel.text, newMeanings))
        }
    }
}
fun convertMeaningsToString(meanings: List<Meanings>?):String {
    val stringBuilder = StringBuilder()
    val safeMeanings = meanings ?: return ""
    for ((index, meaning) in safeMeanings.withIndex()){
        if(index + 1 != safeMeanings.size){
            stringBuilder.append(String.format("%s%s", meaning.translation?.translation, ", "))
        }else{
            stringBuilder.append(meaning.translation?.translation)
        }
    }
    return stringBuilder.toString()
}