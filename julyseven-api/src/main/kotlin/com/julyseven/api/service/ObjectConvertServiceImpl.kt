package com.julyseven.api.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.stereotype.Service

@Service
class ObjectConvertServiceImpl : ObjectConvertService{

    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    override fun convertToMap(o: Any): Map<String, Any> {

        return objectMapper.convertValue(o, object: TypeReference<Map<String, Any>>() {})
    }
}