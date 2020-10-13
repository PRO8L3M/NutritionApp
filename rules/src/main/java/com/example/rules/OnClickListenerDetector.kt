package com.example.rules

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

@Suppress("UnstableApiUsage")
class OnClickListenerDetector : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames(): List<String>? = listOf("setOnClickListener")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)

        val evaluator = context.evaluator
        if (evaluator.isMemberInClass(method, "android.view.View")) reportUsage(context, node)
    }

    private fun reportUsage(context: JavaContext, node: UCallExpression) {
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getCallLocation(call = node, includeReceiver = true, includeArguments = true),
            message = "Jak uzywasz tego listenera to zlo"
        )
    }

    companion object {
        val IMPLEMENTATION = Implementation(OnClickListenerDetector::class.java, Scope.JAVA_FILE_SCOPE)
        val ISSUE = Issue.create(
            id = "OnClickListenerDetector",
            briefDescription = "Nie wolno tego uzywac",
            explanation = "Dlatego!",
            category =  Category.USABILITY,
            priority = 8,
            severity = Severity.WARNING,
            androidSpecific = true,
            implementation = IMPLEMENTATION
        )
    }
}