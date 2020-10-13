package com.example.rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class IssueRegistry: IssueRegistry() {
    override val issues: List<Issue> = listOf(LogDetector.ISSUE, OnClickListenerDetector.ISSUE)
    override val api: Int = CURRENT_API
}