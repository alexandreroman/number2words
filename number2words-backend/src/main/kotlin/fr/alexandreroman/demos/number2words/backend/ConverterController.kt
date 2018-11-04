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

package fr.alexandreroman.demos.number2words.backend

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * REST controller exposing a single service for converting a number to words.
 */
@RestController
class ConverterController(private val converter: NumberToWordsConverter) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/convert")
    @ResponseBody
    fun convertToWords(@RequestParam("n") number: Int): String {
        logger.debug("Converting number to words: {}", number)
        val words = converter.convert(number)
        logger.info("Number converted: {} -> {}", number, words)
        return words
    }
}
