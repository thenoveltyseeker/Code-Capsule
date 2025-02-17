package com.github.thenoveltyseeker.codecapsule

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
import org.json.JSONObject
import java.io.File

class ContextStorage(private val project: Project) {
    private val logger = Logger.getInstance(ContextStorage::class.java)
    private val contextFile = File(project.basePath, ".codecapsule/context.json")

    init {
        ensureContextFileExists()
    }

    private fun ensureContextFileExists() {
        if (!contextFile.exists()) {
            contextFile.parentFile.mkdirs()  // Create `.codecapsule` directory
            contextFile.writeText("{}")  // Initialize empty JSON
        }
    }

    fun saveContext(identifier: String, context: String) {
        try {
            val json = loadContextJson()
            json.put(identifier, context)
            contextFile.writeText(json.toString(4))  // Pretty print JSON
            logger.info("📌 Context saved for $identifier in ${contextFile.absolutePath}")

            // Refresh Virtual File System
            LocalFileSystem.getInstance().refreshAndFindFileByIoFile(contextFile)?.let { VfsUtil.markDirtyAndRefresh(true, false, false, it) }
        } catch (e: Exception) {
            logger.error("❌ Failed to save context for $identifier", e)
        }
    }

    fun getContext(identifier: String): String? {
        val json = loadContextJson()
        return json.optString(identifier, null)
    }

    private fun loadContextJson(): JSONObject {
        return try {
            val content = contextFile.readText()
            JSONObject(content)
        } catch (e: Exception) {
            logger.error("❌ Failed to read context file", e)
            JSONObject()
        }
    }
}
