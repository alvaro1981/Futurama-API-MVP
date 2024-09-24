package com.example.futuramaapi.model

data class Character(
    val name: Name,
    val images: Images,
    val gender:String,
    val species: String,
    val homePlanet:String,
    val occupation:String,
    val sayings:List<String>,
    val id:Int,
    val age:String){
}
