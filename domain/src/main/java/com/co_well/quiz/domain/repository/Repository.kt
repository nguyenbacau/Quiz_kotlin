package com.co_well.quiz.domain.repository

interface Repository {
    fun scanImage(imgUri: String): String

    fun importFile()
}