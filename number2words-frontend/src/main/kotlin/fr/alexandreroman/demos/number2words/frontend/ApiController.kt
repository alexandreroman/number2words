/*
 * Copyright (c) 2018 Pivotal Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.alexandreroman.demos.number2words.frontend

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller exposing API for this application.
 */
@RestController
class ApiController(private val converter: ConverterServiceClient) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/api/convert")
    fun convert(@RequestParam("n") number: Int): ConversionResult {
        val words = converter.convert(number)
        logger.info("Number converted: {} -> {}", number, words)
        return ConversionResult(number, words)
    }
}

data class ConversionResult(val number: Int, val words: String)
