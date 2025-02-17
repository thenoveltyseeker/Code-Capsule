//package com.dizzi.plugin.codecapsule
//
//import ai.grazie.utils.mpp.UUID
//import com.intellij.openapi.actionSystem.AnAction
//import com.intellij.openapi.actionSystem.AnActionEvent
//import com.intellij.openapi.ui.Messages
//import com.intellij.psi.PsiElement
//import com.intellij.psi.util.PsiTreeUtil
//import org.jetbrains.kotlin.psi.KtNamedFunction
//import org.jetbrains.kotlin.psi.KtClass
//
//class AddContextAction : AnAction() {
////    override fun actionPerformed(event: AnActionEvent) {
////        val project = event.project ?: return
////        val editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return
////        val psiFile = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE) ?: return
////
////        val caretOffset = editor.caretModel.offset
////        val element: PsiElement = psiFile.findElementAt(caretOffset) ?: return
////
////        // Safely get function or class from PSI
////        val function: KtNamedFunction? = PsiTreeUtil.getParentOfType(element, PsiElement::class.java, false) as? KtNamedFunction
////        val clazz: KtClass? = PsiTreeUtil.getParentOfType(element, PsiElement::class.java, false) as? KtClass
////
////        val identifier = function?.name ?: clazz?.name ?: return
////
////        // Show context popup
////        Messages.showInfoMessage(project, "Adding context to: $identifier", "Code Capsule")
////    }
//
//    override fun actionPerformed(event: AnActionEvent) {
//        val project = event.project ?: return
//        val editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return
//        val psiFile = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE) ?: return
//        val caret = editor.caretModel.primaryCaret
//        val offset = caret.offset
//
//        // Find Kotlin function or class at the caret position
//        val elementAtCaret = psiFile.findElementAt(offset)
//        val function = PsiTreeUtil.getParentOfType(elementAtCaret, PsiElement::class.java, false)
//        val clazz = PsiTreeUtil.getParentOfType(elementAtCaret, PsiElement::class.java, false)
//
//        val identifier = "Rolbin"
//        if (identifier == null) {
//            Messages.showMessageDialog(project, "No function or class detected at cursor.", "Code Time Capsule", Messages.getWarningIcon())
//            return
//        }
//
//        val context = Messages.showInputDialog(project, "Enter context:", "Add Context", Messages.getQuestionIcon()) ?: return
//        if (context.isBlank()) return
//
//        saveContext(identifier, context)
//        Messages.showMessageDialog(project, "Context added for $identifier", "Code Time Capsule", Messages.getInformationIcon())
//    }
//
//    private fun saveContext(identifier: String, context: String) {
//        val contextFile = java.io.File(System.getProperty("user.dir") + "/.metadata/context.json")
//        val json = if (contextFile.exists()) {
//            org.json.JSONObject(contextFile.readText())
//        } else {
//            org.json.JSONObject()
//        }
//
//        json.put(identifier, context)
//        contextFile.writeText(json.toString(4))
//    }
//}
