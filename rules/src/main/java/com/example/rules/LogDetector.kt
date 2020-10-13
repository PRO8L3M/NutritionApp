package com.example.rules

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

@Suppress("UnstableApiUsage")
class LogDetector : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames(): List<String>? = listOf("tag", "i", "d")

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)

        val evaluator = context.evaluator
        if (evaluator.isMemberInClass(method, "android.util.Log")) reportUsage(context, node)
    }

    private fun reportUsage(context: JavaContext, node: UCallExpression) {
    context.report(
        issue = ISSUE,
        scope = node,
        location = context.getCallLocation(call = node, includeReceiver = true, includeArguments = true),
        message = "adasdasd asdasdasdasd asdas  dasda "
    )
    }

    companion object {
        val IMPLEMENTATION = Implementation(LogDetector::class.java, Scope.JAVA_FILE_SCOPE)
        val ISSUE = Issue.create(
            id = "LogDetector",
            briefDescription = "Blaa blla",
            explanation = "asdasd",
            category =  Category.USABILITY,
            priority = 8,
            severity = Severity.WARNING,
            androidSpecific = true,
            implementation = IMPLEMENTATION
        )
    }
}