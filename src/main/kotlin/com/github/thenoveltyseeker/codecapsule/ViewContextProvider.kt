package com.github.thenoveltyseeker.codecapsule

import com.intellij.codeInsight.hints.*
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction
import com.intellij.codeInsight.hints.presentation.PresentationFactory
import com.intellij.psi.PsiElement

import org.jetbrains.kotlin.com.intellij.psi.PsiFile
import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement
import org.jetbrains.kotlin.psi.KtPackageDirective
import javax.swing.JPanel

@Suppress("UnstableApiUsage")
class ViewContextProvider : InlayHintsProvider<NoSettings> {
    override val key: SettingsKey<NoSettings> = SettingsKey("com.github.thenoveltyseeker.codecapsule.hints")
    override val name: String = "Code Time Capsule"
    override val previewText: String? = "Example function() // Hover to see context"

    override fun createSettings(): NoSettings = NoSettings()

    override fun createConfigurable(settings: NoSettings): ImmediateConfigurable {
        return object : ImmediateConfigurable {
            override fun createComponent(listener: ChangeListener): JPanel = JPanel()
        }
    }

    override fun getCollectorFor(
        file: com.intellij.psi.PsiFile,
        editor: Editor,
        settings: NoSettings,
        sink: InlayHintsSink
    ): InlayHintsCollector {
        val project = file.project

        return object : InlayHintsCollector {
            override fun collect(element: PsiElement, editor: Editor, sink: InlayHintsSink): Boolean {
                val function: PsiElement? = PsiTreeUtil.getParentOfType(element, PsiElement::class.java, false)
                val clazz: PsiElement? = PsiTreeUtil.getParentOfType(element, PsiElement::class.java, false)

                val identifier = "Rolbin"
                val context = ContextStorage(project).getContext(identifier)

//                val psiFile2 = element.containingFile as? PsiFile
//                val function2: PsiNamedElement? = element as? PsiNamedElement
//
//                val packageName2 = getPackageName(psiFile2)
//                val elementName2 = function?.name ?: return
//
//                val identifier2 = "$packageName2.$elementName2"
//
//                if (!context.isNullOrEmpty()) {
//                    val presentationFactory = PresentationFactory(editor)
//                    val presentation = presentationFactory.smallText("📌 $context")
//
//                    sink.addInlineElement(element.textOffset, false, presentation)
//                }
//                return true
                if (!context.isNullOrEmpty()) {
                    val presentationFactory = PresentationFactory(editor)

                    // Tooltip presentation (Hover will show full context)
                    val baseText = presentationFactory.smallText("📌 Context Available")
                    val tooltipPresentation = presentationFactory.withTooltip(context, baseText)

                    // Inline presentation with tooltip
                    val textPresentation = presentationFactory.smallText("📌 Context Available")
                    val finalPresentation = presentationFactory.referenceOnHover(tooltipPresentation, InlayPresentationFactory.ClickListener { event, translated ->

                    })



                    // Add inline hint
                    sink.addInlineElement(element.textOffset, true, finalPresentation)
                }
                return true


            }
        }
    }

//    private fun getPackageName(file: PsiElement): String {
//        val packageDirective = org.jetbrains.kotlin.psi.KtPsiUtil.findChildByType(file, KtNamedFunction.java)
//        return packageDirective?.fqName?.asString() ?: "default"
//    }
}
