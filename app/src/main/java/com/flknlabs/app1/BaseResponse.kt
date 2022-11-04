package com.flknlabs.app1

data class BaseResponse (
    val genres : List<Genres>,
    val homepage : String,
    val id : Int,
    val imdb_id : String,
    val original_language : String,
    val original_title : String,
    val overview : String,
    val popularity : Double,
    val backdrop_path : String,
    val poster_path : String,
    val release_date : String,
    val spoken_languages : List<SpokenLanguages>,
    val status : String,
    val tagline : String,
    val title : String,
    val video : Boolean,
    val vote_average : Double,
    val vote_count : Int,
)