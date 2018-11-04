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

import org.springframework.stereotype.Component
import pl.allegro.finance.tradukisto.ValueConverters

/**
 * Number converter service.
 */
@Component
class NumberToWordsConverter {
    fun convert(number: Int): String {
        // Create a converter (only English is supported in this implementation).
        val conv = ValueConverters.ENGLISH_INTEGER
        return conv.asWords(number)
    }
}
