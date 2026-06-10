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

class CpfTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formatted = StringBuilder()

        for (i in originalText.indices) {
            formatted.append(originalText[i])
            if (i == 2 || i == 5) {
                formatted.append(".")
            }
            if (i == 8) {
                formatted.append("-")
            }
        }

        val cpfOffsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset + 1
                if (offset <= 8) return offset + 2
                if (offset <= 11) return offset + 3
                return 14 // Tamanho máximo do CPF mascarado
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 6) return offset - 1
                if (offset <= 10) return offset - 2
                if (offset <= 14) return offset - 3
                return 11 // Tamanho máximo do CPF limpo
            }
        }

        return TransformedText(AnnotatedString(formatted.toString()), cpfOffsetMapping)
    }
}