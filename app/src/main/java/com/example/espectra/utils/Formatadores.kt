package com.example.espectra.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class MascaraDataTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val apenasNumeros = text.text.filter { it.isDigit() }.take(8)

        val output = buildString {
            for (i in apenasNumeros.indices) {
                append(apenasNumeros[i])
                if ((i == 1 || i == 3) && i != apenasNumeros.lastIndex) {
                    append("/")
                }
            }
        }

        val mapeamentoDeCursor = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 4) return offset + 1
                return offset + 2
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset - 1
                return offset - 2
            }
        }

        return TransformedText(AnnotatedString(output), mapeamentoDeCursor)
    }
}