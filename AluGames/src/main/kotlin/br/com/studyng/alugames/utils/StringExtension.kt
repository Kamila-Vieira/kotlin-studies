package br.com.studyng.alugames.utils

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.transformInAge(): Int {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val birthDate = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    val age = Period.between(birthDate, today).years

    return age
}