package com.github.thenoveltyseeker.codecapsule

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.ui.JBColor
import java.util.regex.Matcher
import java.util.regex.Pattern

class CustomCommentAnnotator : Annotator {
    private val pattern: Pattern = Pattern.compile("\\bCapsule\\b")
    private val capsuleColor = TextAttributesKey.createTextAttributesKey(
        "My color",
        TextAttributes(JBColor.decode("#eab676"), null, null, null, 0)
    )

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiComment) {
            val text: String = element.getText()
            val matcher: Matcher = pattern.matcher(text)

            val commentStart: Int = element.getTextRange().startOffset

            while (matcher.find()) {
                val startOffset: Int = commentStart + matcher.start()
                val endOffset: Int = commentStart + matcher.end()

                val textRange = TextRange(startOffset, endOffset)

                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(textRange)
                    .tooltip("This is a custom tag")
                    .textAttributes(capsuleColor)
                    .create()
            }
        }
    }
}