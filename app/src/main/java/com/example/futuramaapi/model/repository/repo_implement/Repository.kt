package com.example.futuramaapi.model.repository.repo_implement

import com.example.futuramaapi.model.Character

interface Repository {
    suspend fun getDataFromApi():List<Character>
}