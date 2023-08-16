package com.example.eom_fe.data

data class ProofResponseData(
    val proofId: Int,
    val date: String,
    val writing: String,
    val images: List<ImageData>
) : java.io.Serializable
