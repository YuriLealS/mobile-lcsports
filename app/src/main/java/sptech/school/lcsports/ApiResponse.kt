package sptech.school.lcsports

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

// Classe de dados para mapear o JSON
data class ApiResponse(
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("billet_link")
    val billetLink: String,

    @SerializedName("pix")
    val pix: Pix
)

data class Pix(
    @SerializedName("qrcode_image")
    val qrcodeImage: String
)

fun extrairInformacoesDoJson(json: String): Pair<String, String>? {
    try {
        val gson = Gson()
        val apiResponse = gson.fromJson(json, ApiResponse::class.java)

        val billetLink = apiResponse.data.billetLink
        val qrcodeImage = apiResponse.data.pix.qrcodeImage

        return Pair(billetLink, qrcodeImage)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}

