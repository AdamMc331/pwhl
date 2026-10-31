package com.adammcneilly.pwhl.mobile.shared

expect suspend fun readFile(
    fileName: String,
): String
