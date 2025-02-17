package com.github.thenoveltyseeker.codecapsule

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import java.util.regex.Matcher
import java.util.regex.Pattern

class AddContextAction : AnAction() {

    private val pattern: Pattern = Pattern.compile("\\bCapsule\\b")

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(event: AnActionEvent) {
        val editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE) ?: return

        val caretOffset = editor.caretModel.offset
        val element: PsiElement? = psiFile.findElementAt(caretOffset)
        if(element != null && element is PsiComment) {
            val matcher: Matcher = pattern.matcher(element.text)
            event.presentation.isEnabledAndVisible = matcher.find()
        } else {
            event.presentation.isEnabledAndVisible = false
        }
    }

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE) ?: return

        val caretOffset = editor.caretModel.offset
        val element: PsiElement = psiFile.findElementAt(caretOffset) ?: return

        val psiComment = PsiTreeUtil.getParentOfType(element, PsiComment::class.java, false)
        val clazz= PsiTreeUtil.getParentOfType(element, PsiElement::class.java, false)

        val identifier = "Rolbin"

        // Ask user for context
        val context = Messages.showMultilineInputDialog(
            project,
            "Enter context for $identifier:",
            "Create Code Capsule",
            null,
            Messages.getQuestionIcon(),
            null
        ) ?: return

        // Save context in project directory
        ContextStorage(project).saveContext(identifier, context)

        Messages.showInfoMessage(project, "Capsule saved successfully!", "Code Capsule")
    }
}
