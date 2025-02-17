//@file:Suppress("UnstableApiUsage")
//
//package com.github.thenoveltyseeker.codecapsule
//
//import com.intellij.codeInsight.hints.*
//import com.intellij.openapi.editor.Editor
//import com.intellij.psi.PsiElement
//import com.intellij.psi.util.PsiTreeUtil
//import org.jetbrains.kotlin.psi.KtClass
//import org.jetbrains.kotlin.psi.KtFunction
//import org.jetbrains.kotlin.psi.KtNamedFunction
//import javax.swing.JPanel
//import com.intellij.codeInsight.hints.presentation.TextInlayPresentation
//import com.intellij.codeInsight.hints.presentation.PresentationFactory
//
//class ViewContextProvider : InlayHintsProvider<NoSettings> {
//    override val key: SettingsKey<NoSettings> = SettingsKey("com.dizzi.codecapsule.hints")
//    override val name: String = "Code Time Capsule"
//    override val previewText: String? = "Example function() // Hover to see context"
//
//    override fun createSettings(): NoSettings = NoSettings()
//
//    override fun createConfigurable(settings: NoSettings): ImmediateConfigurable {
//        return object : ImmediateConfigurable {
//            override fun createComponent(listener: ChangeListener): JPanel = JPanel()
//        }
//    }
//
//    override fun getCollectorFor(
//        file: com.intellij.psi.PsiFile,
//        editor: Editor,
//        settings: NoSettings,
//        sink: InlayHintsSink
//    ): InlayHintsCollector {
//        return object : InlayHintsCollector {
//            override fun collect(element: PsiElement, editor: Editor, sink: InlayHintsSink): Boolean {
//                val function = PsiTreeUtil.getParentOfType(element, PsiElement::class.java)
//                val clazz = PsiTreeUtil.getParentOfType(element,PsiElement::class.java)
//
//                val identifier = "Rolbin"
//                val context = getContext(identifier)
//
//                if (context != null) {
//
//
//
//                    val presentationFactory = PresentationFactory(editor)
//                    val presentation = presentationFactory.smallText("📌 Context: $context")
//
//                    sink.addInlineElement(element.textOffset, false, presentation)
//
////                    sink.addInlineElement(editor.offsetToLogicalPosition(element.textOffset), false) {
////                        com.intellij.ui.components.JBLabel("📌 Context: $context")
////                    }
//                }
//                return true
//            }
//        }
//    }
//
//    private fun getContext(identifier: String): String? {
//        // Simulated context storage (can be replaced with JSON storage)
//        val contextMap = mapOf(
//            "MyFunction" to "This function is responsible for XYZ",
//            "MyClass" to "Handles all database interactions"
//        )
//        return contextMap[identifier]
//    }
//}
