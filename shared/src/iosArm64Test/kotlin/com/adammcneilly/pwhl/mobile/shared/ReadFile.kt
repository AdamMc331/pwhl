package com.adammcneilly.pwhl.mobile.shared

import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
actual suspend fun readFile(
    fileName: String,
): String {
    return Res.readBytes(fileName).decodeToString()
}
