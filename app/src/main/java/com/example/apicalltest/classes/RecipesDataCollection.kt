package com.example.apicalltest.classes

import java.util.*
import java.util.jar.Attributes

class RecipesDataCollection: ArrayList<RecipesDataCollectionItem>()

data class RecipesDataCollectionItem(
    val name: String //El nombrede la receta
)