package com.example.project.presentation.api
import com.example.project.presentation.list.Character

data class GOTResponse(
    val url: String,
    val name: String,
    val gender: String,
    val born: String,
    val died: String,
    val titles: List<Character>,
    val aliases: List<Character>,
    val father: String,
    val mother: String,
    val spouse: String,
    val allegiances: List<Character>
)