package com.julyseven.api.service

interface ObjectConvertService {

    fun convertToMap(o : Any): Map<String, Any>
}