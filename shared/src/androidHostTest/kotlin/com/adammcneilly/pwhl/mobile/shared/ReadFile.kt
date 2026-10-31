package com.adammcneilly.pwhl.mobile.shared

import java.io.File

actual suspend fun readFile(
    fileName: String,
): String {
    val file = File("src/commonTest/composeResources/$fileName")
    return file.readText()
}
